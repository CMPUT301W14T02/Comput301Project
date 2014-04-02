package ca.ualberta.cs.cmput301t02project.activity;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import ca.ualberta.cs.cmput301t02project.R;
import ca.ualberta.cs.cmput301t02project.controller.CommentController;
import ca.ualberta.cs.cmput301t02project.model.CommentModel;
import ca.ualberta.cs.cmput301t02project.model.ReplyList;
import ca.ualberta.cs.cmput301t02project.model.User;
import ca.ualberta.cs.cmput301t02project.view.CommentListAdapter;
import ca.ualberta.cs.cmput301t02project.view.CommentListAdapterAbstraction;

/**
 * Displays comments replies to the current selected comment.
 * Current comment information including the current comment and a list of its replies is stored in ProjectApplication.getInstance().
 */
public class BrowseReplyCommentsActivity extends BrowseCommentsActivityAbstraction {

	private ReplyList model;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reply_list);
		listView = (ListView) findViewById(R.id.replyListView);
		TextView selectedComment = (TextView) findViewById(R.id.selected_comment);
		final String currentCommentId = getIntent().getStringExtra("CommentId");
		final CommentController commentController = new CommentController(currentCommentId, this);
		final CommentModel currentComment = commentController.getComment();
		selectedComment.setText(currentComment.getText());
		model = new ReplyList(currentCommentId, this);
		setupPage();
		model.addObserver(adapter);
		
		Button replyComment = (Button) findViewById(R.id.reply_button);
		replyComment.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(BrowseReplyCommentsActivity.this, CreateCommentActivity.class);
				intent.putExtra("CommentId", currentCommentId);
				startActivity(intent);
			}
		});
		
		Button favoriteComment = (Button) findViewById(R.id.fav_button);
		favoriteComment.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				User user = User.getUser();
				ReplyList repliesToFav = new ReplyList(currentComment.getId(), getApplicationContext());
				ArrayList<CommentModel> replies = repliesToFav.getList();
				user.addFavoriteComment(currentComment, replies);
				commentController.incrementRating();
			}
		});

		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> l, View v, int position, long id) {
				CommentModel nestedComment = (CommentModel) adapter.getItem(position);
				Intent goToReplyListActivity = new Intent(getApplicationContext(), BrowseReplyCommentsActivity.class);
				goToReplyListActivity.putExtra("CommentId", nestedComment.getId());
				startActivity(goToReplyListActivity);
			}
		});
	}
	
	@Override
	public void onResume() {
		super.onResume();
		adapter.notifyDataSetChanged();
	}
	
	@Override
	public CommentListAdapterAbstraction initializeAdapter() {
		this.adapter = new CommentListAdapter(this, R.layout.list_item, model);
		return adapter;
	}
	
}
