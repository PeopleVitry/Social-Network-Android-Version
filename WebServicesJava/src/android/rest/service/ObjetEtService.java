package android.rest.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONObject;





@Path("/ObjetEtservice")
public class ObjetEtService {
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	//@Produces({ MediaType.TEXT_XML })
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	
	@Path("{todo}")
	
	

	
	  public List<Objet> getObjetEtService(@PathParam("todo") String var) {
		System.out.println(var);
		
		
		ConnectObjet maConnect=new ConnectObjet();

		  List<Objet> malistObjet=maConnect.getObjetFromBdd(var);
		  

		return malistObjet ;
		
	     }

}
