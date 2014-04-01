package ca.ualberta.cs.cmput301t02project.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import ca.ualberta.cs.cmput301t02project.R;
import ca.ualberta.cs.cmput301t02project.model.CommentModel;
import ca.ualberta.cs.cmput301t02project.model.MyCommentsListModel;
import ca.ualberta.cs.cmput301t02project.model.User;
import ca.ualberta.cs.cmput301t02project.view.CommentListAdapterAbstraction;
import ca.ualberta.cs.cmput301t02project.view.MyCommentsAdapter;

/**
 * Displays comments that the current user authored. 
 * Current user information including a list of their comments is stored in ProjectApplication.getInstance().
 */
public class BrowseMyCommentsActivity extends BrowseCommentsActivityAbstraction {

	private MyCommentsListModel model;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_comments_list);
		model = User.getUser().getMyComments();
		// Create the sortBy menu and set up the adapter, inherited from BrowseCommentsActivity -SB
		setupPage();
		
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> l, View v, int position, long id) {
				CommentModel nestedComment = (CommentModel) adapter.getItem(position);
				Intent goToEditCommentActivity = new Intent(getApplicationContext(), EditCommentActivity.class);
				goToEditCommentActivity.putExtra("CommentId", nestedComment.getId());
				startActivity(goToEditCommentActivity);
			}
		});
	}
	
	/**
	 * Creates an adapter for displaying the list of the current user's comments on the screen.
	 * <p>
	 * Called from onCreate().
	 * Comments from the current user are able to be edited when the user clicks on one. 
	 * <p>
	 * @return the adapter used for BrowseMyCommentsActivity
	 */
	@Override
	public CommentListAdapterAbstraction initializeAdapter(){
		this.adapter = new MyCommentsAdapter(this, R.layout.list_item, model);
		return adapter;
	}

}
