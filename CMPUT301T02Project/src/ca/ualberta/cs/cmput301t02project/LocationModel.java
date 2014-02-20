package ca.ualberta.cs.cmput301t02project;


import android.app.Application;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;


public class LocationModel extends Application {
	
	double lat;
	double lng;
	Location location; 
	

	//LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
	//lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, locationListener);
	
	//Retrieve location manager
	
	public void afunct(){
	LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);	
	//Requests location from provider
	//String provider = LocationManager.GPS_PROVIDER;
	
	//locationManager.requestSingleUpdate(provider, locationListener, looper);
	
	locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
	
	//if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){ 
      //  locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
    //}
	}
	// Retrieve location updates through LocationListener interface
	private final LocationListener locationListener = new LocationListener(){
	// TODO: override the four methods.
		@Override
		public void onLocationChanged (Location location) {
			if (location != null) {
				double lat = location.getLatitude();
				double lng = location.getLongitude();

				
			} else {
				//do something later here
			}
		}
		@Override
		public void onProviderDisabled (String provider) {
			//TODO
		}
		@Override
		public void onProviderEnabled (String provider) {
			//TODO
		}
		@Override
		public void onStatusChanged (String provider, int status, Bundle extras) {
			//TODO
		}
		
	};
	

	//locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
	//Stores in location object

}
