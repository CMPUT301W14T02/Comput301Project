package ca.ualberta.cs.cmput301t02project.activity;

import android.os.Bundle;
import android.widget.ListView;
import ca.ualberta.cs.cmput301t02project.R;
import ca.ualberta.cs.cmput301t02project.model.FollowedUserListCommentsModel;
import ca.ualberta.cs.cmput301t02project.model.User;
import ca.ualberta.cs.cmput301t02project.view.CommentListAdapter;
import ca.ualberta.cs.cmput301t02project.view.CommentListAdapterAbstraction;

/**
 * Displays comments authored by the current user's followed users. 
 */
public class BrowseFollowedCommentsActivity extends BrowseCommentsActivityAbstraction {

	private FollowedUserListCommentsModel model;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_comments_list);
		listView = (ListView) findViewById(R.id.commentListView);
		model = User.getUser().getFollowedUsers();
		
		// Create the sortBy menu and set up the adapter, inherited from BrowseCommentsActivity -SB
		setupPage();
	}
	
	@Override
	public void onResume() {
		super.onResume();
		model.refresh();
		adapter.notifyDataSetChanged();
	}
	
	/**
	 * Creates an adapter for displaying the list of the followed user's comments on the screen.
	 * <p>
	 * Called from onCreate().
	 * <p>
	 * @return the adapter used for BrowseFollowedCommentsActivity
	 */
	@Override
	public CommentListAdapterAbstraction initializeAdapter(){
		this.adapter = new CommentListAdapter(this, R.layout.list_item, model);
		return adapter;
	}
	
	@Override
	public void goToHelpPage(){
		// go to help page for replying to comments
	}
}
