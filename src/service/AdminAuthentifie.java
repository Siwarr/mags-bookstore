/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Admin;


/**
 *
 * @author Jendli
 */
public class AdminAuthentifie {
     
    
    private Admin admin;
    private static AdminAuthentifie instance;

    private AdminAuthentifie() {
    }

    public static AdminAuthentifie getInstance() {
        if(instance==null)
            instance=new AdminAuthentifie();
        return instance;
    }
    
    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin a) {
        this.admin=a;
    }
}
