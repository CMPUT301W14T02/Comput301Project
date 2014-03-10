package ca.ualberta.cs.cmput301t02project.controller;

import ca.ualberta.cs.cmput301t02project.model.CommentListModel;
import ca.ualberta.cs.cmput301t02project.model.LocationModel;
import android.graphics.Bitmap;

public class CommentListController extends CommentListControllerAbstraction {

	public CommentListController(CommentListModel model) {
		this.model = model;
	}

	public void addNewComment(String text, String username,
			LocationModel location, Bitmap picture) {
		model.addNewComment(text, username, location, picture);
	}
}
