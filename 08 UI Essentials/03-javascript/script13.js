
var nums = [1,3,7,0]

for(let num of nums){
    try{
        if(num%2===0){
            console.log("I like it " + num);
        }else{
            throw "I dont like odd numbers";
        }
    }catch(err){
        console.error(err);        
    }
}