package ca.ualberta.cs.cmput301t02project.model;

import android.app.Application;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

public class LocationModel extends Application {

    private Location location;
    /*this is used to get the context, if we add this to the singleton, passing it here
     * may be unnecessary -KW
     */
    private Context currContext; 
   
    public LocationModel(Context c) {

	super();
	
	this.currContext = c; // Context passed to constructor -KW
	
	// Retrieve location manager
	LocationManager locationManager = (LocationManager) currContext.getSystemService(Context.LOCATION_SERVICE);
	// Requests location from provider
	if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
	    locationManager.requestLocationUpdates(
		    LocationManager.GPS_PROVIDER, 0, 0, locationListener);
	} else {
	    // How are we going to handle no location available
	}
    }
    

    // Retrieve location updates through LocationListener interface
    private final LocationListener locationListener = new LocationListener() {

	// TODO: override the four methods.
	@Override
	public void onLocationChanged(Location location) {

	    if (location != null) {
		double lat = location.getLatitude(); //added for testing -KW
		double lng = location.getLongitude();
		setLocation(location);
	    } else {
		// do something later here
	    }
	}

	@Override
	public void onProviderDisabled(String provider) {

	    // TODO
	}

	@Override
	public void onProviderEnabled(String provider) {

	    // TODO
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {

	    // TODO
	}
    };
    
    

    // May be required to get location, not sure yet
    public Location getLocation() {

	return location;
    }

    // Set location in locationListener, using GPS coordinates
    public void setLocation(Location location) {

	this.location = location;
    }

    // If user wants to manually enter coordinates to set location
    public void setLocation(double lat, double lng) {

	location.setLatitude(lat);
	location.setLongitude(lng);
    }

    // Stores in location object
}
