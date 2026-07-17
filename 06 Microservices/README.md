MicroServices 
=========================================================================================

    Modern App Expectations

        + Interaperability
        + Granular Scalability
        + Scope to implement using enchanced method / techniques.
        + Granular Level Maintanability

    A monolythic application has all the modules in a single deployment unit. Hence
    it is definitly not possible to scale an independent module.

    Microservices is a design pattern that promots an eco-system of co-operative isolated apps that function as a
    single Application. As the apps are isolated and independent, each of the app
        1. can be developed in a different environment - interaperable
        2. granular scalability is also possible as the apps are independent.
        3. and so on all modern expectations can be met.

    Challenges while adopting to Microservices

        1. Decomposition
            converting a monolythic design into a microservice design
        2. Integration
            a. inter-service communication
            b. distributed transaction
            c. common url for a client-app to talk to the entire eco-system of microservices.                    
        3. Monitoring 
        4. Distributed Tracing

    Microservices Design Patterns

        Decomposition Design Patterns
            Decomposition by Domain
            Decomposition by Sub-domain
        Integration Design Patterns
            Api Gateway Pattern
            Aggregator Pattern
            Client Side Component Pattern
        Database Design Patterns
            Database Per Service
            Shared Database            
            Saga Pattern
            CQRS Pattern
        Observability Design Patterns
            Log Aggregator
            Performence Metrics Aggregator
            Distributed Tracing
        Cross Cutting Design Patterns
            Discovery Service 
            Circuite Breaker
            External Configuaration

    Case Study BudgetTracking APP
        1. We need to have different consumer or account holders to register
        2. Each accountHolder mst be able to record his spending or earning transactions.
        3. Generate a statement periodically displaying the total spending , the total earning and the balance.

        Decomposition By Domain

            1. profile-service          allow consuemr or accountholders to register
            2. txns-service             allow the consumer or accountholder to insert/update/delete the transactions
            3. statement-service        generate the periodic statement.

        Sub-Domain Pattern guides through bounded-context.

                We will decompose the budgetTrackinApp into 3 microservices
                    (a) Profiles-Service
                            AccountHolder (Entity)
                                accountHolderId
                                fullName
                                mobileNumber
                                mailId
                                userId
                                password

                    (b) Transactions-Service
                            AccountHolder (Entity)
                                accoountHolderId
                                txns: Set<Txn>
                                currentBal

                            Txn (Entity)
                                dateOfTxn
                                txnId
                                txnAmount
                                txnType
                                owner : AccountHolder

                    (c) Statement-Service                
                            AccountHolder (Model)
                                accountHolderId
                                fullName
                                mobileNumber
                                mailId

                            Txn (Model)
                                dateOfTxn
                                txnId
                                txnAmount
                                txnType

                            Statement (Model)
                                owner : AccountHolder
                                txns: Set<txns>
                                startDate: Date
                                endDate: Date
                                totalSpending
                                totalEarning
                                balance

            Shared Database Pattern

                Having a single DB for all microservices
                in brown field apps

            Database Per Service Pattern

                Each microservice has its own database
                in all green field apps

            Discovery Service Pattern

                discovery-service
                    |
                    |- all microservices will register their address with discovery-service
                    |- the address are retrived from here by the needy microservices

            Data Aggregation Pattern

                Aggregation is about desiging a microservice that can collect info
                from other microservices analyze and aggreagate the data and pass the 
                aggregated data to the client, saving the client from making multiple requests
                for different parts of the data.

                the 'statement-microservice' is an example for this pattern.

            Client Side Component Pattern

                Each component of the UI/UX application can place
                their individual reqeusts to different microservices parellelly
                and should be receiving the resposnes as well parllelly.

            Distributed Tracing Design Pattern

                Tracing - Service

                    Whenever a request comes to any of the microservices in our app-ecosystem,
                    that request is given a unique ID and is reported to the Tracing-Service
                    every time, the request goes from one service to another service until
                    the final resposne is sent to the clinet. And the tracing-service
                    will record all the track of this request along with any performence
                    metrics and log info attached with the request.

            Load Balancing Design Pattern

                load balalcing means mapping the incoming reqeusts to multiple instacnes of the 
                same microservice based on some (round-robin) algorithm.

                tools like Ribbon / Spring Cloud Load Balancer ..etc., are used to perform load 
                balancing.

            API Gateway Design Pattern

                gateway-service <------------(all reqs)--------------- any-client
                    |
                    | -> forward that request to the respective micro-service
                         receives the response from that micro-service
                                                            |
                                                            |-----(response)----> client

            Circuit - Breaker Design Pattern

                circuit-breaker-thrushold

                    when the first request could not reach a specific microservice (due to its down-time),
                    a fallback machanisim is triggered.

                    After that the circuit is made open (broken), means that the fallback machanisim
                    will address all the other consiquitive request targetting that microservice.

                    When a request to the sme micro-service is inbound after the thrushold, then the
                    circuit is half-closed, means that a new atempt to reach the microserivce is made,
                            }|- on successful contact, the circuit is closed
                             |- or if that microservice is still unavailable, the circuit continues to be open.

                    tools like Resiliance4j ..etc., are for the purpose.

            External-Cofiguaration Design Pattern

                    repository (github) [contains a list all config files of all microservice]
                        |
                        |                
                config-service
                            |<- when ever a microservice has to start, it will first send a fetech req the
                            |   the config-service
                            |
                            | the config-service will check for the cofnig file in the repo
                            |
                            |<- the config file is passed to the microservice by the config-service
                            |
                            |<- wheever the config fiels are modified and pushed into the repo
                            |   the config service will automatically notify all the respective microservices
                                    and the microservices will receive the updated config-file and restart all by 
                                    themselves.

