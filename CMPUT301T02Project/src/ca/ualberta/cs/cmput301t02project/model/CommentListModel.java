package ca.ualberta.cs.cmput301t02project.model;

import java.util.ArrayList;

import ca.ualberta.cs.cmput301t02project.view.SView;

/**
 * Interface for classes that act like a CommentListModel including:
 * FavoritesListModel, 
 * MyCommentsListModel, 
 * RepliesToFavesListModel, 
 * TopLevelCommentListModel, 
 * and FollowedUserCommentsListModel.
 * Contains a method for adding a CommentModel to a CommentListModel
 * and retrieving a list of CommentModels stored in the CommentListModel
 */
public abstract class CommentListModel extends SModel<SView<CommentListModel>> {

	/**
	 * Adds a CommentModel to the CommentListModel.
	 * @param comment	the comment to add	
	 */
	public abstract void add(CommentModel comment);
	
	/**
	 * Retrieves the ArrayList of CommentModels stored in CommentListModel.
	 * @return	the list of CommentModels contained in the CommentListModel
	 */
	public abstract ArrayList<CommentModel> getList();
	
	public abstract void refresh();
	
}