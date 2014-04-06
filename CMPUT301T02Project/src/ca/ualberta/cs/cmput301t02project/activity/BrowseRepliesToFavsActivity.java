package ca.ualberta.cs.cmput301t02project.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ListView;
import ca.ualberta.cs.cmput301t02project.R;
import ca.ualberta.cs.cmput301t02project.controller.CommentController;
import ca.ualberta.cs.cmput301t02project.model.CommentModel;
import ca.ualberta.cs.cmput301t02project.model.RepliesToFavsListModel;
import ca.ualberta.cs.cmput301t02project.model.User;
import ca.ualberta.cs.cmput301t02project.view.CommentListAdapter;

/**
 * Displays the replies to a selected favorited comment of the current user.
 * Called when the user selects the a comment from the BrowseFavoritesActivity.
 * Current user information including their favorites list & the replies to those favorites is stored in User.
 */
public class BrowseRepliesToFavsActivity extends BrowseCommentsActivityAbstraction {

	// parent of the replies -SB
	private CommentModel currentComment;
	
	// list of replies to currentComment -SB
	private RepliesToFavsListModel model;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// set up the screen display. uses same layout as BrowseFavoritesActivity -SB
		setContentView(R.layout.activity_favorites_list);
		listView = (ListView) findViewById(R.id.commentListView);
		model = User.getUser().getRepliesToFavs();
		
		// retrieve the id of the parent and set the currentComment -SB
		final String currentCommentId = getIntent().getStringExtra("CommentId");
		final CommentController commentController = new CommentController(currentCommentId, this);
		currentComment = commentController.getComment();
		
		model.setParent(currentComment);

		// create the sortBy menu and set up the adapter, inherited from BrowseCommentsActivity -SB
		setupPage();
	}

	@Override
	public void onResume() {
		super.onResume();
		
		// update the view if the model has changed -SB
		model.refresh();
		adapter.notifyDataSetChanged();
	}
	
	public CommentListAdapter initializeAdapter(){
		
		// return the adapter for this class -SB
		this.adapter = new CommentListAdapter(this, R.layout.list_item, model);
		return adapter;
	}
	
	@Override
	public void goToHelpPage(){
		
		// go to help page for browsing favorites
		Intent viewIntent = new Intent("android.intent.action.VIEW",Uri.parse(
				"https://rawgithub.com/CMPUT301W14T02/Comput301Project/master/Help%20Pages/browse_favorite_comments.html"));
		startActivity(viewIntent);
	}
}
