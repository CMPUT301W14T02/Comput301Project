package ca.ualberta.cs.cmput301t02project.controller;

import android.location.Location;
import ca.ualberta.cs.cmput301t02project.model.CommentListModel;
import ca.ualberta.cs.cmput301t02project.model.CommentModel;


public class MyCommentsController extends CommentListControllerAbstraction {

	public MyCommentsController(CommentListModel model) {
		this.model = model;
	}

	public void addNewComment(CommentModel comment) {
		
		model.add(comment);
	}
	
	public void changeText(CommentModel model, String text) {
		model.setText(text);
	}
	
	public void changeLocation(CommentModel model, Location location) {
		model.setLocation(location);
	}
}
