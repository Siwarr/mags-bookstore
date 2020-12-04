/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Client;
import entities.Reclamation;
import java.util.List;


/**
 *
 * @author Jendli
 */
public interface InterfaceReclamation {
    
    public void ajouterReclamation(Reclamation r,Client c);
    public void supprimerReclamation(String code );
    public void modifierReclamation(String code);
    public List<Reclamation> afficherListReclamations();
    public List<Client> afficherListReclamationsClient();
    public List<Reclamation> afficherMesReclamations(Client c);
    public int rowCount();
    
}
