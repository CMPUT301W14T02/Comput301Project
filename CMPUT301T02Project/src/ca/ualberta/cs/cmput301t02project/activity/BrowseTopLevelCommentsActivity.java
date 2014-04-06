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
import ca.ualberta.cs.cmput301t02project.model.TopLevelCommentList;
import ca.ualberta.cs.cmput301t02project.view.CommentListAdapter;

/**
 * Displays comments top level comments (aka. comments with no parents, comments that are not replies to anything). 
 * Called when the user selects the "Browse" button on the main menu. 
 * When the user selects a comment BrowseReplyActivity is called where the user is able to 
 * view the comment's replies, view the comment's picture (if there is one attached), 
 * reply to the comment, add the comment to their favorites, follow the comment author, 
 * or edit the comment (if it is their own comment).
 * A list of all top level comments is retrieved from the server in the Server class.
 */
public class BrowseTopLevelCommentsActivity extends BrowseCommentsActivityAbstraction{
	
	private TopLevelCommentList model;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// set up the screen display -SB
		setContentView(R.layout.activity_top_level_list);
		model = TopLevelCommentList.getInstance(getApplicationContext());
		listView = (ListView) findViewById(R.id.commentListView);
		
		// create the sortBy menu and set up the adapter, inherited from BrowseCommentsActivity -SB
		setupPage();
		
		model.addObserver(adapter);
		
		// actions to take when a comment is selected -SB 
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> l, View v, int position, long id) {
				
				// get the selected comment -SB
				CommentModel nestedComment = (CommentModel) adapter.getItem(position);
				
				// go to the BrowseReplyCommentsActivity to view the selected comment's replies etc -SB
				Intent goToReplyListActivity = new Intent(getApplicationContext(),BrowseReplyCommentsActivity.class);
				goToReplyListActivity.putExtra("CommentId", nestedComment.getId());
				startActivity(goToReplyListActivity);
			}
		});
	}
	
	@Override
	public void onResume() {
		super.onResume();
		
		// update the view if the model has changed -SB
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
		
		// go to help page for top level comments
		Intent viewIntent = new Intent("android.intent.action.VIEW",Uri.parse(
				"https://rawgithub.com/CMPUT301W14T02/Comput301Project/master/Help%20Pages/browse_top_level_comments.html"));
		startActivity(viewIntent);
	}
}
