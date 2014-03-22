package ca.ualberta.cs.cmput301t02project.activity;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

		Button post = (Button) findViewById(R.id.create_post);

		post.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {

				EditText inputComment = (EditText) findViewById(R.id.create_text);

				// Refactor into MVC?
				CommentModel comment;
				comment = commentListController.addNewComment(inputComment.getText().toString(), 
						null, ProjectApplication.getInstance().getName().toString());
				
				myCommentsListController.addNewComment(comment);
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
