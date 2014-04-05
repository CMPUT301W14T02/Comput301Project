package ca.ualberta.cs.cmput301t02project.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import ca.ualberta.cs.cmput301t02project.R;

/**
 * An activity with the sole purpose of providing EditText boxes
 * for the user to enter the latitude and longitude of the other
 * location they would like to sort by 
 */

public class EnterSearchCoordinatesActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.enter_coordinates);

		final EditText latitude = (EditText) findViewById(R.id.search_latitude_box);
		final EditText longitude = (EditText) findViewById(R.id.search_longitude_box);

		Button sort = (Button) findViewById(R.id.search_by_location_button);

		sort.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {

				double lat = Double.valueOf(latitude.getText().toString());
				double lon = Double.valueOf(longitude.getText().toString());
				
				//return user's entries to the previous activity
				Intent intent = new Intent();
				intent.putExtra("Other Location Lat", lat);
				intent.putExtra("Other Location Lon", lon);
				setResult(RESULT_OK,intent);
				
				finish();
			}

		});
	}
}