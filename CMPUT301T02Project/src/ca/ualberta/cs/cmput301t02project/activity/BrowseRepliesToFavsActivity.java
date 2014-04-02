package ca.ualberta.cs.cmput301t02project.activity;

import android.os.Bundle;
import android.widget.ListView;
import ca.ualberta.cs.cmput301t02project.R;
import ca.ualberta.cs.cmput301t02project.controller.CommentController;
import ca.ualberta.cs.cmput301t02project.model.CommentModel;
import ca.ualberta.cs.cmput301t02project.model.RepliesToFavsListModel;
import ca.ualberta.cs.cmput301t02project.model.User;
import ca.ualberta.cs.cmput301t02project.view.CommentListAdapterAbstraction;
import ca.ualberta.cs.cmput301t02project.view.RepliesToFavsAdapter;

public class BrowseRepliesToFavsActivity extends BrowseCommentsActivityAbstraction{

	private RepliesToFavsListModel model;
	private CommentModel currentComment;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_favorites_list);
		listView = (ListView) findViewById(R.id.commentListView);
		model = User.getUser().getRepliesToFavs();
		final String currentCommentId = getIntent().getStringExtra("CommentId");
		final CommentController commentController = new CommentController(currentCommentId, this);
		currentComment = commentController.getComment();

		setupPage();
		
		/*
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> l, View v, int position, long id) {
				CommentModel nestedComment = (CommentModel) adapter.getItem(position);
				Intent goToReplyListActivity = new Intent(BrowseFavoritesActivity.this, BrowseReplyCommentsActivity.class);
				goToReplyListActivity.putExtra("CommentId", nestedComment.getId());
				startActivity(goToReplyListActivity);
			}
		});
		*/

	}

	@Override
	public void onResume() {
		super.onResume();
		model.refresh();
		adapter.notifyDataSetChanged();
	}
	
	public CommentListAdapterAbstraction initializeAdapter(){
		this.adapter = new RepliesToFavsAdapter(this, R.layout.list_item, model, currentComment);
		return adapter;
	}

}