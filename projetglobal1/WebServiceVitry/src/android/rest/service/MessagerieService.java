/**
 * 
 */
package android.rest.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author unknown
 *
 */
@Path("/messagerie")

public class MessagerieService {
	private String url ;
	private Connection con ;
	public void connect(){
		  try{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("DRIVER OK ! ");
			
			url = "jdbc:mysql://localhost/vitrydb";
			con = DriverManager.getConnection(url,"root","");
			System.out.println("Connection effective !");
		  }
		  catch(Exception e){
			  System.out.println(e.getMessage()+"");
		  }
	}
	
	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Path("EcrireMessage")
	public void writeMessage(@FormParam ("guid_from") String guid_from,@FormParam("guid_to") String guid_to,@FormParam("message") String message1){
		System.out.println(message1);
		connect();
		boolean succes = false ;
		//String message1 = new String ("oussama nadir");
		//message = new String ("oussama nadir");
		try{
		Statement stm = con.createStatement() ;
		String query = "insert into message_people (guid_from , guid_to , message ) VALUES ("+guid_from+","+guid_to+",'"+message1+"') ";
		//String query = "insert into message_people (guid_from , guid_to , message ) VALUES ("+guid_from+","+guid_to+",'"+message+"') ";
		
		//System.out.println(message1);
		System.out.println(query);
		int count = stm.executeUpdate(query);
		if(count == 0 ){
			System.out.println("Aucune ligne insérée");
		}
		else{
			System.out.println("Enregistement Effectuée");
			succes = true ;
		}
        stm.close();
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		//return succes ;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("guid_messageCount-{guid}")
	public int getNewMessageCount(@PathParam("guid_messageCount") String guid){
		connect();
		int count = 0 ;
		try{
			Statement stm = con.createStatement() ;
			String query = "select count(id) AS total from message_people where guid_to = "+guid+" and lu = 0 ";
			ResultSet res = stm.executeQuery(query);
			while(res.next()){
				count = res.getInt("total");
			}
			res.close();
			stm.close();
		}
		catch(Exception e){
			System.out.println(e.getMessage()+"");
		}	
		return count;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("guid_1-{guid_1}-guid_2-{guid_2}")
	public List<Message_people> getListMessage(@PathParam("guid_1") String guid_1,@PathParam("guid_2") String guid_2){
		connect();
		List<Message_people> listMessage = new ArrayList<Message_people>();
		try{
			Statement stm = con.createStatement();
			String query = "Select message_people.* , from_user.email AS email_from , to_user.email AS email_to from message_people join elgg_users_entity AS from_user on from_user.guid = message_people.guid_from join elgg_users_entity AS to_user on to_user.guid = message_people.guid_to   where (guid_from ="+guid_1+" AND guid_to="+guid_2+" ) OR (guid_from ="+guid_2+" AND guid_to="+guid_1+")   ";
			System.out.println(query);
			ResultSet res = stm.executeQuery(query);
			while(res.next()){
				Message_people msg = new Message_people(res.getString("id"), res.getString("guid_from"), res.getString("guid_to"), res.getString("message"), res.getInt("lu"), res.getString("lu_a"), res.getString("envoye_a"),res.getString("email_from"),res.getString("email_to"));
				listMessage.add(msg);
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage()+"");
		}
		return listMessage ;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("guid_from-{guid_from}-guid_to-{guid_to}-nouveau-{nouveau}")
	public List<Message_people> getListNewMessage(@PathParam("guid_from") String guid_from,@PathParam("guid_to") String guid_to, @PathParam("nouveau") int nouveau){
		connect();
		List<Message_people> listMessage = new ArrayList<Message_people>();
		try{
			Statement stm = con.createStatement();
			String query = "Select * from message_people where guid_from ="+guid_from+" AND guid_to="+guid_to+"  AND lu = 0    ";
			ResultSet res = stm.executeQuery(query);
			while(res.next()){
				Message_people msg = new Message_people(res.getString("id"), res.getString("guid_from"), res.getString("guid_to"), res.getString("message"), res.getInt("lu"), res.getString("lu_a"), res.getString("envoye_a"));
				listMessage.add(msg);
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage()+"");
		}
		return listMessage ;
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("guid_from-{guid_from}-guid_to-{guid_to}")
	public void setMessageRead(@PathParam("guid_from") String guid_from,@PathParam("guid_to") String guid_to){
		connect();
		try{
		Statement stm = con.createStatement();
		String  query = "UPADATE message_people SET lu = 1 WHERE guid_from="+guid_from+" AND guid_to = "+guid_to+"";
		int i = stm.executeUpdate(query);
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("guid-{guid}")
	public List<Message_people_received> getMessageReceived(@PathParam("guid")String guid){
	List<Message_people_received> listMessageReceived = new ArrayList<Message_people_received>();	
	connect();
	try{
		Statement stm = con.createStatement() ;
		String query = "SELECT guid , name , username , connecte , sum(case when lu = 0 then 1 else 0 end) AS countNewMessage from message_people left join elgg_users_entity on elgg_users_entity.guid = message_people.guid_from where message_people.guid_to = "+guid+" ";
		ResultSet res = stm.executeQuery(query);
		while(res.next()){
			Message_people_received msg = new Message_people_received(res.getString("guid"), res.getInt("connecte"), res.getString("name"), res.getString("username"), res.getInt("countNewMessage"));
			listMessageReceived.add(msg);
		}
	}
	catch(Exception e){
		System.out.println(e.getMessage());
	}
	return listMessageReceived ;
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("email-{email}")
	public String getGuidFromMail(@PathParam("email") String email){
		connect();
		String guid ="";
	try{
		Statement stm = con.createStatement();
		String query = "SELECT guid from elgg_users_entity where email ='"+email+"'";
		System.out.println(query);
		ResultSet res = stm.executeQuery(query);
		if(res.next()){
			guid = res.getString("guid");
		}
		
	}	
	catch(Exception e){
		System.out.println(e.getMessage());
	}
		return guid;
	}
}
