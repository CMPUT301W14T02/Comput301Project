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

	public CommentModel addNewComment(String text, Bitmap picture,
			Location location, String username) {
		CommentModel comment;
		String loc = "Initialize location";
		Location currentLocation = new Location(loc);
		currentLocation = ProjectApplication.getCurrentLocation();
		double lon = currentLocation.getLongitude();
		double lat = currentLocation.getLatitude();
		String test =  String.valueOf(lat) + String.valueOf(lon);
		comment = new CommentModel(test+text, picture, location, username);
		model.add(comment);
		return comment;
	}
}
