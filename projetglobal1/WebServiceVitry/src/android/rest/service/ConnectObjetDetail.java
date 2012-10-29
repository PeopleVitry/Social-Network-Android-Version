package android.rest.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class ConnectObjetDetail {
	
	
	

	public  List<ObjetDetail> getDetailObjetFromBdd(int var ) {
	
		

		List<ObjetDetail> listObjetOuService = new LinkedList<ObjetDetail>();
		try {

	        Class.forName("com.mysql.jdbc.Driver");
			System.out.println("DRIVER OK ! ");
			
			String url = "jdbc:mysql://localhost/vitrydb";
			Connection con = DriverManager.getConnection(url,"root","");
			System.out.println("Connection effective !");
			
			//Création d'un objet Statement
			Statement state = con.createStatement();
			
			//L'objet ResultSet contient le résultat de la requête SQL
			
			ResultSet result = state.executeQuery("select id,nom,adresse,telephone,Latitude,Longitude,class_numero from interface join class on class.numero=interface.class_numero where class.numero="+var) ;
			
			while(result.next()){
	
			          int id = result.getInt("id");
			          String nom =result.getString("nom");
			          String adresse=result.getString("adresse");			          
			          String telephone=result.getString("telephone");
			          double Latitude=result.getDouble("Latitude");
			          double Longitude=result.getDouble("Longitude");
			          int class_numero = result.getInt("class_numero");
			          
			          ObjetDetail monObjet=new ObjetDetail(id, nom, adresse, telephone, Latitude, Longitude, class_numero);
			          listObjetOuService.add(monObjet);
			
			}
			for(ObjetDetail objet : listObjetOuService){
	        	  
	        	  System.out.println(objet.getId());
	        	  System.out.println(objet.getNom());
	        	  System.out.println(objet.getAdresse());
	        	  System.out.println(objet.getTelephone());
	        	  System.out.println(objet.getLatitude());
	        	  System.out.println(objet.getLongitude());
	        	  System.out.println(objet.getClass_numero());

	          }
			
			result.close();
            state.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listObjetOuService;
	}
	

}
