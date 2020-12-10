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
public class Basket {
    private User user;
    private Ouvrage ouvrage;
    private int quantity;

    public Basket(User user, Ouvrage ouvrage, int quantity) {
        this.user = user;
        this.ouvrage = ouvrage;
        this.quantity = quantity;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Ouvrage getOuvrage() {
        return ouvrage;
    }

    public void setOuvrage(Ouvrage ouvrage) {
        this.ouvrage = ouvrage;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Basket{" + "user=" + user + ", ouvrage=" + ouvrage + ", quantity=" + quantity + '}';
    }
    
    public String getBookTitle(){
        return ouvrage.getNomOuvrage();
    }
    
    public double getBookPrice() {
        return ouvrage.getPrixOuvrage();
    }

    public double getTotalPrice() {
        return this.getQuantity() * this.getBookPrice();
    }
    
}
