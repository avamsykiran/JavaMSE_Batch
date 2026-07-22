//Arrays

class Emp{
    constructor(id,name,sal){
        this.id=id;
        this.name=name;
        this.sal=sal;
    }

    ta(){
        return this.sal*0.2;
    }

    totalPay(){
        return this.sal+this.ta();
    }
}

emps = [
    new Emp(1,"Vamsy",12345),
    new Emp(2,"Suma",10345),
    new Emp(3,"Rani",22345),
    new Emp(4,"Vinay",2345),
    new Emp(5,"Latha",42345),
    new Emp(6,"Vasu",19345),
    new Emp(7,"Vijay",12375),
    new Emp(8,"Vikram",12348),
    new Emp(9,"Vinodh",42345),
    new Emp(10,"Zubera",92345)
];

console.log(emps);

emps.pop(); //removes the last inserted element.
console.log(emps);

emps.splice(4,2); //removes the 4th and 5th elements
console.log(emps);

emps.push(new Emp(11,"Koaml",78900));
console.log(emps);

emps.sort( (e1,e2) => e1.sal>e2.sal?1:(e1.sal<e2.sal?-1:0) );
console.log(emps);

console.log(emps.find(e => e.sal===2345));
console.log(emps.find(e => e.sal===45));

console.log(emps.findIndex(e => e.sal===2345));
console.log(emps.findIndex(e => e.sal===45));

emps.forEach( e => e.sal=e.sal+(e.sal*0.05) );
console.log(emps);

names = emps.map(e => e.name);
console.log(names)

console.log(emps.filter(e => e.sal >= 20000))

maxSalEmp = emps.reduce( (e1,e2) => e1.sal>e2.sal?e1:e2 )
console.log(maxSalEmp);