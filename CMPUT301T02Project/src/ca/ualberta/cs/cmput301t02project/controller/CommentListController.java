package ca.ualberta.cs.cmput301t02project.controller;

import android.graphics.Bitmap;
import android.location.Location;
import ca.ualberta.cs.cmput301t02project.ProjectApplication;
import ca.ualberta.cs.cmput301t02project.model.CommentListModel;
import ca.ualberta.cs.cmput301t02project.model.CommentModel;

/**
 * For updating CommentListModels.
 */
public class CommentListController extends CommentListControllerAbstraction {

	public CommentListController(CommentListModel model) {
		this.model = model;
	}

	/**
	 * Adds a new CommentModel to a CommentListModel.
	 * <p>
	 * Creates a new CommentModel using its parameters.
	 * <p>
	 * @param text	The comment text (Eg. "This is a comment")
	 * @param picture	A photo to attach to the comment (in bitmap form)
	 * @param username	The username of the commenter (Eg. "Bob")
	 * @return	The newly created CommentModel that has been added to the CommentListModel
	 */
	public CommentModel addNewComment(String text, Bitmap picture, String username, Location customLocation) {
		
		CommentModel comment;
		Location commentLocation;
		
		if (customLocation != null) {
			commentLocation = customLocation;
		} else { 
			commentLocation = ProjectApplication.getInstance().getCurrentLocation();
		}
		
		comment = new CommentModel(text, picture, commentLocation, username);
		model.add(comment);
		return comment;
	}
}
