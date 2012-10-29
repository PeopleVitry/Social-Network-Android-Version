package com.formation.projetglobal;


import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class tchat extends Activity{
	
	
//	Message monMsg= new Message("kamel", "salutation", 0, "aboude");
	
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.tchat);
	        
	        
	        Button btnloclisation= (Button)findViewById(R.id.btnlocaAmi);
	        btnloclisation.setOnClickListener(new OnClickListener() {
				
				
				public void onClick(View v) {
					Intent intent =new Intent(tchat.this, LocalisationAmi.class);
					startActivity(intent);
					
				}
			});
	       
	        Button btnenvoyer= (Button)findViewById(R.id.Envoyer);
	        btnenvoyer.setOnClickListener(new OnClickListener() {
				
				public void onClick(View v) {
					
					message();
				}
			});   
	        
	       
	        	
	        }
	
	 
	  public String message(){

		  List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(); //On crée la liste qui contiendra tous nos paramètres
		    nameValuePairs.add(new BasicNameValuePair("station_id","aboude")); //
		    nameValuePairs.add(new BasicNameValuePair("direction", "tahar"));
	        RequestServer requete = new RequestServer("http://192.168.1.102:8080/WebServiceVitry/message",nameValuePairs);
	        return requete.sendRequest();
	  }
	 }

	
	 
	 
	 
	  


