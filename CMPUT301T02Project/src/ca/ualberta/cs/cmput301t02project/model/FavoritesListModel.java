package ca.ualberta.cs.cmput301t02project.model;

import java.util.ArrayList;

import android.content.Context;

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
	 * Stores comment on phone storage <p> Stores comment favorited by user to phone by calling  storeFavorite method in StorageModel class BrowseRepliesActivity <p>
	 * @param comment  - comment to be stored
	 * @param repliesToFavs
	 */
	public void addFavoriteComment(CommentModel comment, ArrayList<CommentModel> replies, RepliesToFavsListModel repliesToFavs) {
		add(comment);
		if (!replies.isEmpty()) {
			repliesToFavs.addAll(replies);
		}
	}
	
}
