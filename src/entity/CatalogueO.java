package entity;


import java.util.ArrayList;

public class CatalogueO {
    
      private int id;
    private String titre;
    ArrayList <Categorie> categorie =new ArrayList<>();
    
     public CatalogueO() {}

    public CatalogueO(int id, String titre) {
        this.id = id;
        this.titre = titre;
    }

    public int getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public ArrayList<Categorie> getCategorie() {
        return categorie;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setCategorie(ArrayList<Categorie> categorie) {
        this.categorie = categorie;
    }

    @Override
    public String toString() {
        return "CatalogueO{" + "id=" + id + ", titre=" + titre + ", categorie=" + categorie + '}';
    }

    
}
