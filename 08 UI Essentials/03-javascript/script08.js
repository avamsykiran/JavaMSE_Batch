//Objects Literals (JSON)

var emp1 = {id:101,name:"Vamsy",sal:45600};
console.log(emp1);

//classes - ES6

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

var emp3 = new Emp(3,"Srinu",89000);
console.log(emp3);
console.log(emp3.ta())
console.log(emp3.totalPay())

//inheretence
class Manager extends Emp {
    constructor(id,name,sal,allowence){
        super(id,name,sal);
        this.allowence=allowence;
    }

    totalPay(){
        return this.sal+this.ta()+this.allowence;
    }
}

var emp4 = new Manager(4,"Indhikaa",89000,890);
console.log(emp4);
console.log(emp4.ta())
console.log(emp4.totalPay())
