package ca.ualberta.cs.cmput301t02project.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import ca.ualberta.cs.cmput301t02project.R;
import ca.ualberta.cs.cmput301t02project.model.GPSLocation;
import ca.ualberta.cs.cmput301t02project.model.Server;
import ca.ualberta.cs.cmput301t02project.model.User;

/**
 * Displays menu options. 
 * Clicking a menu option leads to a different activity. 
 * Menu options include:
 * Create, 
 * Browse, 
 * Favorites, 
 * My Comments, 
 * and Followed User's Comments.
 * Called from LoginActivity 
 * or any activity that extends ActionBarActivity when "Home" is selected on the menu.
 */
public class MainMenuActivity extends ActionBarActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// set up the screen display -SB		
		setContentView(R.layout.activity_main);

		// print welcome message on screen -SB
		TextView welcomeMessage = (TextView) findViewById(R.id.welcome_message);
		welcomeMessage.setText("Welcome " + User.getUser().getName() + "!");
		
		// record the user's current location -SB
		GPSLocation.initializeLocation(getApplicationContext());
		
		// actions to take when the "Create" button is selected -SB
		Button createComment = (Button) findViewById(R.id.create);
		createComment.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				// try to retrieve comments saved on the server -SB
				Server server = new Server(MainMenuActivity.this);
				if(!server.isNetworkAvailable()) {
					
					// print error message to the screen -SB
					showMessage(MainMenuActivity.this, "You don't have internet connection.");
				}
				else {
					
					// go to the CreateCommentActivity to create a new top level comment -SB
					Intent intent = new Intent(MainMenuActivity.this, CreateCommentActivity.class);
					intent.putExtra("isTopLevelComment", true);
					startActivity(intent);
				}
			}
		});

		// actions to take when the "Browse" button is selected -SB
		Button browseComment = (Button) findViewById(R.id.browse);
		browseComment.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				// go the BrowseTopLevelCommentsActivity to view top level comments -SB
				startActivity(new Intent(MainMenuActivity.this, BrowseTopLevelCommentsActivity.class));
			}
		});
		
		// actions to take when the "Favorites" button is selected -SB
		Button browseFavorites = (Button) findViewById(R.id.favorites);
		browseFavorites.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				// go to the BrowseFavoritesActivity to view the current user's favorited comments -SB
				startActivity(new Intent(MainMenuActivity.this, BrowseFavoritesActivity.class));
			}
		});
		
		// actions to take when the "My Comments" button is selected -SB
		Button browseMyComments = (Button) findViewById(R.id.my_comments);
		browseMyComments.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				// go to the BrowseMyCommentsActivity to view comments the current user wrote -SB
				startActivity(new Intent(MainMenuActivity.this, BrowseMyCommentsActivity.class));
			}
		});
		
		
		// actions to take when the "Followed User's Comments" button is selected -SB
		Button browseFollowedComments = (Button) findViewById(R.id.followed_users);
		browseFollowedComments.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				// go to the BrowseFollowedCommentsActivity to view comments authored users that the current user follows -SB
				startActivity(new Intent(MainMenuActivity.this, BrowseFollowedCommentsActivity.class));
			}
		});
	}
	
	// override to select a different menu XML than the ActionBarActivity default (since there is no need for "Home") -SB
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public void goToHelpPage(){
		
		// redirect to help page for main menu -SB
		Intent viewIntent = new Intent("android.intent.action.VIEW",Uri.parse(
				"https://rawgithub.com/CMPUT301W14T02/Comput301Project/master/Help%20Pages/main_menu.html"));
		startActivity(viewIntent);
	}
}
