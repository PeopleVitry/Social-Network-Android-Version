package com.formation.projetglobal;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ExpandableListView;
import android.widget.Toast;

public class Listedamis extends Activity {
	public static  int id_utilisateur;
	public static ExpandableListView expandableList = null;
//private int  id_utilisateur=35;
	public static Listedamisdapter1 adapter;
//////////////////////////////////HANDLER///////////////////////////////////////////

/*	private   Handler handler = new Handler() {

    	//private int id_utilisateur;

		public void handleMessage(android.os.Message msg) {

    	     if(msg.what == 0) {
    	    	
    	    	 
    	    getlistAmiConnecte(requetServeur(id_utilisateur))	; 
    	    	 
    	    	// Toast.makeText(Listedamis.this, "coucou", Toast.LENGTH_LONG).show();
    	    	 
    	    	 
    	    	 
    	    	 
 	      

    	                       }
    	     
    	      if(msg.what == 1){
    	    	 
    	    	 ArrayList<Ami> malistamisss1= getlistAmiConnecte(requetServeur(id_utilisateur));
    	  		ArrayList<Groupe> groupes = new ArrayList<Groupe>();
    	      	Groupe groupe = new Groupe("Liste d'Amis Connectés" );

    	  			groupe.setObjets(malistamisss1);

    	  			groupes.add(groupe);
    	  		

    	  			Listedamisdapter1 adapter = new Listedamisdapter1(Listedamisdapter1.context, groupes);

    	  		expandableList.setAdapter(adapter);
    	    	  
    	    	 
    	    	  
    	  		// Toast.makeText(Listedamis.this, "coucou22", Toast.LENGTH_LONG).show();
    	    	  
    	    	  
    	    	  
    	    	  
    	    	  
    	    	  
    	                       }

    	                                                  };

    	                                        };*/
	
	
	
	
	
	
	
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		/////gestion des threads//////
	/*	new Thread(new Runnable() {
      	  
        	   public void run() {
        		   traitementlistami();
        		   traitementconnect();
                
        	                     }

        	                   }).start();*/
		super.onCreate(savedInstanceState);
		
		
		/////////////////gestion des threads//////////////////
		
		
		
		
		setContentView(R.layout.mainlistedamis);
		Intent thisIntent = getIntent();
		 id_utilisateur= thisIntent.getExtras().getInt("id_utilisateur");

		expandableList = (ExpandableListView) findViewById(R.id.expandableView);
		
		ArrayList<Ami> malistamisss= getlistAmiConnecte(requetServeur(id_utilisateur));
		ArrayList<Groupe> groupes = new ArrayList<Groupe>();
    	Groupe groupe = new Groupe("Liste d'Amis Connectés" );

			groupe.setObjets(malistamisss);

			groupes.add(groupe);
		

		 adapter = new Listedamisdapter1(this, groupes);

		expandableList.setAdapter(adapter);
	}
	/////////////thread listami/////////////////////
	
/*private void traitementlistami() {
		
		Thread listamiThread =
		 new Thread(new Runnable() {

	        	public void run() {
	        		
	        			while(true){
	        			handler.sendEmptyMessage(0);
	        			try {
							Thread.sleep(5000);
						     } catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						                                       }
	        			            }
	        		
	        	                  }

	        	                  });
		
		listamiThread.start();
	
		
		
	                               }
	
	
	/////////////////////thread changement de statut///////////////////


private void traitementconnect() {
	
	Thread connecthread =
	 new Thread(new Runnable() {

        	public void run() {
        		while(true){
        			handler.sendEmptyMessage(1);
        			try {
						Thread.sleep(5000);
					     } catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					                                       }
        			            }
        		
        		
        	                  }

        	                  });
	
	connecthread.start();

	
	
                               }*/

	
	//////////////////////////////////
	
	 public String requetServeur( int id_utilisateur){
	    	
	    	String jaxrsmessage="offfffffffffffffff";
	
	
		try {
		    HttpClient httpclient = new DefaultHttpClient();
	//    HttpGet request = new HttpGet("http://10.10.120.239:8080/AndroidJAX-RS/allo");
	   HttpGet request = new HttpGet("http://"+MesPreferences.adress_ip+":4454/WebServiceVitry/listAmi/id_utilisateur-"+id_utilisateur+"");
	
		//request.addHeader("Accept", "text/html");
		//request.addHeader("Accept", "text/xml");
		//request.addHeader("Accept", "text/plain");      
	    HttpResponse response = httpclient.execute(request);
	    HttpEntity entity = response.getEntity();
	    InputStream instream = entity.getContent();
	   jaxrsmessage = read(instream);
	  
	
	
	   } catch (ClientProtocolException e) {
	    e.printStackTrace();
	     } catch (IOException e) {
	    e.printStackTrace();
	  }
		return jaxrsmessage;	
	    	
	
	    	
	    }
	   private static String read(InputStream instream) {
	        StringBuilder sb = null;
	        try {
	             sb = new StringBuilder();
	             BufferedReader r = new BufferedReader(new InputStreamReader(
	                       instream));
	        for (String line = r.readLine(); line != null; line = r.readLine()) {
	             	sb.append(line);
				}
	
				instream.close();
	
	        } catch (IOException e) {
	        }
	        return sb.toString();
	
	   }
	   
	   public ArrayList<Ami>  getlistAmiConnecte(String  jsonString){
	    	
		    
		   ArrayList<Ami> malisteami= new ArrayList<Ami>();
		    	
		    	
		    	try {
		    		
		    		JSONObject json = new JSONObject(jsonString);
		    		
		    		JSONArray jArray = json.getJSONArray("ami");
		    		
		    	for (int i = 0; i < jArray.length(); i++) {
						
						JSONObject jsonObject = jArray.getJSONObject(i);	
					//	Ami monami=new Ami(jsonObject.getString("nom"), jsonObject.getString("prenom"), jsonObject.getString("email"));
						Ami monami=new Ami(jsonObject.getInt("guid"),jsonObject.getString("nom"), jsonObject.getString("prenom"), jsonObject.getString("email"),
								jsonObject.getDouble("latitude"), jsonObject.getDouble("longitude"),jsonObject.getInt("connecte"));
						malisteami.add(monami);
						
						
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
		    	
		    	
				return malisteami;
		    	
			 	   	
		    }
	 
	/////////////////////////////////
	
	
}