/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magsbookstore.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import magsbookstore.entite.Client;
import magsbookstore.technique.myconnexion;

/**
 *
 * @author ghaith khiari
 */
public class ServiceCompteClient extends Client {
    
    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
    myconnexion cnx;
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
    public ServiceCompteClient()
    {
        cnx=myconnexion.getInstance();
        System.out.println("Connection établie");
    }
    @Override
    public void ajoutercompte(Client p) {
        try {
            
            String req= "INSERT INTO `compte` (`cin`,`nom`, `prenom`, `ville`, `code_postale`, `num_de_telf`, `date_inscri`) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement ps = cnx.getConnection().prepareStatement(req);
            ps.setString(1, p.getCin());
            ps.setString(2, p.getNom());
             ps.setString(3, p.getPrenom());
              ps.setString(4, p.getVille());
               ps.setInt(5, p.getCode_postale());
                ps.setLong(6, p.getCode_postale());
                 ps.setString(7, p.getDate_inscri());
            ps.executeUpdate(); 
        } catch (SQLException ex) {
            Logger.getLogger(myconnexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void afficher(Client c1) {
        List<Client> clients=new ArrayList();
        System.err.println(c1.getCin());
        try {
            
            String req1;
            req1 = "select * from compte where `cin` = "+c1.getCin();
            Statement s= cnx.getConnection().prepareStatement(req1);
           //s.setString(1,c1.getCin());
           
            ResultSet rs = s.executeQuery(req1);
            while(rs.next())
            {
                
                Client c = new Client();
                c.setId(rs.getInt("id"));
                c.setNom(rs.getString("nom"));
                c.setCode_postale(rs.getInt("code_postale"));
                c.setVille(rs.getString("ville"));
                c.setNum_de_telf(rs.getInt("num_de_telf"));
                c.setDate_inscri(formatter.format(rs.getDate("date_inscri")));
                clients.add(c);
            }
        } catch (SQLException ex) {
           Logger.getLogger(myconnexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(clients) ;      
    }
    
    
    @Override
    public void supprimercompte(Client p){
    
  
             try {
          
            String req= "DELETE FROM `compte` WHERE `cin` = (?)";
            PreparedStatement ps = cnx.getConnection().prepareStatement(req);
            ps.setString(1,p.getCin());
            
            ps.executeUpdate(); 
        } catch (SQLException ex) {
            Logger.getLogger(myconnexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("supprision terminer");
    } 
   // @Override
     public void modifiercompte(String nom , String pre , String ville, String pwd ,String cin ){
         
         try {
            String query = "update compte  set nom = ?, prenom = ? , ville = ? , pwd = ? where cin = ? ";
            Statement s= cnx.getConnection().createStatement();
            PreparedStatement ps = cnx.getConnection().prepareStatement(query);
             ps.setString(1,nom );
            ps.setString(2,pre);  
            ps.setString(3, ville);
           
             ps.setString(4,pwd );
             ps.setString(5,cin );
            ps.executeUpdate();
            System.out.println("compte modifié");
            } catch (SQLException ex) {
             System.err.println("Erreur de modification");
             System.out.println(ex.getMessage());
             }
        /* System.out.println("****Bienvenue au interface de modification****");
          System.out.println("****Merci de choisie ce que vous voulez modifier ****");
           System.out.println(""+"1: modifer votre nom  "
                   +"2:modifier votre prenom "+
                   "3 : numéro de votre téléphone "+
                   "4: votre ville"
                   + "5: votre mot de passe ");
           Scanner sc = new Scanner(System.in);
           int choix = sc.nextInt();
           String x= sc.nextLine();
         switch (choix){
             case 1 : 
                 System.out.println("enter votre nouveau nom ");
                 String nom =sc.nextLine();
                 c1.setNom(nom);
                 System.out.println(c1.getNom());
                  System.out.println(c1.getCin());
                 
                 
                     try {
            
            String req= "UPDATE `compte` SET `nom` = (?) WHERE `compte`.`cin` = (?);";
            PreparedStatement ps = cnx.getConnection().prepareStatement(req);
            ps.setString(1, c1.getNom());
             ps.setString(2, c1.getCin());
             
            ps.executeUpdate(); 
                         System.out.println("updated!!!");
        } catch (SQLException ex) {
            Logger.getLogger(myconnexion.class.getName()).log(Level.SEVERE, null, ex);
        }
                 
                  break;
                  
             
             case 2 :  
                 System.out.println("enter votre nouveau prenom ");
                 String prenom =sc.nextLine();
                 c1.setPrenom(prenom);
                 System.out.println(c1.getPrenom());
                  try {
            
            String req= "UPDATE `compte` SET `prenom` = (?) WHERE `compte`.`cin` = (?);";
            PreparedStatement ps = cnx.getConnection().prepareStatement(req);
            ps.setString(1, c1.getPrenom());
             ps.setString(2, c1.getCin());
             
            ps.executeUpdate(); 
                         System.out.println("updated!!!");
        } catch (SQLException ex) {
            Logger.getLogger(myconnexion.class.getName()).log(Level.SEVERE, null, ex);
        } 
                 break;
                  
                              
             
             case 3 :
                 System.out.println("enter votre nouveau numéro ");
                 long num =sc.nextLong();
                 c1.setNum_de_telf(num);
                 System.out.println(c1.getNum_de_telf());
                  try {
            
            String req= "UPDATE `compte` SET `num_de_telf` = (?) WHERE `compte`.`cin` = (?);";
            PreparedStatement ps = cnx.getConnection().prepareStatement(req);
            ps.setLong(1, c1.getNum_de_telf());
             ps.setString(2, c1.getCin());
             
            ps.executeUpdate(); 
                         System.out.println("updated!!!");
        } catch (SQLException ex) {
            Logger.getLogger(myconnexion.class.getName()).log(Level.SEVERE, null, ex);
        }
                  break;
                  
            
             
             case 4 : System.out.println("enter votre nouvelle ville  ");
                 String ville =sc.nextLine();
                 c1.setVille(ville);
                 System.out.println(c1.getVille());
                  try {
            
            String req= "UPDATE `compte` SET `ville` = (?) WHERE `compte`.`cin` = (?);";
            PreparedStatement ps = cnx.getConnection().prepareStatement(req);
            ps.setString(1, c1.getVille());
             ps.setString(2, c1.getCin());
             
            ps.executeUpdate(); 
                         System.out.println("updated!!!");
        } catch (SQLException ex) {
            Logger.getLogger(myconnexion.class.getName()).log(Level.SEVERE, null, ex);
        }
                  break;
                  case 5 : 
                 System.out.println("enter votre nouveau nom ");
                 String pwd =sc.nextLine();
                 c1.setPwd(pwd);
                 System.out.println(c1.getPwd());
                  System.out.println(c1.getCin());
                 
                 
                     try {
            
            String req= "UPDATE `compte` SET `pwd` = (?) WHERE `compte`.`cin` = (?);";
            PreparedStatement ps = cnx.getConnection().prepareStatement(req);
            ps.setString(1, c1.getNom());
             ps.setString(2, c1.getCin());
             
            ps.executeUpdate(); 
                         System.out.println("updated!!!");
        } catch (SQLException ex) {
            Logger.getLogger(myconnexion.class.getName()).log(Level.SEVERE, null, ex);
        }
                 
                  break;
         
         
         }*/
             
     
     
     
     
     }
   
    }
    


    
    
    

