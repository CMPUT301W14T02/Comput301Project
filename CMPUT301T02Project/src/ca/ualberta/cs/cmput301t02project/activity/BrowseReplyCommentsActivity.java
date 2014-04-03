package ca.ualberta.cs.cmput301t02project.activity;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import ca.ualberta.cs.cmput301t02project.R;
import ca.ualberta.cs.cmput301t02project.controller.CommentController;
import ca.ualberta.cs.cmput301t02project.model.CommentModel;
import ca.ualberta.cs.cmput301t02project.model.ReplyList;
import ca.ualberta.cs.cmput301t02project.model.Server;
import ca.ualberta.cs.cmput301t02project.model.User;
import ca.ualberta.cs.cmput301t02project.view.CommentListAdapter;
import ca.ualberta.cs.cmput301t02project.view.CommentListAdapterAbstraction;

/**
 * Displays comments replies to the current selected comment.
 * Current comment information including the current comment and a list of its replies is stored in User.
 */
public class BrowseReplyCommentsActivity extends BrowseCommentsActivityAbstraction {

	private ReplyList model;
	// the name of the author of the selected comment is used by the menu 
	// to check if it should create a "edit comment" item -SB
	private String currentCommentAuthor = "";
	
	// the comment to be edited when "edit comment" menu option is selected -SB
	private CommentModel editComment = null;
	
	// for the edit comment menu item when it is created dynamically -SB
	private final int MENU_EDIT = Menu.FIRST;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reply_list);
		
		listView = (ListView) findViewById(R.id.replyListView);
		TextView selectedComment = (TextView) findViewById(R.id.selected_comment);
		
		final String currentCommentId = getIntent().getStringExtra("CommentId");
		CommentController commentController = new CommentController(currentCommentId, this);
		CommentModel currentComment = commentController.getComment();
		currentCommentAuthor = currentComment.getUsername();
		editComment = currentComment;
		
		selectedComment.setText(currentComment.getText());
		
		model = new ReplyList(currentCommentId, this);
		setupPage();
		model.addObserver(adapter);
		
		Button replyComment = (Button) findViewById(R.id.reply_button);
		replyComment.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Server server = new Server(BrowseReplyCommentsActivity.this);
				if(!server.isNetworkAvailable()) {
					int duration = Toast.LENGTH_SHORT;
					Toast toast = Toast.makeText(BrowseReplyCommentsActivity.this, "You don't have internet connection.", duration);
					toast.show();
				}
				else {
					Intent intent = new Intent(BrowseReplyCommentsActivity.this, CreateCommentActivity.class);
					intent.putExtra("CommentId", currentCommentId);
					startActivity(intent);
				}
			}
		});
		
		final Button favoriteComment = (Button) findViewById(R.id.fav_button);
		favoriteComment.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				CommentController commentController = new CommentController(currentCommentId, BrowseReplyCommentsActivity.this);
				CommentModel currentComment = commentController.getComment();
				User user = User.getUser();
				ReplyList repliesToFav = new ReplyList(currentComment.getId(), getApplicationContext());
				ArrayList<CommentModel> replies = repliesToFav.getList();
				user.addFavoriteComment(currentComment, replies);
				commentController.incrementRating();
				int duration = Toast.LENGTH_SHORT;
				favoriteComment.animate();
				Toast toast = Toast.makeText(BrowseReplyCommentsActivity.this, "Added to favorites!", duration);
				toast.show();
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
	
	
	// override to select a different menu xml than the default -SB
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		// do all the default menu setup tasks -SB
		super.onCreateOptionsMenu(menu);
		
		// the current user who is logged in -SB
		String currentUser = User.getUser().getName();
		
		// if the current user is also the comment author, show "edit comment" as a menu option -SB
		if(currentUser.equals(currentCommentAuthor)){
			menu.add(0, MENU_EDIT, Menu.NONE, R.string.edit_menu_item);
		}
		return true;
	}
	
	// override to deal with new edit menu item
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		super.onOptionsItemSelected(item);
		
		 switch (item.getItemId()) {
	    	case MENU_EDIT:
	    		editComment();
	    		return true;
	        default:
	            return super.onOptionsItemSelected(item);
		 }
	}
	
	/**
	 * Allows the user to edit a selected comment.
	 * <p>
	 * Redirects to the EditCommentActivity when it is called if editComment is set in onCreate.
	 * Called when the "edit comment" menu item is selected.
	 * <p>
	 */
	private void editComment(){
		
		// make sure editComment was set in onCreate -SB
		if(editComment != null){
			
			// go to the edit comment activity & send comment to edit -SB
			Intent goToEditCommentActivity = new Intent(getApplicationContext(), EditCommentActivity.class);
			goToEditCommentActivity.putExtra("CommentId", editComment.getId());
			startActivity(goToEditCommentActivity);
		}
	}

	@Override
	public void goToHelpPage(){
		// go to help page for replying to comments
	}
}
