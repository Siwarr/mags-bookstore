package bookstore.entites;


import java.util.Objects;

/**
 *
 * @author bondka
 */
public class Ouvrage {
    private int id;
    private String name;
    private String author;
    private String edition;
    private float price;
    private boolean isAvail;
    private int quantite;
    private String description;

    public Ouvrage() {
    }

    public Ouvrage(String author,String name, float price, String edition,boolean isAvail,int quantite,String description) {
        this.author = author;
        this.name = name;
        this.price = price;
        this.edition = edition;
        this.isAvail=isAvail;
        this.quantite=quantite;
        this.description=description;
    }
    public Ouvrage(int id,String author,String name, float price, String edition,boolean isAvail,int quantite,String description) {
        this.id=id;
        this.author = author;
        this.name = name;
        this.price = price;
        this.edition = edition;
        this.isAvail=isAvail;
        this.quantite=quantite;
        this.description=description;
    }
    
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean getIsAvail() {
        return isAvail;
    }

    public void setIsAvail(boolean isAvail) {
        this.isAvail = isAvail;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.name);
        hash = 43 * hash + Objects.hashCode(this.author);
        hash = 43 * hash + Objects.hashCode(this.edition);
        hash = 43 * hash + Float.floatToIntBits(this.price);
        hash = 43 * hash + (this.isAvail ? 1 : 0);
        hash = 43 * hash + this.quantite;
        hash = 43 * hash + Objects.hashCode(this.description);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Ouvrage other = (Ouvrage) obj;
        if (Float.floatToIntBits(this.price) != Float.floatToIntBits(other.price)) {
            return false;
        }
        if (this.isAvail != other.isAvail) {
            return false;
        }
        if (this.quantite != other.quantite) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.author, other.author)) {
            return false;
        }
        if (!Objects.equals(this.edition, other.edition)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        return true;
    }
    
    
    
}