Decomposition by domain and sub-domain

    budgettracking 
        profiles service
            AccountHolder Entity
                Long ahId
                String fullName
                String mobile
                String mailId

        txns service
            AccountHolder Entity
                Long ahId
                Double currentBalance
                Set<Txn> txns

            Txn           Entity
                Long txnId
                String header
                Double amount
                TxnType type
                LocalDate txnDate
                AccountHolder holder

        statement service
            AccountHolder Model
                Long ahId
                String fullName
                String mobile
                String mailId
                Double currentBalance

            Txn           Model
                Long txnId
                String header
                Double amount
                TxnType type
                LocalDate txnDate

            Statement     Model
                LocalDate start
                LocalDate end
                AccountHolder profile
                Set<Txn> txns
                totalCredit
                totalDebit
                statementBalance

Aggregator Pattern

    CLIENT -----> req for statement ------------> statement-service 
                                                        |       ---------------> profile service
                                                        |   <---account holder data---
                                                        |   --------------------> txns service
                                                        |   <----list of txns-------
                                            does the composition 
                                            and computation
         CLIENT   <---statement obj-------  into statement obj

Discovery Service Design Pattern

                discovery-service
                (spring cloud netflix eureka discovery service)
                        ↑|
                    registration of urls 
                    and retrival of urls
                        |↓
            -------------------------------------
            |               |                   |
    profile-service     txns-service     statement-service

Api Gateway Design Pattern

    Andriod App/Angular App/ReactJS App
        ↑↓
     api-gateway
     (spring cloud api gateway)
        |
        |
        | <---->   discovery-service
            ↑    ( netflix eureka discovery service)
            |            ↑|
            |        registration of urls 
            |       and retrival of urls
            ↓            |↓
            -------------------------------------
            |               |                   |                
    profile-service     txns-service     statement-service     

