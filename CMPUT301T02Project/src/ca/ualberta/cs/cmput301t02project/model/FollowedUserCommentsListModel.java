package ca.ualberta.cs.cmput301t02project.model;

import java.util.ArrayList;

import android.content.Context;

/**
 * Contains comments whose authors were selected to be followed by a User. 
 * Includes a method for updating the comments to reflect any changes.
 */
public class FollowedUserCommentsListModel extends StoredCommentListAbstraction{

	private static FollowedUserCommentsListModel instance;
	
	public FollowedUserCommentsListModel(Context context) {
		super(context);
	}
	
	public static FollowedUserCommentsListModel getInstance(Context context) {
		if(instance == null) {
			instance = new FollowedUserCommentsListModel(context);
		}
		return instance;
	}

	@Override
	protected String getPreferencesKey() {
		return "FOLLOWED_LIST_KEY" + User.getUser().getName();
	}
	
	/**
	 * Retrieves an updated list of comments written by specified usernames.
	 * <p>
	 * Pulls all the comments from the users in case new comments were composed by them. 
	 * Returns a new list of followed user's comments.
	 * <p>
	 * @param names	the author's whose comments to pull from the server
	 * @return		an updated FollowedUserCommentsListModel containing any new comments by followed authors
	 */
	public FollowedUserCommentsListModel updated(ArrayList<String> names){
		
		Server server = new Server(context);
		FollowedUserCommentsListModel list = new FollowedUserCommentsListModel(context); 
		
		// pull comments from each followed user and add them to the list -SB
		for(int i = 0; i < names.size(); i++){
			ArrayList<CommentModel> serverList = server.pullFollowedUserComments(names.get(i));
			list.addAll(serverList);
		}
		notifyViews();
		
		return list;
	}
}
