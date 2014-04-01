package ca.ualberta.cs.cmput301t02project.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import ca.ualberta.cs.cmput301t02project.R;
import ca.ualberta.cs.cmput301t02project.model.CommentListModel;
import ca.ualberta.cs.cmput301t02project.model.CommentModel;
import ca.ualberta.cs.cmput301t02project.model.TopLevelCommentList;
import ca.ualberta.cs.cmput301t02project.view.CommentListAdapter;
import ca.ualberta.cs.cmput301t02project.view.CommentListAdapterAbstraction;

/**
 * Displays comments top level comments (aka. comments with no parents, comments that are not replies to anything). 
 * A list of all top level comments is stored in ProjectApplication.getInstance().
 */

public class BrowseTopLevelCommentsActivity extends BrowseCommentsActivityAbstraction {
	
	// TODO: Refactor code to use new classes
	
	private CommentListModel model;
	//private CommentListAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_top_level_list);
		model = new TopLevelCommentList(this);

		// Create the sortBy menu and set up the adapter, inherited from BrowseCommentsActivity -SB
		setupPage();
		
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> l, View v, int position, long id) {
				CommentModel nestedComment = (CommentModel) adapter.getItem(position);
				
				Intent goToReplyListActivity = new Intent(getApplicationContext(),BrowseReplyCommentsActivity.class);
				goToReplyListActivity.putExtra("CommentId", nestedComment.getId());
				startActivity(goToReplyListActivity);
			}
		});
	}
	
	/**
	 * Creates an adapter for displaying the top level comments.
	 * <p>
	 * Called from onCreate().
	 * Replies to a comment can be viewed by clicking that comment. 
	 * <p>
	 * @return 
	 */
	public CommentListAdapterAbstraction initializeAdapter(){
		this.adapter = new CommentListAdapter(this, R.layout.list_item, model);
		return adapter;
	}
}
