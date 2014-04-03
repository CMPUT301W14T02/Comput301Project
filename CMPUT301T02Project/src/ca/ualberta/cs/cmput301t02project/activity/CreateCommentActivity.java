package ca.ualberta.cs.cmput301t02project.activity;

import java.io.File;

import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import ca.ualberta.cs.cmput301t02project.R;
import ca.ualberta.cs.cmput301t02project.controller.CommentController;
import ca.ualberta.cs.cmput301t02project.controller.TopLevelListController;
import ca.ualberta.cs.cmput301t02project.model.GPSLocation;
import ca.ualberta.cs.cmput301t02project.model.User;

/**
 * Allows a user to create their own comment. 
 * picture is defined in PictureActivity, which deals with all the image related tasks.
 */
public class CreateCommentActivity extends PictureActivity {
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final boolean isTopLevel = getIntent().getBooleanExtra("isTopLevelComment", false);
		final String commentId = getIntent().getStringExtra("CommentId");
		setContentView(R.layout.activity_create_comment);
		
		final EditText latitude = (EditText) findViewById(R.id.latitude_box);
		final EditText longitude = (EditText) findViewById(R.id.longitude_box);
		
		double currLat = GPSLocation.getInstance().getLocation().getLatitude();
		double currLon = GPSLocation.getInstance().getLocation().getLongitude();
	
		latitude.setText(String.valueOf(currLat));
		longitude.setText(String.valueOf(currLon));
		
		Button post = (Button) findViewById(R.id.create_post);

		post.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				EditText inputComment = (EditText) findViewById(R.id.create_text);
				String text = inputComment.getText().toString();
				
				Location location = GPSLocation.getInstance().getLocation();
				Location commentLocation = new Location("");
				
				try {
					double lat = Double.valueOf(latitude.getText().toString());
					double lon = Double.valueOf(longitude.getText().toString());
					if ((lat > 90) || (lat < -90)) { throw new NumberFormatException();}
					if ((lon > 180) || (lon < -180)) { throw new NumberFormatException();}
					commentLocation.setLatitude(lat);
					commentLocation.setLongitude(lon);
					
				} catch (NumberFormatException ex){
					/*if any exceptions are caught above, the comment will be given 
						GPS coordinates by default */
					commentLocation = location;
				}
				
				// picture is defined in PictureActivity -SB
				if(isTopLevel) {
					TopLevelListController controller = new TopLevelListController(CreateCommentActivity.this);
					controller.add(text, picture, commentLocation, User.getUser());
				}
				else {
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
	}

}
