Java SE
----------------------------------------------------------------------------------------

    Introduction

        (a) Evolution
        (b) Features
        (c) Characteristics
        (d) Tokens and Naming Conventions
        (e) Data types and declaration syntax and operators
        (f) Arrays
        (g) Control Structures

                int num = 1_23_456; //it is a valid integer 

                switch(day.toLowerCase()) {
                    case "monday": break;
                    case "tuesday": break;
                    case "wednesday": break;                    
                    ...etc.,
                }

                String[] friends = new String[]{"Vamsy","Kiran","Mohan"};

                for(String friend : friends){
                    //red as for each friend in friends
                }

                Tokens
                    Keywords
                    Identifiers
                    Operators
                    Comments
                    Literals

    OOP Concepts

        Class And Object

            class is a user defiend data type that represents an entity
            via fields and methods.

            object is a variable of class type.

        Encapsulation

            is to provide indirect access to the core fields and methods. (data-hiding)

            default, private, protected and public 

            using private fields and public getters and setter in encapsulation,
            granular level access control is possible.
                (a) a private field with no getter and setter is completly a hidden field
                (b) a private field with only getter is a readonly field
                (c) a private field with only setter is a writeonly field
                (d) a private field with both getter and setter is a completly accessable field
             
            public class Student {
                private int rollNum;
                private String fullName;
                private double fee;

                public Student(){
                //default constructor   
                }

                public Student(String fullName,double fee){
                    //paramatrized constuctor

                }
                
                public Student(int rollNum,String fullName,double fee){
                    //paramatrized constuctor

                }

                public Student(Student s){
                    //copy constructor
                    
                }

                public int getRollNum(){
                    return rollNum;
                }

                public void setRollNum(int rollNum){
                    this.rollNum=rollNum;
                }
            }

            Student s = new Student(); 

            1. read only
                class Dummy {
                    private int x;

                    public int getX(){
                        return x;
                    }
                }
                
            2. write only
                class Dummy {
                    private int x;

                    public void setX(int x){
                        this.x=x;
                    }
                }

            3. read-write
                class Dummy {
                    private int x;

                    public int getX(){
                        return x;
                    }

                    public void setX(int x){
                        this.x=x;
                    }
                }

            Types of Classes

                POJO        Plain Old Java Object

                            is any java class that has
                                1. all data members are private
                                2. Has a default constructor 
                                3. Has a paramatrized constuctor optionally
                                4. getter, setter for all the private data members

                DTO/Model   is any POJO that represents a domain-entity liek Student, BankAccount, Employee, ...etc.,
                            and must ovveride the following three emthods from java.lang.Object class
                                1. hashcode
                                2. equals
                                3. toString

                Entity      is any model that is mapped to a database table using any ORM library .

                Service     is any POJO that offers bussiness logic

                UI          is any POJO that offers User-Interaction through command lione interface

                Controller  is any POJO that offers control of flow of execution in an application.

                DAO         Data Access Object
                            is any POJO that offers database related logic liek
                                retriviong records
                                adding a record
                                deleting a record or
                                updating a record

                Repository  is any DAO that uses a ORM library to talk to a database.
   
        Polymorphsim

            overlaoding
                method of the same scope(either same class or to a super and a sub class) 
                having same name but different 
                arg-list (either arg-type or arg-count or both).

                class Monkey {
                    public void eat(Fruit fruit){
                        while(fruit.exists()){
                            byteAndChewAndSwallow(fruit);
                        }
                    }

                    public void eat(IceCream iceCream){
                        while(iceCream.exists()){
                            lickAndSwallow(iceCream);
                        }
                    }                    
                }

            overriding

                methods from a super and a sub type, have exactly the same signature (retrunType name and arg-list)

                class Human extends Monkey {
                    public void eat(Fruit fruit){
                        wash(fruit);
                        Piece[] pieces = cut(fruit);
                        for(Piece p : pieces){
                            chewAndSwallow(p);
                        }
                    }                   
                }

            Method Resolution

                Fruit f = new Friut();
                IceCream i = new IceCream();

                Monkey m = new Monkey();
                Human h = new Human();

                m.eat(f);   //Monkey::eat having Fruit type arg
                h.eat(f);   //Human::eat having Fruit type arg
                m.eat(i);   //Monkey::eat having IceCream type arg
                h.eat(i);   //Monkey::eat having IceCream type arg

            Constructor Overloading

                Default Constructor                No-Arg-Constructor
                Paramatrized Constructor           Constructor-With-Args
                Copy Constructor                   Paramatrized Constructor having one of its args 
                                                   as an object of the same class            

                class Rect {
                    private int len;
                    private int brd;

                    //Default Constructor
                    public Rect(){
                        this(5,10);
                    }
                    
                    //Paramatrized Constructor
                    public Rect(int len,int brd){
                        this.len=len;
                        this.brd=brd;
                    }

                    //Copy Constructor
                    public Rect(Rect r){
                        this(r.len,r.brd);
                    }

                }

        Inheretence

            is to create a new UDT from an existing type.
            the exisitng type is called the super-type
            and the newly created type is called sub-type

            'extends' keyword is used to derive a class from a class or a interface from an interface
            'implements' keyword is used to derive a class from a interface.

            class Pen {
                Nib nib;
                Barrel barrel;
                Refill refill;

                public void write(String text,Paper paper){

                }
            }

            class Marker extends Pen {
                public void write(String text,WhiteBoard board){

                }
            }

            Types of Inheretences
                Simple          Employee (empId,name,sal) <--- Manager (...,hra)
                
                Multi-Level     Employee (empId,name,sal) <--- Manager (...,hra) <--- Director (..,share)

                Multiple        Not Supported among classes and among interfaces in java
                                But a class can implement multiple interfaces

                Hirarchial                                  |<-- ContractEmployee (...,contractDuration)
                                Employee (empId,name,sal) <-|
                                                            |<-- Manager (...,hra)

                Hybrid                                      |<-- ContractEmployee (...,contractDuration)
                                Employee (empId,name,sal) <-|
                                                            |<-- Manager (...,hra) <--- Director (..,share)

            Constructor Phenomenon

                An object allocation to a sub-class triggers super class constructor follwoed by sub-class constructor

                To alter the defualt constructor of the super class being invoked, we can use 'super' keyword

            Super-type references can hold sub-type objects, however vice-versa is not possible.

                Employee e1 = new ContractEmployee();
                Employee e2 = new Manager();
                Employee e3 = new Director();

                Manager m1 = (Manager) e2;
                Manager m2 = (Manager) e1; //ClassCastException

        Abstraction

            Abstraction means declaring but not implementing.

            also known as behaviour/implementation hiding

            in Jave, abstraction can be achived through

            Abstract Class          is a class to which an object can not be allocated.

                abstract class Person (id,firstNAme,lastName,dateOfBirth,bloodGroup)
                        |
                        |<-Principal           
                        |<-Student
                        |<-Teacher
                        |<-NonTeachingStaff

            Interface               is a user defiend data type that has no fields.
                                    generally interfaces represent roles.
                                    multiple interfaces can be implemented by one class.

            To Create an abstract UserDefiendType
                        ↓
                        Does it has fields ?    ------- NO ----> create UDT as interface
                                |
                                | YES
                                ↓         
                                create UDT as an abstract class

            Abstract Method         is a method that does not have an implementation.
                                    only abstract classes and interfaces can have an abstract method.

                        both interfaces and abstract classes can also accomidate 
                        'abstract functions', but it is not mandate.

            abstract class Animal
                |
                |<- class FemaleAnimal  -----> Mother,Sibling
                |<- class MaleAnimal    -----> Father,Sibling

            abstract class Bird
                |
                |<- class FemaleBird -----> Mother,Sibling
                |<- class MaleBird   -----> Father,Sibling

            interface Mother    has an abstract method giveBirth();
            interface Father
            interface Sibling

    String Manipulation

        String class is immutable

            String s1 = "Hello ";       
            s1 = s1 + "World "; //s1 is reallocated as a new-string to contina "Hello World"

        StringBuilder
        StringBuffer        is thread-safe and hence a little slow in performence

    Exception Handling

        Throwable
            |- Exception                <- CHECKED_EXCEPTIONS       Compiler will force to handle 
            |   |- RuntimeExceptiomn    <- UN_CHECKED_EXCEPTIONS    Compiler Ignores or doesn't check
            |
            |- Error
        
        UN_CHECKED_EXCEPTIONS   are not supposed to be handled at all, but they must be avoided through
                                defensive programming.

                                class EmployeeServiceImpl {
                                    public String getFullName(Employee emp){
                                        String fullName="";

                                        if(emp!=null){ //avoiding NullPointerException
                                            fullName = emp.getLastName() +", "+emp.getFirstName();
                                        }

                                        return fullName;
                                    }
                                }
    
        CHECKED_Exceptions      are supposed to be handled using try..catch or are supposed to be thrown to 
                                the caller method using 'throws' keyword.

            try{

            }catch(ExceptionType1 exp){
                
            }catch(ExceptionType2 exp){
                
            } ... finally {
                //is used to ensure that any closable onbjects are closed.
            }

            try{

            }catch(ExceptionType1 | ExceptionType2 exp){
                
            } ... finally {
                //is used to ensure that any closable objects are closed.
            }

            try( /*declare closable object like files or connections */ ) {

            }catch(ExceptionType1 | ExceptionType2 exp){
                
            } 

                try( 
                        Connection con = DriverManager.getConnection(url,uid,pwd);
                        PreparedStatement pst = con.prepareStatement(DEL_EMP_QRY);
                     ) {
                    pst.executeUpdate();
                }catch(SQLException exp){

                }

                try ( FileInputStream fin = new FileInputStream("fileName.txt"); ) {
                        
                        //fin is to be sued
                        
                } catch(IOException exp){

                }

