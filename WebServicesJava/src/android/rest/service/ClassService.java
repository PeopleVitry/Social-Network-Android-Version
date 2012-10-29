package android.rest.service;



import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;



@Path("/ClassObjet")
public class ClassService {
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	//@Produces({ MediaType.TEXT_XML})
	@Path("{todo}")
	  public List<ClassObjet> getObjetEtService(@PathParam("todo") int id) {
		 
		ConnectClass maConnect=new ConnectClass();

		  List<ClassObjet> malistObjet=maConnect.getClassFromBdd(id);
		  

		return malistObjet ;
		
	     }
	

}
