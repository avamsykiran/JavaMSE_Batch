//Template Literals / Interpolation

let c1 = {cid:101,name:'Vamsy',mobile:'9052224753',email:'vamsy@gmail.com',dob:new Date('1987-01-01')};
console.log("Hello "+c1.name+"! It is good to call you at "+c1.mobile);
console.log(`
        Hello ${c1.name}! 
        It is good to call you at ${c1.mobile}
        `);

//Destructured assignment

//let name = c1.name;
//let mobile = c1.mobile;

var {name,mobile} = c1;
console.log(`Hello ${name}! It is good to call you at ${mobile}`);

/*const printAge = (c) => {
    let age = (new Date()).getFullYear()-c.dob.getFullYear();
    console.log(`Hello ${c.name}! You are ${age} years old`);
};
printAge(c1);
*/
/*
const printAge = (name,dob) => {
    let age = (new Date()).getFullYear()-dob.getFullYear();
    console.log(`Hello ${name}! You are ${age} years old`);
};

printAge(c1.name,c1.dob);
*/

const printAge = ({name,dob}) => {
    let age = (new Date()).getFullYear()-dob.getFullYear();
    console.log(`Hello ${name}! You are ${age} years old`);
};

printAge(c1);

var nums=[1,2,3,4,5,6];
var [x,y,,z] = nums;

console.log(`x=${x} , y=${y}, z=${z}`)