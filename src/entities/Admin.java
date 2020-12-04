/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Jendli
 */
public class Admin {
    private int id;
    private String login;
    private String mdp;
    private String mail;
    private String nom;
    private String prenom;

    public Admin() {
    }

    public Admin(int id, String login, String mdp, String mail, String nom, String prenom) {
        this.id = id;
        this.login = login;
        this.mdp = mdp;
        this.mail = mail;
        this.nom = nom;
        this.prenom = prenom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Override
    public String toString() {
        return "Admin{" + "id=" + id + ", login=" + login + ", mdp=" + mdp + ", mail=" + mail + ", nom=" + nom + ", prenom=" + prenom + '}';
    }
    
    
}
