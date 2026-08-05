Rest API Vs GraphQL
----------------------------------------------------------------------------

    REST API
        REST (Representational State Transfer) is an architectural style based on HTTP principles. REST APIs map operations to specific endpoints (URLs) using standard HTTP verbs (GET, POST, PUT, DELETE). 
        
        Every resource (e.g., /api/v1/orders, /api/v1/users) has a fixed data response structure defined by the server.

    GraphQL
        GraphQL is an API query language and runtime created by Meta. It provides a single endpoint (typically /graphql) driven by a strongly typed schema (.graphqls files).
        
        Schema- and query-driven. The client requests exact fields needed through Queries, Mutations, or Subscriptions using @Controllers and @SchemaMapping / @QueryMapping resolvers in Spring-boot.

    Feature         REST API                        GraphQL
    ------------------------------------------------------------------------------------------------------
    Data Fetching   Fixed server response payload;  Declarative fetching; client requests exact fields, 
                    susceptible to over-fetching    eliminating over/under-fetching.
                    or under-fetching.,

    Endpoints       Multiple endpoints              Single unified endpoint (/graphql).
                    (/users, /users/{id}/orders)    

    Error Handling  Native HTTP Status codes        Almost always returns 200 OK; partial errors return 
                    (200, 404, 500, 401).           inside an errors JSON array payload.
       

When to Use Which?
    REST API when:
        Public/Third-Party APIs: When exposing APIs to external clients or partners where standard HTTP conventions, stability, and OpenAPI/Swagger documentation are expected.

        Heavy File Uploads/Downloads & Streaming: Handling binary files, media streams, or multipart requests (/upload, /download) is far simpler and natively supported in REST.

        High-Cacheable Read Workloads: Applications relying heavily on CDN caching (e.g., e-commerce product catalogs, news feeds, static content APIs).

        Simple CRUD Microservices: Internal service-to-service communication or straightforward database-backed microservices with predictable data structures.

    GraphQL when:
        Multi-Client Platforms (Mobile + Web + Smart TV):

            Example: A mobile app needs only user.name and user.avatar, while a desktop dashboard needs user.name, user.address, user.orders, and user.paymentHistory. GraphQL lets both query the exact subset in a single HTTP request.

        Aggregating Multiple Backends / Microservices (BFF Pattern):

            Example: A dashboard screen needs data from the Order Service, Payment Service, and User Service. Instead of making 3 HTTP REST calls from the client (under-fetching), a GraphQL Gateway layer retrieves and joins all 3 backend calls in a single network round trip.

        Real-Time WebSockets / Subscriptions: When clients need live updates (e.g., live order tracking, chat apps, stock price updates) via GraphQL Subscriptions.

