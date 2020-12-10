/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.entity;

import bookstore.technique.Myconnexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author OrbitG
 */
public class Ouvrage {
    private int idOuvrage;
    private String nomOuvrage;
    private float prixOuvrage;

    public Ouvrage() {
    }

    public Ouvrage(String nomOuvrage, float prixOuvrage) {
        
        this.nomOuvrage = nomOuvrage;
        this.prixOuvrage = prixOuvrage;
    }

    public Ouvrage(int idOuvrage, String nomOuvrage, float prixOuvrage) {
        this.idOuvrage = idOuvrage;
        this.nomOuvrage = nomOuvrage;
        this.prixOuvrage = prixOuvrage;
    }

    public int getIdOuvrage() {
        return idOuvrage;
    }

    public void setIdOuvrage(int idOuvrage) {
        this.idOuvrage = idOuvrage;
    }

    public String getNomOuvrage() {
        return nomOuvrage;
    }

    public void setNomOuvrage(String nomOuvrage) {
        this.nomOuvrage = nomOuvrage;
    }

    public float getPrixOuvrage() {
        return prixOuvrage;
    }

    public void setPrixOuvrage(float prixOuvrage) {
        this.prixOuvrage = prixOuvrage;
    }

    @Override
    public String toString() {
        return " idOuvrage=" + idOuvrage + ", nomOuvrage=" + nomOuvrage + ", prixOuvrage=" + prixOuvrage;
    }

}
