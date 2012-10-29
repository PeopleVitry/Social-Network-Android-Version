package com.formation.projetglobal;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;



import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

public class BusEtService extends Activity {
	
	
	TextView  now_busA ;
   	TextView now_busB ;
    TextView next_busA;
    TextView next_busB;
    TextView now_busC ;
    TextView next_busC ;
    TextView busA;
    TextView busB;
    TextView busC;
    TextView destin_busA;
    TextView destin_busB;
    TextView destin_busC;
    
    
    TextView indiceauj , indicedem , polluantauj , polluantdem , niveauauj , niveaudem ;
    

    
	private   Handler handler = new Handler() {

    	public void handleMessage(android.os.Message msg) {

    	     if(msg.what == 0) {
    	    	 
    	    	 
    	       WifiManager wifi = (WifiManager)getSystemService(Context.WIFI_SERVICE);
    	        
    	         
    	         String idReseau=wifi.getConnectionInfo().getBSSID();
    	         //Toast.makeText(BusEtService.this, ""+idReseau, Toast.LENGTH_LONG).show();
    	         if((MesPreferences.bssid1.equals(idReseau)) ||  (MesPreferences.bssid2.equals(idReseau))){
    	        	 //Toast.makeText(BusEtService.this, ""+MesPreferences.bssid1+MesPreferences.idstationbus1, Toast.LENGTH_LONG).show();
    	        	//String idReseau1=wifi.getConnectionInfo().getBSSID();
     	    		ArrayList<Station_Bus> malisteStation;
        	         Map monPronProjetglobalActivity= new Map();
        	         malisteStation= monPronProjetglobalActivity.getListStation_Bus(monPronProjetglobalActivity.requetServeurStation(idReseau));
        	         
        	      //Toast.makeText(BusEtService.this, ""+malisteStation.size(), Toast.LENGTH_LONG).show();
        	               String message= malisteStation.get(0).getNumero_ligne_A()+","+(malisteStation.get(0).getNumero_ligne_B()+","+malisteStation.get(0).getNumero_ligne_C());
        	    	
        	               //Toast.makeText(BusEtService.this, ""+malisteStation.get(0).getNom_destination_A(), Toast.LENGTH_LONG).show();
     	      //setTpsAttente(getTpsAttente("293_925_1003,132_581_591","A"),"A");
        	    	String destination= malisteStation.get(0).getDestination_A();
        	    	//Toast.makeText(BusEtService.this, destination, Toast.LENGTH_LONG).show();
     	     //setTpsAttente(getTpsAttente(message,destination),"A");
        	    	
     	     setTpsAttente(getTpsAttente(message,destination),destination);
     	     
     	     busA.setText(""+malisteStation.get(0).getBus_A());
     	        busB.setText(""+malisteStation.get(0).getBus_B());
     	        busC.setText(""+malisteStation.get(0).getBus_C());
     	        
     	        destin_busA.setText(malisteStation.get(0).getNom_destination_A());
     	        destin_busB.setText(malisteStation.get(0).getNom_destination_B());
     	        destin_busC.setText(malisteStation.get(0).getNom_destination_C());
        	        	
    	        	 
    	        	 
    	    	}
    	    	else  {
    	    		setTpsAttente(getTpsAttente(""+MesPreferences.idstationbus1+","+MesPreferences.idstationbus2+"",""+MesPreferences.direction+""),""+MesPreferences.direction+"");
    	    	       // if(idReseau!=null){
    	    	        	 busA.setText(""+MesPreferences.numligne1+"");
    	    	             busB.setText(""+MesPreferences.numligne2+"");
    	    	             busC.setText(""+MesPreferences.numligne3+"");
    	    	             
    	    	             destin_busA.setText(""+MesPreferences.terminusbus1+"");
    	    	             destin_busB.setText(""+MesPreferences.terminusbus2+"");
    	    	             destin_busC.setText(""+MesPreferences.terminusbus3+"");
    	    	         
    	    		
    	    	}
    	        ///}
    	        //else
    	        	//setTpsAttente(getTpsAttente("293_925_1003,132_581_591","A"),"A");
    	        
    	        
    	        
    	                       }
    	     
    	      if(msg.what == 1){
    	    	  setPollution(getPollution());
    	    	  
    	    	  
    	                       }

    	                                                  };

    	                                        };
	
    	                                        
	
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		
		
	 	 
        	  
