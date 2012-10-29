/**
 * 
 */
package android.rest.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author unknown
 *
 */
@Path("/authentification")
public class AuthentificationService {
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
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("email-{email}")
	public UserInfo testLogin(@PathParam("email") String email){
		connect();
		UserInfo user = new UserInfo();
		user.setSucces(false);
		try{
			Statement stm = con.createStatement();
			String query = "SELECT guid , name , username , email , latitude , longitude , connecte from elgg_users_entity where email like ('"+email+"')";
			//System.out.println(query);
			ResultSet res = stm.executeQuery(query);
			if(res.next()){
				user.setGuid(res.getString("guid"));
				user.setConnecte(res.getInt("connecte"));
				user.setEmail(res.getString("email"));
				user.setName(res.getString("name"));
				user.setUsername(res.getString("username"));
				user.setLatitude(res.getDouble("latitude"));
				user.setLongitude(res.getDouble("longitude"));
				user.setSucces(true);
			}
			else{
				user.setSucces(false);
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		return user;
	}
}
