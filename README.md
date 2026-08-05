Assignments and Case Studies
----------------------------------------------------------------------------------------------

Assignment-1: Java and Spring Boot
    Core-Micro-Services (Vaccination Problem Statement)
    InterService Communiction using FeignClients

Assignment-2: Java and Micro-Services
    Core-Micro-Services (Vaccination Problem Statement)
    ++Discovery Service using Eureka
    ++InterService Communiction using FeignClients and LoadBalancer

Assignment-3: Java and Micro-Services
    Core-Micro-Services (Vaccination Problem Statement)
    Discovery Service using Eureka
    InterService Communiction using FeignClients and LoadBalancer
    ++API Gateway

Case Study: Java and Micro-Services
    Core-Micro-Services (Vaccination Problem Statement)
    Discovery Service using Eureka
    InterService Communiction using FeignClients and LoadBalancer
    API Gateway    
    ++Distributed Tracing using Zipkin
    
Assignment-4: HTML and Css
    Static Digital Resume using HTML 5 & CSS 3

Assignment-5: TypeScript
    Develop a nodejs project with typescript-6
    Add a module 'models.ts' having the below classes exported
        Employee                id,name,salary,houseRentAllowence, netPay()
            |
            |<- ContractEmployee contractDuration
            |<- Manager          travelAllowence

        override netPay() whereever required.

    Add a module 'main.ts', import the classes from models.ts
        create an object for each of those classes and print.

Assignment-6: ReactJS Basics
    Develop a ReactJS SPA having the below components    

    (a) EMICaliculator Component (class-component)
        (i) Using a form, user is expected to input
            Loan Amount, No Of EMIs and ROI
        (ii) Compute and display the EMI.
        (iv) the computed emi msut be recomputed on changing any of the 3 inputs

    (b) Today Component (function Component)
        this component shall display the current date on the top-right corner of the page
        above the Header component, that shall accept a date-format as an attribute from 
        its parent component, and display the date as per the given format.

Assignment-7: ReactJS react-router, useForm Hook, YUP,  and Global State Management using RTK
    Develop a ReactJS SPA the does CRUD operations on a AccountHolder entity.
    The AccountHolder has ahId, fullName, mailId, mobileNumber and currentBalance as fields.
    Use Redux Tool Kir Entity Manager for global state management.
    Use react-bootstrap for styling.

Assignment-8: ReactJS using ReduxThunk and axios integrating rest-api
    Develop a ReactJS SPA the does CRUD operations on a AccountHolder entity.
    The AccountHolder has ahId, fullName, mailId, mobileNumber and currentBalance as fields.
    using ReduxThunk and axios , integrate the app with the rest-api. 
    (use bta-profile microservice from the microservices example step1)
    
Case Study:
    BudgetTrackingApp
    ------------------------------------------------

    Accounts
        |<-multiple-> Transactions
        
    Links on the navbar
        /home   that brigns up the customers page.

        Accounts Page is the landing page
            1. it has to support CRUD operations on accounts
            4. Against each account record, apart from edit and delete buttons, a statement button is needed 
                that when clicked will navigate to statement page
            5. Use a bootstrap model to display account-form for
                add or edit operations

        Statement page
            1. Display the list of transaction related to the account selected.
            2. is the page that supports CRUD operatiosn on transactions
            
Assignment-9: Integrate ReactJS using AppolloCleint with GraphQL
    Complete the form handling of the 'adb-app'
    With useQuery , load the contactByID in case of edit operation.
    With useMutation, insert or update the contact on form submit.