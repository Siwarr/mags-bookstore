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

/**
 *
 * @author bondka
 */
public class ServiceOuvrage implements InterfaceOuvrage {
    Myconnection cnx;
    int nbOuvrages=0;
    List<Ouvrage>ouvrages=new ArrayList<>();
    List<Client>clients=new ArrayList<>();
    List<Emprunt>emprunts=new ArrayList<>();
    

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
    
    /*

    @Override
    public List<Ouvrage> ouvrageData() {
        List<Ouvrage> lo=new ArrayList<>();
        try {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            String req1= "select * from ouvrage ";
            Statement s= cnx.getConnection().createStatement();
            //ArrayList<Personne> list = new ArrayList(50);
            ResultSet rs = s.executeQuery(req1);
            
            while(rs.next())
            {
                Ouvrage ouvrage=new Ouvrage();
                ouvrage.setAuthor(rs.getString("author"));
                ouvrage.setName(rs.getString("name"));
                ouvrage.setPrice(rs.getFloat("price"));
                ouvrage.setEdition(rs.getString("edition"));
                ouvrage.setIsAvail(rs.getBoolean("isAvail"));
                ouvrage.setQuantite(rs.getInt("quantite"));
                ouvrage.setDescription(rs.getString("description"));
                //System.out.println("id "+rs.getInt("id")+"author "+rs.getString("author")+"name"+rs.getString("name")+"price"+rs.getFloat("price")+"edition"+rs.getString("edition"));
                lo.add(ouvrage);
            }
        } catch (SQLException ex) {
            System.err.println("errrr");
        }
        return lo;
    }*/

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
    /*
    @Override
    public boolean modifier(int id, Ouvrage o) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try{
            Statement st= cnx.getConnection().createStatement();
            st.executeUpdate("UPDATE ouvrage SET Name='"+o.getName()+"',author='"+o.getAuthor()+"',edition='"+o.getEdition()+"',price='"+o.getPrice()+"',description='"+o.getDescription()+"',isAvail='"+o.getIsAvail()+"',quantite='"+o.getQuantite()+"',description='"+o.getDescription()+"' WHERE id="+id);
            return true;
            
        } catch (SQLException e) {
            //Logger.getLogger(ServiceOuvrage.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error : "+e.getMessage());
                return false;
        }
        
    }
*/
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
   /* @Override
    public void detailOuvrage(int id) {
    	
        try {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            String req1= "select * from ouvrage where id="+id;
            Statement s= cnx.getConnection().createStatement();
            ResultSet rs = s.executeQuery(req1);
            while(rs.next())
            {
            	System.out.println(rs.getInt("id")+" author: "+rs.getString("author")+" name: "+rs.getString("name")+" price: "+rs.getFloat("price")+" edition: "+rs.getString("edition")+" isAvail: "+rs.getBoolean("isAvail")+" quantite: "+rs.getInt("quantite")+" description: "+rs.getString("description"));
            }
        } catch (SQLException ex) {
            System.err.println("erreur affichage");
            System.out.println(ex.getMessage());
        }
    }*/

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
    
    public List<Emprunt> afficherEmprunt() {
        try {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            String req1= "select * from emprunt";
            Statement s= cnx.getConnection().createStatement();
            ResultSet rs = s.executeQuery(req1);
            while(rs.next())
            {
            	Emprunt e=new Emprunt();
            	e.setBookName(rs.getString("bookName"));
            	e.setMemberName(rs.getString("memberName"));
            	e.setIssueTime(rs.getDate("issueTime"));
            	e.setRenew_count((rs.getInt("renew_count")));
            	
                emprunts.add(e);
            }
        } catch (SQLException ex) {
            System.err.println("erreur affichage");
            System.out.println(ex.getMessage());
        }
        return emprunts;
    }
    public List<Emprunt> afficherMesEmprunt(Client c) {
    	List<Emprunt>empruntsClient=new ArrayList<>();

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
            	
            	empruntsClient.add(e);
            }
        } catch (SQLException ex) {
            System.err.println("erreur affichage");
            System.out.println(ex.getMessage());
        }
        return empruntsClient;
    }
}