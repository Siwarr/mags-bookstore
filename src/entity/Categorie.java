package entity;

import entity.Ouvrage;
import java.util.ArrayList;

public class Categorie {
    private int id;
    private String  lib;
    private String description;
   ArrayList <Ouvrage> ouv ;
   
   
   
   
   
   public Categorie(int id ,  String lib , String description)
    {
        this.id = id;
        this.lib = lib;
        this.description = description;
        ouv= new ArrayList<>();
    }
   
   public Categorie()
   {
       ouv= new ArrayList<>();
   }

    public int getId() {
        return id;
    }

    public String getLib() {
        return lib;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<Ouvrage> getOuv() {
        return ouv;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLib(String lib) {
        this.lib = lib;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setOuv(ArrayList<Ouvrage> ouv) {
        this.ouv = ouv;
    }

    @Override
    public String toString() {
        return "Categorie{" + "id=" + id + ", "
                + "=" + lib + ", description=" + description + ", ouv=" + ouv + '}';
    } 
    
    
}
