package ca.ualberta.cs.cmput301t02project.model;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;

public class GPSLocation {

	private LocationManager locationManager;
	private static GPSLocation instance;
	private GPSLocation(Context context) {
		locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
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
		Criteria criteria = new Criteria();
		String provider = locationManager.getBestProvider(criteria, false);
		return locationManager.getLastKnownLocation(provider);
	}
	
}
