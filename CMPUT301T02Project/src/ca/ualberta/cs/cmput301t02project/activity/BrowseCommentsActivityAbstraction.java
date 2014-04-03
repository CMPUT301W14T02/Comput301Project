package ca.ualberta.cs.cmput301t02project.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import ca.ualberta.cs.cmput301t02project.R;
import ca.ualberta.cs.cmput301t02project.model.User;
import ca.ualberta.cs.cmput301t02project.view.CommentListAdapterAbstraction;

/**
 * Contains common code inherited by activities that browse and sort comments, including 
 * BrowseMyCommmentsActivity, BrowseReplyCommentsActivity, BrowseFavoritesActivity, BrowseTopLevelCommentsActivtiy.
 * Includes methods for 
 * displaying a drop-down menu of sorting options, 
 */
public abstract class BrowseCommentsActivityAbstraction extends Activity implements OnItemSelectedListener{
	
	// The adapter used by the class that extends BrowseCommentsActivityAbstraction -SB
	protected CommentListAdapterAbstraction adapter;
	protected ListView listView;
	
	/**
	 * Adapter must be initialized in a class that extends BrowseCommentsActvityAbstraction so it can be specialized to the activity.
	 * <p>
	 * An adapter must be created and set up within any class that inherits BrowseCommentsActvityAbstraction.
	 * <p> 
	 * @return the adapter used by the class
	 */
	public abstract CommentListAdapterAbstraction initializeAdapter();
	
	public void goToHelpPage(){
		// default help page
	}
	
	/**
	 * Performs tasks to set up the activity including creating a sorting menu and initializing the adapter.
	 * <p>
	 * Sets up and displays a menu on the screen.
	 * Initializes the adapter for the class that extends BrowseCommentsActivityAbstraction. 
	 * Sets the adapter locally so actions can be performed if a menu item is selected
	 * <p>
	 */
	public void setupPage(){
		// Create the menu -SB
		createSpinner();
		adapter = initializeAdapter();
		listView.setAdapter(adapter);
	}
	
	public void logout(){
		Intent logoutActivity = new Intent(getApplicationContext(),LoginActivity.class);
		startActivity(logoutActivity);
	}
	
	public void returnToHomepage(){
		Intent goHome = new Intent(getApplicationContext(),MainMenuActivity.class);
		startActivity(goHome);
	}
	

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    
	    switch (item.getItemId()) {
	    	case R.id.help_page:
	    		goToHelpPage();
	    		return true;
	    	case R.id.home:
	    		returnToHomepage();
	    		return true;
	        case R.id.logout:
	            logout();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	/**
	 * Creates a drop-down menu of sorting options.
	 * <p>
	 * Called from onCreate().
	 * <p>
	 */
	public void createSpinner(){
		
		// Based on:
		// //www.androidhive.info/2012/04/android-spinner-dropdown-example/
		// Accessed February 26, 2014
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
		adapter.notifyDataSetChanged();
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub		
	}

}
