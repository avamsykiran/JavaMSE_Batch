//Functions Demo

function greet(){
    console.log("Hello");
}

const greetUser = function(user){
    console.log("Hello "+user);
}

//ECMA Script - ES6

const welcome = () => "Hai! You are welcome!"; //arrow function having no-args and returns a string

const welcomeUser = user => "Hello! "+user+" you are welcome"; //arrow function having an arg and returns a string

const greetByTime = () => { //arrow function having no-arg and returns a string and has a complex implementation
    var h = (new Date()).getHours();
    
    var greeting="";

    if(h>=3 && h<=11) greeting="Good Morning";
    else if(h>=12 && h<=16) greeting="Good Noon";
    else greeting="Good Evening";

    return greeting;
};

const simpleInterest = (p,t,r) => ((p*t*r)/100); //arrow function having 3 arg and returns a number

greet();
greetUser("Vamsy");

console.log(welcome());
console.log(welcomeUser("Vamsy"));
console.log(greetByTime())
