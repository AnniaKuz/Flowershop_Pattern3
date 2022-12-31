package data_sources.mongo_db;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.result.DeleteResult;
import entities.ProductType;
import org.bson.Document;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import data_sources.ProductDataSource;
import data_sources.mysql.ProductMySQL;
import entities.ProductDTO;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class ProductMongoDB implements ProductDataSource <ProductDTO>{
    private  static ProductMongoDB instance;

    @Override
    public Integer add(ProductDTO product) {
        Integer id=getLastId()+1;
        product.setId(id);
        MongoDatabase database = MongoConnector.getConnection();
        MongoCollection collection = database.
                getCollection("products");
        Document document = new Document();
        document.append("id", product.getId());
        document.append("type", product.getProductType().toString());
        document.append("price", product.getPrice());
        document.append("info", product.getInfo());
        collection.insertOne(document);
        System.out.println("Id: "+id);
        return id;
    }

    @Override
    public void remove(Integer id) {
        MongoDatabase database = MongoConnector.getConnection();
        MongoCollection collection = database.
                getCollection("products");

        Bson query = eq("id", id);
        try {
            DeleteResult result = collection.deleteOne(query);
            System.out.println("Deleted document count: " + result.getDeletedCount());
        } catch (MongoException me) {
            System.err.println("Unable to delete due to an error: " + me);
        }

    }

    @Override
    public List<ProductDTO> getAll() {
      List<ProductDTO> products = new ArrayList<>();
        MongoDatabase database = MongoConnector.getConnection();
        MongoCollection collection = database.
                getCollection("products");

        MongoCursor<Document> cursor = collection.find().iterator();
        try {
            while(cursor.hasNext()) {
                Document document = cursor.next();
                products.add(new ProductDTO(document.getInteger("id"), ProductType.valueOf(document.getString("type")),document.getDouble("price"),
                        document.get("info")));
            }
        } finally {
            cursor.close();
        }


        return products;
    }

    @Override
    public ProductDTO getProductById(Integer id) {
        return null;
    }

    public Integer getLastId(){
        List<ProductDTO> products = getAll();
        if(!products.isEmpty()) {
            return products.get(products.size() - 1).getId();
        }
        return 0;
    }

    public static ProductMongoDB getInstance(){
        if(instance==null){
            instance=new ProductMongoDB();
        }
        return instance;
    }
}
