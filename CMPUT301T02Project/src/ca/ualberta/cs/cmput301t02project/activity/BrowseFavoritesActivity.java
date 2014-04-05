package ca.ualberta.cs.cmput301t02project.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import ca.ualberta.cs.cmput301t02project.R;
import ca.ualberta.cs.cmput301t02project.model.CommentModel;
import ca.ualberta.cs.cmput301t02project.model.FavoritesListModel;
import ca.ualberta.cs.cmput301t02project.model.User;
import ca.ualberta.cs.cmput301t02project.view.CommentListAdapter;
import ca.ualberta.cs.cmput301t02project.view.CommentListAdapter;

/**
 * Displays the favorited comments of the current user.
 * Called when the user clicks the "Favorites" button on the main menu.
 * When the user selects a comment its replies are shown.
 * Current user information including their favorites list is stored in User.
 */
public class BrowseFavoritesActivity extends BrowseCommentsActivityAbstraction {

	private FavoritesListModel model;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// set up the sreen display -SB
		setContentView(R.layout.activity_favorites_list);
		listView = (ListView) findViewById(R.id.commentListView);
		model = User.getUser().getFavorites();

		setupPage();
		
		// when a comment is selected show its replies -SB
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> l, View v, int position, long id) {
				
				// get the selected comment -SB
				CommentModel nestedComment = (CommentModel) adapter.getItem(position);
				
				// go to the BrowseRepliesToFavsActivity to see the replies to that comment -SB
				Intent goToRepliesToFavsActivity = new Intent(BrowseFavoritesActivity.this, BrowseRepliesToFavsActivity.class);
				goToRepliesToFavsActivity.putExtra("CommentId", nestedComment.getId());
				startActivity(goToRepliesToFavsActivity);
			}
		});
	}

	@Override
	public void onResume() {
		super.onResume();
		
		// update the view if the model has changed -SB
		model.refresh();
		adapter.notifyDataSetChanged();
	}
	
	@Override
	public CommentListAdapter initializeAdapter(){
		
		// return the adapter for this class -SB
		this.adapter = new CommentListAdapter(this, R.layout.list_item, model);
		return adapter;
	}
	
	@Override
	public void goToHelpPage(){
		
		// go to help page for favoriting comments
		Intent viewIntent = new Intent("android.intent.action.VIEW",Uri.parse(
				"https://rawgithub.com/CMPUT301W14T02/Comput301Project/master/Help%20Pages/browse_favorite_comments.html"));
		startActivity(viewIntent);
	}
}
