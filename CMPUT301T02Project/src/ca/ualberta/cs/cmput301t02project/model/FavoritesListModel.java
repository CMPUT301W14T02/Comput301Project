package ca.ualberta.cs.cmput301t02project.model;

import java.util.ArrayList;

import android.content.Context;

/**
 * Contains the favorited comments of a User.
 * Includes a method for storing favorites and their replies to the phone storage.
 */
public class FavoritesListModel extends StoredCommentListAbstraction {

	private static FavoritesListModel instance;
	
	public FavoritesListModel(Context context) {
		super(context);
	}
	
	public static FavoritesListModel getInstance(Context context) {
		if(instance == null) {
			instance = new FavoritesListModel(context);
		}
		return instance;
	}

	@Override
	protected String getPreferencesKey() {
		return "FAVORITES_LIST_KEY" + User.getUser().getName();
	}

	/**
	 * Stores comment on phone storage. 
	 * <p> 
	 * Stores comment favorited by user to phone by calling  
	 * storeFavorite method in StorageModel class BrowseRepliesActivity.
	 * The replies are contained in an ArrayList of CommentModels.
	 * <p>
	 * @param comment		comment to be stored
	 * @param repliesToFavs	replies to the comment to be stored
	 */
	public void addFavoriteComment(CommentModel comment, ArrayList<CommentModel> replies, RepliesToFavsListModel repliesToFavs) {
		add(comment);
		if (!replies.isEmpty()) {
			repliesToFavs.addAll(replies);
		}
	}
}
