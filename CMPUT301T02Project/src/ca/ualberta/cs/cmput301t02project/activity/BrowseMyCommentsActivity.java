package ca.ualberta.cs.cmput301t02project.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import ca.ualberta.cs.cmput301t02project.ProjectApplication;
import ca.ualberta.cs.cmput301t02project.R;
import ca.ualberta.cs.cmput301t02project.model.CommentListModel;
import ca.ualberta.cs.cmput301t02project.model.CommentModel;
import ca.ualberta.cs.cmput301t02project.view.CommentListAdapterAbstraction;
import ca.ualberta.cs.cmput301t02project.view.MyCommentsAdapter;

/**
 * Displays comments that the current user authored. 
 * Current user information including a list of their comments is stored in ProjectApplication.getInstance().
 */
public class BrowseMyCommentsActivity extends BrowseCommentsActivityAbstraction {

	private CommentListModel myCommentsList;
	private ListView myCommentListView;
	private MyCommentsAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_comments_list);
		myCommentListView = (ListView) findViewById(R.id.commentListView);

		// Create the sortBy menu, inherited from BrowseCommentsActivity -SB
		setupPage(); 
		
		
		myCommentListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> l, View v, int position, long id) {
				// Refactor into MVC?	
				CommentModel nestedComment = (CommentModel) adapter.getItem(position);
				ProjectApplication.getInstance().setCurrentComment(nestedComment);
				
				Intent goToEditCommentActivity = new Intent(getApplicationContext(),EditCommentActivity.class);
				startActivity(goToEditCommentActivity);
			}
		});
	}
	
	

	@Override
	public void onResume() {
		super.onResume();
		
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
		
		// Retrieve the current comments list -SB
		myCommentsList = ProjectApplication.getInstance().getUser().getMyComments();

		// Add comments to adapter
		adapter = new MyCommentsAdapter(this, R.layout.list_item, myCommentsList.getCommentList());
		adapter.setModel(myCommentsList);
		
		// Display comments in adapter
		myCommentListView.setAdapter(adapter);
		
		return adapter;
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.top_level_list, menu);
		return true;
	}


}
