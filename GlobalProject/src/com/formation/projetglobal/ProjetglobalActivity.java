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
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.renderscript.Program.TextureType;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
public class ProjetglobalActivity extends Activity    {
	public static Editor ip;
	public static Editor dist;
	public static EditText monEdit  ; 
	//public static TextView monEdit;
	private String authentif;
	
	protected void onStart() {
		
		getPreferences();
		MesPreferences.GETADRESSE(getBaseContext());
		MesPreferences.GETADRESSEAUTHENTIFI(getBaseContext());
		
		
        super.onStart(); 
     
        setContentView(R.layout.settingsauthentification);
        		
       
        getWindow().setFlags(2, 2);
        setTitle("XMPP Settings");
        Button ok = (Button) findViewById(R.id.ok);
        ImageButton prefBtn = (ImageButton) findViewById(R.id.prefButton);
        prefBtn.setOnClickListener(new OnClickListener() {

                public void onClick(View v) {
                       Intent settingsActivity = new Intent(getBaseContext(), MesPreferences.class);
                      startActivity(settingsActivity);
                }
        });
        final EditText monEdit= (EditText)findViewById(R.id.login);
        monEdit.setText(""+MesPreferences.authentificate, TextView.BufferType.EDITABLE);
        
        ok.setOnClickListener(new OnClickListener() {
            
	
	public void onClick(View v) {
		
		
        
		
		String username =monEdit.getText().toString();
		
     	UserInfo utilisateur_encour = getUser(requetServeur(username));
       	if(utilisateur_encour.isSucces()){
	        SharedPreferences myPrefs = ProjetglobalActivity.this.getSharedPreferences("myPrefs", MODE_WORLD_READABLE);
	        SharedPreferences.Editor prefsEditor = myPrefs.edit();
	        prefsEditor.putString("guid", ""+utilisateur_encour.getGuid());
	        prefsEditor.putString("email", ""+utilisateur_encour.getEmail());
	        prefsEditor.putString("name", ""+utilisateur_encour.getName());
	       
	        prefsEditor.commit();
	       
	        Intent intent3=new Intent(ProjetglobalActivity.this, Map.class);
	        startActivity(intent3);
       	}
       	
	}
	
	});
	}

	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	
		
	public String requetServeur( String  email){
    	
    	String jaxrsmessage="offfffffffffffffff";


	try {
	    HttpClient httpclient = new DefaultHttpClient();
   HttpGet request = new HttpGet("http://"+MesPreferences.adress_ip+":4454/WebServiceVitry/authentification/email-"+email+"");
     
    HttpResponse response = httpclient.execute(request);
    HttpEntity entity = response.getEntity();
    InputStream instream = entity.getContent();
   jaxrsmessage = read(instream);
  


   } catch (ClientProtocolException e) {
    e.printStackTrace();
     } catch (IOException e) {
    e.printStackTrace();
  }
	Log.i("request Server :", jaxrsmessage);
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
   
   public UserInfo  getUser(String  jsonString){
    	
	    
	   UserInfo user = new UserInfo() ;
	 
	    Log.i("Response server : ", jsonString);
	    	
	    	try {
	    		
	    		JSONObject json = new JSONObject(jsonString);
	    		if(json.getBoolean("succes")){
	    		user.setConnecte(json.getInt("connecte"));
	    		user.setGuid(json.getString("guid"));
	    		user.setEmail(json.getString("email"));
	    		user.setName(json.getString("name"));
	    		user.setUsername(json.getString("username"));
	    		user.setLatitude(json.getDouble("latitude"));
	    		user.setLongitude(json.getDouble("longitude"));
	    		user.setSucces(json.getBoolean("succes"));
	    		}
	    		else{
	    			user.setSucces(false);
	    		 return user ;	
	    		}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
	    	
	    	
			return user;
	    	
		 	   	
	    }
 
/////////////////////////////////
   private   void getPreferences() {

		 SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
		
		MesPreferences.adress_ip= preferences.getString("adress_ip", "nothing" );
		
		MesPreferences.distanceami=Float.parseFloat(preferences.getString("distanceami", "5000")) ;
		
		MesPreferences.login=preferences.getString("login", "nothing" );
		
		MesPreferences.password=preferences.getString("password", "password" );
		MesPreferences.authentificate=preferences.getString("authentificate", "nothing" );
		
MesPreferences.bssid1= preferences.getString("bssid1", "e0:46:9a:bc:f2:8e" );
		
		MesPreferences.bssid2= preferences.getString("bssid2", "e0:46:9a:bc:f1:02" );
		
		MesPreferences.idstationbus1= preferences.getString("idstationbus1", "293_925_1003" );
		
		MesPreferences.idstationbus2= preferences.getString("idstationbus2", "132_581_591" );
				
		MesPreferences.numligne1= Float.parseFloat(preferences.getString("numligne1", "293"));
		
		MesPreferences.numligne2=Float.parseFloat(preferences.getString("numligne2", "132"));
		
		MesPreferences.numligne3=Float.parseFloat(preferences.getString("numligne3", "0000"));
		
		MesPreferences.direction= preferences.getString("direction", "R");
		MesPreferences.terminusbus1= preferences.getString("terminusbus1", "villejuif" );
		
		MesPreferences.terminusbus2= preferences.getString("terminusbus2", "bibliothéque françois.M" );
		
		MesPreferences.terminusbus3= preferences.getString("terminusbus3", "terminusbus3" );
		
	
	}

}
	
	
