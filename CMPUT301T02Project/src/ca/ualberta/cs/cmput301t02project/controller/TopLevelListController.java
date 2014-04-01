package ca.ualberta.cs.cmput301t02project.controller;

import android.content.Context;
import android.graphics.Bitmap;
import android.location.Location;
import ca.ualberta.cs.cmput301t02project.model.CommentModel;
import ca.ualberta.cs.cmput301t02project.model.TopLevelCommentList;
import ca.ualberta.cs.cmput301t02project.model.User;

public class TopLevelListController {
	
	private TopLevelCommentList list;
	
	public TopLevelListController(Context context) {
		list = TopLevelCommentList.getInstance(context);
	}

	public void add(String text, Bitmap picture, Location location, User user) {
		CommentModel comment = new CommentModel(text, picture, location, user.getName());
		comment.setTopLevelComment(true);
		list.add(comment);
		user.addMyComment(comment);
	}
}
