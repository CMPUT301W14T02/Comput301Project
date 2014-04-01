package ca.ualberta.cs.cmput301t02project.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import ca.ualberta.cs.cmput301t02project.R;
import ca.ualberta.cs.cmput301t02project.controller.CommentController;
import ca.ualberta.cs.cmput301t02project.model.GPSLocation;
import ca.ualberta.cs.cmput301t02project.model.User;

/**
 * Allows a user to create their own comment. 
 */
public class CreateCommentActivity extends Activity {
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String commentId = getIntent().getStringExtra("CommentId");
		final CommentController commentController = new CommentController(commentId, this);
		setContentView(R.layout.activity_create_comment);
		
		final EditText latitude = (EditText) findViewById(R.id.latitude_box);
		final EditText longitude = (EditText) findViewById(R.id.longitude_box);
		
		double currLat = GPSLocation.getInstance().getLocation().getLatitude();
		double currLon = GPSLocation.getInstance().getLocation().getLongitude();
		final String strLat = String.valueOf(currLat);
		final String strLon = String.valueOf(currLon);
		
		latitude.setText(strLat);
		longitude.setText(strLon);
		
		Button post = (Button) findViewById(R.id.create_post);

		post.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				EditText inputComment = (EditText) findViewById(R.id.create_text);
				String text = inputComment.getText().toString();
				Bitmap picture = null;
				
				Location location;
				if ((latitude.getText().toString())!= strLat || ((longitude.getText().toString())!= strLon)) {
					double lat = Double.valueOf(latitude.getText().toString());
					double lon = Double.valueOf(longitude.getText().toString());
					location = new Location("");
					location.setLatitude(lat);
					location.setLongitude(lon);
				}
				else {
					location = GPSLocation.getInstance().getLocation();
				}
				commentController.addReply(text, picture, location, User.getUser().getName());
				finish();
			}
		});

	}

}
