package ca.ualberta.cs.cmput301t02project.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;
import ca.ualberta.cs.cmput301t02project.R;
import ca.ualberta.cs.cmput301t02project.model.CommentModel;
import ca.ualberta.cs.cmput301t02project.model.MyCommentsListModel;
import ca.ualberta.cs.cmput301t02project.model.Server;
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
		listView = (ListView) findViewById(R.id.commentListView);
		model = User.getUser().getMyComments();
		// Create the sortBy menu and set up the adapter, inherited from BrowseCommentsActivity -SB
		setupPage();
		
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> l, View v, int position, long id) {
				Server server = new Server(BrowseMyCommentsActivity.this);
				if(!server.isNetworkAvailable()) {
					int duration = Toast.LENGTH_SHORT;
					Toast toast = Toast.makeText(BrowseMyCommentsActivity.this, "You don't have internet connection.", duration);
					toast.show();
				}
				else {
					CommentModel nestedComment = (CommentModel) adapter.getItem(position);
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
		model.refresh();
		adapter.notifyDataSetChanged();
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
	
	@Override
	public void goToHelpPage(){
		// go to help page for replying to comments
	}
}
