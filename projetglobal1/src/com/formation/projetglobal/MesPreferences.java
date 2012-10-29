package com.formation.projetglobal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MesPreferences extends PreferenceActivity {
	public static String prefadresok;
	public static String adress_ip ;
	public static float distanceami;
	public static SharedPreferences preferences;
	public static String login;
	public static String password;
	public static String authentificate;
	public static String bssid1;
	public static String bssid2;
	public static String idstationbus1;
	public static String idstationbus2;
	public static float numligne1;
	public static float numligne2;
	public static float numligne3;
	public static String direction;
	public static String terminusbus1;
	public static String terminusbus2;
	public static String terminusbus3;
	

	@Override

	protected void onCreate(Bundle savedInstanceState) {

	super.onCreate(savedInstanceState);

	addPreferencesFromResource(R.xml.preferences);
	
	Preference customPref = (Preference) findPreference("customPref");
    
    customPref
                   .setOnPreferenceClickListener(new OnPreferenceClickListener() {

                            public boolean onPreferenceClick(Preference preference) {
                            	 getPreferences();
                            	SharedPreferences customSharedPreference = getSharedPreferences(
                                        "myCustomSharedPrefs", Activity.MODE_PRIVATE);
                        SharedPreferences.Editor editor = customSharedPreference
                                        .edit();
                        editor.putString("myCustomPref",
                                        "The preference has been clicked");
                        editor.commit();
                        Toast.makeText(getBaseContext(), "Modifications terminées", Toast.LENGTH_SHORT).show();
                       
                        return true;
                                                             
                                   
                                    
                            }

                    });
	
}
	
	
	public   void getPreferences() {

		 preferences = PreferenceManager.getDefaultSharedPreferences(this);
		
		adress_ip= preferences.getString("adress_ip", "nothing" );
		
		distanceami=Float.parseFloat(preferences.getString("distanceami", "5000")) ;
		
		login=preferences.getString("login", "nothing" );
		
		password=preferences.getString("password", "password" );
		
		authentificate=preferences.getString("authentificate", "nothing" );
		
		bssid1= preferences.getString("bssid1", "e0:46:9a:bc:f2:8e" );
		
		bssid2= preferences.getString("bssid2", "e0:46:9a:bc:f1:02" );
		
		idstationbus1= preferences.getString("idstationbus1", "293_925_1003" );
		
		idstationbus2= preferences.getString("idstationbus2", "132_581_591" );
		
		numligne1= Float.parseFloat(preferences.getString("numligne1", "293"));
		
		numligne2=Float.parseFloat(preferences.getString("numligne2", "132"));
		
		numligne3=Float.parseFloat(preferences.getString("numligne3", "0000"));
		
		direction= preferences.getString("direction", "R");
		terminusbus1= preferences.getString("terminusbus1", "villejuif" );
		
		terminusbus2= preferences.getString("terminusbus2", "bibliothéque françois.M" );
		
		terminusbus3= preferences.getString("terminusbus3", "terminusbus3" );
		
		
	
	}
	
	
	public static String GETADRESSE(Context context){
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);	
		return preferences.getString("adress_ip", "" );
	} 
	
	public static String GETADRESSEAUTHENTIFI(Context context){
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);	
		return preferences.getString("authentificate", "" );
	} 
	
	public static String GETbss1(Context context){
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);	
		return preferences.getString("bssid1", "" );
	} 
	public static String GETbss2(Context context){
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);	
		return preferences.getString("bssid2", "" );
	} 
	
	
	
}
