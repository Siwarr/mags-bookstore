package metier;

import Connexion.MyConnexion;
import entity.Categorie;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
public class ServiceCategorie 
{
    
    //Ajouter categorie
    public boolean CreeCategorie(Categorie c)   
    {
        try
        {
            Statement st = MyConnexion.connect().createStatement();
            //Apres la declaration de stattemnt on doit ecrire la requette
            st.executeUpdate("insert into categorie value(null,'"+c.getLib()+"','"+c.getDescription()+"')");
            MyConnexion.connect().close();
            return true;
        }
        catch(Exception e)
        {
            System.out.println("Error : "+e.getMessage());
            return false;
        }
    }
    
    //supprimer categorie
    public boolean deleteCategorie(int id )
    {
        try
        {
            Statement st = MyConnexion.connect().createStatement();
            st.executeUpdate("delete from ouvrage where id_cat="+id+";");
            st.executeUpdate("delete from categorie where id="+id+";");
            return true;
        }
        catch(Exception e)
        {
            System.out.println("Error : "+e.getMessage());
            return false;
        }
    }
    
    //hethi fi sercvice c
    //afficher categorie
    
    public ArrayList<Categorie> getAll()
    {
        try
        {
            ArrayList<Categorie> list = new ArrayList<Categorie>();
            Connection con = MyConnexion.connect();
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery("select * from categorie");  
            while(rs.next())  
            {
		Categorie c =new Categorie();
		c.setId(rs.getInt("id"));
                c.setLib(rs.getString("lib"));
                c.setDescription(rs.getString("description"));
		list.add(c);
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
    
    
    //chercher categorie
    public Categorie findCategorieById(int id)
    {
        try
        {
            Connection con = MyConnexion.connect();
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery("select * from categorie where id="+id);  
            Categorie c =new Categorie();
            while(rs.next())  
            {
		c.setId(rs.getInt("id"));
                c.setLib(rs.getString("lib"));
                c.setDescription(rs.getString("description"));
                break;
            }
            con.close();
            return c;
        }
        catch(Exception e)
        {
            System.out.println("Error : "+e.getMessage());
            return null;
        }
    }
    
    //modif categorie 
    
    public boolean modifier(int id, Categorie c)
    {
        try
        {
            Statement st = MyConnexion.connect().createStatement();
            st.executeUpdate("UPDATE categorie SET lib='"+c.getLib()+"',description='"+c.getDescription()+"' WHERE id="+id);
            return true;
        }
        catch(Exception e)
        {
            System.out.println("Error : "+e.getMessage());
            return false;
        }
    }
}