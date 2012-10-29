
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
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class Map extends MapActivity {
	public double latitude;
    public double longitude;
    public float distance;
    public static String adress_ip ;
    public double latA;
    public double lngA;
	public static LocationManager locationManager;
    public static String locationProvider = LocationManager.NETWORK_PROVIDER; 
	public  static int id_utilisateur= 35;
	private MapView	mapView;
	private String mail_utilisateur;
	private ExpandableListView expandableList1;
	private int tailleliste=0;
	
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		monthread();
		NotificationMessage.initConnection(this);
		getPreferences();
		MesPreferences.GETADRESSE(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapviewgenerale); 
              
       ImageButton prefBtn = (ImageButton) findViewById(R.id.imageButtonconf);
        prefBtn.setOnClickListener(new OnClickListener() {

                public void onClick(View v) {
                       Intent settingsActivity = new Intent(Map.this, MesPreferences.class);
                      startActivity(settingsActivity);
                }
        });
        
        //////////////////////SOS//////////////////////
        
        ImageButton SOSBtn = (ImageButton) findViewById(R.id.imageButtonSOS);
        SOSBtn.setOnClickListener(new OnClickListener() {

                public void onClick(View v) {
                       Intent settingsActivity = new Intent(Map.this, Alarme.class);
                      startActivity(settingsActivity);
                }
        });
        
        
        
          
        
        //////////////////////////////////////////////
        
        ///////////////////////////    
        
          SharedPreferences myPrefs = this.getSharedPreferences("myPrefs", MODE_WORLD_READABLE);
             mail_utilisateur = myPrefs.getString("email", "nothing");
             ArrayList<Ami> listpourId =getIdAmiJson(requetServeurid_utilisateur());
             
    	      	   
    	   id_utilisateur=listpourId.get(0).getGuid();
    	   
      
       
        
            String locationContext = Map.LOCATION_SERVICE;
        
      // fonction qui active mon statut de connexion et le rend connecté  
        String b= requetServeur(1);
   
  
       
        locationManager = (LocationManager) getSystemService(locationContext);
        if (locationManager != null && locationProvider != null) {
        majCoordonnes(); 
        locationManager.requestLocationUpdates(locationProvider, 0,0, new MajListener());
   		}
        
        
        ImageButton btnBusEtMeteo=(ImageButton)findViewById(R.id.imageButtonratp);
        btnBusEtMeteo.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent intent1 = new Intent(Map.this,BusEtService.class);
				startActivity(intent1);
			}
		});

        ArrayList<Station_Bus> malisteStation;
        
     
        mapView = (MapView) this.findViewById(R.id.mapView);
        mapView.setBuiltInZoomControls(true);
        // pour passer a la liste d'amis
     OnClickListener btnlistener1= new OnClickListener() {
			
			public void onClick(View actuelView) {
				
				Intent intent1 = new Intent(Map.this,Listedamis.class);
				intent1.putExtra("id_utilisateur",id_utilisateur);
		   startActivity(intent1);
			}
   };
   super.onCreate(savedInstanceState);

               Button listAmi = (Button)this.findViewById(R.id.button4);
               listAmi.setOnClickListener(btnlistener1);
             
               OnClickListener btnlistener2= new OnClickListener() {
	
	      public void onClick(View actuelView) {
	 	
		Intent monintent=new Intent(Map.this,Recherche.class) ;
		monintent.putExtra("monparametre","service");
		startActivity(monintent);
	  }
    };
    super.onCreate(savedInstanceState);

      Button acceder2 = (Button)this.findViewById(R.id.button1);
      acceder2.setOnClickListener(btnlistener2);
      ArrayList<Ami> malistamisss= getlistAmiConnecte(requetServeur());
        
      int intLatitude = (int) (latitude * 1E6) ;
      int intLongitude = (int)(longitude *1E6) ;
      
      
        List<Overlay> mapOverlays = mapView.getOverlays();  

                Drawable drawable = this.getResources().getDrawable(R.drawable.ic_launcher);  

                CustomItemizedOverlay itemizedOverlay =   

                new CustomItemizedOverlay(drawable, this);  
                GeoPoint point = new GeoPoint(intLatitude, intLongitude);  
                OverlayItem overlayitem =   

                     new OverlayItem(point, "Hello", "je suis à Vitry, Paris!");
              
              itemizedOverlay.addOverlay(overlayitem); 
              mapOverlays.add(itemizedOverlay);  
              MapController mapController = mapView.getController();  
              mapController.animateTo(point);  

              mapController.setZoom(14);
              /////////////////////////////////////////////////
              
              for (Ami objet : malistamisss){
            	  
              if ((objet.getLatitude()!=0) && (objet.getLongitude()!=0 && (objet.getConnecte()==1))){
              int intlatA= (int) (latA=objet.getLatitude() * 1E6) ;
              int intlongA = (int)(lngA=objet.getLongitude() *1E6) ; 
              Location amiLocation = new Location("ami location");
              amiLocation.setLatitude(objet.getLatitude());
              amiLocation.setLongitude(objet.getLongitude());
              
              Location locationB = new Location("point B");

              locationB.setLatitude(latitude);
              locationB.setLongitude(longitude);
             
              distance = locationB.distanceTo(amiLocation);
              if (distance<MesPreferences.distanceami) 
            	  {
                
            	  
            	  
            	  
            	  
            	  
              Drawable drawableami = this.getResources().getDrawable(R.drawable.marker);  

              CustomItemizedOverlay itemizedOverlayami =   

              new CustomItemizedOverlay(drawableami, this);  
              GeoPoint pointami = new GeoPoint(intlatA, intlongA);  
              OverlayItem overlayitemami =   

                   new OverlayItem(pointami, "Hello", "je suis à Vitry, Paris!");
            
            itemizedOverlayami.addOverlay(overlayitemami); 
            mapOverlays.add(itemizedOverlayami); 
            
            MapController mapController1 = mapView.getController();  
            mapController.animateTo(pointami);  

            mapController1.setZoom(14);
            ((Vibrator) getSystemService(Map.VIBRATOR_SERVICE)).vibrate(1000);
            
/////////////////notification ami dans la zone////////////////////
        	  
				///////////////////////////////////////////////////////////
              
              }
              else{
            	  
            	  
            	  //Toast.makeText(Map.this, ""+distance, Toast.LENGTH_LONG).show();
            	  
            	  
              }
	}
              
              }         
              
              
              Button btnObjet = (Button)this.findViewById(R.id.button6);
              btnObjet.setOnClickListener(new OnClickListener() {
					
				public void onClick(View v) {
					Intent monintent2=new Intent(Map.this,Recherche.class) ;
					monintent2.putExtra("monparametre","objet");
					startActivity(monintent2);
					
				}
			});
              
              
              
	}
              
     ///////////////////////////////////////////////////////
              ///////////////////////threared presence ami////////////////////////
	  public void monthread(){
          Thread monthread=new Thread(new Runnable() {
        	  	 
        	 	@Override
        	  	 
        	  	public void run() {
        	 		 
       	  	while(true){
    			
    			try {
					Thread.sleep(5000);
					ArrayList<Ami> mmmmmmm= getlistAmiConnecte(requetServeurAmiConnect());
      	  		     if (mmmmmmm.size()>tailleliste){
      	  		    //	 Toast.makeText(Map.this, ""+tailleliste, Toast.LENGTH_LONG).show();
      	  			 ((Vibrator) getSystemService(Map.VIBRATOR_SERVICE)).vibrate(1000);
      	  			 tailleliste=mmmmmmm.size();
      	  			 ////notif ami
      	  			for (Ami objet : mmmmmmm){
      	  			String ns = Context.NOTIFICATION_SERVICE;
    				NotificationManager mNotificationManager = (NotificationManager)Map.this.getSystemService(ns);
    				
    				int icon = R.drawable.ic_stat_name;
    				CharSequence tickerText = "L'ami(e) "+objet.getNom()+" est à coté !!!";
    				long when = System.currentTimeMillis();

    				Notification notification = new Notification(icon, tickerText, when);
    				
    				CharSequence contentTitle = objet.getNom();
    				CharSequence contentText =  "L'ami(e) "+objet.getNom()+"est à coté !!!";
    				Intent notificationIntent = new Intent(Map.this, JaberActivity.class);
    				//notificationIntent.putExtra("email", fromName);
    				//notificationIntent.putExtra("contact_guid", contact_guid);
    				PendingIntent contentIntent = PendingIntent.getActivity(Map.this, 0, notificationIntent, 0);


    				notification.setLatestEventInfo(Map.this, contentTitle, contentText, contentIntent);
    				
    				final int HELLO_ID = 1;

    				mNotificationManager.notify(HELLO_ID, notification);
      	  			}
      	  		 }
      	  		     else{
      	  		    	tailleliste=mmmmmmm.size();
      	  		    	 
      	  		     }
					
					
				     } catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				                                       }
    			            }
        	 	}
        	  	 
        	  	});
          monthread.start();
          
          }
	  public String requetServeurAmiConnect(){
	    	
	    	String jaxrsmessage="offfffffffffffffff";
	
	
		try {
		    HttpClient httpclient = new DefaultHttpClient();

	   HttpGet request = new HttpGet("http://"+MesPreferences.adress_ip+":4454/WebServiceVitry/listAmiconnect/id_utilisateur-"+id_utilisateur+"");
	
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
	 
	////////////////////////////////////////////////////////////////////////////////////
    
	public void majCoordonnes() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Fournisseurs :");
		stringBuilder.append("\n");
		stringBuilder.append(locationProvider);
		stringBuilder.append(" : ");
		Location location =
		locationManager.getLastKnownLocation(locationProvider);
		if (location != null) {
			Map p=new Map();
		p.latitude=latitude;
		p.longitude=longitude;
		
		latitude  = location.getLatitude();

		longitude = location.getLongitude();
		
		// mise a jour de ma position dans la base de données
		miseAjourPosition(id_utilisateur, latitude, longitude);
		stringBuilder.append(latitude);
		stringBuilder.append(", ");
		stringBuilder.append(longitude);
		
        Location locationA = new Location("point A");
         latA =48.8 ;
         lngA =2.3667 ;
        locationA.setLatitude(latA);
        locationA.setLongitude(lngA);

        Location locationB = new Location("point B");

        locationB.setLatitude(latitude);
        locationB.setLongitude(longitude);

         float distance = locationA.distanceTo(locationB);
         
		} else {
		
			stringBuilder.append("Non déterminée");
			}
		
			Log.d("MaPositionMaj", stringBuilder.toString());
			}
			/**
			* Écouteur utilisé pour les mises à jour des coordonnées.
			*/
			
	
	
	@Override
	protected boolean isRouteDisplayed(){
	
		return false;
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event){
	
		if (keyCode == KeyEvent.KEYCODE_S){
		
			mapView.setSatellite(!mapView.isSatellite());
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	class MajListener implements android.location.LocationListener { 
		public void onLocationChanged(Location location) { 
		majCoordonnes();
		}

		
		
		
        public void onProviderDisabled(String provider) {
         // TODO Auto-generated method stub

             }
         public void onProviderEnabled(String provider) {
         // TODO Auto-generated method stub

           }
        public void onStatusChanged(String provider, int status, Bundle extras) {
         // TODO Auto-generated method stub

            }
		}


	/*fonction qui récupere la station de bus  cette fonction et apellé depuis la classe BusEtService*/
	 public String requetServeurStation(String mavar){
	    	
	    	String jaxrsmessage="offfffffffffffffff";
	
	
		try {
		    HttpClient httpclient = new DefaultHttpClient();
	
		    HttpGet request = new HttpGet("http://"+MesPreferences.adress_ip+":4454/WebServiceVitry/listStation/"+mavar);
	
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
	 // fonction qui lit le intream
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
	 //fonction qui parse la liste des station de bus 
	 public ArrayList<Station_Bus>  getListStation_Bus(String  jsonString){
	    
		 JSONObject jObject;
		 ArrayList<Station_Bus> malisteStation= new ArrayList<Station_Bus>();
		    	
		    	
		    	
		    	try {
		    		jObject = new JSONObject(jsonString);
		    		JSONObject Object = jObject.getJSONObject("stationBus");
		    		
		    		
		    		Station_Bus monObjet=new Station_Bus(Object.getString("id_station"), Object.getString("numero_ligne_A"), Object.getInt("bus_A"), 
		    				Object.getString("destination_A"), Object.getString("nom_destination_A"), Object.getString("numero_ligne_B"),Object.getInt("bus_B"), Object.getString("destination_B"),
		    				Object.getString("nom_destination_B"), Object.getString("numero_ligne_C"),
		    				Object.getInt("bus_C"), Object.getString("destination_C"), Object.getString("nom_destination_C"), Object.getString("adresse"));

						malisteStation.add(monObjet);
						
					
				//	}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
		    	
		    	
				return malisteStation;
		    	
			 	   	
		    }
        
        
      
        
		protected void onDestroy() {
		//// mettre le statut de connection a zéro si je quitte l'application
		String aaaaaaaaaa= requetServeur(0);
			
			super.onDestroy();
		}
	 
	 
	// mettre le statut de connection a zéro si je quitte l'application
	 public String requetServeur(int var){
		 
			
		 String jaxrsmessage="offfffffffffffffff";
			
			
			try {
			    HttpClient httpclient = new DefaultHttpClient();
		
			    HttpGet request = new HttpGet("http://"+MesPreferences.adress_ip+":4454/WebServiceVitry/books/name-"+var+"-editor-"+id_utilisateur+"");
		
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
	 
	 //fonction pour mettre a jour la position de l'utilisateur dans la base
	 private String miseAjourPosition (int id_utilisateur,double latitude,double longitude ){
		 
		
		 String jaxrsmessage="offfffffffffffffff";
			
			
			try {
			    HttpClient httpclient = new DefaultHttpClient();
		
			    HttpGet request = new HttpGet("http://"+MesPreferences.adress_ip+":4454/WebServiceVitry/position/guid-"+id_utilisateur+"-latitude-"+latitude+"-longitude-"+longitude+"");
			   
		
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
	 
	  public ArrayList<Ami>  getlistAmiConnecte(String  jsonString){
	    	
		    
		   ArrayList<Ami> malisteami= new ArrayList<Ami>();
		    	
		    	
		    	try {
		    		
		    		JSONObject json = new JSONObject(jsonString);
		    		
		    		JSONArray jArray = json.getJSONArray("ami");
		    		
		    	for (int i = 0; i < jArray.length(); i++) {
						
						JSONObject jsonObject = jArray.getJSONObject(i);	
				Ami monami=new Ami(jsonObject.getString("nom"), jsonObject.getString("prenom"), jsonObject.getString("email"),
								jsonObject.getDouble("latitude"), jsonObject.getDouble("longitude"),jsonObject.getInt("connecte"));
						malisteami.add(monami);
						
						
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
		    	
		    	
				return malisteami;
		    	
			 	   	
		    }
	  
	  // fonction qui récupere la liste ami
		 public String requetServeur(){
		    	
		    	String jaxrsmessage="offfffffffffffffff";
		
		
			try {
			    HttpClient httpclient = new DefaultHttpClient();
	
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
		 /////
		// récuperer l'id utilisateur 
	public String requetServeurid_utilisateur(){
		    	
		    	String jaxrsmessage="offfffffffffffffff";
		
		
			try {
			    HttpClient httpclient = new DefaultHttpClient();
		
	   HttpGet request = new HttpGet("http://"+MesPreferences.adress_ip+":4454/WebServiceVitry/getIdUtilisateur/Mail-"+mail_utilisateur+"");
		     
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
	
	 public ArrayList<Ami>  getIdAmiJson(String  jsonString){
	    	
		    
		   ArrayList<Ami> malisteami= new ArrayList<Ami>();
		    	
		    	
		    	try {
		    		
		    		JSONObject json = new JSONObject(jsonString);
		    		
		    		JSONArray jArray = json.getJSONArray("ami");
		    		
		    	for (int i = 0; i < jArray.length(); i++) {
						
				JSONObject jsonObject = jArray.getJSONObject(i);	
					
				Ami monami=new Ami(jsonObject.getInt("guid"), jsonObject.getString("nom"), jsonObject.getString("prenom"),
				jsonObject.getString("email"), jsonObject.getDouble("latitude"), jsonObject.getDouble("longitude"), jsonObject.getInt("connecte"));
						
				malisteami.add(monami);
						
						
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
		    	
		    	
				return malisteami;
		    	
			 	   	
		    }
	public float gatDistance(Location A, Location B)
	{
		return A.distanceTo(B);
	}
	private   void getPreferences() {

		 SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
		
		MesPreferences.adress_ip= preferences.getString("adress_ip", "nothing" );
		
		MesPreferences.distanceami=Float.parseFloat(preferences.getString("distanceami", "5000")) ;
		
		MesPreferences.login=preferences.getString("login", "nothing" );
		
		MesPreferences.password=preferences.getString("password", "password" );
		
	
		
	
	
	}

	
}
