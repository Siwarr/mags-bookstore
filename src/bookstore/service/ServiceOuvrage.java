package bookstore.service;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import bookstore.database.Myconnection;
import bookstore.entites.Client;
import bookstore.entites.Emprunt;
import bookstore.entites.Ouvrage;
import bookstore.interfaces.InterfaceOuvrage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.scene.chart.PieChart;

/**
 *
 * @author bondka
 */
public class ServiceOuvrage implements InterfaceOuvrage {
    Myconnection cnx;
    int nbOuvrages=0;
    List<Ouvrage>ouvrages=new ArrayList<>();
    List<Client>clients=new ArrayList<>();
    
    

	public List<Ouvrage> getOuvrages() {
		return ouvrages;
	}
	public void setOuvrages(List<Ouvrage> ouvrages) {
		this.ouvrages = ouvrages;
	}
	public ServiceOuvrage()
    {
        cnx=Myconnection.getInstance();
    }
    @Override
    public void ajouterOuvrage(Ouvrage o) {
        try {
            // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            String req= "insert into ouvrage (author,name,price,edition,isAvail,quantite,description) values (?,?,?,?,?,?,?)";
            PreparedStatement ps = cnx.getConnection().prepareStatement(req);
            ps.setString(1, o.getAuthor());
            ps.setString(2, o.getName());
            ps.setFloat(3, o.getPrice());
            ps.setString(4, o.getEdition());
            ps.setBoolean(5, o.getIsAvail());
            ps.setInt(6, o.getQuantite());
            ps.setString(7, o.getDescription());
            ps.executeUpdate();
            nbOuvrages++;
        } catch (SQLException ex) {
            System.err.println("erreur insertion");
        }
    }
    

    @Override
    public void supprimerOuvrage(Ouvrage o) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try {
		String req="delete from ouvrage where name=?";
        PreparedStatement ps = cnx.getConnection().prepareStatement(req);
		ps.setString(1, o.getName());
		ps.executeUpdate();
	} catch (Exception e) {
		System.out.println(e);
	}
    }

    @Override
    public void modifierOuvrage(Ouvrage o,Ouvrage v) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String req="update ouvrage set author=?,name=?,price=?,edition=?,isAvail=?,quantite=?,description=?  where name="+o.getName();

        try {
            PreparedStatement ps = cnx.getConnection().prepareStatement(req);
            ps.setString(1, v.getAuthor());
            ps.setString(2, v.getName());
            ps.setFloat(3, v.getPrice());
            ps.setString(4, v.getEdition());
            ps.setBoolean(5, v.getIsAvail());
            ps.setInt(6, v.getQuantite());
            ps.setString(7, v.getDescription());
            ps.setString(8, o.getName());
            ps.executeUpdate();
	} catch (Exception e) {
		System.out.println(e);
	}
    }
   
    @Override
    public List<Ouvrage> afficher() {
    	
        try {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            String req1= "select * from ouvrage ";
            Statement s= cnx.getConnection().createStatement();
            ResultSet rs = s.executeQuery(req1);
            while(rs.next())
            {
            	Ouvrage o=new Ouvrage();
            	o.setAuthor(rs.getString("author"));
            	o.setName(rs.getString("name"));
            	o.setPrice(rs.getFloat("price"));
            	o.setEdition(rs.getString("edition"));
            	o.setIsAvail(rs.getBoolean("isAvail"));
            	o.setQuantite(rs.getInt("quantite"));
            	o.setDescription(rs.getString("description"));
                ouvrages.add(o);
            }
        } catch (SQLException ex) {
            System.err.println("erreur affichage");
            System.out.println(ex.getMessage());
        }
        return ouvrages;
    }
    @Override
    public boolean SearchOuvrage(String name) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String req1= "select * from ouvrage where name= "+"'"+name+"'";
        Boolean flag=false;
        try {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            
            Statement s= cnx.getConnection().createStatement();
            ResultSet rs = s.executeQuery(req1);
            while(rs.next())
            {
                System.out.println("Succes de Recherche :\n id: "+rs.getInt("id")+" author: "+rs.getString("author")+" name: "+rs.getString("name")+" price: "+rs.getFloat("price")+" edition: "+rs.getString("edition")+" isAvail: "+rs.getBoolean("isAvail")+" quantite: "+rs.getInt("quantite")+" description: "+rs.getString("description"));
                flag=true;
                //return flag;
            }
        } catch (SQLException ex) {
            System.err.println("erreur de recherche");
        }
        return flag;        
    }
    
    public ObservableList<PieChart.Data> getBookGraphStat(){
    	ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
    	try {
    	String qu1="Select count(*) from ouvrage";
    	String qu2="Select count(*) from emprunt";
	    	Statement s= cnx.getConnection().createStatement();
			ResultSet rs = s.executeQuery(qu1);
			if(rs.next())
	        {
	        	int count= rs.getInt(1);
	        	data.add(new PieChart.Data("Total ouvrage ("+ count+ ")",count));
	        }
			ResultSet rs1 = s.executeQuery(qu2);
			if(rs1.next())
	        {
	        	int count= rs1.getInt(1);
	        	data.add(new PieChart.Data("Total emprunts ("+ count+ ")",count));
	        }
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return data;
    }
}