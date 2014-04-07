package ca.ualberta.cs.cmput301t02project.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ListView;
import ca.ualberta.cs.cmput301t02project.R;
import ca.ualberta.cs.cmput301t02project.model.FollowedUserCommentsListModel;
import ca.ualberta.cs.cmput301t02project.model.User;

/**
 * Displays all comments authored by the current user's followed users. 
 * Called when the user clicks the "Followed User's Comments" button on the main menu.
 * Current user information including comments belonging to their followed users and a list of their usernames is stored in User.
 */
public class BrowseFollowedCommentsActivity extends BrowseCommentsActivityAbstraction {

	private FollowedUserCommentsListModel model;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// set up the screen display. uses the same layout as BrowseMyCommentsActivity -SB
		setContentView(R.layout.activity_my_comments_list);
		createSpinner();
		listView = (ListView) findViewById(R.id.commentListView);
		model = User.getUser().getFollowedUsers();
		super.initializeView(model);
	}
	
	@Override
	public void onResume() {
		super.onResume();
		model.refresh();
	}
	
	@Override
	public void goToHelpPage(){
		
		// go to help page for viewing followed comments -SB
		Intent viewIntent = new Intent("android.intent.action.VIEW",Uri.parse(
				"https://rawgithub.com/CMPUT301W14T02/Comput301Project/master/Help%20Pages/browse_followed_comments.html"));
		startActivity(viewIntent);
	}
}
