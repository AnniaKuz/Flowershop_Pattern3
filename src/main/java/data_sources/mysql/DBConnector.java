package data_sources.mysql;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnector {

    private static Connection con;
    private  static  String url;
    private  static  String user;
    private  static  String pass;
    private static String driverClassName;

    static
    {
        Properties props = new Properties();
        FileInputStream fis = null;

        try {
            fis = new FileInputStream("src\\main\\resources\\mysql.properties");
            props.load(fis);
            url = props.getProperty("URL");
            user = props.getProperty("USERNAME");
            pass = props.getProperty("PASSWORD");
            driverClassName = props.getProperty("DRIVER_CLASS");


        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            Class.forName(driverClassName);
            con = DriverManager.getConnection(url, user, pass);
        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection()
    {
        return con;
    }
}
