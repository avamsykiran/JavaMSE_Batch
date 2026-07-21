MongoDB
---------------------------------------------------------

    What is MongoDB?
        NoSQL Database Management System

    Database Management System:
        Data Availablity
        Data Consistency
        Reliability
        Transaction Atomicity and Management

    Database Models
        Flat File Data Models               .csv,.xml,.json
        Network Data Models
        Hyrarchial Data Models
        Relational Data Models              oracle,ms access, ms sql server,...etc

    Graph Representation of Data         Hyrarchial Data Models
    ------------------------------------------------------------------

            D2H Portal
            -------------
                    Channel
                    Packages
                    Subscriber

                    Subscriber
                        has multiple Packages
                                        has a group of Channels

                    Subscribers: [{
                        subId
                        subName
                        Packages : [{
                            packId:
                            activationDate:
                            validTillDate:
                            channels:[{

                            }]
                        }]

                    }]

    Hyrarchial Data Model
    ----------------------[ noSQL ]

        MongoDB
        ChacheDB
        ....etc

    Lab Setup
    -----------------

        MongodB Server
        MongodB Client
        MongoDb Compass

                https://fastdl.mongodb.org/windows/mongodb-windows-x86_64-5.0.1-signed.msi
                https://fastdl.mongodb.org/osx/mongodb-macos-x86_64-5.0.2.tgz

    Start Your Server:
                            mongod --version
                            mongod --dbpath ./data
                            
    Start Your Commad Line Client
                            mongo
    
    GUI     Mongo Compass

    MongodB DB structure                        RDBMS DB Structure 
    --------------------------------------------------------------------
    Database                                        Database
        Collection                                      Table
            Docuemnts                                       Record/Row/Tuple
                Fields and Document                                 Attributes/Col/Fields


    MongoDB is inspired by JSON and uses the same syntax to represent documnets (entities).
    But MongoDB calls this notation as BSON (Binary Script Object Notation)

    The JSON notation is 
        1. the object starts with { and ends with }
        2. each field and value are represented as fieldName:value
        3. the field value pairs are separated with a comma
        4. Sub-Objects (sub-entites/sub-documents) are enclosed within [] ([] represent an array).

    example:
            {
                "shcoolId":101,
                "title":"Jt. Josephs High School",
                "location":"Madhurawada,Vizag",
                "staff":[
                    {
                        "staffId":1,
                        "name":"Shekar Kammula",
                        "subject":"Maths"
                    },
                    {
                        "staffId":2,
                        "name","Padmaja",
                        "subject":"Physics",
                        "inchargeOf":"Class 10"
                    }
                ]
            }

    MongoDB Commands
    ---------------------------------------------------------------------

    use DATABASE_NAME               create or switch to a database
    db                              show the current database
    show dbs                        show all databases ; and a db is not visible unless
                                    it has atleast one document.
    db.dropDatabase()               is used to drop a existing database.

    db.createCollection(name)       is used to create a collection
    show collections                is used to list all the collections in the current db
    db.COLLECTION_NAME.drop()       is used to delete a collection completly

    db.COLLECTION_NAME.insertOne({})               one record will be inserted
    db.COLLECTION_NAME.insertMany([{},{},{}])       bulk insertions
    
    db.COLLECTION_NAME.insert({})
    db.COLLECTION_NAME.insert([{},{},{}])

    db.COLLECTION_NAME.update({"key":"value"}, {$set{"key":"newValue"}})                
        updates first matching record

    db.COLLECTION_NAME.findOneAndUpdate({"key":"value"}, {$set{"key":"newValue"}})
    db.COLLECTION_NAME.updateOne({"key":"value"}, {$set{"key":"newValue"}})
    db.COLLECTION_NAME.update({"key":"value"}, {$set{"key":"newValue"}},{multi:true})    
        updates all matching records
    
    db.COLLECTION_NAME.updateMany({"key":"value"}, {$set{"key":"newValue"})

    db.COLLECTION_NAME.save({_id:idValue,NEW_DATA})                                     
        replaces the entire record

    db.COLLECTION_NAME.remove({})                               remove all documents
    db.COLLECTION_NAME.remove({key:value})                      remove all matching documents
    db.COLLECTION_NAME.remove({key:value},1)                    remove first matching document
    db.COLLECTION_NAME.remove({key:value},{justOne:1})          remove first matching document

    db.COLLECTION_NAME.find().pretty()
    db.COLLECTION_NAME.find({})                     find by example 
    db.COLLECTION_NAME.find({},{KEY:0/1})           projection of needed fields only
    db.COLLECTIONNAME.findOne({})                   retrive only first matching deocuemnt
    db.COLLECTION_NAME.find().limit(NUMBER)         limiting
    db.COLLECTION_NAME.find().limit(NUMBER).skip(NUMBER)
    db.COLLECTION_NAME.find().sort({KEY:1/0/-1})

    Note:

        _id field
            each document (record) is uniqly identified by "_id" field
            if "_id" is absent in a record while inserting, an "_id" is auto-generated with
            a datatype called ObjectID
    
    Opertion Cluases

        Operation	            Syntax	
        ============================================================================
        Equality	            {<key>:<value>}	
        Less Than	            {<key>:{$lt:<value>}}
        Less Than Equals	    {<key>:{$lte:<value>}}
        Greater Than	        {<key>:{$gt:<value>}}	
        Greater Than Equals	    {<key>:{$gte:<value>}}	
        Not Equals	            {<key>:{$ne:<value>}}	
        Values in an array	    {<key>:{$in:[<value1>, <value2>,……<valueN>]}}	
        Values not in an array	{<key>:{$nin:<value>}}	

        And                     { $and: [ {<key1>:<value1>}, { <key2>:<value2>} ] }
        Or                      { $or: [ {<key1>:<value1>}, { <key2>:<value2>} ] }
        Not                     { $NOT: [ {key1: value1}, {key2:value2} ] }

    //lets create a bulky collection to learn retrivals

    db.goods.insert([
        {_id:1,title:"Rice",unit:"25kg Bag",rate:2500,category:"CERALS"},
        {_id:2,title:"Palm Oil",unit:"1 Kg Packet",rate:500,category:"OIL"},
        {_id:3,title:"Olive Oil",unit:"1 Kg Packet",rate:5100,category:"OIL"},
        {_id:4,title:"Urd Dal",unit:"1 Kg Packet",rate:56,category:"PULSES"},
        {_id:5,title:"Channa Dal",unit:"1 Kg Packet",rate:67,category:"PULSES"},
        {_id:6,title:"Groudn Nuts",unit:"1 Kg Packet",rate:80,category:"PULSES"},
        {_id:7,title:"Wheat Flour",unit:"1 Kg Packet",rate:120,category:"FLOURS"},
        {_id:8,title:"Horlicks",unit:"1 Kg Bottle",rate:560,category:"BEVERAGES"},
        {_id:9,title:"Boost",unit:"1 Kg Bottle",rate:760,category:"BEVERAGES"},
        {_id:10,title:"Coke",unit:"600ml Can",rate:100,category:"BEVERAGES"},
        {_id:11,title:"Pepsi",unit:"600ml Can",rate:100,category:"BEVERAGES"},
        {_id:12,title:"Coke",unit:"1.5ltr Bottle",rate:100,category:"BEVERAGES"},
        {_id:13,title:"Pepsi",unit:"1.5ltr Bottle",rate:100,category:"BEVERAGES"},
        {_id:14,title:"Boat Mango",unit:"250ml Pack",rate:500,category:"BEVERAGES"},
        {_id:15,title:"Boat Multi Fruit",unit:"250ml Pack",rate:500,category:"BEVERAGES"},
        {_id:16,title:"Glucose",unit:"500g Pack",rate:500,category:"BEVERAGES"},
        {_id:17,title:"Vermicelli",unit:"500g Packet",rate:450,category:"OTHERS"},
        {_id:18,title:"Popcorn",unit:"50g Packet",rate:110,category:"OTHERS"},
        {_id:19,title:"Gulab Jamun Mix",unit:"500g Packet",rate:220,category:"OTHERS"},
        {_id:20,title:"Curd",unit:"500g Packet",rate:45,category:"OTHERS"}
    ])

    db.goods.find().limit(5)
    db.goods.find().limit(5).skip(5)
    db.goods.find().skip(db.goods.count()-5)
    db.goods.find({$and: [{rate:{$gt:100}},{category:"BEVERAGES"}] })
    db.goods.find({$and: [{rate:{$gt:70}},{rate:{$lt:700}},{category:{$in:["OIL","PULSES"]}}] })
    
    db.goods.find(
        {$or: 
            [ 
                {$and: [{category:"OIL"},{rate:{$lt:1000}}]},
                {$and: [{category:"PULSES"},{rate:{$lt:100}}]}, 
                {$and: [{category:"BEVERAGES"},{rate:{$gt:100}}]},
                {$and: [{category:"OTHERS"},{rate:{$lt:200}}]}
            ]
        }
    );

    db.goods.find(
        {$or: 
            [ 
                {$and: [{category:"OIL"},{rate:{$lt:1000}}]},
                {$and: [{category:"PULSES"},{rate:{$lt:100}}]}, 
                {$and: [{category:"BEVERAGES"},{rate:{$gt:100}}]},
                {$and: [{category:"OTHERS"},{rate:{$lt:200}}]}
            ]
        },
        {_id:0,title:1,category:1,rate:1}
    );

    db.goods.find(
        {$or: 
            [ 
                {$and: [{category:"OIL"},{rate:{$lt:1000}}]},
                {$and: [{category:"PULSES"},{rate:{$lt:100}}]}, 
                {$and: [{category:"BEVERAGES"},{rate:{$gt:100}}]},
                {$and: [{category:"OTHERS"},{rate:{$lt:200}}]}
            ]
        },
        {_id:0,title:1,category:1,rate:1}
    ).sort({category:1,rate:1});
    
    Aggregation Framework
    ==================================================================================================

        db.COLLECTION_NAME.aggregate(AN_ARRAY_OF_AGGREGATE_STAGES)
        
        Stage Pipeline Operators
        ---------------------------------------------------------------------------------------------------
        $match          filtering  {expr: {filter expression}}
        $sort           sorting
        $group          grouping
        $limit          limiting
        $skip           skiping for pagiantions
        $project        projection

        $group Operation	            Syntax	
        ---------------------------------------------------------------------------------------------------
        $sum	                db.mycol.aggregate([{$group : {_id : "$grpcol", sumCol : {$sum : "$col"}}}])
        $avg	                db.mycol.aggregate([{$group : {_id : "$grpCol", avgCol : {$avg : "$col"}}}])
        $min	                db.mycol.aggregate([{$group : {_id : "$grpCol", minCol : {$min : "$col"}}}])
        $max	                db.mycol.aggregate([{$group : {_id : "$grpCol", maxCol : {$max : "$col"}}}])
                    
        db.goods.aggregate([{$group : {_id : "$category", totalRate : {$sum : "$rate"}}}])
        db.goods.aggregate([{$group : {_id : "$category", avgRate : {$avg : "$rate"}}}])
        db.goods.aggregate([{$group : {_id : "$category", minRate : {$min : "$rate"}}}])
        db.goods.aggregate([{$group : {_id : "$category", maxRate : {$max : "$rate"}}}])

        db.goods.aggregate([
            {$group : {_id : "$category", maxRate : {$max : "$rate"}}},
            {$sort:{maxRate:1}}
        ]);

        db.goods.aggregate([
                {$match:{rate:{$gte:150}}},
                {$group:{_id:"$category",sumRate:{$sum:"$rate"},avgRate:{$avg:"$rate"}}} ,
                {$match:{avgRate:{$gte:2000}}},
                {$project:{avgRate:1}}
        ]);

        db.goods.aggregate([{$group : {_id : "$unit", count : {$sum : 1}}}]);

        db.goods.aggregate([{$group : {_id : {category:"$category",unit:"$unit"}, count : {$sum : 1}}}]);

    