package bookstore.service;


import bookstore.entity.Basket;
import bookstore.entity.Ouvrage;
import bookstore.entity.User;
import bookstore.technique.Myconnexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServicesBasket /*implements IServiceBasket<Basket>*/ {
     Myconnexion conx;
    public ServicesBasket()
    {
        conx=Myconnexion.getIstance();
    }

    public void ajouter(Basket b) {
        try {
            
            String req = "INSERT INTO BASKET VALUES (? , ? , ?)";
            PreparedStatement st= conx.getConnection().prepareStatement(req);
            st.setInt(1, b.getUser().getIdUser());
            st.setInt(2, b.getOuvrage().getIdOuvrage());
            st.setInt(3, b.getQuantity());
            st.executeUpdate();
            System.out.println("Basket ajoute!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

    }

    public void supprimer(Basket b) {
        try {

            String req = "DELETE FROM BASKET WHERE user_id=? AND book_id=? ";
            PreparedStatement st= conx.getConnection().prepareStatement(req);
            st.setInt(1, b.getUser().getIdUser());
            st.setInt(2, b.getOuvrage().getIdOuvrage());
            st.executeUpdate();
            System.out.println("Basket supprime!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

    }

    public void modifier(Basket b) {
        System.out.println(b);
        try {

            String req = "UPDATE BASKET SET quantity=? WHERE user_id=? AND book_id=?";
            PreparedStatement st= conx.getConnection().prepareStatement(req);
            st.setInt(2, b.getUser().getIdUser());
            st.setInt(3, b.getOuvrage().getIdOuvrage());
            st.setInt(1, b.getQuantity());
            st.executeUpdate();
            System.out.println("Panier modifiee!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

    }

    public ObservableList<Basket> getBasketOfUser(int userId) {
        ObservableList<Basket> res = FXCollections.observableArrayList();
        try {

            String req = "Select * FROM BASKET WHERE user_id=? ";
            PreparedStatement st= conx.getConnection().prepareStatement(req);
            st.setInt(1, userId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int quantity = rs.getInt("quantity");
                int bookid = rs.getInt("book_id");
                Ouvrage ouvrage = new Ouvrage(4,"ssm",20.1f);//getOuvrageById(bookid) apres integration
                User user = new User(1,"aymen");//getCurrentUser() apres integration
                res.add(new Basket(user, ouvrage, quantity));
            }
            rs.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return res;

    }


    public void emptyBaket(int b){
        try {

            String req = "DELETE * FROM BASKET WHERE user_id=? ";
            PreparedStatement st= conx.getConnection().prepareStatement(req);
            st.setInt(1,b);
            st.executeUpdate();
            System.out.println("All Baskets supprime!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
    }
}




	
	
	

