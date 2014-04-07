package ca.ualberta.cs.cmput301t02project.controller;

import android.content.Context;
import android.graphics.Bitmap;
import android.location.Location;
import ca.ualberta.cs.cmput301t02project.model.CommentModel;
import ca.ualberta.cs.cmput301t02project.model.TopLevelCommentList;
import ca.ualberta.cs.cmput301t02project.model.User;

/**
 * This class provides a controller for the Top Level Comment List.
 * Top Level Comments can be added only through this class.
 * Used by the CreateCommentActivity when a new top level comment is created. 
 * Includes a method for storing a new top level comment.
 */
public class TopLevelListController {
	
	private TopLevelCommentList list;
	private Context context;
	
	/**
	 * Constructor.
	 * @param context A context is needed to deal with the server
	 */
	public TopLevelListController(Context context) {
		list = TopLevelCommentList.getInstance(context);
		this.context = context;
	}

	/**
	 * Adds a new Top Level Comment.
	 * <p>
	 * Called from CreateCommentActivity when a new comment is created. 
	 * The comment is stored on the server and in the User class.
	 * The Bitmap image is scaled before it is stored. 
	 * <p>
	 * @param text The text of the comment
	 * @param picture The picture of the comment
	 * @param location The location of the comment
	 * @param user The author of the comment
	 */
	public void add(String text, Bitmap picture, Location location, User user) {
		
		// scale the picture -SB
		CommentController.scalePicture(picture);
		
		// create a new top level comment -SB
		CommentModel comment = new CommentModel(text, picture, location, user.getName());
		comment.setTopLevelComment(true);
		
		// add the comment to the complete list of top level comments -SB
		list.add(comment);
		
		// add comment to the current user's my comments list -SB
		user.addMyComment(comment, context);
	}
}
