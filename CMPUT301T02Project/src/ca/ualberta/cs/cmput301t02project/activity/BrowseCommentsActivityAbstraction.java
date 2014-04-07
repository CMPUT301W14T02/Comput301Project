package ca.ualberta.cs.cmput301t02project.activity;

import java.util.ArrayList;

import android.content.Intent;
import android.location.Location;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import ca.ualberta.cs.cmput301t02project.R;
import ca.ualberta.cs.cmput301t02project.model.CommentListModel;
import ca.ualberta.cs.cmput301t02project.view.CommentListAdapter;

/**
 * Contains common code inherited by activities that browse and sort comments, including 
 * all the Browse* activities:
 * BrowseMyCommmentsActivity, BrowseReplyCommentsActivity, BrowseFavoritesActivity, 
 * BrowseTopLevelCommentsActivtiy, and BrowseFollowedCommentsActivity.
 * Includes methods for 
 * displaying a drop-down menu of sorting options
 */
public abstract class BrowseCommentsActivityAbstraction extends ActionBarActivity implements OnItemSelectedListener {
	
	// The adapter used by the class that extends BrowseCommentsActivityAbstraction -SB
	protected CommentListAdapter adapter;
	protected ListView listView;
	private static final int GET_COORDINATES = 0;
	
	protected void initializeView(CommentListModel model) {
		this.adapter = new CommentListAdapter(this, R.layout.list_item, model);
		listView.setAdapter(adapter);
		model.addView(adapter);
	}

	/**
	 * Creates a drop-down menu of sorting options.
	 * <p>
	 * Called from onCreate().
	 * <p>
	 */
	protected void createSpinner(){
		
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
			adapter.sortByFaves();
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
	 * then sends them to the adapter as a location to be used for sorting.
	 * Parameters are never set in our code, 
	 * instead android is responsible for calling this method.
	 * <p> 
	 */
	@Override
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
		
	}
}
