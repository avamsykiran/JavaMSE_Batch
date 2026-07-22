import greet from './welcome';

console.log(greet("Vamsy Kiran"));
console.log(greet("Sagar Guru Charan","Dr. "));

import arraydemoFunction from './arrayDemo';
arraydemoFunction();

import checkPrime from './numOperations';

let [factors,numberType] = checkPrime(1024);
console.log(`factors of 1024 are ${factors} and it is a ${numberType}`);

import enumsDemofunction from './enumsDemo';
enumsDemofunction();

import { RegularEmployee, Manager } from './oopDemo'; 

var emp1 = new RegularEmployee("Vamsy","Aripaka","Flat No 123, Nidhi Apartments, Banglore",78900);
console.log(emp1);
console.log(emp1.netPay())

var emp2 = new RegularEmployee("Murthy","KGN",{city:"Banglore",doorNumber:"1-23",state:"Karnataka"},88900);
console.log(emp2);
console.log(emp2.netPay())

var mgr1 = new Manager("Kiran","V",{doorNumber:"1-55",city:"Hyd",state:"Telangana"},89000,8900);
console.log(mgr1);
console.log(mgr1.netPay())