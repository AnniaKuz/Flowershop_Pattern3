package data_sources.mongo_db;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import data_sources.TicketDataSource;
import entities.Invoice;
import entities.ProductDTO;
import entities.ProductType;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class TicketMongoDB implements TicketDataSource {

    private static TicketMongoDB instance;
    @Override
    public Integer addInvoice(Invoice invoice) {
        Integer id=getLastId()+1;
        invoice.setId(id);
        MongoDatabase database = MongoConnector.getConnection();
        MongoCollection collection = database.
                getCollection("invoices");
        Document document = new Document();
        document.append("id", invoice.getId());
        document.append("totalProducts", invoice.getTotalProducts());
        document.append("totalPrice", invoice.getTotalPrice());
        collection.insertOne(document);
        System.out.println("Invoice id: " +id);
        return id;
    }

    @Override
    public List<Invoice> getAll() {
        List<Invoice> invoices = new ArrayList<>();
        MongoDatabase database = MongoConnector.getConnection();
        MongoCollection collection = database.
                getCollection("invoices");

        MongoCursor<Document> cursor = collection.find().iterator();
        try {
            while(cursor.hasNext()) {
                Document document = cursor.next();
                Invoice invoice = new Invoice();
                invoice.setId((Integer)document.get("id"));
                invoice.setTotalProducts((Integer)document.get("totalProducts"));
                invoice.setTotalPrice((Double)document.get("totalPrice"));
                invoices.add(invoice);
            }
        } finally {
            cursor.close();
        }

        return invoices;
    }

    public Integer getLastId(){
        List<Invoice> invoices = getAll();
        if(!invoices.isEmpty()) {
            return invoices.get(invoices.size() - 1).getId();
        }
        return 0;
    }

    public static TicketMongoDB getInstance(){
        if(instance==null){
            instance=new TicketMongoDB();
        }
        return instance;
    }
}
