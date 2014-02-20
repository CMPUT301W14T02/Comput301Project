package ca.ualberta.cs.cmput301t02project;

import android.app.Application;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

public class LocationModel extends Application
{

	private double lat;
	private double lng;
	private Location location;

	public LocationModel()
	{

		super();
		// Retrieve location manager
		LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		// Requests location from provider
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0,
				0, locationListener);

	/*	MAY NEED TO IMPLEMENT THIS LATER FOR CACHED COMMENTS  
	 *  
	 	if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
		 locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
		 0, 0, this);
		 } */
	}

	// Retrieve location updates through LocationListener interface
	private final LocationListener locationListener = new LocationListener()
	{

		// TODO: override the four methods.
		@Override
		public void onLocationChanged(Location location)
		{

			if (location != null)
			{
				double lat = location.getLatitude();
				double lng = location.getLongitude();

			} else
			{
				// do something later here
			}
		}

		@Override
		public void onProviderDisabled(String provider)
		{

			// TODO
		}

		@Override
		public void onProviderEnabled(String provider)
		{

			// TODO
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras)
		{

			// TODO
		}

	};

	// Stores in location object

}
