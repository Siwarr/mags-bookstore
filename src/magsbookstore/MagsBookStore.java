/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magsbookstore;

import java.sql.Date;
import java.text.SimpleDateFormat;
import magsbookstore.Service.ServiceCompte;
import magsbookstore.Service.ServiceCompteClient;
import magsbookstore.entite.Client;

/**
 *
 * @author ghaith khiari
 */
public class MagsBookStore {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
Date date = new Date(System.currentTimeMillis());
String dt = formatter.format(date);
       Client c1 =new Client("nom","prenom", "ville",8100,29845744,dt,"798888");
       Client c2 =new Client("nom","prenom", "ville",8100,29845744,dt,"796688");
      
        //Myconnexion cnx= Myconnexion.getIstance();
       // ServiceCompte sp=new ServiceCompte();
         ServiceCompteClient sp1=new ServiceCompteClient();
        //sp.ajoutercompte(c1);
         //sp.ajoutercompte(c2);
      
        
       //sp.supprimercompte(c1);
        //sp.afficher();
        sp1.afficher(c1);
    }
    
}
