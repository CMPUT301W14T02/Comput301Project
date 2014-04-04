package ca.ualberta.cs.cmput301t02project.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
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
 * and My Comments.
 */
public class MainMenuActivity extends ActionBarActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		
		setContentView(R.layout.activity_main);

		// Print welcome message on screen -SB
		TextView welcomeMessage = (TextView) findViewById(R.id.welcome_message);
		welcomeMessage.setText("Welcome " + User.getUser().getName() + "!");
		GPSLocation.initializeLocation(getApplicationContext());
		// If "Create" is clicked -SB
		Button createComment = (Button) findViewById(R.id.create);
		createComment.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Server server = new Server(MainMenuActivity.this);
				if(!server.isNetworkAvailable()) {
					int duration = Toast.LENGTH_SHORT;
					Toast toast = Toast.makeText(MainMenuActivity.this, "You don't have internet connection.", duration);
					toast.show();
				}
				else {
					Intent intent = new Intent(MainMenuActivity.this, CreateCommentActivity.class);
					intent.putExtra("isTopLevelComment", true);
					startActivity(intent);
				}
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
		
		Button browseFavorites = (Button) findViewById(R.id.favorites);
		browseFavorites.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
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
	
	// override to select a different menu xml than the ActionBarActivity default -SB
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
