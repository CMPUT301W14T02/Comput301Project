package ca.ualberta.cs.cmput301t02project.activity;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import ca.ualberta.cs.cmput301t02project.Camera;
import ca.ualberta.cs.cmput301t02project.ProjectApplication;
import ca.ualberta.cs.cmput301t02project.R;
import ca.ualberta.cs.cmput301t02project.controller.CommentListController;
import ca.ualberta.cs.cmput301t02project.controller.MyCommentsController;
import ca.ualberta.cs.cmput301t02project.model.CommentModel;

/**
 * Allows a user to create their own comment. 
 */
public class CreateCommentActivity extends Activity {

	// TODO: Refactor with new classes
	private CommentListController commentListController;
	private MyCommentsController myCommentsListController;

	
	//Likely will not need this attribute. Will know when write code for custom location. -KW
	private Location currentLocation;

	public void setCustomCurrentLocation(double lat, double lon) {
		currentLocation.setLatitude(lat);
		currentLocation.setLongitude(lon);
	}
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		commentListController = new CommentListController(ProjectApplication.getInstance().getCurrentCommentList());
		myCommentsListController = new MyCommentsController(ProjectApplication.getInstance().getUser().getMyComments());
		
		setContentView(R.layout.activity_create_comment);
		
		final EditText latitude = (EditText) findViewById(R.id.latitude_box);
		final EditText longitude = (EditText) findViewById(R.id.longitude_box);
		
		double currLat = ProjectApplication.getInstance().getCurrentLocation().getLatitude();
		double currLon = ProjectApplication.getInstance().getCurrentLocation().getLongitude();
		final String strLat = String.valueOf(currLat);
		final String strLon = String.valueOf(currLon);
		
		latitude.setText(strLat);
		longitude.setText(strLon);
		
		Button post = (Button) findViewById(R.id.create_post);

		post.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {

				EditText inputComment = (EditText) findViewById(R.id.create_text);
				
				//for custom locations
				double lat = 0, lon = 0;
				Location customLocation = new Location("");

				if ((latitude.getText().toString())!= strLat && ((longitude.getText().toString())!= strLon))
				{
					lat = Double.valueOf(latitude.getText().toString());
					lon = Double.valueOf(longitude.getText().toString());
					customLocation.setLatitude(lat);
					customLocation.setLongitude(lon);
				} 

				// Refactor into MVC?
				CommentModel comment;
				
				comment = commentListController.addNewComment(inputComment.getText().toString(), 
						null, ProjectApplication.getInstance().getName().toString(), customLocation);
				
				myCommentsListController.addNewComment(comment, getApplicationContext());
				ProjectApplication.getInstance().pushUser();
				finish();
			}
		});

	}
	
	
	// For the takePhoto method
	Uri imageFileUri;
	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	
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
		String folder = Environment.getExternalStorageDirectory().getAbsolutePath() + "/tmp";
		File folderF = new File(folder);
		
		if (!folderF.exists()) {
			folderF.mkdir();
		}
	        
		String imageFilePath = folder + "/" + String.valueOf(System.currentTimeMillis()) + "jpg";
		File imageFile = new File(imageFilePath);
		imageFileUri = Uri.fromFile(imageFile);
	        
		intent.putExtra(MediaStore.EXTRA_OUTPUT, imageFileUri);
		startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
	}
	    
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_comment, menu);
		return true;
	}

}
