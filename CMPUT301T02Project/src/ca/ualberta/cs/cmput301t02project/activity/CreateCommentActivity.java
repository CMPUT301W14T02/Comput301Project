package ca.ualberta.cs.cmput301t02project.activity;

import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import ca.ualberta.cs.cmput301t02project.R;
import ca.ualberta.cs.cmput301t02project.controller.CommentController;
import ca.ualberta.cs.cmput301t02project.controller.TopLevelListController;
import ca.ualberta.cs.cmput301t02project.model.GPSLocation;
import ca.ualberta.cs.cmput301t02project.model.User;

/**
 * Allows a user to create their own comment. 
 * The user can add text to the comment, attach a picture, and change their location. 
 * The location is set by default to the user's current location.
 * Called when the "Create" button is selected from the main menu (for creating a top level comment)
 * or when the "Reply" button is selected from ReplyCommentActivity (for creating a reply). 
 * picture is defined in PictureActivity, which deals with all the image related tasks.
 */
public class CreateCommentActivity extends PictureActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// determine if the comment to be created is a top level comment or a reply -SB
		final boolean isTopLevel = getIntent().getBooleanExtra("isTopLevelComment", false);
		final String commentId = getIntent().getStringExtra("CommentId");
		
		// set up the screen display -SB
		setContentView(R.layout.activity_create_comment);
		final EditText latitude = (EditText) findViewById(R.id.latitude_box);
		final EditText longitude = (EditText) findViewById(R.id.longitude_box);
		
		// display the user's current location by default -SB
		double currLat = GPSLocation.getInstance().getLocation().getLatitude();
		double currLon = GPSLocation.getInstance().getLocation().getLongitude();
		
		latitude.setText(String.valueOf(currLat));
		longitude.setText(String.valueOf(currLon));
		
		// actions to take when the "Post" button is pressed -SB
		Button post = (Button) findViewById(R.id.create_post);
		post.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				// store the user's text input -SB
				EditText inputComment = (EditText) findViewById(R.id.create_text);
				String text = inputComment.getText().toString();

				// store the user's location input -SB
				Location location = GPSLocation.getInstance().getLocation();
				Location commentLocation = new Location("");
				
				// check if user input for location is in an acceptable format -SB
				try {
					double lat = Double.valueOf(latitude.getText().toString());
					double lon = Double.valueOf(longitude.getText().toString());
					
					if ((lat > 90) || (lat < -90)) { 
						throw new NumberFormatException();
					}
					if ((lon > 180) || (lon < -180)) { 
						throw new NumberFormatException();
					}
					
					commentLocation.setLatitude(lat);
					commentLocation.setLongitude(lon);
				} 
				
				// if any exceptions are caught above, the comment will be given GPS coordinates by default
				catch (NumberFormatException ex) {
					commentLocation = location;
				}
				
				// picture is defined in PictureActivity -SB
				if(isTopLevel) {
					
					// post a top level comment with no parent comment -SB
					TopLevelListController controller = new TopLevelListController(CreateCommentActivity.this);
					controller.add(text, picture, commentLocation, User.getUser());
				}
				else {
					
					// post a reply to a comment -SB
					CommentController commentController = new CommentController(commentId, CreateCommentActivity.this);
					commentController.addReply(text, picture, commentLocation, User.getUser());
				}
				finish();
			}
		});
	}

	@Override
	public void goToHelpPage(){
		
		// redirect to help page for creating comments -SB
		Intent viewIntent = new Intent("android.intent.action.VIEW",Uri.parse(
				"https://rawgithub.com/CMPUT301W14T02/Comput301Project/master/Help%20Pages/create_comment.html"));
		startActivity(viewIntent);
	}
}
