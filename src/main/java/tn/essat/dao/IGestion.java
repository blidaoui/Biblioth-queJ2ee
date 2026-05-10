package tn.essat.dao;

import java.util.List;

import tn.essat.model.Categorie;
import tn.essat.model.Emprunt;
import tn.essat.model.Livre;
import tn.essat.model.Utilisateur;

public interface IGestion {

	public List<Categorie> getAllCats();
	public List<Livre> getAllLivresByCats(int id);
	public Livre getLivreById(int id);
	public Categorie getCatsById(int id);
	public List<Emprunt> getAllEmpruntsByUser(int id);
	public void addEmprunt(Emprunt emp);
	public void addUser(Utilisateur u);
	public void addLivre(Livre lv);
	public void addCategorie(Categorie cat);
	public void deleteLivre(int id);
	public void deleteCategorie(int id);
	
	
	public Utilisateur verifUser(String username, String password);
}