        	 new Thread(new Runnable() {
            	  
           	   public void run() {
           		traitementBus();
                   traiementPollution();
           	                     }

           	                   }).start();
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interfaceapplicationvitryhuborigine); 
        
        // Instanciation du WebView...
        WebView wvSite = (WebView)findViewById(R.id.webmeteo);
        WebView wvSite1 = (WebView)findViewById(R.id.webView1);
      
        //...on active JavaScript...
        WebSettings webSettings = wvSite.getSettings();
        WebSettings webSettings1 = wvSite1.getSettings();
        webSettings1.setBuiltInZoomControls(true);
        webSettings.setJavaScriptEnabled(true);
         
         //...et on charge la page
        wvSite.loadUrl("http://weathersticker.wunderground.com/weathersticker/cgi-bin/banner/ban/wxBanner?bannertype=wu_clean2day_metric_cond&airportcode=LFPO&ForcedCity=Vitry-sur-Seine&ForcedState=&wmo=07149&language=FR");
        wvSite1.loadUrl("http://feedget.infotrafic.com/img.php?nom=idf&type=L&widget_rand=91857");
        now_busA = (TextView)this.findViewById(R.id.now_BusA);  
        now_busB = (TextView)this.findViewById(R.id.now_BusB);
        next_busA = (TextView)this.findViewById(R.id.next_BusA);
        next_busB = (TextView)this.findViewById(R.id.next_BusB);
        now_busC = (TextView)this.findViewById(R.id.now_BusC);
        next_busC = (TextView)this.findViewById(R.id.next_BusC);


     
        
      indiceauj= (TextView)this.findViewById(R.id.indiceauj); 
  	  indicedem= (TextView)this.findViewById(R.id.indicedem);  
  	  polluantauj= (TextView)this.findViewById(R.id.polluantauj); 
  	  polluantdem= (TextView)this.findViewById(R.id.polluantdem); 
  	  niveauauj= (TextView)this.findViewById(R.id.niveauauj); 
  	  niveaudem= (TextView)this.findViewById(R.id.niveaudem); 
        
        
        
        //WifiManager wifi = (WifiManager)getSystemService(Context.WIFI_SERVICE);
      //  wifi.getConnectionInfo().getSSID();
        
        //String idReseau=wifi.getConnectionInfo().getBSSID();
        
      //  Toast.makeText(BusEtService.this, idReseau, Toast.LENGTH_LONG).show();
        
       // ArrayList<Station_Bus> malisteStation;
        //Map monPronProjetglobalActivity= new Map();
        
        //malisteStation= monPronProjetglobalActivity.getListStation_Bus(monPronProjetglobalActivity.requetServeurStation(idReseau));
        
        //if (malisteStation.size()== 0){
   	  // Toast.makeText(BusEtService.this,"la liste est vide", Toast.LENGTH_LONG).show();
   	   
      // }
