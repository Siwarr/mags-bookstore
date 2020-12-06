package bookstore.database;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author bondka
 */
public class Myconnection {
    private String url="jdbc:mysql://127.0.0.1:3306/bookstore";
    private String login="root";
    private String pwd="";
    private Connection connexion;
    private static Myconnection instance;
    
    private Myconnection()
    {
        try {
            connexion=DriverManager.getConnection(url, login, pwd);
            System.out.println("connection établie");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Myconnection getInstance()
    {
        if(instance==null)
            instance=new Myconnection();
        
        return instance;
    }
    public Connection getConnection()
    {
        return connexion;
    }
}