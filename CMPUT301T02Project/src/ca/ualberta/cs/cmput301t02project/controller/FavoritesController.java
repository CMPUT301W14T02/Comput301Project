package ca.ualberta.cs.cmput301t02project.controller;

import ca.ualberta.cs.cmput301t02project.model.CommentListModel;
import ca.ualberta.cs.cmput301t02project.model.CommentModel;

/**
 * For updating CommentListModels of favorite comments.
 */
public class FavoritesController extends CommentListControllerAbstraction {

	public FavoritesController(CommentListModel model) {
		this.model = model;
	}

	/**
	 * Add comment to a list of favorites.
	 * <p>
	 * Adds a specified comment to the CommentListModel.
	 * Used when a comment is favorited in 
	 * BrowseReplyCommentsActivity.
	 * <p>
	 * @param comment	CommentModel to add to favorites list
	 */
	public void favoriteComment(CommentModel comment) {

		model.add(comment);
	}

}
