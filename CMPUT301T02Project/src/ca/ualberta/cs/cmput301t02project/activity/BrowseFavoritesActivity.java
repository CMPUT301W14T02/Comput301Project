package ca.ualberta.cs.cmput301t02project.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import ca.ualberta.cs.cmput301t02project.ProjectApplication;
import ca.ualberta.cs.cmput301t02project.R;
import ca.ualberta.cs.cmput301t02project.model.CommentListModel;
import ca.ualberta.cs.cmput301t02project.model.CommentModel;
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
		
		// To view the replies of a favorited comment -TH
		favoritesListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> l, View v, int position, long id) {
				// Refactor into MVC?
				//CommentModel nestedComment = (CommentModel) adapter.getItem(position);
				//ProjectApplication.getInstance().getUser().getFavorites(getApplicationContext());
				CommentListModel favReplies = ProjectApplication.getInstance().getUser().getFavoriteReplies(getApplicationContext(), position);
				ProjectApplication.getInstance().getUser().setFavoritesToView(favReplies);
				Intent goToReplyListActivity = new Intent(getApplicationContext(), BrowseFavoritesActivity.class);
				startActivity(goToReplyListActivity);
			}
		});

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
		
		// Retrieve favorites from phone storage -TH
		//favoritesList = ProjectApplication.getInstance().getUser().getFavorites(getApplicationContext());
		favoritesList = ProjectApplication.getInstance().getUser().getFavoritesToView();

		// Add comments to adapter
		adapter = new FavoritesAdapter(this, R.layout.list_item, favoritesList.getCommentList());
		adapter.setModel(favoritesList);
		
		// Display comments in adapter
		favoritesListView.setAdapter(adapter);
		
		return adapter;
	}

}
