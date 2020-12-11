package bookstore.interfaces;

import java.util.List;

import bookstore.entites.Client;
import bookstore.entites.Emprunt;

public interface InterfaceEmprunt {
	public List<Emprunt> afficherMesEmprunt(Client c);
	public List<Emprunt> afficherEmprunt();
}
