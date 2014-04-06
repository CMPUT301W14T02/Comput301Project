package ca.ualberta.cs.cmput301t02project.activity;

import java.util.ArrayList;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import ca.ualberta.cs.cmput301t02project.R;
import ca.ualberta.cs.cmput301t02project.controller.CommentController;
import ca.ualberta.cs.cmput301t02project.model.CommentModel;
import ca.ualberta.cs.cmput301t02project.model.ReplyList;
import ca.ualberta.cs.cmput301t02project.model.Server;
import ca.ualberta.cs.cmput301t02project.model.User;
import ca.ualberta.cs.cmput301t02project.view.CommentListAdapter;

/**
 * Displays the current comment,
 * its replies, 
 * a photo if one is attached to the comment, 
 * and buttons for creating a reply to the comment, favoriting the comment, or following the author.
 * Called when the user selects a comment from BrowseTopLevelCommentsActivity or BrowseReplyCommentsActivity.
 * When the user selects a comment they are able to view its replies. 
 * Additionally, the action bar contains an option for editing the current comment if its author is the current User.
 * Current comment information including the current comment and a list of its replies is stored in User.
 */
public class BrowseReplyCommentsActivity extends BrowseCommentsActivityAbstraction {

	private ReplyList model;
	
	/* the name of the author of the selected comment is used by the menu 
	 * to check if it should create a "edit comment" item -SB
	 */
	private String currentCommentAuthor = "";
	
	// the comment to be edited when "edit comment" menu option is selected -SB
	private CommentModel editComment = null;
	
	// for the edit comment menu item when it is created dynamically -SB
	private final int MENU_EDIT = Menu.FIRST;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// set up the screen display -SB
		setContentView(R.layout.activity_reply_list);
		listView = (ListView) findViewById(R.id.replyListView);
		TextView selectedComment = (TextView) findViewById(R.id.selected_comment);
		
		// retrieve the id of the parent and set the currentComment -SB
		final String currentCommentId = getIntent().getStringExtra("CommentId");
		CommentController commentController = new CommentController(currentCommentId, this);
		CommentModel currentComment = commentController.getComment();
		
		// get the current comment author and set the comment to edit -SB
		currentCommentAuthor = currentComment.getUsername();
		editComment = currentComment;
		
		// display the currently selected comment and its author on the screen -SB
		selectedComment.setText(currentComment.getText()+"\n(by " + currentComment.getUsername() + ")");
		
		// display the comment's picture if there is one -SB
		if(currentComment.hasPicture()){
			ImageView image = (ImageView) findViewById(R.id.comment_picture);
			image.setImageBitmap(currentComment.getPicture());
		}
		
		model = new ReplyList(currentCommentId, this);
		
		// create the sortBy menu and set up the adapter, inherited from BrowseCommentsActivity -SB
		setupPage();
		
		model.addObserver(adapter);
		
		// actions to take when the "Reply" button is pressed -SB
		Button replyComment = (Button) findViewById(R.id.reply_button);
		replyComment.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				// try to retrieve comments saved on the server -SB
				Server server = new Server(BrowseReplyCommentsActivity.this);
				if(!server.isNetworkAvailable()) {
					
					// print error message to the screen -SB
					showMessage(BrowseReplyCommentsActivity.this, "You don't have internet connection.");
				}
				else {
					
					// go to the CreateCommentActivity to allow the user to create a reply to the current comment -SB
					Intent intent = new Intent(BrowseReplyCommentsActivity.this, CreateCommentActivity.class);
					intent.putExtra("CommentId", currentCommentId);
					startActivity(intent);
				}
			}
		});
		
		// actions to take when the "Add to Favorites" button is pressed -SB
		final Button favoriteComment = (Button) findViewById(R.id.fav_button);
		favoriteComment.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				// retrieve the current comment -SB
				CommentController commentController = new CommentController(currentCommentId, BrowseReplyCommentsActivity.this);
				CommentModel currentComment = commentController.getComment();
				
				// retrieve the current user -SB
				User user = User.getUser();
				
				// retrieve the replies to the current comment -SB
				ReplyList repliesToFav = new ReplyList(currentComment.getId(), getApplicationContext());
				ArrayList<CommentModel> replies = repliesToFav.getList();
				
				// add the current comment and its replies to the current user's favorites -SB
				user.addFavoriteComment(currentComment, replies);
				
				// increase the rating of the current comment -SB
				commentController.incrementRating();
				
				// print a popup message -SB
				favoriteComment.animate();
				showMessage(BrowseReplyCommentsActivity.this, "Added to favorites!");
			}
		});
		
		// actions to take when the "Follow" button is pressed -SB
		final Button followUser = (Button) findViewById(R.id.follow_button);
		followUser.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				// retrieve the current comment -SB
				CommentController commentController = new CommentController(currentCommentId, BrowseReplyCommentsActivity.this);
				CommentModel currentComment = commentController.getComment();
				
				// retrieve the current user -SB
				User user = User.getUser();
				
				// add the current comment to the current user's followedUser list (which is converted to username in User) -SB
				user.addFollowedUser(currentComment);
				
				// print a popup message -SB
				followUser.animate();
				showMessage(BrowseReplyCommentsActivity.this, "You are now following " + currentComment.getUsername());
			}
		});
		
		// actions to take when a reply comment is selected -SB
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> l, View v, int position, long id) {
				
				// get the selected comment -SB
				CommentModel nestedComment = (CommentModel) adapter.getItem(position);
				
				// go to BrowseReplyCommentsActivity to see replies of the selected comment -SB
				Intent goToReplyListActivity = new Intent(getApplicationContext(), BrowseReplyCommentsActivity.class);
				goToReplyListActivity.putExtra("CommentId", nestedComment.getId());
				startActivity(goToReplyListActivity);
			}
		});
	}

	@Override
	public void onResume() {
		super.onResume();
		
		// update the view if the model has changed -SB
		adapter.notifyDataSetChanged();
	}
	
	@Override
	public CommentListAdapter initializeAdapter() {
		
		// return the adapter for this class -SB
		this.adapter = new CommentListAdapter(this, R.layout.list_item, model);
		return adapter;
	}
	
	
	// override to select a different menu xml than the default to facilitate edit comment feature -SB
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
		
		// go to help page for replying to comments -SB
		Intent viewIntent = new Intent("android.intent.action.VIEW",Uri.parse(
				"https://rawgithub.com/CMPUT301W14T02/Comput301Project/master/Help%20Pages/browse_reply_comments.html"));
		startActivity(viewIntent);
	}
}
