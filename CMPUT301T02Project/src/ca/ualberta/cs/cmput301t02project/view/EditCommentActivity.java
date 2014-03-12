package ca.ualberta.cs.cmput301t02project.view;

import ca.ualberta.cs.cmput301t02project.CurrentUser;
import ca.ualberta.cs.cmput301t02project.ProjectApplication;
import ca.ualberta.cs.cmput301t02project.R;
import ca.ualberta.cs.cmput301t02project.controller.CommentListController;
import ca.ualberta.cs.cmput301t02project.controller.MyCommentListController;
import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditCommentActivity extends Activity {

		private MyCommentListController myCommentsListController;
		private CommentListController commentListController;

		
		//Likely will not need this attribute. Will know when write code for custom location. -KW
		private Location currentLocation;

		public void setCustomCurrentLocation(double lat, double lon) {
			currentLocation.setLatitude(lat);
			currentLocation.setLongitude(lon);
		}
			
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			myCommentsListController = new MyCommentListController(ProjectApplication.getMyCommentList());
			commentListController = new CommentListController(ProjectApplication.getCommentList());
			setContentView(R.layout.activity_edit_comment);
			EditText inputComment = (EditText) findViewById(R.id.edit_text);
			inputComment.setText(ProjectApplication.getCurrentComment().getText());

			Button post = (Button) findViewById(R.id.edit_post);
			String loc = "No location yet";
			currentLocation = new Location(loc);

			post.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					EditText inputComment = (EditText) findViewById(R.id.edit_text);
					// Refactor into MVC?
					// Username is temporarily in front of the comment. Can redesign
					// later -SB

					myCommentsListController.changeText(ProjectApplication.getCurrentComment(), inputComment.getText()
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
