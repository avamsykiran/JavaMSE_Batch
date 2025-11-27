Spring 6.x
-----------------------------------------------------------------------------------

    Introduction

        is a framework that offers support for developing
        a varaity of java enterprise (distributed) applications.

        Features
            (I)     light-weight due to modularity
            (II)    is interoparable

        Spring Modules
            Spring Core         is a pre-requisite for all other modules
            Spring Beans        offers IoC using BeanFactory container
            Spring Context      offers IoC using ApplicationContext container
            Spring SpEL         offers Spring ExpressionLanguae
            Spring AOP          offers Aspect Oriented Programming
            Spring JDBC         offers JDBC support
            Spring Data JDBC    offers JDBC based dynamically implemented DAO(s)
            Spring Data JPA     offers JPA based  dynamically implemented DAO(s)
            Spring Batch        offers Batch Programming
            Spring Web          offers support for MVC based Dynamic Web App and REST api development
            Spring Security     offers support for authorization and authentication feature development
            Spring Test         offers testing code support
            Spring Boot         offers auto-config.
            Spring Cloud        offers microservices support
            ...et.,

IoC in Spring
-------------------------------------------------------

    Spring Core, Spring Beans or
    Spring Core, Spring Context

    I-o-C   Inversion of Control.

            The application will not create the required objects, but the objects are created , managed
            and supplied to the application whenever needed by a software-component called Container.

            Container   is that one who creates, manages and supplies object of other components in an 
                        application.

            Component   is any class that offers a functionality of the application and whoes objects
                        are being managed by the container.

                        A Service, or a DAO, or A Utility ..et.,

            Bean        is an object of a component, or an object that being managed by a container.

            Relating one bean to another, or suppling a bean when needed is called dependency injection.

            Dependency Injection is a way to do IoC.

            Assumiong we have HR-application

            com.cts.hrapp.dao

                interface EmployeeDAO{
                    void add(Employee emp);
                    List<Employee> getAll();
                }

                class EmployeeDAOJDBCImpl implements EmployeeDAO {

                    //override and implement add and getAll methods
                    //using jdbc Connection, Statement, ResultSet ..etc.,
                }

                class EmployeeDAOJPAImpl implements EmployeeDAO {

                    //override and implement add and getAll methods
                    //using jpa-hibernate Sessions, EntityManager ..etc.,
                }

            com.cts.hrapp.service

                interface EmployeeService {
                    void add(Employee emp);
                    List<Employee> getAll();
                    boolean isValid(Employee emp);
                }

                class EmployeeServiceImpl implements EmployeeService {

                    private EmployeeDAO empDAO;

                    public EmployeeServiceImpl(){
                        /*this.empDAO = new EmployeeDAOJDBCImpl();
                        this.empDAO = new EmployeeDAOJPAImpl(); */
                    }
                    
                    public EmployeeServiceImpl(EmployeeDAO empDAO){
                        this.empDAO = empDAO;
                    }

                    public void setEmpDAO(EmployeeDAO empDAO){
                        this.empDAO = empDAO;
                    }

                    public boolean isValid(Employee emp){
                        //all valdiation logic
                    }

                    public void add(Employee emp){
                        if(isVAlid(emp)){
                            empDao.add(emp);
                        }
                    }

                    public List<Employee> getAll(){
                        return empDao.getAll();
                    }
                }

    Spring Offers two Containers to take care of dependency injection.

        BeanFactory             from Spring Beans
        ApplicationContext      from Spring Context

    Bean Configuration

        Bean Configuration is a machanism to inform the container
            (a) How many components and bean do we need the contianer to manage
            (b) Which bean is dependent on which other bean.

        Spring offers three way to configure beans
            (1) Xml Based Configuration
            (2) Annotation Based Configuration
            (3) Java Based Configuration

        Xml Based Configuration

            we create an xml file that contians the bean related info

            beans.xml
                <beans>
                    <bean id="empJdbcDao" class="com.cts.hrapp.EmployeeDAOJDBCImpl" />

                    <bean id="empJpaDao" class="com.cts.hrapp.EmployeeDAOJPAImpl" />

                    <bean id="empService" class="com.cts.hrapp.EmployeeServiceImpl" >
                        <property name="empDAO" ref="empJdbcDao" />
                    </bean>
                </beans>

            To Load the Config

                ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

                https://docs.spring.io/spring-framework/docs/4.2.x/spring-framework-reference/html/xsd-configuration.html

        Annotation Based Configuration

            We use annotation to inform the list of components and the dependencies as well.

            @Component("id")        //this id is optional, the lowercase classNaem will be the default
                |
                |<- @Repository
                |<- @Service
                |<- @Controller
                |<- @RestController
                |<- @Advice
                |<- @ControllerAdvice
                |<- @RestControllerAdvice
                ...etc.,

            @Scope("")               can be set to
                                        singleton       creates only one bean and supplies the same when needed

                                        prototype       creates a new bean and supplies the same when needed
                                        
                                        request         creates a new bean when ever a request is received 
                                                        (works only in WebApplicationContext)

                                        session         creates a new bean when ever a new xession gets created
                                                        (works only in WebApplicationContext)

                                        global-session  creates a new bean when ever the 
                                                        web server initializes ServletContainer
                                                        (works only in WebApplicationContext)

            @Configuration
            @ComponentScan("com.cts.hrapp")
            class BeanConfig {

            }

            To Load the Config

                ApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);

            @Value              is used to inject values from '.properties / .yaml' files into
                                primitive fields and string fields.

            @PropertySource     used along with @Configuration , and supplies the name of the '.properties' file.
                                where the .properties file has key-value pair of externalized values.
                                
            @Autowired          is used to inject references of other beans into fields .

                                byType  a field is injected with a bean, provicded their data types match.

                                byName  a field is injected with a bean, provicded their names/ids are mentioned
                                        using @Qualifier.

                                Field Injection             @Autowired is applied on a field 
                                Constructor Injection       @Autowired is applied on a constructor
                                Setter Injection            @Autowired is applied on a setter
                                Method Injection            @Autowired is applied on a method / method argument

            
        Java Based Config

            is sued to create beans using java methods. This is used for creating benas to classes
            on which an annotation can not be applied as we do not own the source code of that class.

            @Configuration
            @ComponentScan("com.cts.hrapp")
            class BeanConfig {

                @Bean
                Scanner scan(){     //method naem will be the id
                    return new Scanner(System.in);
                }
            }

