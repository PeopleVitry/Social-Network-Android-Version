package android.rest.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class ConnectSatationBus {
	
	public  List<Station_Bus> getObjetFromBdd(String mavar) {

		List<Station_Bus> listStation_Bus = new LinkedList<Station_Bus>();
		try {

	        Class.forName("com.mysql.jdbc.Driver");
			System.out.println("DRIVER OK ! ");
			
			String url = "jdbc:mysql://localhost/vitrydb";
			Connection con = DriverManager.getConnection(url,"root","");
			System.out.println("Connection effective !");
			
			//Création d'un objet Statement
			Statement state = con.createStatement();
			
			//L'objet ResultSet contient le résultat de la requête SQL
			
			
			ResultSet result = state.executeQuery("select * from station_bus where id_Station='"+mavar+"';") ;
			//ResultSet result = state.executeQuery("select code,type, libelle from objet where type ='service'");
			
			while(result.next()){
				String id_station =result.getString("id_Station");
				String numero_ligne_A =result.getString("numero_Ligne_A");
				String numero_ligne_B =result.getString("numero_Ligne_B");
				String numero_ligne_C =result.getString("numero_Ligne_C");
			
			    String destination_A =result.getString("Destination_A");
			    String destination_B =result.getString("Destination_B");
			    String destination_C =result.getString("Destination_C");
			          int bus_A = result.getInt("Bus_A");
			          int bus_B = result.getInt("Bus_B");
			          int bus_C = result.getInt("Bus_C");
			          String nom_destination_A =result.getString("Nom_destination_Bus_A");
			          String nom_destination_B =result.getString("Nom_destination_Bus_B");
			          String nom_destination_C =result.getString("Nom_destination_Bus_C");
			          String adresse=result.getString("Adresse");
			          Station_Bus monObjet=new Station_Bus(id_station, numero_ligne_A, bus_A, destination_A, nom_destination_A, numero_ligne_B,
			        		  bus_B, destination_B, nom_destination_B, numero_ligne_C, bus_C, destination_C, nom_destination_C, adresse);
			          listStation_Bus.add(monObjet);
			
			}
			for(Station_Bus objet : listStation_Bus){
	        	  
	        	  System.out.println(objet.getBus_A());
	        	  System.out.println(objet.getBus_B());
	        	  System.out.println(objet.getId_station());

	          }
			
			result.close();
            state.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listStation_Bus;
	}
	
	



	

}
