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
import ca.ualberta.cs.cmput301t02project.view.MyCommentsAdapter;

/**
 * Displays comments that the current user authored. 
 * Current user information including a list of their comments is stored in ProjectApplication.getInstance().
 */
public class BrowseMyCommentsActivity extends Activity implements OnItemSelectedListener {

	private CommentListModel myCommentsList;
	private ListView myCommentListView;
	private MyCommentsAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_comments_list);
		myCommentListView = (ListView) findViewById(R.id.commentListView);

		// Create the sortBy menu -SB
		createSpinner();

		initializeAdapter();
		
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
	 */
	private void initializeAdapter(){
		
		// Retrieve the current comments list -SB
		myCommentsList = ProjectApplication.getInstance().getUser().getMyComments();

		// Add comments to adapter
		adapter = new MyCommentsAdapter(this, R.layout.list_item, myCommentsList.getCommentList());
		adapter.setModel(myCommentsList);
		
		// Display comments in adapter
		myCommentListView.setAdapter(adapter);
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
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.top_level_list, menu);
		return true;
	}
	
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
		
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