Spring Boot 3.x
--------------------------------------------

    is another module of spring framework that offers auto-configuration. this facilittes 
    Rapid Application Development. Spring Boot also has embeded server feature that provides
    server-less application development and deployment.

    Every spring boot applciatioon is marked with @SpringBootApplication

    @SpringBootApplication = @Configuration + @ComponentScan + @EnableAutoConfig

    @EnableAutoConfig

        (1) it loads application.properties and application.yaml as defualt external property providers.
        (2) it loads default config from starter-packages of each spring module we include like
            spring web          automatically, DispatcherServlet is configed
                                automatically, InternalResourceViewResolver is configed
            spring data         automatically, trasacntion and connection pools are config
            spring security     automatically, form based authentication is config
            ...etc.,

        and all of the auto-configs can be customized.

    To Create a spring boot application / spring-starter-project
        (a) using https://start.spring.io  
        (b) using spring boot cli
        (c) using STS.

    @SpringBootApplication
    public class SpringDemo02BootApplication {

        public static void main(String[] args) {
            SpringApplication.run(SpringDemo02BootApplication.class, args);
        }

    }

    SpringApplication.run

        (1) Load the configurations after component scan and application.properties into ApplicationContext.
        (2) Execute CommandLineRunner (s) (if any)
        (3) Execute the embeded server (if any)
        (4) wait until the server shutsdown (if any)
        (5) the application-context is destroyed and the app is terminated

    CommandLineRunner is an interface whoes implementation classes are executed as startup-objects of the 
    spring boot application. Any task that needs to ebe executed at the start up time of the app
    can be programed in these CommndLineRunner implementation classes.

Spring YAML Configuration
------------------------------------------------------------------------

    YAML is a alternate format for .proeprties file

    application.properties

        spring.application.name=App Name
        spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
        spring.datasource.url=jdbc:mysql://localhost:3306/db
        spring.datasource.username=root
        spring.datasource.password=root

    application.yml

        spring:
            application:
                name: App Name
            datasource:
                driver-class-name: com.mysql.cj.jdbc.Driver
                url: jdbc:mysql://localhost:3306/db
                username: root
                password: root
                
 Spring Profiles
