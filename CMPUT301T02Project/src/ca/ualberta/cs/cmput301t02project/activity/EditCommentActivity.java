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

			
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String commentId = getIntent().getStringExtra("CommentId");
		final CommentController commentController = new CommentController(commentId, this);
			
		setContentView(R.layout.activity_edit_comment);
		
		EditText inputComment = (EditText) findViewById(R.id.edit_text);

		inputComment.setText(commentController.getText());
		
		final EditText latitude = (EditText) findViewById(R.id.latitude_editBox);
		final EditText longitude = (EditText) findViewById(R.id.longitude_editBox);
		
		double commentLat = commentController.getComment().getLocation().getLatitude();
		double commentLon = commentController.getComment().getLocation().getLongitude();
		final String strLat = String.valueOf(commentLat);
		final String strLon = String.valueOf(commentLon);
		
		latitude.setText(strLat);
		longitude.setText(strLon);


		Button post = (Button) findViewById(R.id.edit_post);
		
		post.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				EditText inputComment = (EditText) findViewById(R.id.edit_text);
				String newText = inputComment.getText().toString();
				commentController.edit(newText);
				
				//for editing locations
				double lat = 0, lon = 0;
				/*
				if ((latitude.getText().toString())!= strLat || ((longitude.getText().toString())!= strLon))
				{
					lat = Double.valueOf(latitude.getText().toString());
					lon = Double.valueOf(longitude.getText().toString());
					commentLocation.setLatitude(strLat);
					commentLocation.setLongitude(strLon);
					commentController.getComment().setLocation(commentLocation);
				} */
				
				lat = Double.valueOf(latitude.getText().toString());
				lon = Double.valueOf(longitude.getText().toString());
				commentController.getComment().getLocation().setLatitude(lat);
				commentController.getComment().getLocation().setLongitude(lon);
				
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
