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

            Component   is any class that offers a functionality of the application adn whoes objects
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
                |<- ControllerAdvice
                |<- RestControllerAdvice
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
                                
            @Autowired          is used to inject references of other beans into fields .

                                byType  a field is injected with a bean, provicded their data types match.

                                byName  a field is injected with a bean, provicded their names/ids are mentioned
                                        using @Qualifier.

                                Field Injection             @Autowired is applied on a field 
                                Constructor Injection       @Autowired is applied on a constructor
                                Setter Injection            @Autowired is applied on a setter
                                Method Injection            @Autowired is applied on a method / method argument


            @PropertySource     used along with @Configuration , and supplies the name of the '.properties' file.
                                where the .properties file has key-value pair of externalized values.

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

Spring Rest-api

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

    Spring Profiles

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

    Spring Actuator

        actuator is a health and metrics monitoring tool.

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>  

        /actuator                       this gives compelte analysis and lsit of indicators
        /actuator/health
        /actuator/health/indicator

    Spring Batch

        Is a spring module to execute batch operations.
        A batch operation refers to a any time-consuming operation that is scheduled to execute.

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-batch</artifactId>
            <version>6.2.0</version>
        </dependency>


        (or)

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-batch</artifactId>            
        </dependency>


        Spring Batch look at each job as a group of steps in sequence. 
        Spring Batch uses a sql-database as job-repository.
        Spring Boot auto-config's h2db as a job-repo.
        Each Step will have three parts (ItemReader, ItemWriter and ItemProcess) where an item indicates
        one record (record can be a string or anative valeu or a complex object).

        JOB
            Step1
            Step2
            Step3
            ...etc.,

        Step
            ItemReader      is responsible to receive data
            ItemProcess     is responsible to process data
            ItemWriter      is reponsible to dispatch data


    Spring JMS

        Spring JMS is a messageing service module from Spring Framework

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-jms</artifactId>
            <version>4.3.3.RELEASE</version>
        </dependency>

        (or)
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-boot-starter-jms</artifactId>            
        </dependency>

        JMSTemplate

            is a bean provided by Spring JMS to send a message over
            a messaging server.

            jmsTemplate.convertAndSend(msgObj);

        JMS Listener

            To create a listener without annotation

            public class MyAppMsgListener implements MessaginListener {
                public void onMessage(Message msg){
                    //we will write code that has to be invoekd
                    //on receiving a message
                }
            }

            To create a listener with annotaiont

                @JmsListener(destination = "myDestination")

                on any method that does something on receiving a msg.

    Spring YAML Configuration

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

    Spring Web MVC

        Evolution of Web
            WebSite (static)                -       html content is pre-written and cannot be changed from user to user
            WebApplication (dynamic)        
                DynamicWebApplication       -       html contnet is generated as the user input dynamically on the server
                SPA - Single Page Applications -    html contnet is generated as the user input dynamically on the client

        DynamicWebApplication

            is an application where an html content is generated on the go (dynamically)
            when a request is received by a server-side executable program and the
            generated content is send as a response.

            Means
                (1) we will have a program that is capable of execution on a server
                (2) this program is executed when a request is received
                (3) the program execution results in dynamically generated html content
                (4) that generated html content is sent as a response

            Server-Side Executable Programs
                Java        Servlets
                .Net        ASP.Net WebForms
                PHP
                ....etc.,

        MVC - Archetecture

            Controller      is a server-side program that is capable of
                            receiving a request

            View            is a html-generating tool

            Model           is any object that carries data from controller to the view.

        OnJava  MVC Archetecture

            DAOs/Repos  <--Entities--> Services <---Model---> Controllers    <----REQ---- Client
                                                                    |                         ↑  
                                                                    | model                   |
                                                                    ↓                         |
                                                                    Views   ---RESP (html)--->|

            DAO/Repos       are POJOs that execute data base related operations

            Entities        are POJOs that are mapped to a database

            Services        are POJOs that execute bussiness pogic

            Model           are POJOs that carry data in the applciation

            Controllers     are Servlets

            Views           are JSP / JSF / Thymeleaf ...etc.,

        OnJava Spring - SingleFrontController MVC Archetecture
            
            DAOs/Repos  <-Entities-> Services <--Model--> Controllers <---model&view--> FrontController <----REQ---- Client
                                                                                            |                         ↑  
                                                                                            | model                   |
                                                                                            ↓                         |
                                                                                            Views   ---RESP (html)--->|

            DAO/Repos       are POJOs that execute data base related operations

            Entities        are POJOs that are mapped to a database

            Services        are POJOs that execute bussiness logic

            Model           are POJOs that carry data in the applciation

            Controllers     are POJOs that manage the flow of control 
                            through the underlying services

            FrontController DispatcherServlet from Spring
                                (1) receive the req
                                (2) extract data from the req like req parameters/cookies/request body
                                (3) invoke the mapped action method from a controller and passes
                                        the extracted data to that action method
                                (4) the model and view is received from the invoekd action method
                                (5) the model is passed to the designated view

            Views           are JSP / JSF / Thymeleaf ...etc.,

            interface HandlerMapping  
                        |
                        |<- BeanNameRequestHandlerMApping
                        |<- ControllerNameRequestHandlerMappin
                        |<- SimpleUrlHandlerMApping

                        SimpleUrlHandlerMApping

                            @ReqeustMapping(value=url,method=RequestMEthod.GET/POST)
                                |
                                |<-@GetMapping
                                |<-@PostMapping

                        is configured by Spring Boot by default, and thsi helps FrontController (DispatcherServlet)
                        to find out the mapped action method from controller for a incoming req.

            interface ViewResolver
                        |
                        |<- MessageBundleResourceViewResolver           
                        |<- XmlResourceViewResolver
                        |<- InternalResourceViewResolver

                    MessageBundleResourceViewResolver
                        uses a .proeprties file containing viewName=viewPath

                    XmlResourceViewResolver
                        uses a .xml file containing viewNaems and viewPAth

                    InternalResourceViewResolver
                        uses a formula to map a viewName to a viewPath    

                        viewPath = prefix + viewName + suffix

                        where prefix and suffix are configurable proeprties of InternalResourceViewResolver.

                    Spring Boot by default creates a bean of InternalResourceViewResolver
                    whose prefix is mapped to templates folder
                    and suffix is mapped to '.html'

                    By default the view engine supported by Spring Boot is Thymeleaf.

                    We can create any number of ViewResolvers of any type.
                    But 'order' proeprty mus tbe set for each viewResolver mandatly.

            class ModelAndView

                is the expected return type of all action methods in a controller.

                as the name suggest it is an encapsulation of models and viewName.

                ModelAndView(String viewName)
                ModelAndView(String viewName,String modelAttributeName,Object modelAttribute)

                mv.setViewName("");
                mv.addObject(attributeName,attribute);

            @ReqeustParam

                is used to map a request query paramater to an arg of an action method

            @PathVariable

                is used to map a request path paramater to an arg of an action method

            @ModelAttribute

                (1) can be used to return an object as a model to every incoming request
                    in that case this attribute is applied on a method that returns the modle object

                (2) can be used to map the request form body to a arg in action metnod
                    in that case this attribute is applied on the arg of the action method
     
     Thymeleaf
        
        The Thymeleaf is an open-source Java library that is licensed under the Apache License 2.0. It is a HTML5/XHTML/XML template engine. It provides full integration with Spring Framework.

        Thymeleaf supports 
            variable expressions (${...}) like Spring EL and executes on model attributes
            asterisk expressions (*{...}) execute on the form backing bean (modelAttributes)
            hash expressions (#{...}) are for internationalization
            link expressions (@{...}) rewrite URLs.

        <dependency>  
            <groupId>org.springframework.boot</groupId>  
            <artifactId>spring-boot-starter-thymeleaf</artifactId>  
        </dependency>  

        To activate thymeleaf on .html
            
            <html lang="en" xmlns:th="http://www.thymeleaf.org">  
        
        application.properties

            spring.thymeleaf.cache=false  
            spring.thymeleaf.suffix=.html  
        
        Thymeleaf HTML attributes

            th:text
            th:value
            th:field
            th:object
            th:href
            th:if
            th:class
            th:each="loopingVariable : ${arrayOrListOrSet}"
            th:insert
            th:replace
        
            data-th-text
            data-th-field
            data-th-value
                    ...etc., for html 5

        For a deep reading:
            https://www.thymeleaf.org/doc/tutorials/2.1/usingthymeleaf.html

    Spring Reactive MongoDB

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-mongodb-reactive</artifactId>
        </dependency>
        <dependency>
            <groupId>de.flapdoodle.embed</groupId>
            <artifactId>de.flapdoodle.embed.mongo</artifactId>
            <scope>test</scope>
        </dependency>

        Config:

            @Configuration
            @EnableReactiveMongoRepositories
            public class MongoReactiveApplication extends AbstractReactiveMongoConfiguration {
                @Bean
                public MongoClient mongoClient() {
                    return MongoClients.create();
                }

                @Override
                protected String getDatabaseName() {
                    return "someDatabaseName";
                }
            }

        Mongo Document and Repo
            @Document
            public class Account {
            
                @Id
                private String id;
                private String owner;
                private Double value;
            
                // getters and setters
            }

            @Repository
            public interface AccountCrudRepository extends ReactiveCrudRepository<Account, String> {
                Flux<Account> findAllByValue(String value);
                Mono<Account> findFirstByOwner(Mono<String> owner);
            }

            (or)
            @Repository
            public interface AccountReactiveRepository extends ReactiveMongoRepository<Account, String> { 

            }

    Spring Web Flux for Reactive Programming

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

RestTemplate
---------------------------------------------------------------

    RestTemplate is a rest-client

    RestTemplate restClient = new RestTemplate();

    ResponseEntity<Type> resp = restClient.getForObject(url,Type.class)
    ResponseEntity<Type> resp = restClient.postForObject(url,reqBody)
    ResponseEntity<Type> resp = restClient.putForObject(url,reqBody)
    ResponseEntity<Void> resp = restClient.deleteForObject(url)

RestTemplate vs WebClient
--------------------------------------------

    RestTemplate is blocking in nature or it is synchronized.

        RestTemplate is employed to consume rest-api
        RestTemplate brings ResponseiveEntity

    WebClient is non-blocking in nature or it is asyunchronized or reactive.

        WebClient is employed to consume Reactive (webFlux) rest-api
        WebClient brings Mono / Flux

Open Feign
--------------------------------------------------

    is a dynamically auto-implemented rest client.
    comes as a part (sub-module) of spring-cloud modules.

    this eliminates the usage of rest-template.

    this module autoamtes rest-api calls.

    @FeignClient(name="empClient",url="http://localhost:9999/emps")
    public interface EmployeeService {
        @GetMapping
        List<Employee> getAll();
    }

    @EnableFeignClient
        on the congfiuration class

Internationalization
------------------------------------------------------

    No extra dependencies needed.

    Spring Web MVC iuses 'LocaleResolver' to find out what loacles to be loaded.

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.US);
        return slr;
    }

    Default Locale is different from no locale found.

    Spring Web MVC uses 'LocaleChangeInterceptor' to change the locale of a application
    programatically or dynamically from the user.    

    @Configuration
    public class MyWebConfigs implements WebMvcConfigurer {

        @Bean
        public LocaleChangeInterceptor localeChangeInterceptor() {
            LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
            lci.setParamName("lang");
            return lci;
        }

        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(localeChangeInterceptor());
        }
    }

    Spring Boot Message Bundles

        resource/
            |-messages.properties           //is our fallback message bundle
            |-messages_us.proeprties        //is our default message bundle as per the config
            |-messages_fr.proeprties
            |-messages_in.proeprties
        ...etc.,

    <h1 data-th-text="#{greeting}"></h1>

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




