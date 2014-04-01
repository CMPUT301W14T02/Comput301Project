package ca.ualberta.cs.cmput301t02project.model;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

public class GPSLocation {

	private LocationManager locationManager;
	private static GPSLocation instance;
	private String provider;
	
	private GPSLocation(Context context) {
		locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
		Criteria criteria = new Criteria();
		provider = locationManager.getBestProvider(criteria, true);
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 100, 1, new LocationListener() {
			
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
			
			@Override
			public void onLocationChanged(Location location) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	public static void initializeLocation(Context context) {
		if(instance == null) {
			instance = new GPSLocation(context);
		}
	}
	
	public static GPSLocation getInstance() {
		if(instance == null) {
			throw new IllegalAccessError("Location isn't initialized yet");
		}
		return instance;
	}

	public Location getLocation() {
		Location location = locationManager.getLastKnownLocation(provider);
		if(location == null) {
			location = new Location("");
			location.setLatitude(0.0);
			location.setLongitude(0.0);
		}
		return location;
	}
	
}
