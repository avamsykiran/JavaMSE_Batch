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
                
        Polymorphsim

            overlaoding
                method of the smae scope having same name but different arg-list.

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

                methods from a super and a sub type, have exactly the same signature (retrunType anme and arg-list)

                class Human extends Monkey {
                    public void eat(Fruit fruit){
                        wash(fruit);
                        Piece[] pieces = cut(fruit);
                        for(Piece p : pieces{
                            byteAndChewAndSwallow(p);
                        }
                    }
                   
                }

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

            To Create a UserDefiendType
                        ↓
                        Does it has fields ?    ------- NO ----> create UDT as interface
                                |
                                | YES
                                ↓         
                                create UDT as class

            Abstract Method         is a method that does not have an implementation.
                                    only abstract classes and interfaces can have an abstract method.

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
        StringBuilder
        StringBuffer

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
    
        CHECKED_Exceptions      are supposed to be handled using try..cath or are supposed to be thrown to 
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
                //is used to ensure that any closable onbjects are closed.
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
            |     | <- class HashSet            order of retrival is non-pridictable
            |     | <- class LinkedHashSet      order of retrival is entry-order
            |     | <- class TreeSet            order of retrival is sorted-order

           Map (interface)              represents a collection of key-value
            |                           put,set,get,keySet,valueSet,containsKey,containsValue,size
            |
            |<- class HashMap           order of retrival is non-pridictable
            |<- class LinkedHashMap     order of retrival is entry-pridictable
            |<- class TreeMap           order of retrival is sorted-pridictable

        Arrays                          is a utility class offering a range of static method for array related operations
        Collections                     is a utility class offering a range of static method to operate on lists,sets and maps

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

            class EmpoyeeFullnameComparator implements Compartor<Employee> {
                @Override
                public int compare(Employee emp1,Employee emp2) {
                    return emp1.getFullName().compareTo(emp2.getFullName())
                }
            }



    Mulit-Layer Arch

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

