package ca.ualberta.cs.cmput301t02project.controller;

import android.graphics.Bitmap;
import android.location.Location;
import ca.ualberta.cs.cmput301t02project.ProjectApplication;
import ca.ualberta.cs.cmput301t02project.model.CommentListModel;
import ca.ualberta.cs.cmput301t02project.model.CommentModel;


public class CommentListController extends CommentListControllerAbstraction {

	public CommentListController(CommentListModel model) {
		this.model = model;
	}

	public CommentModel addNewComment(String text, Bitmap picture, String username) {
		CommentModel comment;
		Location currentLocation = ProjectApplication.getCurrentLocation();
		comment = new CommentModel(text, picture, currentLocation, username);
		model.add(comment);
		return comment;
	}
}
