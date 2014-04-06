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
import ca.ualberta.cs.cmput301t02project.model.MyCommentsListModel;
import ca.ualberta.cs.cmput301t02project.model.Server;
import ca.ualberta.cs.cmput301t02project.model.User;
import ca.ualberta.cs.cmput301t02project.view.CommentListAdapter;

/**
 * Displays comments that the current user authored. 
 * Only displays comments that were posted from that user's device.
 * If a user logs in with the same username on a different device their comments will not show up in "My Comments".
 * Called when the user selects the "My Comments" button on the main menu.
 * When the user selects a comment they are able to edit that comment. 
 * Current user information including a list of their comments is stored in User.
 */
public class BrowseMyCommentsActivity extends BrowseCommentsActivityAbstraction {

	private MyCommentsListModel model;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
		// set up the screen display -SB
		setContentView(R.layout.activity_my_comments_list);
		listView = (ListView) findViewById(R.id.commentListView);
		model = User.getUser().getMyComments();
	
		// create the sortBy menu and set up the adapter, inherited from BrowseCommentsActivity -SB
		setupPage();
		
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> l, View v, int position, long id) {
				
				// try to retrieve comments saved on the server -SB
				Server server = new Server(BrowseMyCommentsActivity.this);
				if(!server.isNetworkAvailable()) {
					
					// print error message to the screen -SB
					showMessage(BrowseMyCommentsActivity.this, "You don't have internet connection.");
				}
				else {
					
					// get the selected comment -SB
					CommentModel nestedComment = (CommentModel) adapter.getItem(position);
					
					// go to the EditCommentActivity to edit that comment -SB
					Intent goToEditCommentActivity = new Intent(getApplicationContext(), EditCommentActivity.class);
					goToEditCommentActivity.putExtra("CommentId", nestedComment.getId());
					startActivity(goToEditCommentActivity);
				}
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
		
		// go to help page for "my comments" -SB
		Intent viewIntent = new Intent("android.intent.action.VIEW",Uri.parse(
				"https://rawgithub.com/CMPUT301W14T02/Comput301Project/master/Help%20Pages/browse_my_comments.html"));
		startActivity(viewIntent);
	}
}