Working With GraphQL on Spring Boot
------------------------------------------------------------------------------------------------------

    Dependencies:
        Spring Web (or Spring Reactive Web)
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-web</artifactId>
            </dependency>

        Spring for GraphQL (spring-boot-starter-graphql)  
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-graphql</artifactId>
            </dependency>

    Configure application.properties

        spring.graphql.graphiql.enabled=true
        spring.graphql.graphiql.path=/graphiql
        spring.graphql.schema.printer.enabled=true    

    Schema-First Design

        Spring Boot automatically scans .graphqls files inside the src/main/resources/graphql/ directory.

        src/main/resources/graphql/schema.graphqls

            type Query {
                bookById(id: ID!): Book
                allBooks: [Book!]!
            }

            type Mutation {
                addBook(title: String!, pageCount: Int!, authorId: ID!): Book!
            }

            type Book {
                id: ID!
                title: String!
                pageCount: Int!                
                author: Author!  # Nested object
            }

            type Author {
                id: ID!
                name: String!
            }

    Domain Models & Repositories & Services
        relavent models and repos shall be defined and are absolutly independent of GraphQL or Rest-api

    GraphQL Controller

        Spring Boot uses annotations like @QueryMapping, @MutationMapping, and @SchemaMapping to wire methods to schema fields.

        @Controller
        public class BookController {

            @Autowired
            private BookRepository bookRepository;

            @Autowired
            private AuthorRepository authorRepository;

            // Maps to type Query { bookById(id: ID!): Book }
            @QueryMapping
            public Book bookById(@Argument String id) {
                return bookRepository.findById(id);
            }

            // Maps to type Query { allBooks: [Book!]! }
            @QueryMapping
            public List<Book> allBooks() {
                return bookRepository.findAll();
            }

            // Maps to type Mutation { addBook(...): Book! }
            @MutationMapping
            public Book addBook(@Argument String title, @Argument Integer pageCount, @Argument String authorId) {
                return bookRepository.save(title, pageCount, authorId);
            }

            /**
            * Solving the N+1 problem:
            * Instead of calling authorRepository.findById(...) individually for every single book,
            * Spring for GraphQL batches all parent Books in 'allBooks' and resolves all Authors in 1 query call.
            */
            @BatchMapping
            public Map<Book, Author> author(List<Book> books) {
                System.out.println("Batch fetching authors for " + books.size() + " books...");
                return authorRepository.findByBooks(books);
            }
        }

    GraphQL Error/Exception Handling

        In GraphQL, when an exception is thrown, the HTTP status code remains 200 OK, but the JSON payload returns an errors array containing information such as message, locations, path, and custom extensions (like error codes or timestamps).

        @ControllerAdvice
        public class GlobalGraphQlExceptionHandler {

            /**
            * Handles BookNotFoundException and returns a formatted GraphQL error.
            */
            @GraphQlExceptionHandler
            public GraphQLError handleBookNotFoundException(BookNotFoundException ex, DataFetchingEnvironment env) {
                return GraphQLError.newError()
                        .errorType(ErrorType.NOT_FOUND) // Standard Spring GraphQL ErrorType 
                                                        //(NOT_FOUND, BAD_REQUEST, UNAUTHORIZED, FORBIDDEN, INTERNAL_ERROR)
                        .message(ex.getMessage())
                        .path(env.getExecutionStepInfo().getPath()) // Captures query field path (e.g., ["bookById"])
                        .location(env.getField().getSourceLocation()) // Captures line & column numbers from the query
                        .extensions(Map.of(
                                "errorCode", "BOOK_NOT_FOUND",
                                "invalidBookId", ex.getBookId(),
                                "timestamp", Instant.now().toString()
                        ))
                        .build();
            }

            /**
            * Handles generic validation or illegal argument exceptions.
            */
            @GraphQlExceptionHandler
            public GraphQLError handleIllegalArgumentException(IllegalArgumentException ex, DataFetchingEnvironment env) {
                return GraphQLError.newError()
                        .errorType(ErrorType.BAD_REQUEST)
                        .message(ex.getMessage())
                        .path(env.getExecutionStepInfo().getPath())
                        .build();
            }

            /**
            * Fallback for all unhandled runtime exceptions to avoid leaking sensitive internal details.
            */
            @GraphQlExceptionHandler
            public GraphQLError handleGenericException(Exception ex, DataFetchingEnvironment env) {
                return GraphQLError.newError()
                        .errorType(ErrorType.INTERNAL_ERROR)
                        .message("An unexpected internal error occurred.")
                        .path(env.getExecutionStepInfo().getPath())
                        .build();
            }
        }

The Structure of a GraphQL Request Payload
------------------------------------------------------------------------------------------------------

    Regardless of what client tool or programming language you use, every GraphQL HTTP request sends a JSON body containing three specific keys:

    {
        "query": "query GetBook($id: ID!) { bookById(id: $id) { title pageCount } }",
        "variables": {
            "id": "101"
        },
        "operationName": "GetBook"
    }

    Key Breakdown:
        query (Required, String): A string containing the GraphQL query, mutation, or subscription document.

        variables (Optional, Object): A JSON object supplying dynamic values for variables declared in your query (e.g., $id: ID!). Passing arguments via variables instead of string interpolation prevents injection vulnerabilities and allows request caching.

        operationName (Optional, String): Specifies which query/mutation to execute if your query string contains multiple named operations.

    3 Types of GraphQL Operations

        1. Queries (Reads)
            Fetches data without modifying state (equivalent to REST GET).

            query FetchBookAndAuthor($bookId: ID!) {
                bookById(id: $bookId) {
                    title
                    author {
                        name
                    }
                }
            }

        2. Mutations (Writes)
            Creates, updates, or deletes data (equivalent to REST POST, PUT, DELETE).

            mutation CreateBook($title: String!, $pageCount: Int!, $authorId: ID!) {
                addBook(title: $title, pageCount: $pageCount, authorId: $authorId) {
                    id
                    title
                }
            }

        3. Subscriptions (Real-Time Streams)
            Establishes a persistent, bidirectional channel (typically over WebSockets or Server-Sent Events / SSE) to receive continuous updates when backend events trigger.

            subscription OnBookAdded {
                bookAdded {
                    id
                    title
                }
            }
    
