/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.entity;

/**
 *
 * @author aymen
 */
public class User {
    private int idUser;
    private String nomUser;

    public User() {
    }

    public User(String nomUser) {
        this.nomUser = nomUser;
    }

    public User(int idUser, String nomUser) {
        this.idUser = idUser;
        this.nomUser = nomUser;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getNomUser() {
        return nomUser;
    }

    public void setNomUser(String nomUser) {
        this.nomUser = nomUser;
    }

    @Override
    public String toString() {
        return "User{" + "idUser=" + idUser + ", nomUser=" + nomUser + '}';
    }
    
    
}
