package data_sources.mongo_db;


import com.mongodb.DB;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoClients;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MongoConnector {
    private static MongoDatabase database;
    private static MongoClient mongoClient;
    //private  static String host;
    //private static String port;
    private static String url;
    private  static String dataBaseName;

    static
    {
        Properties props = new Properties();
        FileInputStream fis = null;

        try {
            fis = new FileInputStream("src\\main\\resources\\mongo.properties");
            props.load(fis);
           // port = props.getProperty("PORT");
            //host = props.getProperty("HOST");
            url = props.getProperty("URL");
            dataBaseName = props.getProperty("DB_NAME");



        } catch (IOException e) {
            e.printStackTrace();
        }

        mongoClient = MongoClients.create(url);
        database =  mongoClient.getDatabase(dataBaseName);


    }
    public static MongoDatabase getConnection()
    {
        return database;
    }

}
