import { ApolloClient, InMemoryCache, createHttpLink } from '@apollo/client';
import { setContext } from '@apollo/client/link/context';

// 1. Point to your Spring Boot GraphQL Endpoint
const httpLink = createHttpLink({
  uri: '/api/graphql',
});

// 2. Attach Authorization Headers (e.g., JWT token from localStorage)
const authLink = setContext((_, { headers }) => {
  const token = localStorage.getItem('authToken');
  return {
    headers: {
      ...headers,
      authorization: token ? `Bearer ${token}` : '',
    },
  };
});

// 3. Initialize and Export Apollo Client
export const apolloClient = new ApolloClient({
  link: authLink.concat(httpLink),
  cache: new InMemoryCache({
    typePolicies: {
      Contact: {
        // Defines 'contactId' as the unique cache identifier key
        keyFields: ['contactId'],
      },
    },
  }),
});