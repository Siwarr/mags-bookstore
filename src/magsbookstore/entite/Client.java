/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magsbookstore.entite;

import java.sql.Date;
import static java.util.Collections.list;
import java.util.List;
import java.util.Objects;
import Exception.SaisieException;
/**
 *
 * @author ghaith khiari
 */
public class Client {
    private int id;
    private String nom;
    private String prenom;
    private String ville ;
    private int code_postale ;
    private long num_de_telf;
    private String date_inscri;
    private String cin ;
    private String email;
private String pwd;
    public Client (String nom, String prenom, String ville, int code_postale, long num_de_telf, String date_inscri, String cin, String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.ville = ville;
        this.code_postale = code_postale;
        this.num_de_telf = num_de_telf;
        this.date_inscri = date_inscri;
        this.cin = cin;
        this.email = email;
    }
     public Client (String nom, String prenom, String ville, int code_postale, long num_de_telf, String date_inscri, String cin, String email,String pwd) {
        this.nom = nom;
        this.prenom = prenom;
        this.ville = ville;
        this.code_postale = code_postale;
        this.num_de_telf = num_de_telf;
        this.date_inscri = date_inscri;
        this.cin = cin;
        this.pwd=pwd ;
        this.email = email;
    }
    

    public Client(String email, String pwd) {
        this.email = email;
        this.pwd = pwd;
    }
    

    public Client(String nom, String prenom, String ville, int code_postale, long num_de_telf, String date_inscri, String cin) {
        this.nom = nom;
        this.prenom = prenom;
        this.ville = ville;
        this.code_postale = code_postale;
        this.num_de_telf = num_de_telf;
        this.date_inscri = date_inscri;
       this.cin=cin;
    }

    public Client () {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return the prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * @param prenom the prenom to set
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * @return the ville
     */
    public String getVille() {
        return ville;
    }

    /**
     * @param ville the ville to set
     */
    public void setVille(String ville) {
        this.ville = ville;
    }

    /**
     * @return the code_postale
     */
    public int getCode_postale() {
        return code_postale;
    }

    /**
     * @param code_postale the code_postale to set
     */
    public void setCode_postale(int code_postale) {
        this.code_postale = code_postale;
    }

    /**
     * @return the num_de_telf
     */
    public long getNum_de_telf() {
        return num_de_telf;
    }

    /**
     * @param num_de_telf the num_de_telf to set
     */
    public void setNum_de_telf(long num_de_telf) {
        this.num_de_telf = num_de_telf;
    }

    /**
     * @return the date_inscri
     */
    public String getDate_inscri() {
        return date_inscri;
    }

    /**
     * @param date_inscri the date_inscri to set
     */
    public void setDate_inscri(String date_inscri) {
        this.date_inscri = date_inscri;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "compte{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", ville=" + ville + ", code_postale=" + code_postale + ", num_de_telf=" + num_de_telf + ", date_inscri=" + date_inscri + ", cin=" + cin + ", email=" + email + ", pwd=" + pwd + '}';
    }

    

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + this.id;
        hash = 31 * hash + Objects.hashCode(this.date_inscri);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Client other = (Client) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.date_inscri, other.date_inscri)) {
            return false;
        }
        return true;
    }
    
public void ajoutercompte(Client p){} ;
    public void supprimercompte(Client p){};
    public void modifiercompte(Client p){};
    public void afficher(){
} 

    public void setId(int id) {
this.id =id  ;  }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) throws SaisieException {
        if (cin.length()==8)
        this.cin = cin;
        else  throw (new SaisieException("cin invalide"));
    }
   

    
    
    
}