//        for (int i = 0; i < malisteStation.size(); i++){
//   	       	   Toast.makeText(BusEtService.this,malisteStation.get(i).getNumero_station(), Toast.LENGTH_LONG).show();
//    	           }
        
        //Toast.makeText(BusEtService.this,malisteStation.get(0).getNom_destination_A(), Toast.LENGTH_LONG).show();
        
        busA= (TextView)findViewById(R.id.num_BusA);
        busB= (TextView)findViewById(R.id.num_BusB);
        busC= (TextView)findViewById(R.id.num_BusC);
        
        destin_busA= (TextView)findViewById(R.id.direction_BusA);
        destin_busB= (TextView)findViewById(R.id.direction_BusB);
        destin_busC= (TextView)findViewById(R.id.direction_BusC);
  
        /*busA.setText("293");
        busB.setText("132");
        busC.setText("d3");
        
        destin_busA.setText("d1");
        destin_busB.setText("d2");
        destin_busC.setText("d3");*/
   
        /*busA.setText(""+malisteStation.get(0).getBus_A());
        busB.setText(""+malisteStation.get(0).getBus_B());
        busC.setText(""+malisteStation.get(0).getBus_C());
        
        destin_busA.setText(malisteStation.get(0).getNom_destination_A());
        destin_busB.setText(malisteStation.get(0).getNom_destination_B());
        destin_busC.setText(malisteStation.get(0).getNom_destination_C());*/
        
        
        
        /////////////////////////////////////////////////////////////////////////////
        
     

}
	
	
	
	
	
	public String getTpsAttente(String station_id ,String direction){
		
	    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(); //On crée la liste qui contiendra tous nos paramètres
	    nameValuePairs.add(new BasicNameValuePair("station_id",station_id)); //
	    nameValuePairs.add(new BasicNameValuePair("direction", direction));
        RequestServer requete = new RequestServer("http://"+MesPreferences.adress_ip+":4453/projet/bus.php",nameValuePairs);
        return requete.sendRequest();
                              }
	
	public void setTpsAttente(String jsonString,String direction){
		   JSONObject jObject;
			try {
				jObject = new JSONObject(jsonString);
				JSONObject response = jObject.getJSONObject("response");
				
				if(direction.equals("A")){
					
					JSONObject a_busA = response.getJSONObject("180").getJSONObject("A");
					JSONObject a_busB = response.getJSONObject("132").getJSONObject("A");
					//JSONObject a_busc = response.getJSONObject("180").getJSONObject("A");
					
					now_busA.setText(a_busA.getString("now"));
					next_busA.setText(a_busA.getString("next"));
					
					now_busB.setText(a_busB.getString("now"));
					next_busB.setText(a_busB.getString("next"));
					
					//now_busC.setText(a_busc.getString("now"));
					//next_busC.setText(a_busc.getString("next"));
				}
				else if(direction.equals("R")){
					JSONObject r_293 = response.getJSONObject("180").getJSONObject("R");
					JSONObject r_132 = response.getJSONObject("132").getJSONObject("R");
					//JSONObject r_180 = response.getJSONObject("180").getJSONObject("R");
					
					
					now_busA.setText(r_293.getString("now"));
					next_busA.setText(r_293.getString("next"));
					
					
					now_busB.setText(r_132.getString("now"));
					next_busB.setText(r_132.getString("next"));
					
					//now_busC.setText(r_180.getString("now"));
					//next_busC.setText(r_180.getString("next"));
				}
				
		    	
			} catch (JSONException e) {
//				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
	
	
	private void traitementBus() {
		
		Thread busThread =
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
		
		         busThread.start();
	
		
		
	                               }
	
	
	
	public void traiementPollution() {
		
		 new Thread(new Runnable() {

	        	public void run() {
	        		handler.sendEmptyMessage(1);
	                              }

	        	                  }).start();
		
	                                  }
	
	public String getPollution(){
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		RequestServer requete = new RequestServer("http://"+MesPreferences.adress_ip+":4453/projet/pollution.php",nameValuePairs);
		return requete.sendRequest();
	}
	
	public void setPollution(String jsonString){
		JSONObject jObject;
		try {
			jObject = new JSONObject(jsonString);
			JSONObject response = jObject.getJSONObject("response");
			JSONObject today = response.getJSONObject("today");
			JSONObject tomorrow = response.getJSONObject("tomorrow");
	    	
			indiceauj.setText(today.getString("indice"));
			polluantauj.setText(today.getString("polluant"));
			niveauauj.setText(today.getString("niveau"));
			
			indicedem.setText(tomorrow.getString("indice"));
			polluantdem.setText(tomorrow.getString("polluant"));
			niveaudem.setText(tomorrow.getString("niveau"));
			
			
		} catch (JSONException e) {
//			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}
