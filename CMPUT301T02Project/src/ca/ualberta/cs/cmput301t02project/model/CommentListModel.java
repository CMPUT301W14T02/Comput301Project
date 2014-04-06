package ca.ualberta.cs.cmput301t02project.model;

import java.util.ArrayList;

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
public interface CommentListModel {

	/**
	 * Adds a CommentModel to the CommentListModel.
	 * @param comment	the comment to add	
	 */
	public void add(CommentModel comment);
	
	/**
	 * Retrieves the ArrayList of CommentModels stored in CommentListModel.
	 * @return	the list of CommentModels contained in the CommentListModel
	 */
	public ArrayList<CommentModel> getList();

}