Distributed Tracing

  Andriod App/Angular App/ReactJS App
        ↑↓
     api-gateway
     (spring cloud api gateway)
        |
        |
        | <---->   discovery-service
            ↑    ( netflix eureka discovery service)
            |            ↑|
            |        registration of urls 
            |       and retrival of urls
            ↓            |↓
            -------------------------------------
            |               |                   |                
    profile-service     txns-service     statement-service
        (sleuth)          (sleuth)            (sleuth)
            |               |                      |
            -------------------------------------------
                        ↑↓
             distrubuted tracing service
                    (zipkin-server)

External Configuaration

  Andriod App/Angular App/ReactJS App
        ↑↓
     api-gateway
     (spring cloud api gateway)
        |
        |
        | <---->   discovery-service
            ↑    ( netflix eureka discovery service)
            |            ↑|
            |        registration of urls 
            |       and retrival of urls
            ↓            |↓
            -------------------------------------
            |               |                   |                
    profile-service     txns-service     statement-service
        (sleuth)          (sleuth)            (sleuth)
            |               |                      |
            ----------------------------------------
                        ↑↓                      ↑↓
             distrubuted tracing service       configuaration-service 
                    (zipkin-server)         (spring cloud config service)
                                                    |
                                                    |
                                                    git-repo
                                                        profile.properties
                                                        txns.properties
                                                        statement.properties
                                                        gateway.properties

