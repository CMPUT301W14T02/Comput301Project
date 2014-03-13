package ca.ualberta.cs.cmput301t02project.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import ca.ualberta.cs.cmput301t02project.ProjectApplication;
import ca.ualberta.cs.cmput301t02project.R;
import ca.ualberta.cs.cmput301t02project.model.CommentListModel;
import ca.ualberta.cs.cmput301t02project.model.CommentModel;
import ca.ualberta.cs.cmput301t02project.view.CommentListAdapter;

public class BrowseTopLevelCommentsActivity extends Activity implements OnItemSelectedListener {
	// TODO: Refactor code to use new classes
	
	private CommentListModel topLevelCommentList;
	private ListView topLevelCommentListView;
	private CommentListAdapter adapter;

	/**
	 * Sets up the on click listener for the listview
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_top_level_list);
		topLevelCommentListView = (ListView) findViewById(R.id.commentListView);

		// Create the sortby menu -SB
		createSpinner();
		
		// Populate the listview & set adapter -SB
		initializeAdapter();
		
		topLevelCommentListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> l, View v, int position, long id) {
				// Refactor into MVC?	
				CommentModel nestedComment = (CommentModel) adapter.getItem(position);
				ProjectApplication.setCurrentComment(nestedComment);
				CommentListModel nestedCommentList = nestedComment.getReplies();
				
				ProjectApplication.setCurrentCommentList(nestedCommentList);
				
				Intent goToReplyListActivity = new Intent(getApplicationContext(),BrowseReplyCommentsActivity.class);
				startActivity(goToReplyListActivity);
			}
		});
	}

	/**
	 * Updates the displayed list in case top level comments have changed
	 */
	@Override
	public void onResume() {
		super.onResume();
		
		// Update in case comments are edited created -SB
		ProjectApplication.setCurrentCommentList(ProjectApplication.getCommentList());
	}
	
	/**
	 * Retrieves comments to display and set the adapter
	 */
	private void initializeAdapter(){
		
		// Retrieve the current comments list -SB
		topLevelCommentList = ProjectApplication.getCommentList();

		// Add comments to adapter
		adapter = new CommentListAdapter(this, R.layout.list_item, topLevelCommentList.getCommentList());
		topLevelCommentList.setAdapter(adapter);
		adapter.setModel(topLevelCommentList);

		// Display comments in adapter
		topLevelCommentListView.setAdapter(adapter);
	}
	
	/**
	 * Creates the sortBy menu and displays it on the screen
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
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.top_level_list, menu);
		return true;
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		String selected = parent.getItemAtPosition(position).toString();
		if (selected.equals("Date")) {
			adapter.sortByDate();
		} else if (selected.equals("Picture")) {
			adapter.sortByPicture();
		} else if (selected.equals("My Location")) {
			adapter.sortByLocation();
		} else if (selected.equals("Other Location")) {
			adapter.sortByOtherLocation();
		} else if (selected.equals("Ranking")) {
			adapter.sortByRanking();
		} else if (selected.equals("Default")) {

		}

	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

}
