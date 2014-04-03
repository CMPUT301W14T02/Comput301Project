package ca.ualberta.cs.cmput301t02project.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import ca.ualberta.cs.cmput301t02project.R;
import ca.ualberta.cs.cmput301t02project.controller.CommentController;

/**
 * Allows a user to edit the comment they selected.
 */
public class EditCommentActivity extends ActionBarActivity {

			
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
	
		latitude.setText(String.valueOf(commentLat));
		longitude.setText(String.valueOf(commentLon));


		Button post = (Button) findViewById(R.id.edit_post);
		
		post.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//for editing text
				EditText inputComment = (EditText) findViewById(R.id.edit_text);
				String newText = inputComment.getText().toString();
				
				//for editing locations
				double lat = 0, lon = 0;
				try {
					lat = Double.valueOf(latitude.getText().toString());
					lon = Double.valueOf(longitude.getText().toString());
					if ((lat > 90) || (lat < -90)) { throw new NumberFormatException();}
					if ((lon > 180) || (lon < -180)) { throw new NumberFormatException();}

				} catch  (NumberFormatException ex){
					lat =  commentController.getComment().getLocation().getLatitude();
					lon = commentController.getComment().getLocation().getLongitude();
				}
				
				commentController.edit(newText, lat, lon);
	
				finish();
			}
		});
	}
}
