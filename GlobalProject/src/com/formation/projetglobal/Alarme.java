package com.formation.projetglobal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Alarme extends Activity {
	
	

	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.alarme);
	        alertDialog();
	        
	        
	        Button btnaggressionSoi= (Button)findViewById(R.id.agress_soi);
	        btnaggressionSoi.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					try{
					String sattion_id=getBssid();
					callServiceWeb("Agression_sur_soi", sattion_id);
					callServiceWebcamera();
				}
					catch(Exception e){
						e.getMessage();
					}
				}
			});
	        Button btnaccident= (Button)findViewById(R.id.accident);
	        btnaccident.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
				
					String sattion_id=getBssid();
					callServiceWeb("Agression_sur_personne", sattion_id);
					callServiceWebcamera();
                  
				}
			});
	        
	        Button btnaggressionautrui= (Button)findViewById(R.id.agressionautrui);
	        btnaggressionautrui.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
				
					String sattion_id=getBssid();
					callServiceWeb("accident", sattion_id);
					try {
					callServiceWebcamera();
					}
					catch(Exception e){
					Toast.makeText(Alarme.this,e.getMessage(), Toast.LENGTH_LONG).show();
					}
				}
				
			});
	     
	 }
	 
	 private String callServiceWeb(String  typeAlam,String station_id){
		 String typeAlarme=typeAlam;
		 String id_station=station_id;
    	
		 String jaxrsmessage="offfffffffffffffff";
						
			try {
			    HttpClient httpclient = new DefaultHttpClient();
		
			    
		    
   
   // HttpGet request = new HttpGet("http://192.168.1.101:8080/producer/resources/testing/timeAlarme-"+typeAlarme+"");
   HttpGet request = new HttpGet("http://192.168.1.103:8080/producer/resources/testing/typeAlarme-"+typeAlarme+"-id_station-"+id_station+"");	 
		 HttpResponse response = httpclient.execute(request);
		    HttpEntity entity = response.getEntity();
		    InputStream instream = entity.getContent();
		   jaxrsmessage = read(instream);
		 
		   } catch (ClientProtocolException e) {
		    e.printStackTrace();
		     } catch (IOException e) {
		    e.printStackTrace();
		  }
		
			return null;
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

	
	 
	 private String getBssid(){
		 
		 WifiManager wifi = (WifiManager)getSystemService(Context.WIFI_SERVICE);
			
		String id_reseau=wifi.getConnectionInfo().getBSSID();
	        
		//Toast.makeText(Alarme.this,id_reseau , Toast.LENGTH_LONG).show();
		 
		return id_reseau;

 
	 }
	 public void alertDialog(){
	    	
	    	AlertDialog.Builder ad= new AlertDialog.Builder(this);
	        ad.setTitle("                   Avertisement ");
	    	ad.setMessage("Cette application alerte la police, tout abus sera puni ");
	    	ad.setPositiveButton("OK", new android.content.DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					
					
				}
			});
	    	
	    	ad.show();
	    	
	    	
	    }
	 private String callServiceWebcamera(){
		 
    	
		 String j="offfffffffffffffff";
						
			try {
			    HttpClient httpclient = new DefaultHttpClient();
		
			    
		    
   
   // HttpGet request = new HttpGet("http://192.168.1.101:8080/producer/resources/testing/timeAlarme-"+typeAlarme+"");
   HttpGet request = new HttpGet("http://"+MesPreferences.adress_ip+":8080/producer/resources/camera");	 
		 HttpResponse response = httpclient.execute(request);
		    HttpEntity entity = response.getEntity();
		    InputStream instream = entity.getContent();
		   j= read(instream);
		 
		   } catch (ClientProtocolException e) {
		    e.printStackTrace();
		     } catch (IOException e) {
		    e.printStackTrace();
		  }
		
			return null;
	 } 
	

}
