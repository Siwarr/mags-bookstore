/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magsbookstore.entite;

import java.sql.Date;

/**
 *
 * @author ghaith khiari
 */
public class compteAdmine extends Client{
   public  compteAdmine (String nom, String prenom, String ville, int code_postale, long num_de_telf, String date_inscri,String cin){
   super(nom,prenom,ville,code_postale,num_de_telf,date_inscri,cin);
   }
 public compteAdmine() {
    }

    @Override
    public void afficher() {
        super.afficher(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ajoutercompte(Client p) {
        super.ajoutercompte(p); //To change body of generated methods, choose Tools | Templates.
    }
 
    
    
    
    
    
}
