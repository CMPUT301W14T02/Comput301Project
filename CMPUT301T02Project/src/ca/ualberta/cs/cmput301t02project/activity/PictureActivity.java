package ca.ualberta.cs.cmput301t02project.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;
import ca.ualberta.cs.cmput301t02project.R;

/**
 * Performs tasks to do with taking and storing images.
 * Inherited by CreateCommentActivity and EditCommentActivity when the user tries to take a photo.
 * Extends ActionBarActivity because all activities that inherit it should have a menu bar.
 */
public class PictureActivity extends ActionBarActivity {

	// if no picture is taken, picture should remain null -SB
	Bitmap picture = null;
	
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

}
