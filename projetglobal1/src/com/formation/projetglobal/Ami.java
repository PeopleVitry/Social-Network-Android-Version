package com.formation.projetglobal;


public class Ami {
	
	private String nom;
	private String prenom;
	private String email;
	private double latitude;
	private double longitude;
	private int connecte;
	private int guid;
	
	
	public Ami(int guid, String nom, String prenom, String email,
			double latitude, double longitude, int connecte) {
		super();
		this.guid = guid;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.latitude = latitude;
		this.longitude = longitude;
		this.connecte = connecte;
	}
	
	public int getGuid() {
		return guid;
	}

	public void setGuid(int guid) {
		this.guid = guid;
	}
	
	
	public int getConnecte() {
		return connecte;
	}

	public void setConnecte(int connecte) {
		this.connecte = connecte;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	

	
	public Ami(String nom, String prenom, String email, double latitude,
			double longitude, int connecte) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.latitude = latitude;
		this.longitude = longitude;
		this.connecte = connecte;
	}

	public Ami() {


	}

}
