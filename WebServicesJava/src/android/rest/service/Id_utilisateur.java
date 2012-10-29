package android.rest.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;



@Path("/getIdUtilisateur")

public class Id_utilisateur {
	
	 String id_utilisateur ;
	@GET
	 @Produces(MediaType.APPLICATION_JSON)
	@Path("Mail-{Mail}")
	public List<Ami> getBookByNameAndEditor(@PathParam("Mail") String  Mail){
		System.out.println(Mail);
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
			ResultSet result = state.executeQuery("SELECT guid,name,username,email,latitude,longitude,connecte FROM elgg_users_entity WHERE email ='"+Mail+"';") ;
		//	ResultSet result = state.executeQuery("SELECT guid FROM elgg_users_entity WHERE email ='"+Mail+"';") ;
			while(result.next()){
	
			          String nomRecherche = result.getString("name");
			          String loginRecherche =result.getString("username");
			          String emailrecherche=result.getString("email");
			          double latitude=result.getDouble("latitude");
			          double longitude=result.getDouble("longitude");
			          int connecte=result.getInt("connecte");
			          int guid=result.getInt("guid");
			          Ami ami=new Ami(guid,nomRecherche, loginRecherche, emailrecherche, latitude, longitude,connecte);
			          amiConnectes.add(ami);
			
			}
			for(Ami amiConnecte : amiConnectes){
	        	  System.out.println("voila le service fonctionne");
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
		Ami ammi= new Ami(0, "AA", "AAA", "AAA", 0.0, 0.0, 0);
		amiConnectes.add(ammi);
		return amiConnectes;
	}


}
