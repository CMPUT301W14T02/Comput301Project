package ca.ualberta.cs.cmput301t02project.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import ca.ualberta.cs.cmput301t02project.ProjectApplication;
import ca.ualberta.cs.cmput301t02project.R;
import ca.ualberta.cs.cmput301t02project.model.CommentListModel;

/**
 * Displays menu options. 
 * Clicking a menu option leads to a different activity. 
 * Menu options include:
 * Create, 
 * Browse, 
 * Favorites, 
 * and My Comments.
 */
public class MainMenuActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);

		// Print welcome message on screen -SB
		TextView welcomeMessage = (TextView) findViewById(R.id.welcome_message);
		welcomeMessage.setText("Welcome " + ProjectApplication.getInstance().getName().toString()	+ "!");
		
		// If "Create" is clicked -SB
		Button createComment = (Button) findViewById(R.id.create);
		createComment.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ProjectApplication.getInstance().setCurrentCommentList(new CommentListModel());
				startActivity(new Intent(MainMenuActivity.this, CreateCommentActivity.class));
			}
		});

		// If "Browse" is clicked -SB
		Button browseComment = (Button) findViewById(R.id.browse);
		browseComment.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainMenuActivity.this, BrowseTopLevelCommentsActivity.class));
			}
		});
		
		// FAVORITES ONCLICK LISTENER WILL GO HERE -SB
		
		// If "My Comments" is clicked -SB
		Button browseMyComments = (Button) findViewById(R.id.my_comments);
		browseMyComments.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainMenuActivity.this, BrowseMyCommentsActivity.class));
			}
		});
		
		// Initialize location -SB
		ProjectApplication.getInstance().InitializeLocationManager(this.getApplicationContext());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
