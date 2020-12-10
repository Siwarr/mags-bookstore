package entity;


public class Ouvrage 
{
    private int id;
    private String name;
    private String edition;
    private int quantite;
    private Categorie cat;

    public Ouvrage(int id, String name, String edition, int quantite, Categorie cat)
    {
        this.id = id;
        this.name = name;
        this.edition = edition;
        this.quantite = quantite;
        this.cat = cat;
    }
    
    public Ouvrage()
    {
        
    }

    public void setId(int id) 
    {
        this.id = id;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public void setEdition(String edition) 
    {
        this.edition = edition;
    }

    public void setQuantite(int quantite)
    {
        this.quantite = quantite;
    }

    public void setCat(Categorie cat)
    {
        this.cat = cat;
    }

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public String getEdition() {
        return edition;
    }

    public int getQuantite() {
        return quantite;
    }

    public Categorie getCat() 
    {
        return cat;
    }

    @Override
    public String toString() 
    {
        return "Ouvrage{" + "id=" + id + 
                ", name=" + name + ", edition=" + 
                edition + ", quantite=" + 
                quantite + ", cat=" + 
                cat + '}';
    }
    
}

