package bookstore.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bookstore.database.Myconnection;
import bookstore.entites.Client;
import bookstore.entites.Ouvrage;
import bookstore.interfaces.InterfaceClient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

public class ServiceClient implements InterfaceClient{
	Myconnection cnx;
    List<Client>clients=new ArrayList<>();
	public ServiceClient()
    {
        cnx=Myconnection.getInstance();
    }
	@Override
    public void ajouterClient(Client c) {
        try {
            // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            String req= "insert into client (id,name,email,tel,pwd) values (?,?,?,?,?)";
            PreparedStatement ps = cnx.getConnection().prepareStatement(req);
            ps.setString(1, c.getId());
            ps.setString(2, c.getName());
            ps.setString(3, c.getEmail());
            ps.setString(4, c.getTel());
            ps.setString(5, c.getPwd());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("erreur insertion");
        }
    }
    
	@Override
    public List<Client> afficherClients() {
    	
        try {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            String req1= "select * from client ";
            Statement s= cnx.getConnection().createStatement();
            ResultSet rs = s.executeQuery(req1);
            while(rs.next())
            {
            	Client c=new Client();
            	c.setId(rs.getString("id"));
            	c.setName(rs.getString("name"));
            	c.setTel(rs.getString("tel"));
            	c.setEmail(rs.getString("email"));
            	
                clients.add(c);
            }
        } catch (SQLException ex) {
            System.err.println("erreur affichage");
            System.out.println(ex.getMessage());
        }
        return clients;
    }
	@Override
    public ObservableList<PieChart.Data> getMembreGraphStat(){
    	ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
    	try {
    	String qu1="Select count(*) from client";
    	String qu2="Select count(DISTINCT memberName) from emprunt";
	    	Statement s= cnx.getConnection().createStatement();
			ResultSet rs = s.executeQuery(qu1);
			if(rs.next())
	        {
	        	int count= rs.getInt(1);
	        	data.add(new PieChart.Data("Total clients ("+ count+ ")",count));
	        }
			ResultSet rs1 = s.executeQuery(qu2);
			if(rs1.next())
	        {
	        	int count= rs1.getInt(1);
	        	data.add(new PieChart.Data("Total clients avec emprunts ("+ count+ ")",count));
	        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return data;
    }
    
}
