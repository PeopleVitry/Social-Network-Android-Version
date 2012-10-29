package com.formation.projetglobal;

	import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
	
	public class Recherche extends Activity{

		List<ClassObjet> listClassObjet=null;
		List<ObjetDetail> listObjetDetail;
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.recherche);		
			
			Intent thisIntent = getIntent();
			String monparametre= thisIntent.getExtras().getString("monparametre");
			
			
			Spinner services = (Spinner)this.findViewById(R.id.spinner1);
			final Spinner spinerClassObjet=(Spinner)this.findViewById(R.id.spinner2);
			
			
			List<Objet> listObjet = getListObjetOuService(requetServeur(monparametre));
			ArrayAdapter<Objet> dataAdapter = new ArrayAdapter<Objet>(this,
			android.R.layout.simple_spinner_item, listObjet);
			services.setAdapter(dataAdapter);
			services.setSelected(false);
			services.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> parent, View view,
					int pos, long id) {
				// TODO Auto-generated method stub
				Objet selected = (Objet)parent.getItemAtPosition(pos);
			//	Toast.makeText(Recherche.this, selected.getLibelle(), Toast.LENGTH_LONG).show();
				
				int mavariable= selected.getCode();
			
				listClassObjet=getListClassObjet(requetServeurObjetClass(mavariable));
			
				
				 ArrayAdapter<ClassObjet> dataAdapterClassObjet = new ArrayAdapter<ClassObjet>(Recherche.this,android.R.layout.simple_spinner_item, listClassObjet);
				

				
				for(ClassObjet objet : listClassObjet){
				//	Toast.makeText(Recherche.this,objet.getType_calss() , Toast.LENGTH_LONG).show();

		          }
				spinerClassObjet.setAdapter(dataAdapterClassObjet);
				spinerClassObjet.refreshDrawableState();
			
			}

			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		
		final Spinner spinerObjetDetail=(Spinner)this.findViewById(R.id.spinner3);
	
		
		spinerClassObjet.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> parent, View view,
					int pos, long id) {
				ClassObjet selected = (ClassObjet)parent.getItemAtPosition(pos);
				//Toast.makeText(Recherche.this, selected.getType_calss(), Toast.LENGTH_LONG).show();
				
				int mavariable= selected.getNumero();
			
				
				listObjetDetail = getListObjetDetail(requetServeurObjetDetail(mavariable));
				listObjetDetail.add(0, new ObjetDetail(0, "clicker pour choisir", "", "", 0, 0, 0));
				
			ArrayAdapter<ObjetDetail> dataAdapterObjetDetail = new ArrayAdapter<ObjetDetail>(Recherche.this,android.R.layout.simple_spinner_item, listObjetDetail);
			spinerObjetDetail.setAdapter(dataAdapterObjetDetail);
			spinerObjetDetail.refreshDrawableState();
				
			}

			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		
		//?????????????????????????????????????????
		
		spinerObjetDetail.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> parent, View view,
					int pos, long id) {
				
			ObjetDetail selected = (ObjetDetail)parent.getItemAtPosition(pos);
			
			if (selected.getId()!=0) {
			Intent monintent= new Intent(Recherche.this, MapObjet.class);
			
			selected.getLatitude();
			selected.getLongitude();
			monintent.putExtra("latitude",selected.getLatitude());
			monintent.putExtra("Longitude",selected.getLongitude());
			startActivity(monintent);
			}
			
			
				
			}

			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	
		//???????????????????????????????????????????
		
		
		Button btnAmis= (Button)findViewById(R.id.btnAmiRech);
		btnAmis.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				//Toast.makeText(Recherche.this, "tu es la sur mapObjet", Toast.LENGTH_LONG).show();
				Intent monIntent=new Intent(Recherche.this, Listedamis.class);
				startActivity(monIntent);
				
			}
		});
		
	Button btnobjetRech= (Button)findViewById(R.id.btnObjetRech);
	btnobjetRech.setOnClickListener(new OnClickListener() {
		
		public void onClick(View v) {
			Intent monintent2=new Intent(Recherche.this,Recherche.class) ;
			monintent2.putExtra("monparametre","objet");
			startActivity(monintent2);
			
		}
	});
	Button btnServiceRech= (Button)findViewById(R.id.btnServiceRech);
	btnServiceRech.setOnClickListener(new OnClickListener() {
		
		public void onClick(View v) {
			Intent monintent2=new Intent(Recherche.this,Recherche.class) ;
			monintent2.putExtra("monparametre","service");
			startActivity(monintent2);
			
		}
	});
	
		
	}
		
		
		//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		
		
		
		public String  requetServeurObjetDetail(int var){
		  	String jaxrsmessage="offfffffffffffffff";
			
			
				try {
				    HttpClient httpclient = new DefaultHttpClient();
			
				    HttpGet request = new HttpGet("http://"+MesPreferences.adress_ip+":4454/WebServiceVitry/ObjetDetailService/"+var);
			
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
		 public ArrayList<ObjetDetail>  getListObjetDetail(String  jsonString){
		    	
			    
			   ArrayList<ObjetDetail> malisteObjet= new ArrayList<ObjetDetail>();
			    	
			    	
			    	try {
			    		
			    		JSONObject json = new JSONObject(jsonString);
			    		
			    JSONArray jArray = json.getJSONArray("objetDetail");
			    		
			    	for (int i = 0; i < jArray.length(); i++) {
							
							JSONObject jsonObject = jArray.getJSONObject(i);	
						
							ObjetDetail monClassObjet=new ObjetDetail(jsonObject.getInt("id"), jsonObject.getString("nom"), jsonObject.getString("adresse"),
									jsonObject.getString("telephone"), jsonObject.getDouble("latitude"), jsonObject.getDouble("longitude"), jsonObject.getInt("class_numero"));
							malisteObjet.add(monClassObjet);
							
							
						}
						
					} catch (Exception e) {
						e.printStackTrace();
					}
			    	
			    	
					return malisteObjet;
			    	
				 	   	
			    }
			//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		
		///////////////////
		
		//fonction qui me revoi la lise des service à parssée
		 public String requetServeur(String mavar){
		    	
		    	String jaxrsmessage="offfffffffffffffff";
		
		
			try {
			    HttpClient httpclient = new DefaultHttpClient();
		
			    HttpGet request = new HttpGet("http://"+MesPreferences.adress_ip+":4454/WebServiceVitry/ObjetEtservice/"+mavar);
		
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
		// fonction ulilisé par requetserveur pour lire instream
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
		 
		 public ArrayList<Objet>  getListObjetOuService(String  jsonString){
		    	
			    
			   ArrayList<Objet> malisteObjet= new ArrayList<Objet>();
			    	
			    	
			    	try {
			    		
			    		JSONObject json = new JSONObject(jsonString);
			    		
			           JSONArray jArray = json.getJSONArray("objet");
			    		
			    	   for (int i = 0; i < jArray.length(); i++) {
							
							JSONObject jsonObject = jArray.getJSONObject(i);	
							Objet monObjet=new Objet(jsonObject.getInt("code"), jsonObject.getString("type"), jsonObject.getString("libelle"));
							malisteObjet.add(monObjet);
							
							
						}
						
					} catch (Exception e) {
						e.printStackTrace();
					}
			    	
			    	
					return malisteObjet;
			    	
				 	   	
			    }
		
	//*************************************************************
		//requet serveur pour ObjetClass
		public String  requetServeurObjetClass(int var){
		  	String jaxrsmessage="offfffffffffffffff";
			
			
				try {
				    HttpClient httpclient = new DefaultHttpClient();
			
				    HttpGet request = new HttpGet("http://"+MesPreferences.adress_ip+":4454/WebServiceVitry/ClassObjet/"+var);
			
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
		
		 public ArrayList<ClassObjet>  getListClassObjet(String  jsonString){
		    	
			    
			   ArrayList<ClassObjet> malisteObjet= new ArrayList<ClassObjet>();
			    	
			    	try {
			    		
			    		JSONObject json = new JSONObject(jsonString);
			    		
			    JSONArray jArray = json.getJSONArray("classObjet");
			    		
			    	for (int i = 0; i < jArray.length(); i++) {
							
							JSONObject jsonObject = jArray.getJSONObject(i);	
						
							ClassObjet monClassObjet=new ClassObjet(jsonObject.getInt("numero"), jsonObject.getString("type_calss"), jsonObject.getInt("objet_code"));
							malisteObjet.add(monClassObjet);
							
							
						}
						
					} catch (Exception e) {
						e.printStackTrace();
					}
			    	
			    	
					return malisteObjet;
			    	
				 	   	
			    }
		
		 
	////////////************************************************	 
		
		
}
