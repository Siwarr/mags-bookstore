package mags.bookstore;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import bookstore.database.Myconnection;
import bookstore.entites.Ouvrage;
import bookstore.service.ServiceOuvrage;

/**
 *
 * @author bondka
 */
public class Bookstore {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Ouvrage o1=new Ouvrage("Guillaume ","Demain",35.6f,"edition",true,20,"text texte texte");
        Ouvrage o2=new Ouvrage("Guillaume m","Demain",35.6f,"edition",true,20,"text texte texte");
        Myconnection cnx= Myconnection.getInstance();
        ServiceOuvrage so=new ServiceOuvrage();
        so.afficher();
        so.ajouterOuvrage(o1);
        so.afficher();
        //so.ajouterOuvrage(o2);
        //List<Ouvrage> lo=new ArrayList<>();
        //so.ouvrageData();
        //System.out.println("avant");
        //so.afficher();
        //so.supprimerOuvrage(o1);
        //System.out.println("apres");
        //so.afficher();
        //System.out.println("modif");
        //System.out.println(o1.getId());
        //so.modifierOuvrage(o1,o2);
        //so.afficher();
        //System.out.println("search");
        //so.SearchOuvrage("Demain");
        //System.out.println("modifier");
        //so.modifier(22,o1);
    }
    
}