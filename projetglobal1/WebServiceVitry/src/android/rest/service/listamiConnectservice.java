package android.rest.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;



@Path("/listAmiconnect")
public class listamiConnectservice {
	
	Amiconnect maconection=new Amiconnect();
	 @GET
	 @Produces(MediaType.APPLICATION_JSON)
	 //@Produces(MediaType.TEXT_XML)
	 @Path("id_utilisateur-{id_utilisateur}")
	 public List<Ami> getClichedMessage(@PathParam("id_utilisateur") int  id_utilisateur) {
	    	int id_utilisa=id_utilisateur;
		   // Return some cliched textual content
		    return maconection.getlistamiconnect(id_utilisa);
		    }

}
