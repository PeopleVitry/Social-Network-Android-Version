/*package com.formation.projetglobal;

import org.apache.jasper.runtime.ProtectedFunctionMapper;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import android.widget.Toast;

////2eme methode nchallah////////////////////////////////////////////
public class MonActivite extends Activity {

private static int CODE_RETOUR = 1;

@Override

public boolean onCreateOptionsMenu(Menu menu) {

getMenuInflater().inflate(R.menu.menuprincipal, menu);

return super.onCreateOptionsMenu(menu);

}

@Override

public boolean onOptionsItemSelected(MenuItem item) {

if(item.getItemId() == R.id.itemOptions) {

startActivityForResult(new Intent(this, MesPreferences.class), CODE_RETOUR);

}

return super.onOptionsItemSelected(item);

}


//Map map=new Map();


private   void getPreferences() {

SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

Map.adress_ip= preferences.getString("adress_ip", "localhost" );
//Map.distanceami= preferences.getFloat("distanceami", 1000);

//Toast.makeText(MonActivite.this, ""+Map.distanceami, Toast.LENGTH_LONG).show();
 
 
  

//((TextView)findViewById(R.id.tvPassword)).setText("Mot de passe : " + preferences.getString("password", ""));

//((TextView)findViewById(R.id.tvRingtone)).setText("Sonnerie : " + preferences.getString("sonnerie", ""));

//((TextView)findViewById(R.id.tvVibrate)).setText("Vibreur : " + preferences.getBoolean("vibrate", false));

}

	
//@Override

protected void onActivityResult(int requestCode, int resultCode, Intent data) {

if(requestCode == CODE_RETOUR) {

Toast.makeText(this, "Modifications terminées", Toast.LENGTH_SHORT).show();

getPreferences();

}

super.onActivityResult(requestCode, resultCode, data);

}







}



*/