------------------------------------------------------------------------
    A profile indicates a set of beans or properties to be activated only for
    a particular phase of the project (staging / dev/ prod ...etc)

    To activate a profile
        
        (a) while executing a spring boot app jar file
            java -jar my-app.jar -Dspring.profiles.active=dev

        (b) in application.properties
                spring.profiles.active=dev

    Profile specific properties files

        application.properties          is used irrespective of a profile (default props file)
        application-prod.properties     is used when spring.profiles.active=prod
        application-dev.properties     is used when spring.profiles.active=dev

        if a property appeares both in default properties file and profile-specfic properties file,
        the profile specfic properties file will have the priority.

    Multiple Profile in the same application.proeprties file

        spring.application.name=App Name
        spring.profiles.active=dev
        #---
        spring.config.activate.on-profile=dev
        spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
        spring.datasource.url=jdbc:mysql://localhost:3306/db
        spring.datasource.username=root
        spring.datasource.password=root
        #---
        spring.config.activate.on-profile=prod
        spring.datasource.driver-class-name=org.h2.Driver
        spring.datasource.url=jdbc:h2:mem:db;DB_CLOSE_DELAY=-1
        spring.datasource.username=sa
        spring.datasource.password=sa

    @Profile annotation

        this is applied along with @Component or @Bean annotations

        @Service
        @Profile("dev")
        public class PincodeSearchServiceFileBasedImpl implements PincodeSearchService {

            public Location getLocationOf(String pincode){
                //write logic to read from a hypothical file of pincodes
                //and return the location
            }
        }

        @Service
        @Profile("prod")
        public class PincodeSearchServiceApiBasedImpl implements PincodeSearchService {

            public Location getLocationOf(String pincode){
                //write logic to call a govt postal api
                //and return the location
            }
        }

Spring Data
------------------------------------------------------------------------
    is a spring module that generates a dynamic repository implementation.
    
    Spring Data API is an umbrella project that simplifies data access across various persistence technologies, including JPA (SQL) and NoSQL databases.

    Spring Data API revolves around the `Repository` interface and its sub-interfaces.

        Repository                  Base marker interface for all repositories and provides no methods 
            |
            |<- CrudRepository      Provides basic CRUD operations. 
            |       |                `save()`, `findById()`, `findAll()`, `delete()`, `count()` 
            |       |
            |       | <- PagingAndSortingRepository
            |       |       |       `findAll(Sort sort)`, `findAll(Pageable pageable)` 
            |       |       |
            |       |       |<- JpaRepository   From Spring Data JPA 
            |       |       |                   `flush()`, `saveAllAndFlush()`
            |       |       |
            |       |       |<- MongoRepository From Spring Data MongoDb (NoSQL)
            |       |
            |       |<- ReactiveCrudRepository
            |       |       |
            |       |       |<- ReactiveMongoRepository
            |       |       
            |       | <- KeyValueRepository
            |       |       |
            |       |       |<- RedisRepository From Spring Data Redis (NoSQL)

        @Entity
        @Table("emps")
        public class Employee {
            @Id
            private Long empId;
            private String fullName;
            private LocalDate hireDate;
            private Double salary;
            private String mailId;

            //consturcotrs and getters and setters ..etc.,
        }

        public interface EmployeeRepo extends JpaRepository<Employee,Long> {
            List<Employee> findAllByFullName(String fullName);
            boolean existsByMailId(String mailId);
            Optional<Employee> findByMailId(String mailId);
        }       

    Core Method Name Notations

        Spring Data uses a Domain Specific Language (DSL) in the method name to construct queries. 
        A typical method signature follows the pattern:

            [Keyword \ Prefix] [Property \ Name] [Query \ Keyword] [Property \ Name] ...

        Method Name Keywords and
    
            `find`      Starts a query method (most common) 
                        `findByLastName(String lastName)` 
                        `findAllByLastName(String lastName)` 

            `count`     Returns the number of entities matching the query 
                        `countByActive(boolean active)` 

            `delete`    Deletes the entities matching the query 
                        `deleteByStatus(Status status)` 

            `exists`    Checks if at least one entity matches the query 
                        `existsByEmail(String email)` 

        Quering Keywords and Patterns

            Simple      Match exact value 
                        `findByEmail(String email)` is similar to  `where email = ?` 

            `And`       Combine conditions with logical AND 
                        `findByFirstNameAndLastName(...)` is similar to `where fn = ? and ln = ?` 

            `Or`        Combine conditions with logical OR 
                        `findByAgeOrStatus(...)` is similar to `where age = ? or status = ?` 

            `Not`       Negate the condition 
                        `findByAgeNot(int age)` is similar to `where age != ?` 

            `Between`   Match values between two boundaries 
                        `findByBirthDateBetween(...)` is similar to `where date between ? and ?` 

            `LessThan` / `GreaterThan` 
                        Comparison operators 
                        `findByHeightLessThan(...)` is similar to `where height < ?` 

            `StartingWith` / `EndingWith` / `Containing` 
                        String matching (often translated to `LIKE`) 
                        `findByAddressContaining(...)` is similar to `where address LIKE '%?%'` 

            `Like` / `NotLike` 
                        String matching (requires you to pass the wildcard `%` in the parameter) 
                        `findByZipCodeLike(String zipCode)` is similar to `where zipCode LIKE ?` 

            `IsNull` / `IsNotNull` 
                        Check for null values 
                        `findByDescriptionIsNull()` is similar to `where description is null` 

            `In` / `NotIn` 
                        Match against a collection of values 
                        `findByRoleIn(Collection<Role> roles)` is similar to `where role in (?)` 

            `True` / `False` 
                        For boolean properties 
                        `findByActiveTrue()` is similar to `where active = true` 

            `IgnoreCase`    
                        Ignore casing for String matching (appended to the property) 
                        `findByCityIgnoreCase(String city)` is similar to `where LOWER(city) = LOWER(?)` 

        Ordering and Limiting Results
        
            `OrderBy`   Sort results by a property 
                        `findByStatusOrderByDateDesc(...)` 

            `Desc` / `Asc`
                        Sort direction 
                        `findByStatusOrderByNameAsc(...)` 

            `First` / `Top`
                        Limit the number of results returned 
                        `findTop10ByAge(int age)` 
        
        When to Use `@Query` (The Escape Hatch)

            When the method name DSL becomes too cumbersome, unreadable, or you need to use database-specific features (e.g., joins, complex aggregations), use the `@Query` annotation. 

            public interface UserRepository extends JpaRepository<User, Long> {
                // JPQL query
                @Query("SELECT u FROM User u WHERE u.age > :minAge AND u.status = :status")
                List<User> findOldActiveUsers(@Param("minAge") int minAge, @Param("status") Status status);
            }
            
            public interface ProductRepository extends MongoRepository<Product, String> {
                // MongoDB Query Language (JSON format)
                @Query("{ 'tags' : { $in : [?0] }, 'price' : { $lt : ?1 } }")
                List<Product> findProductsByTagAndMaxPrice(String tag, double maxPrice);
            }

