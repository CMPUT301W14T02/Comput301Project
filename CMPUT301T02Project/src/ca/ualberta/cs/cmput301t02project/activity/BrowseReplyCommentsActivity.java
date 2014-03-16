package ca.ualberta.cs.cmput301t02project.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import ca.ualberta.cs.cmput301t02project.ProjectApplication;
import ca.ualberta.cs.cmput301t02project.R;
import ca.ualberta.cs.cmput301t02project.model.CommentListModel;
import ca.ualberta.cs.cmput301t02project.model.CommentModel;
import ca.ualberta.cs.cmput301t02project.view.CommentListAdapter;

/**
 * Displays comments replies to the current selected comment.
 * Current comment information including the current comment and a list of its replies is stored in ProjectApplication.
 */
public class BrowseReplyCommentsActivity extends Activity implements OnItemSelectedListener {

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
		selectedComment.setText(ProjectApplication.getCurrentComment().getText());
		
		createSpinner();

		// Get the comment list of replies to selected comment
		replyCommentList = ProjectApplication.getCurrentCommentList();

		initializeAdapter();
		
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
				ProjectApplication.setCurrentComment(nestedComment);
				
				CommentListModel nestedCommentList = nestedComment.getReplies();
				ProjectApplication.setCurrentCommentList(nestedCommentList);

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
	 * Comments from the current user are able to be edited when the user clicks on one. 
	 * <p>
	 */
	private void initializeAdapter(){

		// Add comments to adapter
		adapter = new CommentListAdapter(this, R.layout.list_item, replyCommentList.getCommentList());
		replyCommentList.setAdapter(adapter);
		adapter.setModel(replyCommentList);

		// Display comments in adapter
		replyCommentListView.setAdapter(adapter);

	}
	
	/**
	 * Creates a drop-down menu of sorting options.
	 * <p>
	 * Called from onCreate().
	 * <p>
	 */
	private void createSpinner(){

		// Based on:
		// //www.androidhive.info/2012/04/android-spinner-dropdown-example/
		// Spinner element
		Spinner spinner = (Spinner) findViewById(R.id.spinner);

		// Spinner click listener
		spinner.setOnItemSelectedListener(this);

		// Spinner Drop down elements
		ArrayList<String> sortBy = new ArrayList<String>();
		sortBy.add("Default");
		sortBy.add("Date");
		sortBy.add("Picture");
		sortBy.add("My Location");
		sortBy.add("Other Location");
		sortBy.add("Ranking");

		// Create adapter for spinner
		ArrayAdapter<String> spinner_adapter = new ArrayAdapter<String>(this, R.layout.list_item, sortBy);

		// Drop down layout style - list view with radio button
		spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		// attach adapter to spinner
		spinner.setAdapter(spinner_adapter);
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		String selected = parent.getItemAtPosition(position).toString();
		if (selected.equals("Date")) {
			adapter.sortByDate();
		} 
		else if (selected.equals("Picture")) {
			adapter.sortByPicture();
		} 
		else if (selected.equals("My Location")) {
			adapter.sortByLocation();
		} 
		else if (selected.equals("Other Location")) {
			adapter.sortByOtherLocation();
		} 
		else if (selected.equals("Ranking")) {
			adapter.sortByRanking();
		} 
		else if (selected.equals("Default")) {
			adapter.sortByDefault();
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
	}
}
