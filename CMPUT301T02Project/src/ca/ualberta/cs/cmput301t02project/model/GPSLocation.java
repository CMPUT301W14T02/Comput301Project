package ca.ualberta.cs.cmput301t02project.model;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;

public class GPSLocation {

	LocationManager locationManager;
	public GPSLocation(Context context) {
		locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
	}

	public Location getLocation() {
		Criteria criteria = new Criteria();
		String provider = locationManager.getBestProvider(criteria, false);
		return locationManager.getLastKnownLocation(provider);
	}
	
}
