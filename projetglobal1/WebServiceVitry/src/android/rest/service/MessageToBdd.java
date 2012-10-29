/*	package android.rest.service;
	
	import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/message")

public class MessageToBdd {
	
	@GET
	@Path("Pseudo-{Pseudo}-Msg-{Msg}-prive-{prive}-recepteur-{recepteur}")
	public String getBookByNameAndEditor(@PathParam("Pseudo") String  pseudo, @PathParam("Msg") String message,
			@PathParam("prive") int prive,@PathParam("recepteur") String recepteur){
		
		System.out.println(pseudo);
		System.out.println(message);
		System.out.println(prive);
		System.out.println(recepteur);
		
		
		
		
		try {

	        Class.forName("com.mysql.jdbc.Driver");
			System.out.println("DRIVER OK ! ");
			
			String url = "jdbc:mysql://localhost/vitrydb";
			Connection con = DriverManager.getConnection(url,"root","");
			System.out.println("Connection effective !");
			
			//Création d'un objet Statement
			Statement state = con.createStatement();
			
			//state.executeUpdate("INSERT INTO `message` ( `pseudo` , `message` , `priver` , `recepteur` )VALUES ("+MonMessage.getPseudo()+", "+MonMessage.getMsg()+","+MonMessage.getPrive()+", "+MonMessage.getRecepteur()+")");
			state.executeUpdate("INSERT INTO `message` ( `pseudo` , `message` , `priver` , `recepteur` )VALUES ('"+pseudo+"','"+message+"','"+prive+"','"+recepteur+"')");

			state.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "salut aboud";
	 
		
		
		
		
		
		
}


	
	
}*/
