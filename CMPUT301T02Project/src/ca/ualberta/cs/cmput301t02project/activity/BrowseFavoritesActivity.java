package ca.ualberta.cs.cmput301t02project.activity;

import android.os.Bundle;
import android.widget.ListView;
import ca.ualberta.cs.cmput301t02project.ProjectApplication;
import ca.ualberta.cs.cmput301t02project.R;
import ca.ualberta.cs.cmput301t02project.model.CommentListModel;
import ca.ualberta.cs.cmput301t02project.view.CommentListAdapterAbstraction;
import ca.ualberta.cs.cmput301t02project.view.FavoritesAdapter;

/**
 * Displays the favorited comments of the current user.
 * Current user information including their favorites list is stored in ProjectApplication.
 */
public class BrowseFavoritesActivity extends BrowseCommentsActivityAbstraction {

	private CommentListModel favoritesList;
	private ListView favoritesListView;
	private FavoritesAdapter adapter;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_favorites_list);
		
		favoritesListView = (ListView) findViewById(R.id.commentListView);

		setupPage();

	}

	@Override
	public void onResume() {
		super.onResume();
		initializeAdapter();
		adapter.sortList();
		adapter.notifyDataSetChanged();
	}
	
	/**
	 * Creates an adapter for displaying the list of favorites on the screen.
	 * <p>
	 * Called from onCreate().
	 * <p>
	 * @return 
	 */
	
	public CommentListAdapterAbstraction initializeAdapter(){
		
		// Retrieve myComments from phone storage -TH
		favoritesList = ProjectApplication.getInstance().getUser().getFavorites(getApplicationContext());

		// Add comments to adapter
		adapter = new FavoritesAdapter(this, R.layout.list_item, favoritesList.getCommentList());
		adapter.setModel(favoritesList);
		
		// Display comments in adapter
		favoritesListView.setAdapter(adapter);
		
		return adapter;
	}

}
