package com.formation.projetglobal;

import java.util.List;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class MapObjet  extends MapActivity{
	
	
	
	
	
	private MapView	mapView;



	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapviewgenerale); 
      //On récupère l’Intent que l’on a reçu
        Intent thisIntent = getIntent();
        //On récupère les deux extra grâce à leurs id
        Double latitude = thisIntent.getExtras().getDouble("latitude");
        Double longitude = thisIntent.getExtras().getDouble("longitude");
        int intentLatitude = (int) (latitude * 1E6) ;
        int intentLongitude = (int)(longitude *1E6) ;
        mapView = (MapView) this.findViewById(R.id.mapView);
        mapView.setBuiltInZoomControls(true);


   super.onCreate(savedInstanceState);

        List<Overlay> mapOverlays = mapView.getOverlays();  

                Drawable drawable = this.getResources().getDrawable(R.drawable.ic_launcher);  

                CustomItemizedOverlay itemizedOverlay =   

                     new CustomItemizedOverlay(drawable, this);  

                  
                 GeoPoint point = new GeoPoint(intentLatitude, intentLongitude);  
                

                OverlayItem overlayitem =   

                     new OverlayItem(point, "Hello", "je suis à Vitry, Paris!");
                
                   

                itemizedOverlay.addOverlay(overlayitem); 
                

                mapOverlays.add(itemizedOverlay);  

                   

                MapController mapController = mapView.getController();  

                   

                mapController.animateTo(point);  

              mapController.setZoom(14);
              /////////////////////////////////////////////////
              
              Button btnAmi=(Button)findViewById(R.id.button4);
              btnAmi.setOnClickListener(new OnClickListener() {
				
				public void onClick(View v) {
					Toast.makeText(MapObjet.this, "tu es la sur mapObjet", Toast.LENGTH_LONG).show();
					Intent monIntent=new Intent(MapObjet.this, Listedamis.class);
					startActivity(monIntent);
					
				}
			});
              Button btnObje1=(Button)findViewById(R.id.button6);
            
              btnObje1.setOnClickListener(new OnClickListener() {
				
				public void onClick(View v) {
					Intent monintent2=new Intent(MapObjet.this,Recherche.class) ;
					monintent2.putExtra("monparametre","objet");
					startActivity(monintent2);
					
				}
			});
              
              Button btnService=(Button)findViewById(R.id.button1);
              
              btnService.setOnClickListener(new OnClickListener() {
				
				public void onClick(View v) {
					Intent monintent2=new Intent(MapObjet.this,Recherche.class) ;
					monintent2.putExtra("monparametre","service");
					startActivity(monintent2);
					
				}
			});
              
              
              
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
	

}
