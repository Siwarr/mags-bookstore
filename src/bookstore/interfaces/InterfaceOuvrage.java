package bookstore.interfaces;


import java.util.List;

import bookstore.entites.Ouvrage;

/**
 *
 * @author bondka
 */
public interface InterfaceOuvrage {
    public void ajouterOuvrage(Ouvrage o);
    public void supprimerOuvrage(Ouvrage o);
    public List<Ouvrage> afficher();
    public void SearchOuvrage(String name);
    public void modifierOuvrage(Ouvrage p,Ouvrage v);
    //public void detailOuvrage(int id);
    //public List<Ouvrage> ouvrageData();
    //public boolean modifier(int id,Ouvrage o);
    
    
}