Data Time API

    java.time               Factory Methods
        LocalTime           .now(),.of(hour,min,second)
        LocalDate           .now(),.of(year,MON,day)
        LocalDateTime       .now(),.of(year,MON,day,hour,min,second)    
        ZonedDateTime       .now(zoneId),.of(year,MON,day,hour,min,second,zoneId)    

        ZoneId              .of("GMT+5")

        Period              .between(startDate,endDate)
        Durtion             .between(startTime,endTime)

        DateTimeFormatter   .ofPattern("dd-MM-yyyy")

Generics

    Generics will allow us to implement data-type independent algorithms.

    Algorithms like Swapping or sorting or searching ...etc., do not dependent the data type.

    swap two variable x and y
        t = x;
        x = y;
        y = t;

    And this algo is the smae for any data type .

        class Swapper<T> {
            public void doSwap(T x,T y) {
                T temp = x;
                x = y;
                y = temp;
            }
        }

        class App{
            public static void main(String args[]){
                String s1="Hai",s2="Hell";
                Swapper<String> sw1 = new Swapper<>();
                sw1.doSwap(s1,s2);

                int x=89,y=45;
                //Swapper<int> sw2 = new Swapper<>(); generics do not support native types.
                Swapper<Integer> sw2 = new Swapper<>();
                sw2.doSwap(x,y);
            }
        }

