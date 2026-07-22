
function myFunction(){
    a = 45; //hoisting
    var b = 46; //function scoped
    console.log(a);
    console.log(b);
    if(true){
        var c = 47; //function scoped
        let d = 48; //block scoped
        console.log(c);
        console.log(d);
    }
    console.log(c);
    //console.log(d);
}

myFunction();

console.log(a);
//console.log(b);