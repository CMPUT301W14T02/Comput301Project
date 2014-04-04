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
 */
public class TopLevelListController {
	
	private TopLevelCommentList list;
	private Context context;
	
	/**
	 * Constructor
	 * @param context A context is needed to deal with the server
	 */
	public TopLevelListController(Context context) {
		list = TopLevelCommentList.getInstance(context);
		this.context = context;
	}

	/**
	 * Adds a new Top Level Comment
	 * @param text The text of the comment
	 * @param picture The picture of the comment
	 * @param location The location of the comment
	 * @param user The author of the comment
	 */
	public void add(String text, Bitmap picture, Location location, User user) {
		CommentController.scalePicture(picture);
		CommentModel comment = new CommentModel(text, picture, location, user.getName());
		comment.setTopLevelComment(true);
		list.add(comment);
		user.addMyComment(comment, context);
	}
}
