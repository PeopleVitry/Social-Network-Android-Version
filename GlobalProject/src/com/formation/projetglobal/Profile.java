package com.formation.projetglobal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Profile extends Activity{
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.profileami);
	        ////////////////////////
	        
	     
	        
	        
	        
	        
	        
	        Button btntchat= (Button)findViewById(R.id.button1);
	       // Button btnlocalisation=(Button)findViewById(R.id.button3);
	        btntchat.setOnClickListener(new OnClickListener() {
				
				public void onClick(View v) {
					Intent thisIntent = getIntent();
					 Intent intent =new Intent(Profile.this, JaberActivity.class);
					 String mail = thisIntent.getExtras().getString("email");
					 intent.putExtra("email",thisIntent.getExtras().getString("email"));
					 intent.putExtra("guid",thisIntent.getExtras().getString("guid"));
						startActivity(intent);
				
				}
			});
	        
	        
	       /* btnlocalisation.setOnClickListener(new OnClickListener() {
				
				public void onClick(View v) {
					
					Intent intent1 =new Intent(Profile.this, LocalisationAmi.class);
					startActivity(intent1);
				}
			});*/
	        
	        
	        Intent thisIntent = getIntent();
	        
	       // Toast.makeText(Profile.this,  thisIntent.getExtras().getString("id"), Toast.LENGTH_LONG).show();
	        TextView nomTextView= (TextView)findViewById(R.id.textView1);
	        TextView prenomTextView= (TextView)findViewById(R.id.textView4);
	        TextView emailTextView= (TextView)findViewById(R.id.textView2);   
	        nomTextView.setText(thisIntent.getExtras().getString("nom"));
	        prenomTextView.setText(thisIntent.getExtras().getString("prenom"));
	        emailTextView.setText(thisIntent.getExtras().getString("email"));
	        
	    }

}
