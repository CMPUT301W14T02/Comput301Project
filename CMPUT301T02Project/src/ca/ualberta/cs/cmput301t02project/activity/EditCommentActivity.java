package ca.ualberta.cs.cmput301t02project.activity;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import ca.ualberta.cs.cmput301t02project.R;
import ca.ualberta.cs.cmput301t02project.controller.CommentController;

/**
 * Allows a user to edit the comment they selected.
 */
public class EditCommentActivity extends Activity {

	//Likely will not need this attribute. Will know when write code for custom location. -KW
	private Location currentLocation;

	public void setCustomCurrentLocation(double lat, double lon) {
		currentLocation.setLatitude(lat);
		currentLocation.setLongitude(lon);
	}
			
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String commentId = getIntent().getStringExtra("CommentId");
		final CommentController commentController = new CommentController(commentId, this);
			
		setContentView(R.layout.activity_edit_comment);
		
		EditText inputComment = (EditText) findViewById(R.id.edit_text);
		inputComment.setText(commentController.getText());

		Button post = (Button) findViewById(R.id.edit_post);
		
		post.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				EditText inputComment = (EditText) findViewById(R.id.edit_text);
				String newText = inputComment.getText().toString();
				commentController.edit(newText);
					
				finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_comment, menu);
		return true;
	}
}
