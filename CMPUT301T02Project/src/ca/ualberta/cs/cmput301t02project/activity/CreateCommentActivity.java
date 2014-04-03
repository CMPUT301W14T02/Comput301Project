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
 */
public class CreateCommentActivity extends ActionBarActivity {
	
	// if no picture is taken, picture should remain null -SB
	Bitmap picture = null;
	
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
	
	
	/**
	 * Takes a photo using the android camera
	 * <p>
	 * * Taken directly from the CameraTest project on eclass (accessed April 1, 2014) *
	 * Sets up a file to store the photo.
	 * Takes the photo and allows the user to retake it if needed.
	 * Returns to this activity when done.
	 * <p>
	 * @param v	to allow takePhoto to be called using XML, view is a required parameter
	 */
	public void takePhoto(View v) {

		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		
		String path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/savefile.jpg";
		intent.putExtra(MediaStore.EXTRA_OUTPUT, path);
		startActivityForResult(intent, 0);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		//String path = data.getStringExtra(MediaStore.EXTRA_OUTPUT);
		ImageButton imageButton = (ImageButton) findViewById(R.id.takeAPhoto);
		
		if(requestCode == 0) {
			if(resultCode == RESULT_OK) {
				
				// retrieve the bitmap -SB
				Bitmap bm = (Bitmap) data.getExtras().getParcelable("data");
				
				// display the image on the image button -SB
				imageButton.setImageBitmap(bm);
				
				// set the picture for the comment to the bitmap -SB
				picture = bm;
			}
			else {
				//something went wrong
			}
		}
	}	

	@Override
	public void goToHelpPage(){
		// redirect to help page for creating comments -SB
	}

}
