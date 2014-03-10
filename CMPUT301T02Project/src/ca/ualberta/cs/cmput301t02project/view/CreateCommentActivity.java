package ca.ualberta.cs.cmput301t02project.view;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import ca.ualberta.cs.cmput301t02project.CurrentUser;
import ca.ualberta.cs.cmput301t02project.ProjectApplication;
import ca.ualberta.cs.cmput301t02project.R;
import ca.ualberta.cs.cmput301t02project.controller.CommentListController;

public class CreateCommentActivity extends Activity {

	// TODO: Refactor with new classes
	private CommentListController commentListController;
	// private MyCommentsController myCommentsListController;

	private Location currentLocation;

	public Location getCurrentLocation() {
	    return currentLocation;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		commentListController = new CommentListController(
				ProjectApplication.getCurrentCommentList());
		setContentView(R.layout.activity_create_comment);

		Button post = (Button) findViewById(R.id.create_post);
		String loc = "No location yet";
		currentLocation = new Location(loc);

		// Retrieve location manager
		LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		// Requests location from provider
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0,
				0, locationListener);

		post.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				EditText inputComment = (EditText) findViewById(R.id.create_text);
				
				// Refactor into MVC?
				// Username is temporarily in front of the comment. Can redesign
				// later -SB
				
				commentListController.addNewComment(inputComment.getText().toString(), null, null,
						CurrentUser.getName().toString());
				finish();
			}
		});

	}

	// Retrieve location updates through LocationListener interface
	private final LocationListener locationListener = new LocationListener() {

		// TODO: override the four methods.
		@Override
		public void onLocationChanged(Location location) {

			if (location != null) {
				currentLocation = location;

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
		getMenuInflater().inflate(R.menu.create_comment, menu);
		return true;
	}

}
