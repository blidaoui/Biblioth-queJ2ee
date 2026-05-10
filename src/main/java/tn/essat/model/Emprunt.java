package tn.essat.model;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Emprunt {
	@Id @GeneratedValue( strategy = GenerationType.IDENTITY)

	private int id;
	
	@ManyToOne
	private Utilisateur user;
	@ManyToOne
	private Livre lv;
	private LocalDate date_emprunt;
	
	public Emprunt(int id, Utilisateur user, Livre lv, LocalDate date_emprunt) {
		super();
		this.id = id;
		this.user = user;
		this.lv = lv;
		this.date_emprunt = date_emprunt;
	}

	public Emprunt() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Utilisateur getUser() {
		return user;
	}

	public void setUser(Utilisateur user) {
		this.user = user;
	}

	public Livre getLv() {
		return lv;
	}

	public void setLv(Livre lv) {
		this.lv = lv;
	}

	public LocalDate getDate_emprunt() {
		return date_emprunt;
	}

	public void setDate_emprunt(LocalDate date_emprunt) {
		this.date_emprunt = date_emprunt;
	}
	
	
	
	

}