Collections

    represents data structures in java.
    
    Linear data structures have elements arranged in a sequential and hence they can be refered by the number of thier postion called index. All index based operations like getting an element at an index or removeing an element at an index ..et., are possible.
    
    Non-Linear data structures do not have elements arranged in a sequential and hence they can not be refered by the number of their postion. Non-Linear data structure do not support duplicate elements.

    java.util
           Collection (interface)       add,remove,size,isEmpty,stream,contains
            |
            |<- List (interface)        represents Linear, first(),last(),getAt(int index),removeAt(int index)
            |   |                       support duplicate elements
            |   |
            |   | <- class Vector       is a synchronised growable array implementation
            |   | <- class ArrayList    is a non-synchronised growable array implementation
            |   | <- class LinkexList   is a doubly linked list implementation
            |
            |<- Set (interface)         represents Non Linear
            |     |                     does not support duplicate elements
            |     |
            |     | <- class HashSet            order of retrival is non-pridictable  (uses HashMap internally)
            |     | <- class LinkedHashSet      order of retrival is entry-order (uses LinkedHashMap internally)
            |     | <- class TreeSet            order of retrival is sorted-order (uses AVL (RedBlack) Trees internally)

           Map (interface)              represents a collection of key-value
            |                           put,set,get,keySet,valueSet,containsKey,containsValue,size
            |
            |<- class Hashtable         order of retrival is non-pridictable and synchronized (legecy)
            |<- class HashMap           order of retrival is non-pridictable
            |<- class LinkedHashMap     order of retrival is entry-order
            |<- class TreeMap           order of retrival is sorted-order

        Arrays        is a utility class offering a range of static method for array related operations
        Collections   is a utility class offering a range of static method to operate on lists,sets and maps

        java.lang.Comparable (i)
                is the natural comparision

            public abstract int compareTo(Object 0bj)

            class Employee implements Comparable<Employee> {
                private int empId;
                //fields

                @Override
                public int compareTo(Employee emp) {
                    //logic such that a -ve or a +ve or a zero is returned
                    //on comparing the current object 'this' with 'emp' 

                    return ((Integer)this.empId).compareTo(emp.empId);
                }
            }

            int x = emp1.compareTo(emp2)

            if x is 0 means emp1 is equals to emp2
            if x is -ve means emp1 is less than emp2
            if x is +ve means emp1 is greater than emp2

        java.util.Compartor (i)
                is the custom comparision
            public abstract int compare(Object 0bj1,Object obj2)

            class EmpFullnameComparator implements Compartor<Employee> {
                @Override
                public int compare(Employee emp1,Employee emp2) {
                    return emp1.getFullName().compareTo(emp2.getFullName());
                }
            }

             class EmpSalaryComparator implements Compartor<Employee> {
                @Override
                public int compare(Employee emp1,Employee emp2) {
                    return ((Double)emp1.getSalary()).compareTo(emp2.getSalary());
                }
            }

        Collections.sort(empList);  //natual comparision will work. meaning emps are sorted on empId
        Collections.sort(empList,new EmpFullnameComparator());  //custom comparision will work. meaning emps are sorted on fullName
        Collections.sort(empList,new EmpSalaryComparator());  //custom comparision will work. meaning emps are sorted on salary

        Concurrent Maps

            Concurrent maps solve data corruption due to Race Conditions in multi-threaded environments, by internally managing synchronization, allowing threads to operate on the map safely and efficiently.

            java.util.concurrent.ConcurrentHashMap offers
                Optimistic Reads
                'null' is not allowed neither as a key nor as a value
                Atomic Operations through (CAS - Compare-and-Swap), the following are a few
                    putIfAbsent(K key, V value)
                    computeIfAbsent(K key, Function mappingFunction)
                    replace(K key, V oldValue, V newValue)
            
            Alternatives to ConcurrentHashMap
                Hashtable
                    Single Global Lock: Every public method in Hashtable 
                    (including get()) is synchronized with the same lock.

                Collections.synchronizedMap(new HashMap())
                    This creates a synchronized wrapper around a HashMap and uses a single mutex/lock,
                    limiting concurrency to one thread at a time.

                ConcurrentSkipListMap
                    This is a concurrent implementation of the SortedMap and NavigableMap interfaces.
                    Uses a Skip List data structure, which provides O(log N) performance for most 
                    operations while supporting highly concurrent access. 
                    Used when we want to allow concurrency and sorted order.

    Funtional Interfaces and Lambda Expressions and Method Referencing and Streams API
    ----------------------------------------------------------------------------------------

        Functional programming allows to design a process as a chain of steps, where the output of
        one step will be input for the next step. Amd this is achived through chaining function calls.

        process ------> datasource.step1().step2().step3()

        this needs that we may have to pass one function as a param to another function. functional interfaces allows us to achive this.

        dataSourceOfEmployees
            .step1( anOperation )
            .step2( anotherOperation )

            operations are themselves  functions.

        Assuming we have a lis tof employees 'emps'. Now We need to compute the experience of each employee
        and we have to retrive t=only those employees having minimum 10 yrs iof experience.

            step1       compute experience for all employees
            step2       filter them based on the experience

            First Approach:

                for each employee
                    compute experience

                create a new-list

                for each employee
                    if emp.expr >=10 then
                        add the emp to a new list

            Second Approach:
                
                create a new-list
                
                for each employee
                    compute experience
                    if emp.expr >=10 then
                        add the emp to a new list

            Thrid Approach - Functional Programming

                emps.stream
                        .map            //to compute experience
                        .filter         //to check and filter on experiecne                        
                        .filter         //to check and filter on salary                        
                        .map            //to create fullName by adding first-name with last-name
                        .collect        //to collect only the filtered records into a new list

        Functional interface are those that have only one abstract emthod.

            @FuntionlInterface annotation is used for compiler-check.

            java.util.function  offers a list of functional interfaces. 
                
            Nomunculature:
                1. Supplier<T>      : T get()           has a method that returns a value but has no-args
                2. Consumer<T>      : void accept(T t)  has a method that takes an argument and returns void
                3. Function<T, R>   : R apply(T t)      has a method that takes an argument and returns a value
                4. Predicate<T>     : boolean test(T t) has a method that takes an argument and returns a boolean
            
            Functional interface are introduced to promote functional programming in java.

            Functional interfaces can be implemented using a inline-function-syntax called Lambda Expressions.

            FunctionInterface obj = (paramsList) -> returnValueExpression
            
            FunctionInterface obj = (paramsList) -> {
                //an implementation
                retrun value;
            }

        Method Referencing

            allows an object of functional interface to refer to any method whose signature matches
            with the signature of the method in the functional interface.

            interface Dummy {
                void doThis(Object);
            }        

            Dummy d = System.out::println ; // :: is scope resolution operator
            d.doThis("HGello"); // is same as System.out.priontln("Hello");

        Stream API

            Stream means flow of data.

            java.util.stream
                        |- Stream<T>
                        |- IntStream
                        |- FloatStream
                        |- DoubleStream
                            ....etc.,

            Stream s1 = Stream.of(val1,val2,val3,.....);
            Stream s2 = list.stream();
            Stream s3 = set.stream();
            Stream s4 = Arrays.stream(array);

            Note: A stream once consuemd can not be reused.

            Terminal Operations
                are methods of Stream class that do not return a new Stream
                after a terminal operation we can not chain another operation.

                forEach(consumer)       executes the consumer on each and every element of the stream
                                        and returns void.

                reduce(BinaryOperator)  executes the binary-operator cumilatively on all the elements of the stream
                                        returns the final result wrapped inside Optional class.

                                        java.util.Optional class is used to wrap a value or a null.

                    String<Integer> s1 = Stream.of(10,20,30,40,50);
                    BinaryOperator<Integer> sum =  (a,b) -> a+b ;
                    Optional<Integer> result = s1.reduce(sum); // sum(sum(sum(sum(10,20),30),40),50), so resutl is 150
                    Integer result = s1.reduce(sum,0); // sum(sum(sum(sum(sum(0,10),20),30),40),50), so resutl is 150

                collect(Collector)      Collector is an interface in java.util.stream that is used to read data
                                        from a stream and write it into any other data-source.

                                        Collectors.toSet()      returns an object Collector that can write into a set
                                        Collectors.toList()     returns an object Collector that can write into a list

                                        collect function takes a Collecotr and used it to convert a stream into a list
                                        or a set or any other data-source as per the Collector

            Intermdiate Operations
                are methods of Stream class that a new Stream
            
                filter(predicate)       this executes the predicate on each element of the existing stream,
                                        and returns a new stream having only those elements for whom the predicate
                                        returns true 
                                        
                map(operator)           map executes the operator on each element of the stream and returns a new stream having
                                        the all the results of the operator

                    String<Integer> s1 = Stream.of(10,20,30,40,50);                    
                    s1.map(x -> x*2) ----------> gives me a stream having 20,40,60,80,100

                flatMap(mapper)         this accepts a clustured or nested stream and is going to flatten it into a plain stream

                    List list = listOfLists.stream().flatMap(List::stream).collect(Collectors.toList());

    Mulit-Layer Arch
    --------------------------------------------------------------------------------------
        POJO        Plain Old Java Object, is any java class
                    having fields , getters and setters

        An application is divided into multiple layers where
        each layer has a specific task.

            Models          is any POJO that represents a domain-entity (Student, Employee, ..etc)
                            - the model must define constructors, setters and getters
                            - the model must override toString, equals and hashcode methods from Object class

            Entities        is any model that is mapped to a database-table using ORM.

            Services        is any POJO that offers bussiness-logic like computations, validations and any other
                            domain-specific-algorithms.

            Controllers     is any POJO that offers the flow-control in the app.

            UI              is any POJO that offers user-interface like accepting data ro commands from a 
                            end-user or displaying info or poutput to the end-user.

            DAO             Data-Access-Object that offers database related algorithms to
                            execute operations like insert, update, delete or retrive from a database
                            or any other data-source

            Repository      is a enhanced DAO. that has features like caching and ORM ...etc.,


            Database    <----->     APP     <------>    EndUsers

            Database    <----->[ DAO --(model)--- Service --(model)---- UI ]]<------>    EndUsers

            Mulit-Layer Arch is governed by S.O.L.I.D Principles

                S - Single Responsibility Principle
                    A unit of code (class) shall have only one resposibility (type of operations)
                    Service     handle only bussiness logic
                    DAO/Repo    handle only Database related logic
                    Controllers handle only flow-control related logic
                    UI          hanle only user-Interaction logic like input and output

                O - Open / Clsoed Principle
                    A unit of code must be
                        Open for extension and
                        Closed for altration                                       

                L - Liskov Substitution Principle
                    An object of a super-type must be substitutable by its sub-type.

                    Employee
                        | <- Manager
                        
                    Employee emp = new Manager(); 

                I - Interface Seggrigation Principle
                    An interface msut not force non-implementable behaviours on its sub-types.

                    interface Shape {
                        double gertArea();
                        double getPerimeter();
                        double getVolume();
                    }

                    Expecting Circle,Rectangle, Cuboid, Cylinder will be the sub-types

                    Circle and Rectangle has no implementation for 'volume' (not corrrect)
                    Cuboid and Cylinder has no implementation for 'perimeter' (not corrrect)

                    interface Shape {
                        double getArea();
                    } 

                    interface Shape2D extends Shape {
                        double getPerimeter();
                    }

                    interface Shape3D extends Shape {
                        double getVolume();
                    }

                D - Dependency Inversion Principle
                    A unit of code must depend on the abstraction but not concrete implmentation.

                    com.cts.hrapp.dao
                        class EmployeeDAO {
                            //offers methods to add,delete,update and retrive employees
                            //the implementation was made on JDBC-api
                        }

                    com.cts.hrapp.service
                        class EmployeeService {
                            private EmployeeDAO empDao;

                            public EmployeeService(){
                                this.empDao = new EmployeeDAO();
                            }
                        }

                    EmployeeService is tightly coupled with EmployeeDAO.
                    If we have to repalce jdbc-api with jpa-hibernate.
                    If a create a new EmployeeDAO2 that offers similar operatiosn but those operatiosn may or may not
                    match their signatures with the earlier implementation. If EmployeeDAO has 'addEmployee' method
                    EmployeeDAO2 may ahve 'createEmployee' method. Becasue of this
                    the Service class must be compeltly modified.

                    com.cts.hrapp.dao
                        interface EmployeeDAO {
                            //offers abstract methods to add,delete,update and retrive employees                            
                        }

                        class EmployeeDAOImpl implement EmployeeDAO {
                            //offers methods to add,delete,update and retrive employees
                            //the implementation was made on JDBC-api
                        }

                        class EmployeeDAOImpl2 implement EmployeeDAO {
                            //offers methods to add,delete,update and retrive employees
                            //the implementation was made on jpa and hibernate
                        }

                    com.cts.hrapp.service
                        interface EmployeeService {{

                        }

                        class EmployeeServiceImpl implements EmployeeService {
                            private EmployeeDAO empDao;

                            public EmployeeService(EmployeeDAO dao){
                                this.empDao = dao;
                            }
                        }
 
                    Now when we create a new EmployeeDAOImpl2 with jpa-logic, as that
                    as well implements the EmployeeDAO interface, it has to manditoryly offer
                    methods with the exact smae signature. The Service class need not be modfied.

    JUnit
    ----------------------------------------------------------------------------------
        JUnit is a widely used testing framework that provides annotations and assertions to structure your tests.

        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-engine</artifactId>
            <version>5.10.1</version>
            <scope>test</scope>
        </dependency>

        These annotations define the setup and teardown logic for tests:

            @Test: Marks a method as a test case to be executed.

            @BeforeEach: Runs before each test method in the class. Used for setting up common resources (e.g., creating fresh object instances).

            @AfterEach: Runs after each test method. Used for cleanup.

            @BeforeAll: Runs once before all test methods in the class. Must be a static method. Used for expensive setup that can be shared.

            @AfterAll: Runs once after all test methods in the class. Must be a static method. Used for shared cleanup.

            @DisplayName: Provides a more readable, descriptive name for a test class or method.

            @Disabled: Disables a test class or method, preventing it from running.

        Common Assertions

            Assertions are methods used to verify the expected result of a test:

            assertEquals(expected, actual): Checks if two values are equal.

            assertTrue(condition): Checks if a condition is true.

            assertFalse(condition): Checks if a condition is false.

            assertThrows(Exception.class, executable): Checks that the specified exception is thrown when the executable is run.

            assertNull(object) / assertNotNull(object): Checks if an object is null or not null.

    Mockito
    ----------------------------------------------------------------------------------

        Mockito is a popular Java mocking framework used to create mock objects for dependencies.

        <dependency>
            <groupId>org.mockito</groupId>
            <artifactId>mockito-core</artifactId>
            <version>5.8.0</version>
            <scope>test</scope>
        </dependency>

        <dependency>
            <groupId>org.mockito</groupId>
            <artifactId>mockito-core</artifactId>
            <version>5.8.0</version>
            <scope>test</scope>
        </dependency>

        Key Annotations
            @Mock: Creates a mock instance of a class or interface. This object is a simulated dependency whose behavior you control.

            @Spy: Creates a partial mock or spy of a real object. 
                    Real methods are called by default, but you can choose to stub specific methods.

            @InjectMocks: 
                Creates an instance of the class under test and automatically injects the fields annotated with @Mock or @Spy into it.

            @Captor: 
                Used to create an ArgumentCaptor instance, which captures arguments passed to a mocked method for later assertion.

            @ExtendWith(MockitoExtension.class): 
                The JUnit 5 annotation required to enable Mockito annotations (like @Mock and @InjectMocks) within the test class.

        Core Operations
            1. Stubbing (Defining Behavior)
                We tell the mock object how to behave when a specific method is called.

                    // Define what the mock will return when a method is called
                    when(mockDependency.someMethod()).thenReturn(expectedValue);

                    // Stubbing a method that returns void to throw an exception
                    doThrow(new IllegalStateException()).when(mockDependency).voidMethod();

            2. Verification (Checking Interaction)
                We verify that the class under test interacted with the mock dependency in the expected way.

                // Verify that 'someMethod()' was called exactly once
                verify(mockDependency).someMethod();

                // Verify that 'anotherMethod()' was called twice
                verify(mockDependency, times(2)).anotherMethod();

                // Verify that 'someMethod()' was never called
                verify(mockDependency, never()).someMethod();
            
        