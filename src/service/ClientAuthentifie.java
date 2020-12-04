/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Client;

/**
 *
 * @author Jendli
 */
public class ClientAuthentifie {
    
    private Client client;
    private static ClientAuthentifie instance;

    private ClientAuthentifie() {
    }

    public static ClientAuthentifie getInstance() {
        if(instance==null)
            instance=new ClientAuthentifie();
        return instance;
    }
    
    public Client getClient() {
        return client;
    }

    public void setClient(Client c) {
        this.client = c;
    }

   

    
    
    
}
