package ca.ualberta.cs.cmput301t02project.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import ca.ualberta.cs.cmput301t02project.R;
import ca.ualberta.cs.cmput301t02project.model.CommentModel;
import ca.ualberta.cs.cmput301t02project.model.FavoritesListModel;
import ca.ualberta.cs.cmput301t02project.model.User;
import ca.ualberta.cs.cmput301t02project.view.CommentListAdapter;
import ca.ualberta.cs.cmput301t02project.view.CommentListAdapterAbstraction;
import ca.ualberta.cs.cmput301t02project.view.FavoritesAdapter;

/**
 * Displays the favorited comments of the current user.
 * Current user information including their favorites list is stored in ProjectApplication.
 */
public class BrowseFavoritesActivity extends BrowseCommentsActivityAbstraction {

	private FavoritesAdapter adapter;
	private FavoritesListModel model;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_favorites_list);
		model = User.getUser().getFavorites();

		setupPage();
		
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> l, View v, int position, long id) {
				CommentModel nestedComment = (CommentModel) adapter.getItem(position);
				Intent goToReplyListActivity = new Intent(getApplicationContext(), BrowseFavoritesActivity.class);
				goToReplyListActivity.putExtra("CommentId", nestedComment.getId());
				startActivity(goToReplyListActivity);
			}
		});

	}

	@Override
	public void onResume() {
		super.onResume();
	}
	
	public CommentListAdapterAbstraction initializeAdapter(){
		CommentListAdapterAbstraction adapter = new CommentListAdapter(this, R.layout.list_item, model);
		return adapter;
	}

}
