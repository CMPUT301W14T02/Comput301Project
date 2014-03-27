package ca.ualberta.cs.cmput301t02project.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import ca.ualberta.cs.cmput301t02project.ProjectApplication;
import ca.ualberta.cs.cmput301t02project.R;
import ca.ualberta.cs.cmput301t02project.model.CommentListModel;
import ca.ualberta.cs.cmput301t02project.model.CommentModel;
import ca.ualberta.cs.cmput301t02project.view.CommentListAdapter;
import ca.ualberta.cs.cmput301t02project.view.CommentListAdapterAbstraction;

/**
 * Displays comments replies to the current selected comment.
 * Current comment information including the current comment and a list of its replies is stored in ProjectApplication.getInstance().
 */
public class BrowseReplyCommentsActivity extends BrowseCommentsActivityAbstraction {

	// TODO: Refactor using new classes

	private ListView replyCommentListView;
	private TextView selectedComment;
	private CommentListAdapter adapter;
	private CommentListModel replyCommentList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reply_list);
		replyCommentListView = (ListView) findViewById(R.id.replyListView);
		selectedComment = (TextView) findViewById(R.id.selected_comment);

		// Display selected comment
		selectedComment.setText(ProjectApplication.getInstance().getCurrentComment().getText());
		
		// Create the sortBy menu and set up the adapter, inherited from BrowseCommentsActivity -SB
		setupPage();
		
		// If replying to comment -SB
		Button replyComment = (Button) findViewById(R.id.reply_button);
		replyComment.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(BrowseReplyCommentsActivity.this, CreateCommentActivity.class));
			}
		});

		// To view the replies of a reply -SB
		replyCommentListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> l, View v, int position, long id) {
				// Refactor into MVC?
				CommentModel nestedComment = (CommentModel) adapter.getItem(position);
				ProjectApplication.getInstance().setCurrentComment(nestedComment);
				
				CommentListModel nestedCommentList = nestedComment.getReplies();
				ProjectApplication.getInstance().setCurrentCommentList(nestedCommentList);

				Intent goToReplyListActivity = new Intent(getApplicationContext(), BrowseReplyCommentsActivity.class);
				startActivity(goToReplyListActivity);
			}
		});
	}

	@Override
	public void onResume() {
		super.onResume();
		
		adapter.notifyDataSetChanged();
	}
	
	/**
	 * Creates an adapter for displaying the replies to the current selected comment.
	 * <p>
	 * Called from onCreate().
	 * <p>
	 * @return 
	 */
	public CommentListAdapterAbstraction initializeAdapter(){

		// Get the comment list of replies to selected comment
		replyCommentList = ProjectApplication.getInstance().getCurrentCommentList();
		
		// Add comments to adapter
		adapter = new CommentListAdapter(this, R.layout.list_item, replyCommentList.getCommentList());
		replyCommentList.setAdapter(adapter);
		adapter.setModel(replyCommentList);

		// Display comments in adapter
		replyCommentListView.setAdapter(adapter);

		return adapter;
	}
	
}
