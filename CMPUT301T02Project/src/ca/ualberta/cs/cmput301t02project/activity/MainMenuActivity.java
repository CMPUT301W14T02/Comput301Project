package ca.ualberta.cs.cmput301t02project.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import ca.ualberta.cs.cmput301t02project.R;
import ca.ualberta.cs.cmput301t02project.model.CommentModel;
import ca.ualberta.cs.cmput301t02project.model.User;

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
		welcomeMessage.setText("Welcome " + User.getUser().getName() + "!");
		
		// If "Create" is clicked -SB
		Button createComment = (Button) findViewById(R.id.create);
		createComment.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainMenuActivity.this, CreateCommentActivity.class);
				intent.putExtra("isTopLevelComment", true);
				startActivity(intent);
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
		Button browseFavorites = (Button) findViewById(R.id.favorites);
		browseFavorites.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// Retrieve favorites from storage and set it as the list to view in the next
				// activity -TH
				ArrayList<CommentModel> favs = new ArrayList<CommentModel>();
				favs = User.getUser().getFavorites();
				//ProjectApplication.getInstance().getUser().setFavoritesToView(favs);
				startActivity(new Intent(MainMenuActivity.this, BrowseFavoritesActivity.class));
			}
		});
		
		// If "My Comments" is clicked -SB
		Button browseMyComments = (Button) findViewById(R.id.my_comments);
		browseMyComments.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainMenuActivity.this, BrowseMyCommentsActivity.class));
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
