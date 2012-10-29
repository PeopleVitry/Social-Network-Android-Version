	package android.rest.service;
	
	import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
	
	// service web permettant de mettre le statut a déconnecté ou connecté dans la base
	@Path("/books")
	public class Connection_Statut {
	
	@GET
	@Path("name-{name}-editor-{editor}")
	public String getBookByNameAndEditor(@PathParam("name") int name,@PathParam("editor") int editor){
		int var1=name;
		int var2=editor;
		System.out.println(" les service pour mettre a jour statut");
		System.out.println(var1);
		System.out.println(var2);
		
		
		try {

	        Class.forName("com.mysql.jdbc.Driver");
			System.out.println("DRIVER OK ! ");
			
			String url = "jdbc:mysql://localhost/vitrydb";
			Connection con = DriverManager.getConnection(url,"root","");
			System.out.println("Connection effective !");
			
			//Création d'un objet Statement
			Statement state = con.createStatement();
			
			 state.executeUpdate("UPDATE `elgg_users_entity` SET `connecte`= "+var1+" WHERE guid="+var2) ;
			
            state.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "salut aboud";
		
		
	}
	
	
	}
	
	
	
			
			
			
		
	
	
