package ca.ualberta.cs.cmput301t02project.controller;

import android.graphics.Bitmap;
import ca.ualberta.cs.cmput301t02project.model.CommentListModel;
import ca.ualberta.cs.cmput301t02project.model.CommentModel;
import ca.ualberta.cs.cmput301t02project.model.LocationModel;

public class CommentListController extends CommentListControllerAbstraction {

	public CommentListController(CommentListModel model) {
		this.model = model;
	}

	public void addNewComment(String text, Bitmap picture,
			LocationModel location, String username) {
		model.add(new CommentModel(text, picture, location, username));
	}
}
