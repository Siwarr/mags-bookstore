/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magsbookstore.technique;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ghaith khiari
 */
public class myconnexion {
    
    String url = "jdbc:mysql://localhost:3306/magsbookstore";
 String user = "root";
String pwd = "";
Connection conn; 
Statement ste;
public static myconnexion instance;

private myconnexion(){
    
        try {
            conn = DriverManager.getConnection(url, user, pwd);
            
        } catch (SQLException ex) {
            Logger.getLogger(myconnexion.class.getName()).log(Level.SEVERE, null, ex);
        }}

public static myconnexion getInstance (){
if (instance ==null) {
instance = new myconnexion()  ;}
return instance ;
}
public Connection getConnection () {

return conn;

}



}
