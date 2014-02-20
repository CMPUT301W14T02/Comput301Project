package ca.ualberta.cs.cmput301t02project;

import android.app.Application;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.widget.TextView;

public class LocationModel extends Application {
	
	//LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
	//lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, locationListener);
	
	//Retrieve location manager
	LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);	
	//Requests location from provider
	Looper looper;
	String provider = LocationManager.GPS_PROVIDER;
	locationManager.requestSingleUpdate(provider, locationListener, looper);
	
	// Retrieve location updates through LocationListener interface
	private final LocationListener locationListener = new LocationListener(){
	// TODO: override the four methods.
		public void onLocationChanged (Location location) {
			//TODO
			TextView tv = (TextView) findViewById(R.id.myLocationText);
			if (location != null) {
				double lat = location.getLatitude();
				double lng = location.getLongitude();
				
				tv.setText("Latitude: " + lat
						+ "\nLongitude: " + lng);
			} else {
				tv.setText("NO LOCATION INFO");
			}
		}
		
		public void onProviderDisabled (String provider) {
			//TODO
		}
		
		public void onProviderEnabled (String provider) {
			//TODO
		}
		
		public void onStatusChanged (String provider, int status, Bundle extras) {
			//TODO
		}
		
	};	
	
	//Stores in location object
}
