/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Client;
import entities.Reclamation;

import controller.InterfaceReclamation;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import technique.MyConnection;

/**
 *
 * @author Jendli
 */
public class ServiceReclamation implements InterfaceReclamation{
    
    MyConnection cnx;

    public ServiceReclamation() {
        cnx=MyConnection.getInstance();
    }

    @Override
    public void ajouterReclamation(Reclamation r, Client c) {
            
            int n=(int) (Math.random() * (888888888+1)+100000000);
            int row=rowCount()+1;
            String cd="REC-"+c.getNom().substring(0,3).toUpperCase()+"-"+n+"-"+row+"0";
            Calendar calendar = Calendar.getInstance();
            Date date= new Date(calendar.getTime().getTime());
        try {
            String req= "insert into reclamation (code,titre,description,dateReclamation,nom,mail,id_client) values (?,?,?,?,?,?,?)";
            PreparedStatement ps = cnx.getConnection().prepareStatement(req);
            ps.setString(1, cd);
            ps.setString(2, r.getTitre());
            ps.setString(3, r.getDescription());
            ps.setDate(4, date);
            ps.setString(5, c.getNom());
            ps.setString(6, c.getMail());
            ps.setInt(7, c.getId());
            ps.executeUpdate();
            System.out.println("Réclamation ajouté");
         } catch (SQLException ex) {
             System.out.println("Erreur d'ajout");
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimerReclamation(String code) {
        try {
            String req= "delete from reclamation where (code)= (?)";
            PreparedStatement ps = cnx.getConnection().prepareStatement(req);
            ps.setString(1,code);
            ps.executeUpdate();
            System.out.println("Réclamation supprimé");
         } catch (SQLException ex) {
             System.out.println("Erreur de suppression");
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifierReclamation(String code) {
        try {
            String query = "update reclamation set reponse = ? where code = ?";
            Statement s= cnx.getConnection().createStatement();
            PreparedStatement ps = cnx.getConnection().prepareStatement(query);
            ps.setString(1,"YES");  
            ps.setString(2, code);
            ps.executeUpdate();
            //System.out.println("Réclamation modifié");
            } catch (SQLException ex) {
             System.err.println("Erreur de modification");
             System.out.println(ex.getMessage());
             }
    }

    @Override
    public List<Reclamation> afficherListReclamations() {
         List<Reclamation>L=new ArrayList<>();
        try {
            String req1= "select * from reclamation ";
            Statement s= cnx.getConnection().createStatement();
            ResultSet rs = s.executeQuery(req1);
            while(rs.next())
            {
                //System.out.println("ID:"+rs.getInt("ID")+",Nom:"+rs.getString("Nom"));
                Reclamation r=new Reclamation();
                r.setCode(rs.getString("code"));
                r.setTitre(rs.getString("titre"));
                r.setDescription(rs.getString("description"));
                r.setDateReclamation(rs.getDate("dateReclamation"));
                r.setNomclient(rs.getString("nom"));
                r.setMailclient(rs.getString("mail"));
                r.setAnswer(rs.getString("reponse"));
                //r.setId_client(rs.getInt("id_client"));
                L.add(r);
            }
            } catch (SQLException ex) {
             System.err.println("Erreur d'affichage");
             System.out.println(ex.getMessage());
             }
       return L;
    }
    
    @Override
    public List<Client> afficherListReclamationsClient() {
         List<Client>L=new ArrayList<>();
        try {
            String req1= "select * from reclamation r ,client c where r.id_client=c.id ";
            Statement s= cnx.getConnection().createStatement();
            ResultSet rs = s.executeQuery(req1);
            while(rs.next())
            {
                Client c=new Client();
                c.setLogin(rs.getString("login"));
                c.setMail(rs.getString("mail"));
                c.setNom(rs.getString("Nom"));
                c.setPrenom(rs.getString("prenom"));    
                L.add(c);
            }
            } catch (SQLException ex) {
             System.err.println("Erreur d'affichage");
             System.out.println(ex.getMessage());
             }
       return L;
    }
   
    @Override
    public List<Reclamation> afficherMesReclamations(Client c) {
        List<Reclamation> L=new ArrayList<>();
        try {
            String query= "select * from reclamation "+"where (id_client)=(?) ";
            Statement s= cnx.getConnection().createStatement();
            PreparedStatement ps = cnx.getConnection().prepareStatement(query);
            ps.setInt(1,c.getId());
           
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {   Reclamation r=new Reclamation();
                r.setCode(rs.getString("code"));
                r.setTitre(rs.getString("titre"));
                r.setDescription(rs.getString("description"));
                r.setDateReclamation(rs.getDate("dateReclamation"));
                r.setId_client(rs.getInt("id_client"));
                L.add(r);
            }
            } catch (SQLException ex) {
             System.err.println("Erreur d'affichage");
             //System.out.println(ex.getMessage());
             }
             
       return L;
    }
    @Override
    public int rowCount() {
         
        try {
            String req1= "select count(*) as total from reclamation ";
            Statement s= cnx.getConnection().createStatement();
            ResultSet rs = s.executeQuery(req1);
            while(rs.next())
            {
               
                return rs.getInt("total");
            }
            } catch (SQLException ex) {
             System.err.println("Erreur d'affichage");
             System.out.println(ex.getMessage());
             }
       return 0;
    }
    
    
}
