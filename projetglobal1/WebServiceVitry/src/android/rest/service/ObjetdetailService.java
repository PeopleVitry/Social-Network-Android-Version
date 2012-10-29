package android.rest.service;



import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/ObjetDetailService")
public class ObjetdetailService {
	
	

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	//@Produces({ MediaType.TEXT_XML })
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("{todo}")
//	public String getTodo(@PathParam("todo") String id) {
//		return id;
//	}
	  public List<ObjetDetail> getObjetEtService(@PathParam("todo") int id) {
		 
		ConnectObjetDetail maConnect=new ConnectObjetDetail();

		  List<ObjetDetail> malistDetailObjet=maConnect.getDetailObjetFromBdd(id);
		  

		return malistDetailObjet ;
		
	     }
	

}
