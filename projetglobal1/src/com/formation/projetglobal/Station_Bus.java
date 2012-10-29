package com.formation.projetglobal;

public class Station_Bus {

	
	private String Id_station;
	private String numero_ligne_A;
	private  int Bus_A;
	private String destination_A;
	private String nom_destination_A;
	private String  numero_ligne_B;
	private int Bus_B;
	private String destination_B;
	private String nom_destination_B;
	private String numero_ligne_C;
	private  int Bus_C;
	private String destination_C;
	private String nom_destination_C;
	private String adresse;
	
	
	
	public Station_Bus (){
		
	}



	public Station_Bus(String id_station, String numero_ligne_A, int bus_A,
			String destination_A, String nom_destination_A,
			String numero_ligne_B, int bus_B, String destination_B,
			String nom_destination_B, String numero_ligne_C, int bus_C,
			String destination_C, String nom_destination_C, String adresse) {
		super();
		Id_station = id_station;
		this.numero_ligne_A = numero_ligne_A;
		Bus_A = bus_A;
		this.destination_A = destination_A;
		this.nom_destination_A = nom_destination_A;
		this.numero_ligne_B = numero_ligne_B;
		Bus_B = bus_B;
		this.destination_B = destination_B;
		this.nom_destination_B = nom_destination_B;
		this.numero_ligne_C = numero_ligne_C;
		Bus_C = bus_C;
		this.destination_C = destination_C;
		this.nom_destination_C = nom_destination_C;
		this.adresse = adresse;
	}



	public String getId_station() {
		return Id_station;
	}



	public void setId_station(String id_station) {
		Id_station = id_station;
	}



	public String getNumero_ligne_A() {
		return numero_ligne_A;
	}



	public void setNumero_ligne_A(String numero_ligne_A) {
		this.numero_ligne_A = numero_ligne_A;
	}



	public int getBus_A() {
		return Bus_A;
	}



	public void setBus_A(int bus_A) {
		Bus_A = bus_A;
	}



	public String getDestination_A() {
		return destination_A;
	}



	public void setDestination_A(String destination_A) {
		this.destination_A = destination_A;
	}



	public String getNom_destination_A() {
		return nom_destination_A;
	}



	public void setNom_destination_A(String nom_destination_A) {
		this.nom_destination_A = nom_destination_A;
	}



	public String getNumero_ligne_B() {
		return numero_ligne_B;
	}



	public void setNumero_ligne_B(String numero_ligne_B) {
		this.numero_ligne_B = numero_ligne_B;
	}



	public int getBus_B() {
		return Bus_B;
	}



	public void setBus_B(int bus_B) {
		Bus_B = bus_B;
	}



	public String getDestination_B() {
		return destination_B;
	}



	public void setDestination_B(String destination_B) {
		this.destination_B = destination_B;
	}



	public String getNom_destination_B() {
		return nom_destination_B;
	}



	public void setNom_destination_B(String nom_destination_B) {
		this.nom_destination_B = nom_destination_B;
	}



	public String getNumero_ligne_C() {
		return numero_ligne_C;
	}



	public void setNumero_ligne_C(String numero_ligne_C) {
		this.numero_ligne_C = numero_ligne_C;
	}



	public int getBus_C() {
		return Bus_C;
	}



	public void setBus_C(int bus_C) {
		Bus_C = bus_C;
	}



	public String getDestination_C() {
		return destination_C;
	}



	public void setDestination_C(String destination_C) {
		this.destination_C = destination_C;
	}



	public String getNom_destination_C() {
		return nom_destination_C;
	}



	public void setNom_destination_C(String nom_destination_C) {
		this.nom_destination_C = nom_destination_C;
	}



	public String getAdresse() {
		return adresse;
	}



	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	
	
	
//	public String toString(){
//		return this.numero_station;
//	}


}
