package com.example.pedometer;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;


public class Pedometer extends Activity {
	
	private TextView textLat; 
	private TextView textLong; 
	
	//display for Accelerator 
	private TextView textViewX; 
	private TextView textViewY; 
	private TextView textViewZ; 
	
	//display for sensitivity 
	private TextView textSensitivity; 
	
	private Button btnReset; 
	
	private TextView textSteps; 
	
	//fields for sensor manager
	private SensorManager sensorManager; 
	private float acceleration; 
	
	//fields to calculate number of steps 
	private float previousY;
	private float currentY;  
	private float numSteps; 
	
	//seek bar fields 
	private SeekBar seekBar; 
	private int threshold; //when to trigger a step 
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedometer);
        
        textViewX = (TextView)findViewById(R.id.textViewX); 
        textViewY = (TextView)findViewById(R.id.textViewY); 
        textViewZ = (TextView)findViewById(R.id.textViewZ); 
        
        textSteps = (TextView)findViewById(R.id.textSteps); 
        textSensitivity = (TextView)findViewById(R.id.textSensitive); 
        
        btnReset = (Button)findViewById(R.id.btnReset); 
        
        seekBar = (SeekBar)findViewById(R.id.seekBar1);         
        
        seekBar.setProgress(0); 
        seekBar.setOnSeekBarChangeListener(seekBarListener); 
        threshold = 10; 
        textSensitivity.setText(String.valueOf(threshold)); 
        
        previousY = 0; 
        currentY = 0; 
        numSteps = 0; 
        
        acceleration = 0.00f; 
        enableAccelerometerListening(); 
        
        textLat = (TextView)findViewById(R.id.textLat); 
        textLong = (TextView)findViewById(R.id.textLong); 
        
        LocationManager locManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE); 
        LocationListener locListener = new MyLocationListener(); 
        locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locListener); 
    
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.pedometer, menu);
        return true;
    }
    
    private void enableAccelerometerListening(){
    	sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE); 
    	sensorManager.registerListener(sensorEventListener, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_NORMAL);
    }; 
    
    public class MyLocationListener implements LocationListener {
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
   private SensorEventListener sensorEventListener=
		   	new SensorEventListener(){
	   public void onSensorChanged(SensorEvent event){
		   //gather the values from accelerometer
		   float x = event.values[0]; 
		   float y = event.values[1]; 
		   float z = event.values[2]; 
		   //fetch current y 
		   currentY= y; 
		   //measure if a step is taken 
		   if (Math.abs(currentY-previousY)>threshold){
			   numSteps++; 
			   textSteps.setText(String.valueOf(numSteps)); 
		   }
		   
		   textViewX.setText(String.valueOf(x)); 
		   textViewY.setText(String.valueOf(y)); 
		   textViewZ.setText(String.valueOf(z)); 
		   
		   previousY= y; 
	   }
	   public void onAccuracyChanged(Sensor sensor, int accuracy){
		   
	   }
	   		
   };

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
    public void resetSteps(View v){
    	numSteps=0; 
    	textSteps.setText(String.valueOf(numSteps)); 
    }
    
    private OnSeekBarChangeListener seekBarListener = new OnSeekBarChangeListener(){
    	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
    		threshold = seekBar.getProgress(); 
    		textSensitivity.setText(String.valueOf(threshold));
    	}
    	public void onStartTrackingTouch(SeekBar seekBar){
    		
    	}
    	public void onStopTrackingTouch(SeekBar seekBar){
    		
    	}
    };
}//end class
