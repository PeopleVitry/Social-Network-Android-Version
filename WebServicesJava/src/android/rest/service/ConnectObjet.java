package android.rest.service;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;


public class ConnectObjet {
	
	

	public  List<Objet> getObjetFromBdd(String var) {

		List<Objet> listObjetOuService = new LinkedList<Objet>();
		try {

	        Class.forName("com.mysql.jdbc.Driver");
			System.out.println("DRIVER OK ! ");
			
			String url = "jdbc:mysql://localhost/vitrydb";
			Connection con = DriverManager.getConnection(url,"root","");
			System.out.println("Connection effective !");
			
			//Création d'un objet Statement
			Statement state = con.createStatement();
			
			//L'objet ResultSet contient le résultat de la requête SQL
			
			
			ResultSet result = state.executeQuery("select code,type, libelle from objet where type ='"+var+"';") ;
			//ResultSet result = state.executeQuery("select code,type, libelle from objet where type ='service'");
			
			while(result.next()){
	
			          int code = result.getInt("code");
			          String type =result.getString("type");
			          String libelle=result.getString("libelle");
			          Objet monObjet=new Objet(code, type, libelle);
			          listObjetOuService.add(monObjet);
			
			}
			for(Objet objet : listObjetOuService){
	        	  
	        	  System.out.println(objet.getCode());
	        	  System.out.println(objet.getType());
	        	  System.out.println(objet.getLibelle());

	          }
			
			result.close();
            state.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listObjetOuService;
	}
	
	


}
