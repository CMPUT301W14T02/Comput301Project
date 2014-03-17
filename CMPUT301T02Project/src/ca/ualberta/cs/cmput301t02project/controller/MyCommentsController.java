package ca.ualberta.cs.cmput301t02project.controller;

import android.location.Location;
import ca.ualberta.cs.cmput301t02project.model.CommentListModel;
import ca.ualberta.cs.cmput301t02project.model.CommentModel;

/**
 * For updating a CommentListModel of the current user's comments.
 */
public class MyCommentsController extends CommentListControllerAbstraction {
	
	public MyCommentsController(CommentListModel model) {
		this.model = model;
	}

	/**
	 * Adds a comment to a list of the current user's comments.
	 * <p>
	 * Adds a newly created comment (specified in the parameter) to the CommentListModel.
	 * Used when a comment is created by a user in 
	 * CreateCommentActivity.
	 * <p>
	 * @param comment	CommentModel to add to myComments list
	 */
	public void addNewComment(CommentModel comment) {
		model.add(comment);
	}
	
	/**
	 * Change the text of a comment.
	 * <p>
	 * Changes the text portion of a specified CommentModel.
	 * New text to replace the old text is specified in the text parameter.
	 * Used in EditCommentActivity once a user submits their changes.
	 * Example of changing text: 
	 * old CommentModel text = "this is a comment"
	 * new CommentModel text = "this is an edited comment".
	 * <p>
	 * @param model	CommentModel to change the text of
	 * @param text	The text to update in the CommentModel
	 */
	public void changeText(CommentModel model, String text) {
		model.setText(text);
	}
	
	/**
	 * Change the Location of a comment.
	 * <p>
	 * Changes the location portion of a specified CommentModel.
	 * New location to replace the old location is specified in the text parameter.
	 * Used in EditCommentActivity once a user submits their changes.
	 * <p>
	 * @param model	CommentModel to change the Location of
	 * @param location	The Location to update in the CommentModel
	 */
	public void changeLocation(CommentModel model, Location location) {
		model.setLocation(location);
	}
}
