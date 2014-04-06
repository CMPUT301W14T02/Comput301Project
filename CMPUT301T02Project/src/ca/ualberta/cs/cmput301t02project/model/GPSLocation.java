package ca.ualberta.cs.cmput301t02project.model;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

/**
 * This class provides the location of the mobile device.
 * <p>
 * To use it, one must first call the static initializeLocation method,
 * and then, whenever a location is needed, one must call the
 * getLocation method on the singleton instance.
 * <p>
 */
public class GPSLocation {

	private LocationManager locationManager;
	private static GPSLocation instance;
	private String provider;
	
	/**
	 * Initialized the location Manager.
	 * @param context A context is needed to get Location service.
	 */
	private GPSLocation(Context context) {
		locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
		Criteria criteria = new Criteria();
		provider = locationManager.getBestProvider(criteria, true);
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 100, 1, new LocationListener() {
			
			@Override
			public void onStatusChanged(String provider, int status, Bundle extras) {
			}
			
			@Override
			public void onProviderEnabled(String provider) {
			}
			
			@Override
			public void onProviderDisabled(String provider) {
				
			}
			
			@Override
			public void onLocationChanged(Location location) {
			}
		});
	}
	
	/**
	 * Initialize the singleton, must be called before getting the instance
	 * @param context A context is needed to use the GPS.
	 */
	public static void initializeLocation(Context context) {
		if(instance == null) {
			instance = new GPSLocation(context);
		}
	}
	
	/**
	 * Get the singleton instance, the GPSLocation must have been initialized
	 * before calling this method.
	 * @return the instance
	 */
	public static GPSLocation getInstance() {
		if(instance == null) {
			throw new IllegalAccessError("Location isn't initialized yet");
		}
		return instance;
	}

	/**
	 * Gets the current the Location of the device.
	 * <p>
	 * More accurrately, gets the last known location by the Location Manager.
	 * <p>
	 * @return The location
	 */
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
