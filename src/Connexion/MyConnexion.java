package Connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MyConnexion {
    /*
    private String url="jdbc:mysql://localhost:3306/bookstore";
    private String login="root";
    private String pwd="";
    private Connection connexion;
    private static MyConnexion instance;
    
    private MyConnexion()
    {
        
        try {
            connexion=DriverManager.getConnection(url, login, pwd);
            System.out.println("connexion établie");
        } catch (SQLException ex) {
            Logger.getLogger(MyConnexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        }
    
    public static MyConnexion getIstance()
    {
        if(instance==null)
            instance=new MyConnexion();
        
        return instance;
    }
    public Connection getConnection()
    {
        return connexion;
    }
    
    */
    public static Connection  connect()
    {
        try
	{  
            Class.forName("com.mysql.jdbc.Driver");  
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore","root",""); 
            System.out.println("Connexion effectué avec succée"); //message qui confirme la connexion avec la base de données
            return con;
	}
	catch(Exception e)
	{
            System.out.println("Error : "+e.getMessage());
            return null;
	}  
    }
    
    public static void main(String args[])
    {
        connect();
    }
}
