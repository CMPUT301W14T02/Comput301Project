package ca.ualberta.cs.cmput301t02project.controller;

import android.graphics.Bitmap;
import android.location.Location;
import ca.ualberta.cs.cmput301t02project.ProjectApplication;
import ca.ualberta.cs.cmput301t02project.model.CommentListModel;
import ca.ualberta.cs.cmput301t02project.model.CommentModel;
import ca.ualberta.cs.cmput301t02project.model.LocationModel;

public class CommentListController extends CommentListControllerAbstraction {

	public CommentListController(CommentListModel model) {
		this.model = model;
	}

	public void addNewComment(String text, Bitmap picture,
			LocationModel location, String username) {
		
		String loc = "Initialize location";
		Location currentLocation = new Location(loc);
		currentLocation = ProjectApplication.getCurrentLocation();
		double lon = currentLocation.getLongitude();
		double lat = currentLocation.getLatitude();
		String test =  String.valueOf(lat) + String.valueOf(lon);
		
		model.add(new CommentModel(text, picture, location, username));
	}
}