Implementing Budget-tracker
                                        
    Step#1  implementing decomposed services and do inter-service communication and aggregator
        in.bta:bta-profiles
            dependencies
                org.springframework.boot:spring-boot-starter-web
                org.springframework.boot:spring-boot-devtools
                org.springframework.cloud:spring-cloud-openfeign
                mysq1:mysql-connector-java
                org.springframework.boot:spring-boot-starter-data-jpa
            configuaration
                spring.application.name=profiles
                server.port=9100

                spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
                spring.datasource.username=root
                spring.datasource.password=root
                spring.datasource.url=jdbc:mysql://localhost:3306/bapsDB?createDatabaseIfNotExist=true
                spring.jpa.hibernate.ddl-auto=update

        in.bta:bta-txns
            dependencies
                org.springframework.boot:spring-boot-starter-web
                org.springframework.boot:spring-boot-devtools
                org.springframework.cloud:spring-cloud-openfeign
                mysq1:mysql-connector-java
                org.springframework.boot:spring-boot-starter-data-jpa
            configuaration
                spring.application.name=txns
                server.port=9200

                spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
                spring.datasource.username=root
                spring.datasource.password=root
                spring.datasource.url=jdbc:mysql://localhost:3306/batxnsDB?createDatabaseIfNotExist=true
                spring.jpa.hibernate.ddl-auto=update

        in.bta:bta-statement
            dependencies
                org.springframework.boot:spring-boot-starter-web
                org.springframework.boot:spring-boot-devtools
                org.springframework.cloud:spring-cloud-openfeign
            configuaration
                spring.application.name=statement
                server.port=9300

    Step#2  implementing discovery service and client side load balancing
        in.bta:bta-discovery
            dependencies
                org.springframework.boot:spring-boot-devtools
                org.springframework.cloud:spring-cloud-starter-netflix-eureka-server
            configuaration
                @EnableEurekaServer    on Application class

                spring.application.name=discovery
                server.port=9000

                eureka.instance.hostname=localhost
                eureka.client.registerWithEureka=false
                eureka.client.fetchRegistry=false
                eureka.client.serviceUrl.defaultZone=http://${eureka.instance.hostname}:${server.port}/eureka/
                eureka.server.waitTimeInMsWhenSyncEmpty=0

        in.bta:bta-profiles
            dependencies
                ++ org.springframework.cloud:spring-cloud-starter-netflix-eureka-client
                ++ org.springframework.cloud:spring-cloud-starter-loadbalancer
            configuaration
                ++@EnableDiscoveryClient  on Application class

                eureka.client.serviceUrl.defaultZone=http://localhost:9000/eureka/
                eureka.client.initialInstanceInfoReplicationIntervalSeconds=5
                eureka.client.registryFetchIntervalSeconds=5
                eureka.instance.leaseRenewalIntervalInSeconds=5
                eureka.instance.leaseExpirationDurationInSeconds=5

                spring.cloud.loadbalancer.ribbon.enabled=false

        in.bta:bta-txns
            dependencies
                ++ org.springframework.cloud:spring-cloud-starter-netflix-eureka-client
                ++ org.springframework.cloud:spring-cloud-starter-loadbalancer
            configuaration
                ++@EnableDiscoveryClient  on Application class

                eureka.client.serviceUrl.defaultZone=http://localhost:9000/eureka/
                eureka.client.initialInstanceInfoReplicationIntervalSeconds=5
                eureka.client.registryFetchIntervalSeconds=5
                eureka.instance.leaseRenewalIntervalInSeconds=5
                eureka.instance.leaseExpirationDurationInSeconds=5

                spring.cloud.loadbalancer.ribbon.enabled=false

        in.bta:bta-statement
            dependencies
                ++ org.springframework.cloud:spring-cloud-starter-netflix-eureka-client
                ++ org.springframework.cloud:spring-cloud-starter-loadbalancer
            configuaration
                ++@EnableDiscoveryClient  on Application class

                eureka.client.serviceUrl.defaultZone=http://localhost:9000/eureka/
                eureka.client.initialInstanceInfoReplicationIntervalSeconds=5
                eureka.client.registryFetchIntervalSeconds=5
                eureka.instance.leaseRenewalIntervalInSeconds=5
                eureka.instance.leaseExpirationDurationInSeconds=5

                spring.cloud.loadbalancer.ribbon.enabled=false    

    Step 3: Implement API Gateway Design Pattern
        in.bta:bta-gateway
            dependencies
                org.springframework.boot:spring-boot-devtools
                org.springframework.cloud:spring-cloud-starter-gateway
                org.springframework.cloud:spring-cloud-starter-netflix-eureka-client
                org.springframework.cloud:spring-cloud-starter-loadbalancer
            configuaration
                @EnableDiscoveryClient          on Application class

                spring.application.name=gateway
                server.port=9999

                eureka.client.serviceUrl.defaultZone=http://localhost:9000/eureka/
                eureka.client.initialInstanceInfoReplicationIntervalSeconds=5
                eureka.client.registryFetchIntervalSeconds=5
                eureka.instance.leaseRenewalIntervalInSeconds=5
                eureka.instance.leaseExpirationDurationInSeconds=5

                spring.cloud.gateway.discovery.locator.enabled=true
                spring.cloud.gateway.discovery.locator.lower-case-service-id=true
                
        in.bta:bta-discovery
        in.bta:bta-profiles
        in.bta:bta-txns
        in.bta:bta-statement
              
    Step 4: Implement Distributed Tracing Design Pattern
          in.bta:bta-discovery
          
          in.bta:bta-gateway
            dependencies
                ++org.springframework.boot:spring-boot-starter-actuator
                ++org.springframework.cloud:spring-cloud-starter-sleuth
                ++org.springframework.cloud:spring-cloud-starter-zipkin : 2.2.8.RELEASE
            
            configuaration
                logger.level.org.springramework.web=debug
                management.endpoints.web.exposure.include=*
       
        in.bta:bta-profiles
            dependencies
                ++org.springframework.boot:spring-boot-starter-actuator
                ++org.springframework.cloud:spring-cloud-starter-sleuth
                ++org.springframework.cloud:spring-cloud-starter-zipkin : 2.2.8.RELEASE
            
            configuaration
                logger.level.org.springramework.web=debug
                management.endpoints.web.exposure.include=*

        in.bta:bta-txns
            dependencies
                ++org.springframework.boot:spring-boot-starter-actuator
                ++org.springframework.cloud:spring-cloud-starter-sleuth
                ++org.springframework.cloud:spring-cloud-starter-zipkin : 2.2.8.RELEASE
            
            configuaration
                logger.level.org.springramework.web=debug
                management.endpoints.web.exposure.include=*

        in.bta:bta-statement
            dependencies
                ++org.springframework.boot:spring-boot-starter-actuator
                ++org.springframework.cloud:spring-cloud-starter-sleuth
                ++org.springframework.cloud:spring-cloud-starter-zipkin : 2.2.8.RELEASE
            
            configuaration
                logger.level.org.springramework.web=debug
                management.endpoints.web.exposure.include=*

        tracing-service
            zipkin-server
                https://search.maven.org/remote_content?g=io.zipkin&a=zipkin-server&v=LATEST&c=exec 
                
                java -jar zipkin-server.jar

    Step 5: External Configuaration Design Pattern
        inTheWorkSpace> md bt-props-repo
            //and then create these files in this directory
                // gateway.properties
                // profiles.properties
                // txns.properties
                // statement.properties
                // move the content of 'application.properties' of each microservice into these respective files
                
            inTheWorkSpace> cd bt-props-repo
            inTheWorkSpace\bt-props-repo> git init           
            inTheWorkSpace\bt-props-repo> git add .
            inTheWorkSpace\bt-props-repo> git commit -m "all service properties"
        
        in.bta:bta-discovery    (no changes needed to this app/service)

        in.bta:bta-config
            dependencies
                org.springframework.boot:spring-boot-devtools
                org.springframework.cloud:spring-cloud-config-server
                org.springframework.cloud:spring-cloud-starter-netflix-eureka-client
            
            configuaration  
                @EnableDiscoveryClient
                @EnableConfigServer             on Application class

                spring.application.name=config
                server.port=9090

                spring.cloud.config.server.git.uri=file:///local/git/repo/path/bt-props-repo

                eureka.client.serviceUrl.defaultZone=http://localhost:9000/eureka/
                eureka.client.initialInstanceInfoReplicationIntervalSeconds=5
                eureka.client.registryFetchIntervalSeconds=5
                eureka.instance.leaseRenewalIntervalInSeconds=5
                eureka.instance.leaseExpirationDurationInSeconds=5
        
        in.bta:bta-gateway
            dependencies
                ++ org.springframework.cloud:spring-cloud-starter-bootstrap
                ++ org.springframework.cloud:spring-cloud-config-client

            action - delete 'application.properties'
            configuaration - bootstrap.properties
                spring.cloud.config.name=gateway
                spring.cloud.config.discovery.service-id=config
                spring.cloud.config.discovery.enabled=true
                
                eureka.client.serviceUrl.defaultZone=http://localhost:9000/eureka/                    
        
        in.bta:bta-profiles
            dependencies
                ++ org.springframework.cloud:spring-cloud-starter-bootstrap
                ++ org.springframework.cloud:spring-cloud-config-client
            
            action - delete 'application.properties'
            configuaration - bootstrap.properties
                spring.cloud.config.name=profiles
                spring.cloud.config.discovery.service-id=config
                spring.cloud.config.discovery.enabled=true
                
                eureka.client.serviceUrl.defaultZone=http://localhost:9000/eureka/   

        in.bta:bta-txns
            dependencies            
                ++ org.springframework.cloud:spring-cloud-starter-bootstrap
                ++ org.springframework.cloud:spring-cloud-config-client
            
            action - delete 'application.properties'
            configuaration - bootstrap.properties
                spring.cloud.config.name=txns
                spring.cloud.config.discovery.service-id=config
                spring.cloud.config.discovery.enabled=true
                
                eureka.client.serviceUrl.defaultZone=http://localhost:9000/eureka/   

        in.bta:bta-statement
            dependencies
                ++ org.springframework.cloud:spring-cloud-starter-bootstrap
                ++ org.springframework.cloud:spring-cloud-config-client
            
            action - delete 'application.properties'
            configuaration - bootstrap.properties
                spring.cloud.config.name=statement
                spring.cloud.config.discovery.service-id=config
                spring.cloud.config.discovery.enabled=true
                
                eureka.client.serviceUrl.defaultZone=http://localhost:9000/eureka/   

        Order Of Execution
            Discovery
            Config
            Gateway
            Profiles
            Statement
            Txns

