package android.rest.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class Amiconnect {

	
	
	public  List<Ami> getlistamiconnect(int id) {

		List<Ami> amiConnectes = new LinkedList<Ami>();
		try {

	        Class.forName("com.mysql.jdbc.Driver");
			System.out.println("DRIVER OK ! ");
			
			String url = "jdbc:mysql://localhost/vitrydb";
			Connection con = DriverManager.getConnection(url,"root","");
			System.out.println("Connection effective !");
			
			//Cr�ation d'un objet Statement
			Statement state = con.createStatement();
			
			//L'objet ResultSet contient le r�sultat de la requ�te SQL
			
			//ResultSet result = state.executeQuery("select name,username,email,latitude,longitude from elgg_users_entity ") ;
		//ResultSet result = state.executeQuery("SELECT name,username,email,latitude,longitude FROM elgg_entity_relationships JOIN elgg_users_entity ON guid = guid_two WHERE guid_one =49 and connecte= '1'") ;
		ResultSet result = state.executeQuery("SELECT name,username,email,latitude,longitude FROM elgg_entity_relationships JOIN elgg_users_entity ON elgg_users_entity.guid = elgg_entity_relationships.guid_two WHERE guid_one ="+id+" and connecte= 1  ") ;
			while(result.next()){
	                 // int guid =result.getInt("guid");
			          String nomRecherche = result.getString("name");
			          String loginRecherche =result.getString("username");
			          String emailrecherche=result.getString("email");
			          double latitude=result.getDouble("latitude");
			          double longitude=result.getDouble("longitude");
			         // int connecte=result.getInt("connecte");
			        //  Ami ami=new Ami(nomRecherche, loginRecherche, emailrecherche, latitude, longitude,connecte);
			          Ami ami=new Ami(nomRecherche, loginRecherche, emailrecherche, latitude, longitude);
			          amiConnectes.add(ami);
			
			}
			for(Ami amiConnecte : amiConnectes){
	        	  
	        	  System.out.println(amiConnecte.getNom());
	        	  System.out.println(amiConnecte.getPrenom());
	        	  System.out.println(amiConnecte.getEmail());
	        	  System.out.println(amiConnecte.getLatitude());
	        	  System.out.println(amiConnecte.getLongitude());

	          }
			
			result.close();
            state.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return amiConnectes;
	}

}