Spring Rest-api
------------------------------------------------------------------------

    MVC - Model-View-Controller Arch., (J2EE spec)

        repos <---models--> services <--models--> controllers <----REQ---- CLIENT
                                                        |                      ↑ 
                                                        |model                 | 
                                                        ↓                      | 
                                                        VIEW(s) -----RESP----->|

        Controller are implemented via servlets

        Views       can JSP / JSF ...etc.,

    Single FronController MVC Arch., (Spring Framework)

        repos <---models--> services <--models--> controllers <--model--> FrontController <----REQ---- CLIENT
                                                                                |                      ↑ 
                                                                                |model                 | 
                                                                                ↓                      | 
                                                                                VIEW(s) -----RESP----->|
        FrontController is offered by Spring Framework (DispatcherServlet)

        Controllers are POJO marked as '@Controller' that shall offer action-methods 
                        to receive the data from FrontController and return a vewi-name and model

        views       can JSP / JSF / Thymeleaf ...etc.,


    Single FronController REST Arch., (Spring Framework)

        repos <---models--> services <--models--> rest-controllers <--model--> FrontController <--REQ-- CLIENT
                                                                                    |                       ↑ 
                                                                                    model--RESP(json/xml)-->|


    @RestController
    @RestControllerAdvice
    
    @ReqeustMapping("/hello")
    String handleHello(){
        //this action-method will be executed when a reqeust to '/hello' comes in
        //irrespectiove of any reqeust-method (either GET/POST/DELETE...etc)
        return "";
    }

    @ReqeustMapping(value="/hello",method=RequestMethod.GET)
    String handleHello(){
        //this action-method will be executed ONLY when a GET-reqeust to '/hello' comes in
        return "";
    }

    @ReqeustMapping(value="/hello",method=RequestMethod.GET)  <---- @GetMapping("/hello")

    REST api standards

        We will have to create only one URL per resource.

            Resource        URL
            ------------------------------
            Employee        /emps
            Consumer        /consumers
            Article         /articles
            ...etc
                                                    HttpStatus On   HttpStatus On       HttpStatus On
            HttpMethod      CRUD-Operation          Success         Failure (Client)    Failure (Server)
            --------------------------------------------------------------------------------------------     
                GET         Retriving Records       200-OK          404-NOT_FOUND       500-Internal_Server_Err
                POST        Creating Record         201-CREATED     400-BAD_REQUEST     500-Internal_Server_Err
                PUT         Updating Record         202-ACCEPTED    400-BAD_REQUEST     500-Internal_Server_Err
                DELETE      Deleting Record         204-NO_CONTENT  404-NOT_FOUND       500-Internal_Server_Err

            @RestController
            @ReqeustMapping("/emps")
            public class EmployeeController {

                @Autowired
                private EmployeeService empService;

                @GetMapping
                ResponseEntity<List<Emplouyee>> handleGetAllRecords(){
                    return ResponseEntity.ok(empService.getAll()); 
                }
                
                @GetMapping("/{id}")
                ResponseEntity<Emplouyee> handleGetRecordById(@PathVariable int id){
                    Employee emp = empService.getById(id);
                    return emp != null ?
                            ResponseEntity.ok(emp) :
                            new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
                
                /*
                ...@PostMapping for add
                ...@PutMapping for update
                ...@DeleteMapping for delete
                */
            }  

Swagger UI
----------------------------------------------------------------------
    Swagger UI is a powerful tool for visualizing and interacting with your Spring Boot REST API's documentation. The modern and recommended way to integrate this is by using the springdoc-openapi library.

    <dependency>
        <groupId>org.springdoc</groupId>
        <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
        <version>2.7.0</version>
    </dependency>

    We can typically access it at:
        http://localhost:PORT/swagger-ui.html  (or)
        http://localhost:PORT/swagger-ui/index.html 
    
    The underlying OpenAPI JSON documentation is usually available at:
        http://localhost:PORT/v3/api-docs

Spring Actuator
----------------------------------------------------------------------

    actuator is a health and metrics monitoring tool.

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-actuator</artifactId>
    </dependency>  

    /actuator                       this gives compelte analysis and lsit of indicators
    /actuator/health
    /actuator/health/indicator

Spring Boot Testing
----------------------------------------------------------------------

    Testing is to verify if the implemented code matches the expectated requirment.

    @SpringBootTest                 applied on integration test cases
    
    @AutoConfigureMockMvc           is used to crete mock mvc environemnt object that
                                    can send req's and receive resp's

    MockMvc                         is a class whose bean can be injected due to @AutoConfigureMockMvc.
                                    this class object can send req's and receive resp's,
                                    the following are the methods from MockMvc

                                    perform(method(url))        is used to send a req, method is get/put ..etc
                                    contentType                 is used to verify the incoming content type
                                    andExpect                   is an assert function to assert the resp
                                        status()                is used verify the resp status
                                        content()               extracts the resp body

    @TestConfiguration              is used to customize the config that injects
                                    only selected beans into the test class

    @MockBean                       is used to create a mock implementd bean to assist unit testing.

    @WebMvcTest                     is an alternate for @AutoConfigureMockMvc in unit tests.

Case Study
-----------------------------------------------------------------

    Rest-API for a BudgetPlanning application

        The Budget planning application will be used to plan the incoming and expenditure
        for a variety of projects. The rest-api is expected to provide end-points to 
            
            Retrive /Add/ Modify/ Remove a project record
            Retrive /Add/ Modify/ Remove a Transaction of a project record

            Project
                projectId           : Long
                title               : String
                projectManager      : String
                plannedStartDate    : LocalDate
                plannedEndDate      : LocalDate
                budget              : List<Txn>

            Txn
                txnId               : Long
                header              : String
                amount              : Double
                type                : TxnType   (enum of CREDIT/ DEBIT)
                project             : Project

            Resource        Table           endpoint
            -------------------------------------------------------------------------------------------------           
            Project         projects        /projects

            Transaction     transactions    /projects/{projectId}/transactions
                                            GET
                                            POST

                                            /tranbsactions/{txnId}
                                            GET
                                            PUT
                                            DELETE

Spring Web Flux for Reactive Programming
-------------------------------------------------------------------------------------------------------
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-webflux</artifactId>            
        </dependency>

        @RestController
        @RequestMapping("/accounts")
        public class AccountController {

            private AccountRepository accountRepository;

            @GetMapping
            public Flux<Account> getAll() {
                return accountRepository.findAll();
            }

            @GetMapping("/{id}")
            public Mono<Account> getById(@PathVariable String id) {
                return accountRepository.findById(id);
            }
        }

        Reactive Web Client

        public class EmployeeWebClient {

            WebClient client = WebClient.create("http://localhost:8080");

            Mono<Account> accMono = client.get()
                .uri("/accounts/{id}", "1")
                .retrieve()
                .bodyToMono(Account.class);

            accMono.subscribe(System.out::println); 
            
            Flux<Account> accFlux = client.get()
                .uri("/accounts")
                .retrieve()
                .bodyToFlux(Account.class);

            accFlux.subscribe(System.out::println);

        }
