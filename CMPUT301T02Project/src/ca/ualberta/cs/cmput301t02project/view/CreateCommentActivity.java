package ca.ualberta.cs.cmput301t02project.view;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import ca.ualberta.cs.cmput301t02project.CurrentUser;
import ca.ualberta.cs.cmput301t02project.ProjectApplication;
import ca.ualberta.cs.cmput301t02project.R;
import ca.ualberta.cs.cmput301t02project.controller.CommentListController;
import ca.ualberta.cs.cmput301t02project.controller.MyCommentListController;

public class CreateCommentActivity extends Activity {

	// TODO: Refactor with new classes
	private CommentListController commentListController;
	private MyCommentListController myCommentsListController;

	
	//Likely will not need this attribute. Will know when write code for custom location. -KW
	private Location currentLocation;

	public void setCustomCurrentLocation(double lat, double lon) {
		currentLocation.setLatitude(lat);
		currentLocation.setLongitude(lon);
	}
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		commentListController = new CommentListController(
				ProjectApplication.getCurrentCommentList());
		myCommentsListController = new MyCommentListController(
				ProjectApplication.getMyCommentList());
		setContentView(R.layout.activity_create_comment);

		Button post = (Button) findViewById(R.id.create_post);
		String loc = "No location yet";
		currentLocation = new Location(loc);

		post.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				EditText inputComment = (EditText) findViewById(R.id.create_text);

				// Refactor into MVC?
				// Username is temporarily in front of the comment. Can redesign
				// later -SB

				commentListController.addNewComment(inputComment.getText()
						.toString(), null, null, CurrentUser.getName()
						.toString());
				myCommentsListController.addNewComment(inputComment.getText()
						.toString(), null, null, CurrentUser.getName()
						.toString());
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
