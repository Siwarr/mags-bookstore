package bookstore.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bookstore.database.Myconnection;
import bookstore.entites.Client;
import bookstore.entites.Emprunt;
import bookstore.interfaces.InterfaceEmprunt;

public class ServiceEmprunt implements InterfaceEmprunt {
	Myconnection cnx;
	public ServiceEmprunt()
    {
        cnx=Myconnection.getInstance();
    }
	@Override
	public List<Emprunt> afficherEmprunt() {
    	List<Emprunt> l=new ArrayList<>();
        try {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            String req1= "select * from emprunt ";
            Statement s= cnx.getConnection().createStatement();
            ResultSet rs = s.executeQuery(req1);
            while(rs.next())
            {
            	Emprunt e=new Emprunt();
            	e.setBookName(rs.getString("bookName"));
            	e.setMemberName(rs.getString("memberName"));
            	e.setIssueTime(rs.getDate("issueTime"));
            	e.setRenew_count((rs.getInt("renew_count")));
            	
                l.add(e);
            }
        } catch (SQLException ex) {
            System.err.println("erreur affichage");
            System.out.println(ex.getMessage());
        }
        return l;
    }
	@Override
    public List<Emprunt> afficherMesEmprunt(Client c) {
    	List<Emprunt>list=new ArrayList<>();
    	System.out.println("client : "+c);
        try {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            String req1= "select * from emprunt "+"where (memberName)=(?) ";
            Statement s= cnx.getConnection().createStatement();
            PreparedStatement ps = cnx.getConnection().prepareStatement(req1);
            ps.setString(1,c.getName());
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
            	Emprunt e=new Emprunt();
            	e.setBookName(rs.getString("bookName"));
            	e.setMemberName(rs.getString("memberName"));
            	e.setIssueTime(rs.getDate("issueTime"));
            	e.setRenew_count((rs.getInt("renew_count")));
            	list.add(e);
            }
        } catch (SQLException ex) {
            System.err.println("erreur affichage");
            System.out.println(ex.getMessage());
        }
        return list;
    }
}
