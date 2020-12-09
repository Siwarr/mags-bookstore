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
public class compteClient extends Client  {

    public compteClient(String nom, String prenom, String ville, int code_postale, long num_de_telf, String date_inscri, String cin, String email) {
        super(nom, prenom, ville, code_postale, num_de_telf, date_inscri, cin, email);
    }

    public compteClient(String email, String pwd) {
        super(email, pwd);
    }
    

    public compteClient() {
    }
    public  compteClient (String nom, String prenom, String ville, int code_postale, long num_de_telf, String date_inscri,String cin){
   super(nom,prenom,ville,code_postale,num_de_telf,date_inscri,cin);
   }
    
    
  
    
    
    
}
