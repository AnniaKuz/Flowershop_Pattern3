use ("flowershop");

db.createCollection("products", {
    validator: {
      $jsonSchema: {
        bsonType: 'object',
        required: ['id','type','info','price'],
        properties: {
          id:{
            bsonType: 'number'
          },
          type:{
            enum: ['FLOWER','TREE','DECO']
          },
          info:{
            bsonType: 'string'
          },
          price:{
            bsonType:'number'
          }
        }
      }
    }
  });


  db.createCollection("invoices",{
    validator: {
        $jsonSchema: {
            bsonType: 'object',
            required: ['id', 'totalProducts', 'totalPrice'],
            properties: {
                id:{
                    bsonType: 'number'
                },
                totalProducts:{
                    bsonType: 'number'
                },
                totalPrice:{
                    bsonType: 'number'
                }
            }
        }
    }
  })
  