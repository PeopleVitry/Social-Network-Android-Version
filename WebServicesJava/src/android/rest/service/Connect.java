package android.rest.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;


public class Connect {
	
	/**
	 * @param args
	 */
	public  List<Ami> getDataFromBdd(int id) {

		List<Ami> amiConnectes = new LinkedList<Ami>();
		try {

	        Class.forName("com.mysql.jdbc.Driver");
			System.out.println("DRIVER OK ! ");
			
			String url = "jdbc:mysql://localhost/vitrydb";
			Connection con = DriverManager.getConnection(url,"root","");
			System.out.println("Connection effective !");
			
			//Création d'un objet Statement
			Statement state = con.createStatement();
			
			//L'objet ResultSet contient le résultat de la requête SQL
			
			//ResultSet result = state.executeQuery("select name,username,email,latitude,longitude from elgg_users_entity ") ;
			//ResultSet result = state.executeQuery("SELECT name,username,email,latitude,longitude FROM elgg_entity_relationships JOIN elgg_users_entity ON guid = guid_two WHERE guid_one =35 and connecte=1 ") ;
			ResultSet result = state.executeQuery("SELECT guid, name,username,email,latitude,longitude,connecte FROM elgg_entity_relationships JOIN elgg_users_entity ON guid = guid_two WHERE guid_one ="+id+" ") ;
			while(result.next()){
	
			          String nomRecherche = result.getString("name");
			          String loginRecherche =result.getString("username");
			          String emailrecherche=result.getString("email");
			          double latitude=result.getDouble("latitude");
			          double longitude=result.getDouble("longitude");
			          int connecte=result.getInt("connecte");
			          Ami ami=new Ami(nomRecherche, loginRecherche, emailrecherche, latitude, longitude,connecte);
			          ami.setGuid(result.getInt("guid"));
			          amiConnectes.add(ami);
			
			}
//			for(Ami amiConnecte : amiConnectes){
//	        	  
//	        	  System.out.println(amiConnecte.getNom());
//	        	  System.out.println(amiConnecte.getPrenom());
//	        	  System.out.println(amiConnecte.getEmail());
//	        	  System.out.println(amiConnecte.getLatitude());
//	        	  System.out.println(amiConnecte.getLongitude());
//
//	          }
			
			result.close();
            state.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return amiConnectes;
	}

}
