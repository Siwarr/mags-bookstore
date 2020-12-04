/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package technique;


import java.sql.*;



/**
 *
 * @author Jendli
 */
public class MyConnection {
    
   // private String url="jdbc:mysql://localhost:3307/basepersonne?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private String url="jdbc:mysql://localhost:3306/bookstore";
    private String login="root";
    private String pwd="";
    private Connection cnx;
    private static MyConnection instance;
    
    private MyConnection() {
        try {
           cnx=DriverManager.getConnection(url,login,pwd);
            System.out.println("Connexion établie");
        } catch (SQLException e) {
            System.out.println("Erreur de connexion");
            System.out.println(e.getMessage());
        }
  
    }

    public static MyConnection getInstance() {
        if(instance==null)
            instance=new MyConnection();
        return instance;
    }
    
    public Connection getConnection()
    {
        return cnx;
    }
    
    public void closeConnection(){
        try {
            cnx.close();
            System.out.println("Connexion fermé");
        } catch (SQLException ex) {
            System.out.println("Erreur de fermeture");
            System.out.println(ex.getMessage());
        }
    }
}
