/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Admin;
import entities.Client;

/**
 *
 * @author Jendli
 */
public interface InterfaceLogin_Register {
    
    public void registerClient(Client client);
    public Client seConnecter(String login,String mdp);
    public Admin seConnecterAdmin(String login,String mdp);
    
}
