package ca.ualberta.cs.cmput301t02project.view;

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

public class BrowseTopLevelCommentsActivity extends Activity implements OnItemSelectedListener {
	// TODO: Refactor code to use new classes
	// private CommentListModel commentList;
	// private CommentListAdapter commentListAdapter;

	private ListView topLevelCommentListView;
	private ArrayAdapter<CommentModel> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_top_level_list);
		topLevelCommentListView = (ListView) findViewById(R.id.commentListView);

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

		CommentListModel topLevelCommentList = ProjectApplication.getCommentList();

		// Add comments to adapter
		adapter = new ArrayAdapter<CommentModel>(this, R.layout.list_item, topLevelCommentList.getCommentList());

		// Display comments in adapter
		topLevelCommentListView.setAdapter(adapter);

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

	@Override
	public void onResume() {
		super.onResume();
		ProjectApplication.setCurrentCommentList(ProjectApplication.getCommentList());

		CommentListModel topLevelCommentList = ProjectApplication.getCommentList();

		// Add comments to adapter
		adapter = new ArrayAdapter<CommentModel>(this, R.layout.list_item, topLevelCommentList.getCommentList());

		// Display comments in adapter
		topLevelCommentListView.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.top_level_list, menu);
		return true;
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

}
