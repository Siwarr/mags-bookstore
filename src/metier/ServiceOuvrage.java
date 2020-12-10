/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package metier;

import Connexion.MyConnexion;
import entity.Ouvrage;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public class ServiceOuvrage 
{
    
    public ArrayList<Ouvrage> getAll()
    {
        try
        {
            ArrayList<Ouvrage> list = new ArrayList<Ouvrage>();
            Connection con = MyConnexion.connect();
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery("select * from ouvrage");  
            while(rs.next())  
            {
		Ouvrage o =new Ouvrage();
                System.out.println("Id : "+rs.getInt("id"));
                System.out.println("Name  : "+rs.getString("name"));
		o.setId(rs.getInt("id"));
                o.setName(rs.getString("name"));
                o.setEdition(rs.getString("edition"));
                o.setQuantite(rs.getInt("quantite"));
                
		list.add(o);
            }
            con.close();
            return list;
        }
        catch(Exception e)
        {
            System.out.println("Error : "+e.getMessage());
            return null;
        }
    }
    
    public void method()
    {
        System.out.println("Hello");
    }
}

