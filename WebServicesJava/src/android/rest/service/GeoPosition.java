package android.rest.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;



@Path("/position")

public class GeoPosition {
	@GET
	@Path("guid-{guid}-latitude-{latitude}-longitude-{longitude}")
	public String getBookByNameAndEditor(@PathParam("guid") int  guid, @PathParam("latitude") double latitude,@PathParam("longitude") double longitude){
		
		System.out.println(guid);
		System.out.println(latitude);
		System.out.println(longitude);
		
		
		try {

	        Class.forName("com.mysql.jdbc.Driver");
			System.out.println("DRIVER OK ! ");
			
			String url = "jdbc:mysql://localhost/vitrydb";
			Connection con = DriverManager.getConnection(url,"root","");
			System.out.println("Connection effective !");
			
			//Création d'un objet Statement
			Statement state = con.createStatement();
			
			// state.executeUpdate("UPDATE `elgg_users_entity` SET `latitude`= "+latitude+",'longitude'="+longitude+" WHERE guid="+guid) ;
			state.executeUpdate("UPDATE `elgg_users_entity` SET `latitude`="+latitude+",`longitude`="+longitude+" WHERE guid="+guid+"") ;
			//UPDATE `elgg_users_entity` SET `latitude`=4355555,`longitude`=3456789 WHERE guid=35;
            state.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return" saha nadir";
				
	
}
}
