package com.formation.projetglobal;
import java.util.ArrayList;

import java.util.ArrayList;

public class Groupe {
	private String nom;
	private ArrayList<Ami> objets;

	public Groupe(String nom) {
		super();
		this.nom = nom;
		this.objets = new ArrayList<Ami>();
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public ArrayList<Ami> getObjets() {
		return objets;
	}

	public void setObjets(ArrayList<Ami> objets) {
		this.objets = objets;
	}

}
