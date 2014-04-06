package ca.ualberta.cs.cmput301t02project.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import ca.ualberta.cs.cmput301t02project.R;
import ca.ualberta.cs.cmput301t02project.controller.CommentController;
import ca.ualberta.cs.cmput301t02project.model.CommentModel;

/**
 * Allows a user to edit the comment they selected.
 * User's are only able to edit comments that were created by a user with their username. 
 * The user is able to edit the text of the comment, 
 * attach a picture (or change an existing picture, 
 * and/or change their location.
 * The old comment information (text, picture, location) is filled in by default. 
 * Called when the user selects a comment from BrowseMyCommentsActivity
 * or when the user selects "Edit Comment" from the action bar menu from BrowseReplyCommentsActivity
 * picture is defined in PictureActivity, which deals with all the image related tasks.
 */
public class EditCommentActivity extends PictureActivity {
			
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// retrieve the selected comment's commentId to create a controller for changing the model -SB
		String commentId = getIntent().getStringExtra("CommentId");
		final CommentController commentController = new CommentController(commentId, this);

		// set up the screen display -SB		
		setContentView(R.layout.activity_create_comment);
		
		// display a picture on the image button if one already exists -SB
		CommentModel currentComment = commentController.getComment();
		if(currentComment.hasPicture()){
			
			// set the picture to the one that is there. can be overriden later when new photo is taken -SB
			picture = currentComment.getPicture();
			ImageButton imageButton = (ImageButton) findViewById(R.id.takeAPhoto);
			imageButton.setImageBitmap(picture);
		}
		EditText inputComment = (EditText) findViewById(R.id.create_text);
		
		// display the unedited version of the text from the selected comment -SB
		inputComment.setText(commentController.getText());
		
		final EditText latitude = (EditText) findViewById(R.id.latitude_box);
		final EditText longitude = (EditText) findViewById(R.id.longitude_box);
		
		double commentLat = commentController.getComment().getLocation().getLatitude();
		double commentLon = commentController.getComment().getLocation().getLongitude();

		// display the unedited version of the location from the selected comment -SB
		latitude.setText(String.valueOf(commentLat));
		longitude.setText(String.valueOf(commentLon));

		// actions to take when the "Post" button is pressed -SB
		Button post = (Button) findViewById(R.id.create_post);
		post.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				// edited text -SB
				EditText inputComment = (EditText) findViewById(R.id.create_text);
				String newText = inputComment.getText().toString();
				
				// edited location -SB
				double lat = 0, lon = 0;
				try {
					lat = Double.valueOf(latitude.getText().toString());
					lon = Double.valueOf(longitude.getText().toString());
					
					// check if user input for location is in an acceptable format -SB
					if ((lat > 90) || (lat < -90)) { 
						throw new NumberFormatException();
					}
					if ((lon > 180) || (lon < -180)) { 
						throw new NumberFormatException();
					}
				} 
				// if any exceptions are caught above, the old location will be used by default
				catch  (NumberFormatException ex) {
					lat =  commentController.getComment().getLocation().getLatitude();
					lon = commentController.getComment().getLocation().getLongitude();
				}
				
				// update the comment (picture is specified in PictureActivity) -SB
				commentController.edit(newText, picture, lat, lon);
	
				finish();
			}
		});
	}
	
	@Override
	public void goToHelpPage(){
		
		// redirect to help page for editing comments -SB
		Intent viewIntent = new Intent("android.intent.action.VIEW",Uri.parse(
				"https://rawgithub.com/CMPUT301W14T02/Comput301Project/master/Help%20Pages/edit_comment.html"));
		startActivity(viewIntent);
	}
}