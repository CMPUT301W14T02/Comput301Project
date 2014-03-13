package ca.ualberta.cs.cmput301t02project.activity;

import ca.ualberta.cs.cmput301t02project.ProjectApplication;
import ca.ualberta.cs.cmput301t02project.R;
import ca.ualberta.cs.cmput301t02project.R.id;
import ca.ualberta.cs.cmput301t02project.R.layout;
import ca.ualberta.cs.cmput301t02project.R.menu;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

public class MainMenuActivity extends Activity {
	
	private Location currentLocation;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		TextView welcomeMessage = (TextView) findViewById(R.id.welcome_message);
		welcomeMessage.setText("Welcome " + ProjectApplication.getName().toString()
				+ "!");

		Button createComment = (Button) findViewById(R.id.create);
		createComment.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainMenuActivity.this,
						CreateCommentActivity.class));
			}
		});

		Button browseComment = (Button) findViewById(R.id.browse);
		browseComment.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainMenuActivity.this,
						BrowseTopLevelCommentsActivity.class));
			}
		});
		
		Button browseMyComments = (Button) findViewById(R.id.my_comments);
		browseMyComments.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainMenuActivity.this,
						BrowseMyCommentsActivity.class));
			}
		});
		
		
		
		String loc = "Location Intialization";
		currentLocation = new Location(loc);
		ProjectApplication.setCurrentLocation(currentLocation);
		

		// Retrieve location manager
		LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		// Requests location from provider
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0,
				0, locationListener);

		
	}
		
		// Retrieve location updates through LocationListener interface
		private final LocationListener locationListener = new LocationListener() {

			// TODO: override the four methods.
			@Override
			public void onLocationChanged(Location location) {

				if (location != null) {
					currentLocation = location;
					ProjectApplication.setCurrentLocation(currentLocation);

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
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
