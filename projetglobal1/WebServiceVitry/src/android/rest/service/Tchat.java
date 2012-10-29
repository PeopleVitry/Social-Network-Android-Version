/*package android.rest.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/getMsg")

public class Tchat {
	
	
	
	 @GET
	 @Produces(MediaType.APPLICATION_JSON)
	// @Produces(MediaType.APPLICATION_JSON)
	 
	 public List<Message> getMessageFromDb(){
		
		 
		 List<Message> listMessage= new LinkedList<Message>();
		 
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
				ResultSet result = state.executeQuery("SELECT pseudo,message FROM message limit 0,3") ;
				while(result.next()){  
					String pseudo = result.getString("pseudo");
					String msg = result.getString("message");
					Message monMessage= new Message(pseudo, msg);
					listMessage.add(monMessage);
		
				}
				
				
				result.close();
	            state.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		 
		return listMessage;
	
		 
	 }
	 
	

}


*/