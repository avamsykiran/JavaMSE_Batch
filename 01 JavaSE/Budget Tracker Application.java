Budget Tracker Application
----------------------------------------------------------------------------------------------

    is all about creating an application that allows a registered user to record all their
    spending and earnings. The application has to keep track of the current-balance and
    should be capable of generating account statements as per a given period like weekly, 
    daily, monthly, and yearly. 

    Domain Specific Rules
    1. Each registered user will have one and only one account.
    2. Each account will have a unique account number.
    3. Each account will have a current balance.
    4. Each account will have a list of transactions.   
    5. Each transaction will have a unique transaction ID, a date,
     an amount, and a type (either "DEBIT" or "CREDIT").

    Job1:
        Develop a rest-api to execute CRUD operations on the domain-entities

        (a) /accounts
                    GET,    GET /{id},  POST,   PUT /{id},    DELETE  /{id}

        (b) /accounts/{accId}/txns
                    GET,    GET /{id},  POST,   PUT /{id},    DELETE    /{id}
        
        (c) GET     /stmt/{accId}/{year}                    annual-statement
        (d) GET     /stmt/{accId}/{year}/{monthName}        monthly-statement
        (e) GET     /stmt/{accId}/{date1}/{date2}           custom-periodic-statement
            
    Job2:
        Secure the rest-api using spring security and JWT token based authentication
    
        /auth/**        access granted only for anonymous users
        /public/**      access granted to everybody irrespective of a user being authenticated or anonymous
        anyOtherReq     access granted only for authenticated users

    Job3:
        Develop a Single-page application using ReactJS to interact with 
        the rest-api and perform all the operations related to the budget tracking.