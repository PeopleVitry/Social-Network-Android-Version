package android.rest.service;

import javax.xml.bind.annotation.XmlRootElement;



@XmlRootElement
public class ObjetDetail {
	
	
	private int id;
	private String nom;
	private String adresse;
	private String telephone;
	private double Latitude;
	private double Longitude;
	private int class_numero;
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public double getLatitude() {
		return Latitude;
	}

	public void setLatitude(double latitude) {
		Latitude = latitude;
	}

	public double getLongitude() {
		return Longitude;
	}

	public void setLongitude(double longitude) {
		Longitude = longitude;
	}

	public int getClass_numero() {
		return class_numero;
	}

	public void setClass_numero(int class_numero) {
		this.class_numero = class_numero;
	}

	public ObjetDetail(int id, String nom, String adresse, String telephone,
			double latitude, double longitude, int class_numero) {
	
		this.id = id;
		this.nom = nom;
		this.adresse = adresse;
		this.telephone = telephone;
		Latitude = latitude;
		Longitude = longitude;
		this.class_numero = class_numero;
	}
	
	public ObjetDetail(){
		
		
	}

}
