package entity;

import Connexion.MyConnexion;
import java.util.ArrayList;
import metier.ServiceCategorie;

public class Main 
{
    public static void add()
    {
        Categorie c = new Categorie();
        c.setLib("Libelle 5");
        c.setDescription("Description de jjj");
        ServiceCategorie sc = new ServiceCategorie();
        if(sc.CreeCategorie(c) == true)
        {
            System.out.println("Insertion de categorie est effectué avec succée");
        }
        else
        {
            System.out.println("Erreur au niveau de l'insertion");
        }
    }
    
    public static void delete()
    {
        ServiceCategorie sc = new ServiceCategorie();
        if(sc.deleteCategorie(6) == true)
        {
            System.out.println("Suppression effectué avec sucess");
        }
        else
        {
            System.out.println("Erreur au niveau de la suppression");
        }
    }
    
    public static void getAll()
    {
        ServiceCategorie sc = new ServiceCategorie();
        ArrayList<Categorie> list = sc.getAll();
        System.out.println("La liste est : ");
        for(int i=0 ; i<list.size() ; i++ )
        {
            System.out.println(list.get(i).toString());
        }
    }
    
    public static void update()
    {
        ServiceCategorie sc = new ServiceCategorie();
        Categorie c = new Categorie();
        c.setLib("New Libelle updater");
        c.setDescription("New description update");
        if(sc.modifier(4, c))
        {
            System.out.println("La mise à jour de la catégorie est effectué avec succes");
        }
        else
        {
            System.out.print("Erreur au niveau de la mise à jour");
        }
    }
    
    public static void findCategorieById()
    {
        ServiceCategorie sc = new ServiceCategorie();
        System.out.println(sc.findCategorieById(1));
    }
    
    public static void main(String args[])
    {
        
        /*add();*/
       /* delete();*/
       update();
       /*getAll();*/
      /*findCategorieById();*/
    }
}