OAuth2.0 
-----------------------------------------------------------------------------------------------------------

    OAuth 2.0 (Open Authorization) is the industry-standard framework used for authorization. It allows a website or application to access resources hosted by another application on behalf of a user, without ever exposing the user's login credentials (like their password).

    The Core Problem It Solves
        Before OAuth 2.0, if a third-party application (like a photo-printing website) wanted to access your photos from a cloud storage service (like Google Drive), you had to give that application your actual username and password.

        This created massive security flaws:

            The third-party app stored your password in plain text or reversible formats.
            The app gained total access to your account, not just your photos.
            To revoke access, we had to change your password, breaking integrations for all other apps.

        OAuth 2.0 solves this by introducing an access token—a limited digital key that grants access only to specific data for a limited time.

    Key Actors in OAuth 2.0
        To understand how it works, imagine booking a stay at a modern hotel that uses electronic key cards.

            The Resource Owner (The User): This is you. You own the data (e.g., your profile, photos, or email list) and control who gets access to it.

            The Client (The Application): The third-party website or mobile app that wants to access your data (e.g., a smart calendar app wanting to access your Google Calendar).

            The Authorization Server: The secure engine that verifies your identity, asks for your consent, and issues the access token (e.g., Google's login/identity system). Think of this as the hotel front desk.

            The Resource Server: The API or database hosting the protected data you want to share. Think of this as your hotel room, which opens only when presented with the correct key card.

    How it Works: The Authorization Code Flow
        The most common way OAuth 2.0 operates in web applications is the Authorization Code Grant Type. Here is the step-by-step breakdown:

            +--------+                               +---------------+
            |        |--(A)- Authorization Request ->|   Resource    |
            |        |                               |     Owner     |
            |        |<-(B)-- Authorization Grant ---|   (User)      |
            |        |                               +---------------+
            |        |
            |        |                               +---------------+
            | Client |--(C)- Authorization Grant --->| Authorization |
            |  App   |                               |    Server     |
            |        |<-(D)----- Access Token -------|               |
            |        |                               +---------------+
            |        |
            |        |                               +---------------+
            |        |--(E)----- Access Token ------>|    Resource   |
            |        |                               |     Server    |
            |        |<-(F)--- Protected Resource ---|   (Your Data) |
            +--------+                               +---------------+
        
        The Handshake (A & B): You click "Sign in with Google" on a gaming app. The app redirects your browser to Google’s secure login page. You log in directly with Google and see a prompt: "This app wants permission to view your email address." You click "Allow."

        The Code Exchange (C & D): Google sends a temporary, short-lived Authorization Code back to the gaming app. The gaming app takes this code and sends it securely behind the scenes to Google's Authorization Server, along with its own private application secret key, to prove its identity.

        The Token Delivery (E & F): Google validates the code and secret key, then issues an Access Token (usually formatted as a JWT or JSON Web Token).

        Data Access: The gaming app presents this Access Token to the Google Calendar API. The API validates the token and sends over your calendar data. The app never saw your password.

OAuth2.0 tailoring on Spring Boot Microservices
-----------------------------------------------------------------------------------------------------------
    
    Implementing OAuth 2.0 in a Spring Boot Microservices architecture is a production standard for keeping services secure, decentralized, and stateless. Because microservices scale dynamically, standard session-based security won't work; instead, we rely on **JWT (JSON Web Tokens)** passed via the `Authorization: Bearer` header.

    1. The Core Architecture

        In a secure microservices pattern, roles are strategically divided across the infrastructure:

        Identity Provider / Authorization Server:
            Centralizes user credentials, issues JWT tokens, and exposes a Public Key (`jwks_uri`) for token validation (e.g., Keycloak, Auth0, Okta, or Spring Security Authorization Server).
        
        API Gateway (OAuth 2.0 Client):
            Acts as the single entry point. It negotiates the OAuth 2.0 login flow with the Authorization Server and securely routes requests downstream.
        
        Downstream Microservices (Resource Servers):
            Internal services that trust the JWT tokens. They validate incoming JWT signatures using the Authorization Server's public keys without needing a network database lookup.
    
    2. Implementing the API Gateway (OAuth2 Client)

        The Gateway (built using Spring Cloud Gateway) handles user login and propagates the obtained JWT token to downstream services using a `TokenRelay` filter.

        Dependencies (`pom.xml`)

            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-starter-gateway</artifactId>
            </dependency>
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-oauth2-client</artifactId>
            </dependency>
        
        Configuration (`application.yml`)       
            server:
                port: 8080

            spring:
                cloud:
                    gateway:
                        default-filters:
                            - TokenRelay # Automatically forwards the OAuth2 access token downstream
                        routes:
                            - id: order-service
                            uri: lb://order-service
                            predicates:
                                - Path=/orders/**

                security:
                    oauth2:
                        client:
                            registration:
                                my-identity-provider:
                                    client-id: gateway-client
                                    client-secret: super-secret-key
                                    scope: openid, profile, read
                                    authorization-grant-type: authorization_code
                                    redirect-uri: "{baseUrl}/login/oauth2/code/{registrationId}"
                        provider:
                            my-identity-provider:
                                issuer-uri: http://localhost:8081/realms/myrealm

        Securing Internal Microservices (Resource Servers)

            Downstream microservices don't need to know *how* the user logged in. They only check if the request contains a valid token and if that token has the appropriate scopes.

                <dependency>
                    <groupId>org.springframework.boot</groupId>
                    <artifactId>spring-boot-starter-web</artifactId>
                </dependency>
                <dependency>
                    <groupId>org.springframework.boot</groupId>
                    <artifactId>spring-boot-starter-oauth2-resource-server</artifactId>
                </dependency>

            Configuration (`application.yml`)

                server:
                    port: 8082

                spring:
                    security:
                        oauth2:
                            resource-server:
                                jwt:
                                    jwk-set-uri: http://localhost:8081/realms/myrealm/protocol/openid-connect/certs

            Security Configuration Class

                @Configuration
                @EnableWebSecurity
                public class SecurityConfig {

                    @Bean
                    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                        http
                            .authorizeHttpRequests(authorize -> authorize
                                .requestMatchers("/orders/public/**").permitAll()
                                .requestMatchers("/orders/**").hasAuthority("SCOPE_read")
                                .anyRequest().authenticated()
                            )
                            .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));
                            
                        return http.build();
                    }
                }

    4. Inter-Service Communication (Microservice to Microservice)

        When `Order-Service` needs to call `Inventory-Service`, it has two architectural choices depending on context:

        Option A: Token Propagation (On behalf of User)

            If the downstream service needs to know who the original user is, extract the existing token from the security context and pass it forward using a Spring `WebClient` or `FeignInterceptor`:

            @Bean
            public WebClient webClient(OAuth2AuthorizedClientManager authorizedClientManager) {
                ServletOAuth2AuthorizedClientExchangeFilterFunction oauth2 =
                        new ServletOAuth2AuthorizedClientExchangeFilterFunction(authorizedClientManager);
                return WebClient.builder()
                        .filter(oauth2)
                        .build();
            }
        
        Option B: Client Credentials Grant (System-to-System)

            If the interaction is a pure backend background task completely unrelated to the active user, configure the microservice as an OAuth2 Client using the `client_credentials` grant type. It will fetch an independent system token directly from the Authorization Server to communicate with other resource servers.
