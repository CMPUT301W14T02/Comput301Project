package ca.ualberta.cs.cmput301t02project.activity;

import ca.ualberta.cs.cmput301t02project.ProjectApplication;
import ca.ualberta.cs.cmput301t02project.R;
import ca.ualberta.cs.cmput301t02project.model.CommentListModel;
import ca.ualberta.cs.cmput301t02project.view.FavoritesAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemSelectedListener;

/**
 * Displays the favorited comments of the current user.
 * Current user information including their favorites list is stored in ProjectApplication.
 */
public class BrowseFavoritesActivity extends Activity implements OnItemSelectedListener {

	private CommentListModel favoritesList;
	private ListView favoritesListView;
	private FavoritesAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_favorites_list);
		
		/****************************************************************/
		favoritesListView = (ListView) findViewById(R.id.commentListView);

		initializeAdapter();

	}

	@Override
	public void onResume() {
		super.onResume();
		
		adapter.notifyDataSetChanged();
	}
	
	/**
	 * Creates an adapter for displaying the list of favorites on the screen.
	 * <p>
	 * Called from onCreate().
	 * <p>
	 */
	private void initializeAdapter(){
		
		// Retrieve the current comments list -TH
		favoritesList = ProjectApplication.getUser().getFavorites();

		// Add comments to adapter
		adapter = new FavoritesAdapter(this, R.layout.list_item, favoritesList.getCommentList());
		adapter.setModel(favoritesList);
		
		// Display comments in adapter
		favoritesListView.setAdapter(adapter);
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

}
