package ca.ualberta.cs.cmput301t02project.activity;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import ca.ualberta.cs.cmput301t02project.R;
import ca.ualberta.cs.cmput301t02project.view.CommentListAdapterAbstraction;

/**
 * Contains common code inherited by activities that browse and sort comments, including 
 * all the Browse* activities:
 * BrowseMyCommmentsActivity, BrowseReplyCommentsActivity, BrowseFavoritesActivity, 
 * BrowseTopLevelCommentsActivtiy, and BrowseFollowedCommentsActivity.
 * Includes methods for 
 * displaying a drop-down menu of sorting options
 */
public abstract class BrowseCommentsActivityAbstraction extends ActionBarActivity implements OnItemSelectedListener{
	
	// The adapter used by the class that extends BrowseCommentsActivityAbstraction -SB
	protected CommentListAdapterAbstraction adapter;
	protected ListView listView;
	static final int GET_COORDINATES = 0;
	
	/**
	 * Adapter must be initialized in a class that extends BrowseCommentsActvityAbstraction so it can be specialized to the activity.
	 * <p>
	 * An adapter MUST be created and set up within any class that inherits BrowseCommentsActvityAbstraction.
	 * <p> 
	 * @return the adapter used by the class
	 */
	public abstract CommentListAdapterAbstraction initializeAdapter();
	
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
	
	/**
	 * Prints a pop-up message on the screen.
	 * <p>
	 * The screen and message are specified in the parameters.
	 * context determines which activity to display the message. 
	 * An example input of the context parameter is "BrowseReplyCommentsActivity.this".
	 * message specifies what to print. 
	 * An example input of the message parameter is "No internet connection :(".
	 * This method is called from the Browse* activities that extend BrowseCommentsActivityAbstraction.
	 * <p>
	 * @param context	the calling activity's context
	 * @param message	the desired message to print
	 */
	public void showMessage(Context context, String message){
		int duration = Toast.LENGTH_SHORT;
		Toast toast = Toast.makeText(context, message, duration);
		toast.show();
	}

	/**
	 * Creates a drop-down menu of sorting options.
	 * <p>
	 * Called from onCreate().
	 * <p>
	 */
	public void createSpinner(){
		
		/* Based on:
		 * www.androidhive.info/2012/04/android-spinner-dropdown-example/
		 * Accessed February 26, 2014
		 */
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
		sortBy.add("Faves");

		// Create adapter for spinner
		ArrayAdapter<String> spinner_adapter = new ArrayAdapter<String>(this, R.layout.list_item, sortBy);

		// Drop down layout style - list view with radio button
		spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		// attach adapter to spinner
		spinner.setAdapter(spinner_adapter);		
	}

	/**
	 * Deals with choosing actions when spinner elements are selected.
	 * <p>
	 * All parameters required from implementing OnItemSelectedListener.
	 * This method is not called from our code. 
	 * Instead android does it automatically when a spinner element is clicked.
	 * <p>
	 */
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
			Intent intent = new Intent(this, EnterSearchCoordinatesActivity.class);
			startActivityForResult(intent, GET_COORDINATES);
		} 
		else if (selected.equals("Faves")) {
			adapter.sortByRanking();
		} 
		else if (selected.equals("Default")) {
			adapter.sortByDefault();
		}
		adapter.notifyDataSetChanged();
	}

	/**
	 *  Handles result from EnterSearchCoordinatesActivity 
	 *  <p>
	 * Takes the returned intent and extracts the data sent with it,
	 * namely the latitude and longitude coordinates entered by the user
	 * then sends them to the adapter as a location to be used for sorting 
	 */
	
	protected void onActivityResult(int requestCode, int resultCode, Intent result) {
		if (requestCode == GET_COORDINATES) { 
			
			double otherLocLat = result.getDoubleExtra("Other Location Lat", 0);
			double otherLocLon = result.getDoubleExtra("Other Location Lon", 0);
		
			Location otherLocation = new Location("");
			otherLocation.setLatitude(otherLocLat);
			otherLocation.setLongitude(otherLocLon);
		    
			adapter.sortByOtherLocation(otherLocation);
		
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub		
	}
}
