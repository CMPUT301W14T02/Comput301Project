package ca.ualberta.cs.cmput301t02project.activity;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import ca.ualberta.cs.cmput301t02project.R;
import ca.ualberta.cs.cmput301t02project.model.CommentModel;
import ca.ualberta.cs.cmput301t02project.model.User;
import ca.ualberta.cs.cmput301t02project.view.CommentListAdapterAbstraction;
import ca.ualberta.cs.cmput301t02project.view.FavoritesAdapter;

/**
 * Displays the favorited comments of the current user.
 * Current user information including their favorites list is stored in ProjectApplication.
 */
public class BrowseFavoritesActivity extends BrowseCommentsActivityAbstraction {

	private ArrayList<CommentModel> favoritesList;
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
				// Set the list of favorites the next activity should display -TH
				CommentModel nestedComment = (CommentModel) adapter.getItem(position);
				//CommentListModel favReplies = ProjectApplication.getInstance().getUser().getFavoriteReplies(getApplicationContext(), nestedComment);
				//ProjectApplication.getInstance().getUser().setFavoritesToView(favReplies);
				
				Intent goToReplyListActivity = new Intent(getApplicationContext(), BrowseFavoritesActivity.class);
				startActivity(goToReplyListActivity);
			}
		});

	}

	@Override
	public void onResume() {
		super.onResume();
		//Set to correct list on back button -TH
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
		
		// Retrieve favorites to view (either favorites or a favorited comments replies -TH
		favoritesList = User.getUser().getFavoritesToView().getCommentList();

		// Add comments to adapter
		adapter = new FavoritesAdapter(this, R.layout.list_item, favoritesList);
		adapter.setModel(favoritesList);
		
		// Display comments in adapter
		favoritesListView.setAdapter(adapter);
		
		return adapter;
	}

}
