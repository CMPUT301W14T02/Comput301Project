package ca.ualberta.cs.cmput301t02project.view;

import java.util.ArrayList;

import ca.ualberta.cs.cmput301t02project.ProjectApplication;
import ca.ualberta.cs.cmput301t02project.R;
import ca.ualberta.cs.cmput301t02project.model.CommentListModel;
import ca.ualberta.cs.cmput301t02project.model.CommentModel;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;

public class BrowseMyCommentsActivity extends Activity implements OnItemSelectedListener {

	private CommentListModel myCommentsList;
	//private MyCommentsAdapter myCommentsListAdapter;
	private ListView myCommentListView;
	private ArrayAdapter<CommentModel> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_comments_list);
		myCommentListView = (ListView) findViewById(R.id.commentListView);

		// Create the sortBy menu -SB
		createSpinner();

		updateAdapter();
		
		myCommentListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> l, View v, int position, long id) {
				// Refactor into MVC?	
				CommentModel nestedComment = (CommentModel) adapter.getItem(position);
				ProjectApplication.setCurrentComment(nestedComment);
				
				Intent goToEditCommentActivity = new Intent(getApplicationContext(),EditCommentActivity.class);
				startActivity(goToEditCommentActivity);
			}
		});
	}

	@Override
	public void onResume() {
		super.onResume();
		
		updateAdapter();
	}
	
	private void updateAdapter(){
		
		// Retrieve the current comments list -SB
		myCommentsList = ProjectApplication.getMyCommentList();

		// Add comments to adapter
		adapter = new ArrayAdapter<CommentModel>(this, R.layout.list_item, myCommentsList.getCommentList());

		// Display comments in adapter
		myCommentListView.setAdapter(adapter);
	}
	
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
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

}
