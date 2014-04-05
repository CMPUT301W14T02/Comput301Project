package ca.ualberta.cs.cmput301t02project.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ListView;
import ca.ualberta.cs.cmput301t02project.R;
import ca.ualberta.cs.cmput301t02project.controller.CommentController;
import ca.ualberta.cs.cmput301t02project.model.CommentModel;
import ca.ualberta.cs.cmput301t02project.model.RepliesToFavsListModel;
import ca.ualberta.cs.cmput301t02project.model.User;
import ca.ualberta.cs.cmput301t02project.view.CommentListAdapter;
import ca.ualberta.cs.cmput301t02project.view.CommentListAdapter;

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
		
		model.setParent(currentComment);

		setupPage();

	}

	@Override
	public void onResume() {
		super.onResume();
		model.refresh();
		adapter.notifyDataSetChanged();
	}
	
	public CommentListAdapter initializeAdapter(){
		this.adapter = new CommentListAdapter(this, R.layout.list_item, model);
		return adapter;
	}
	

	@Override
	public void goToHelpPage(){
		// go to help page for browsing favorites
		Intent viewIntent = new Intent("android.intent.action.VIEW",Uri.parse(
				"https://rawgithub.com/CMPUT301W14T02/Comput301Project/master/Help%20Pages/browse_favorite_comments.html"));
		startActivity(viewIntent);
	}
}
