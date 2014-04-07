package ca.ualberta.cs.cmput301t02project.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import ca.ualberta.cs.cmput301t02project.R;
import ca.ualberta.cs.cmput301t02project.model.GPSLocation;

/**
 * An activity with the sole purpose of providing EditText boxes
 * for the user to enter the latitude and longitude of the other
 * location they would like to sort by.
 * Called when the user selects "Other Location" from the spinner. 
 * All classes that extend BrowseCommentsActivityAbstraction (the Browse* activities)
 * have a spinner for sorting with the option of "Other Location".
 */
public class EnterSearchCoordinatesActivity extends ActionBarActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// set up the screen display -SB		
		setContentView(R.layout.enter_coordinates);
		final EditText latitude = (EditText) findViewById(R.id.search_latitude_box);
		final EditText longitude = (EditText) findViewById(R.id.search_longitude_box);

		// actions to take when the "Post" button is pressed -SB
		Button sort = (Button) findViewById(R.id.search_by_location_button);
		sort.setOnClickListener(new View.OnClickListener() {	
			@Override
			public void onClick(View v) {

				// record the user's submission -SB
				
				double lat = 0;
				double lon = 0;
				
				try {
					lat = Double.valueOf(latitude.getText().toString());
					lon = Double.valueOf(longitude.getText().toString());
				} catch (NumberFormatException ex) {
					// If the user doesn't enter anything, default to current location
					lat = GPSLocation.getInstance().getLocation().getLatitude();
					lon = GPSLocation.getInstance().getLocation().getLongitude();
				}
				// return user's entries to the previous activity
				Intent intent = new Intent();
				intent.putExtra("Other Location Lat", lat);
				intent.putExtra("Other Location Lon", lon);
				setResult(RESULT_OK,intent);
				
				finish();
			}
		});
	}
	
	@Override
	public void goToHelpPage(){
		
		// redirect to help page for sorting by other location -SB
		Intent viewIntent = new Intent("android.intent.action.VIEW",Uri.parse(
				"https://rawgithub.com/CMPUT301W14T02/Comput301Project/master/Help%20Pages/other_location.html"));
		startActivity(viewIntent);
	}
}