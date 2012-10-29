package com.formation.projetglobal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.google.android.maps.MapActivity;

public class LocalisationAmi extends MapActivity {
	
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.localisation_ami);
        
        Button btnchatlocalisation= (Button)findViewById(R.id.button6);
        btnchatlocalisation.setOnClickListener(new OnClickListener() {
			
			
			public void onClick(View v) {
				Intent intent =new Intent(LocalisationAmi.this, tchat.class);
				startActivity(intent);
				
				
			}
		});
        
        
        
        
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

}
