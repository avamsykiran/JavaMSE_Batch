
console.log(typeof 34);
console.log(typeof 34.56);
console.log(typeof "Hello"); //dbl-quotes
console.log(typeof 'Hello'); //single-quotes
console.log(typeof `Hello`); //back-quotes
console.log(typeof true);
console.log(typeof null);
console.log(typeof undefined);
console.log(typeof []); //Array
console.log(typeof {}); //JSON
console.log(typeof function(){});

/*
Function is a type in javascript:
1. Functions can be assigned to a variable

    f1 = function(){
        console.log("Hello");
    }

    f1();

2. We can pass functions as paramaters to another function

    f1 = function(userName){
        console.log("Hello " + userName );
    }

    f2 = function(userName){
        console.log("Bye " + userName );
    }

    f3 = function(userName,greet){
        greet(userName);
    }

    f3("Vamsy",f1); // Hello Vamsy
    f3("Vamsy",f2); // Bye Vamsy 

3. A function can return another fucntion

    greetFactory = function(greeting) {
        return (
            function(userName) {
                console.log(greeting +" " + userName);
            }
        );
    }

    sayHello = greetFactory("Hello");
    sayWelcome = greetFactory("Welcome");
    sayBye = greetFactory("Bye");

    sayHello("Vamsy"); //Hello Vamsy
    sayWelcome("Vamsy"); //Welcome Vamsy
    sayBye("Vamsy"); //Bye Vamsy
*/
