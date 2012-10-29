package android.rest.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/listStation")
public class Station_Bus_Service {
	
	ConnectSatationBus maconection=new ConnectSatationBus();
	 @GET
	 @Produces(MediaType.APPLICATION_JSON)
	// @Produces(MediaType.TEXT_XML)
	 @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Path("{todo}")
	 public List<Station_Bus> getClichedMessage(@PathParam("todo") String mavar) {
		 System.out.println(mavar);
	    	
		   // Return some cliched textual content
		    return maconection.getObjetFromBdd(mavar);
		    }
	
	
	

}