Working with Apollo Client and integrating with ReactJS
------------------------------------------------------------------------------------------------------
    1. Installation

        Install Apollo Client and `graphql` in your React project:

            npm install @apollo/client graphql

            AppolloClient           handle the graphql communication config
                AppolloCache        is where the data retrived is cached, (similar to redux store)

                AppolloLink         is a archetecture where the reqiuest is made to pass through a 
                                    chain of opertions called links, where each link/operation handles
                                    one aspect of the communication

                                    grpaqlServer  <-->  link3 <-->link2 <--> link1 <--> ui-app

                                    link1 may be a HttpLink that actually handle req and resp
                                    link2 may be a Lionk that handle authentication
                                    link3 may be another link the handle media conversion like
                                            xml to json  or vice-versa 

                                            and so... on...

                AppolloWrapper      is a part of appollo-client library that is sued to supply 
                                    appollo provider to a NextJS app.
    
    2. Initialize Apollo Client (`apolloClient.ts`)

        Create a centralized Apollo Client configuration file. 
        This configures the connection to our Spring Boot endpoint (`http://localhost:8080/graphql`) 
        and sets up an authorization link (e.g., for JWT tokens).

            // src/apolloClient.ts
            import { ApolloClient, InMemoryCache, createHttpLink } from '@apollo/client';
            import { setContext } from '@apollo/client/link/context';

            // 1. Point to your Spring Boot GraphQL endpoint
            const httpLink = createHttpLink({
                uri: 'http://localhost:8080/graphql',
            });

            // 2. Attach Authorization headers dynamically (e.g., JWT)
            const authLink = setContext((_, { headers }) => {
                const token = localStorage.getItem('token');
                return {
                    headers: {
                    ...headers,
                    authorization: token ? `Bearer ${token}` : '',
                    },
                };
            });

            // 3. Create the Apollo Client instance
            export const client = new ApolloClient({
                link: authLink.concat(httpLink),
                cache: new InMemoryCache(), // Client-side cache
            });

    3. Wrap React App with `ApolloProvider` main.tsx
      
        <React.StrictMode>
            <ApolloProvider client={client}>
                <App />
            </ApolloProvider>
        </React.StrictMode>

    4. Define TypeScript Types & GraphQL Documents

        Define interface types matching our Spring Boot GraphQL schema (`Book` and `Author`), 
        and define queries and mutations using the `gql` tag.

        // src/graphql/bookQueries.ts
        import { gql } from '@apollo/client';

        // TypeScript Interfaces
        export interface Author {
            id: string;
            name: string;
        }

        export interface Book {
            id: string;
            title: string;
            pageCount: number;
            author: Author;
        }

        // Data shapes for Query/Mutation inputs and outputs
        export interface AllBooksData {
            allBooks: Book[];
        }

        export interface BookByIdData {
            bookById: Book | null;
        }

        export interface BookByIdVars {
            id: string;
        }

        export interface AddBookData {
            addBook: Book;
        }

        export interface AddBookVars {
            title: string;
            pageCount: number;
            authorId: string;
        }

        // GraphQL Documents
        export const GET_ALL_BOOKS = gql`
            query GetAllBooks {
                allBooks {
                    id
                    title
                    pageCount
                    author {
                        id
                        name
                    }
                }
            }
        `;

        export const GET_BOOK_BY_ID = gql`
            query GetBookById($id: ID!) {
                bookById(id: $id) {
                    id
                    title
                    pageCount
                    author {
                        name
                    }
                }
            }
        `;

        export const ADD_BOOK = gql`
            mutation AddBook($title: String!, $pageCount: Int!, $authorId: ID!) {
                addBook(title: $title, pageCount: $pageCount, authorId: $authorId) {
                    id
                    title
                    pageCount
                }
            }
        `;

    5. Implement React Components

        A. Executing Queries (`useQuery`)

            Use the `useQuery` hook with TypeScript generic types for type-safe data access.

            const { loading, error, data } = useQuery<AllBooksData>(GET_ALL_BOOKS);

        B. Executing Mutations with Cache Updates (`useMutation`)

            When creating a new record via mutation, update the local Apollo Client cache or refetch queries to update UI automatically.

            const [addBook, { loading, error }] = useMutation<AddBookData, AddBookVars>(ADD_BOOK, {
                refetchQueries: [{ query: GET_ALL_BOOKS }],
            });

            const handleSubmit = async (e: React.FormEvent) => {
                e.preventDefault();
                try {
                    await addBook({
                        variables: {
                            title,
                            pageCount: Number(pageCount),
                            authorId,
                        },
                    });
                } catch (err) {
                    console.error('Mutation error:', err);
                }
            };
    
    6. Handling Spring Boot CORS

        Ensure the Spring Boot GraphQL backend permits Cross-Origin Resource Sharing (CORS) requests from your React client (`http://localhost:3000`).

        @Configuration
        public class CorsConfig implements WebMvcConfigurer {

            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/graphql")
                        .allowedOrigins("http://localhost:3000")
                        .allowedMethods("POST", "GET", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        }
