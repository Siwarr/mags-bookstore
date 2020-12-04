/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author Jendli
 */
public class Reclamation {
    
    private String code;
    private String titre;
    private String description;
    private Client client;
    private Date dateReclamation;
    private int id_client;
    private String nomclient;
    private String mailclient;
    private String answer;

    public Reclamation() {
    }

    public Reclamation(String titre, String description) {
        this.titre = titre;
        this.description = description;
    }

    public Reclamation(String code, String titre, String description, Client client, Date dateReclamation, int id_client, String nomclient, String mailclient, String answer) {
        this.code = code;
        this.titre = titre;
        this.description = description;
        this.client = client;
        this.dateReclamation = dateReclamation;
        this.id_client = id_client;
        this.nomclient = nomclient;
        this.mailclient = mailclient;
        this.answer = answer;
    }

   

    

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateReclamation() {
        return dateReclamation;
    }

    public void setDateReclamation(Date dateReclamation) {
        this.dateReclamation = dateReclamation;
    }
    
    public Client getClient() {
        return client;
    }

    public void setClient(Client c) {
        this.client = c;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public String getNomclient() {
        return nomclient;
    }

    public void setNomclient(String nomclient) {
        this.nomclient = nomclient;
    }

    public String getMailclient() {
        return mailclient;
    }

    public void setMailclient(String mailclient) {
        this.mailclient = mailclient;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
    

    @Override
    public String toString() {
        return "Reclamation{" + "code=" + code + ", titre=" + titre + ", description=" + description + ", dateReclamation=" + dateReclamation + ", id_client=" + id_client + ", nomclient=" + nomclient + ", mailclient=" + mailclient + ", answer=" + answer + '}';
    }

    

    

    
    
    
}
