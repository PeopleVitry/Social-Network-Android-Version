package android.rest.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;


public class ConnectClass {
	
	
	
	
	public  List<ClassObjet> getClassFromBdd(int var) {

		List<ClassObjet> listObjetOuService = new LinkedList<ClassObjet>();
		try {

	        Class.forName("com.mysql.jdbc.Driver");
			System.out.println("DRIVER OK ! ");
			
			String url = "jdbc:mysql://localhost/vitrydb";
			Connection con = DriverManager.getConnection(url,"root","");
			System.out.println("Connection effective !");
			
			//Création d'un objet Statement
			Statement state = con.createStatement();
			
			//L'objet ResultSet contient le résultat de la requête SQL
			
			ResultSet result = state.executeQuery("select numero, type_class ,objet_code from class join objet on class.objet_code=objet.code where objet_code="+var) ;
			
			while(result.next()){
	
			          int numero = result.getInt("numero");
			          String type_calss =result.getString("type_class");
			          int objet_code=result.getInt("objet_code");
			          ClassObjet monClassObjet=new ClassObjet(numero, type_calss, objet_code);
			          listObjetOuService.add(monClassObjet);
			
			}
			for(ClassObjet objet : listObjetOuService){
	        	  
	        	  System.out.println(objet.getNumero());
	        	  System.out.println(objet.getType_calss());
	        	  System.out.println(objet.getObjet_code());

	          }
			
			result.close();
            state.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listObjetOuService;
	}

	

}
