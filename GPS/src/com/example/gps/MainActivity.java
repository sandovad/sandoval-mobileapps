package com.example.gps;

import android.app.Activity;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class MainActivity extends Activity {

	private TextView textLat; 
	private TextView textLong; 
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textLat = (TextView)findViewById(R.id.textLat); 
        textLong = (TextView)findViewById(R.id.textLong); 
        
        LocationManager locManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE); 
        LocationListener locListener = new MyLocationListener(); 
        locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locListener); 
     
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    public class MyLocationListener implements LocationListener{

    	public void onLocationChanged(Location loc){
    		double lat = loc.getLatitude(); 
    		double lon = loc.getLongitude(); 
    		
    		textLat.setText(String.valueOf(lat)); 
    		textLong.setText(String.valueOf(lon)); 
    	}
    	
		
		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub
			
		}
    	
    }
}
