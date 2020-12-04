/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;


import entities.Client;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import technique.MyConnection;
import controller.InterfaceLogin_Register;
import entities.Admin;

/**
 *
 * @author Jendli
 */
public class ServiceLogin_Register implements InterfaceLogin_Register{
    
    MyConnection cnx;

    public ServiceLogin_Register() {
        cnx=MyConnection.getInstance();
    }

    @Override
    public Client seConnecter(String login,String mdp) {
        Client c;
        try {
            String query= "select * from client "+"where (login)=(?) and (mdp)=(?) ";
            Statement s= cnx.getConnection().createStatement();
            PreparedStatement ps = cnx.getConnection().prepareStatement(query);
            ps.setString(1,login);
            ps.setString(2,mdp);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {  c=new Client();
               c.setId(rs.getInt("id"));
               c.setLogin(rs.getString("login"));
               c.setMail(rs.getString("mail"));
               c.setNom(rs.getString("nom"));
               c.setPrenom(rs.getString("prenom"));
               return c;
            }
            } catch (SQLException ex) {
             System.err.println("Erreur de selection");
             System.out.println(ex.getMessage());
             }
        return null;
    }

    @Override
    public void registerClient(Client client) {
         try {
            String req= "insert into client (login,mail,mdp,nom) values (?,?,?,?)";
            PreparedStatement ps = cnx.getConnection().prepareStatement(req);
            ps.setString(1, client.getLogin());
            ps.setString(2, client.getMail());
            ps.setString(3, client.getMdp());
            ps.setString(4, client.getNom());
            ps.executeUpdate();
            System.out.println("Client Ajouté");
         } catch (SQLException ex) {
             System.out.println("Erreur d'ajout de client");
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public Admin seConnecterAdmin(String login, String mdp) {
       Admin admin;
        try {
            String query= "select * from admin "+"where (login)=(?) and (mdp)=(?) ";
            Statement s= cnx.getConnection().createStatement();
            PreparedStatement ps = cnx.getConnection().prepareStatement(query);
            ps.setString(1,login);
            ps.setString(2,mdp);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {  admin=new Admin();
               admin.setId(rs.getInt("id"));
               admin.setLogin(rs.getString("login"));
               admin.setMail(rs.getString("mail"));
               admin.setNom(rs.getString("nom"));
               admin.setPrenom(rs.getString("prenom"));
               return admin;
            }
            } catch (SQLException ex) {
             System.err.println("Erreur de selection");
             System.out.println(ex.getMessage());
             }
        return null;  
    }

   
    
    
    
}
