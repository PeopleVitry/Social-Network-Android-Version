package com.formation.projetglobal;

public class Objet {
	

	private int code;
	private String type;
	private String libelle;
	
	
	public int getCode() {
		return code;
	}


	public void setCode(int code) {
		this.code = code;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getLibelle() {
		return libelle;
	}


	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}


	public Objet(int code, String type, String libelle) {
		
		this.code = code;
		this.type = type;
		this.libelle = libelle;
	}
	
	public String toString(){
		return this.libelle;
	}
	public Objet (){
		
	}

}
