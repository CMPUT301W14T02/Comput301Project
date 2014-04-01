package ca.ualberta.cs.cmput301t02project.controller;

import android.content.Context;
import android.graphics.Bitmap;
import android.location.Location;
import ca.ualberta.cs.cmput301t02project.model.CommentModel;
import ca.ualberta.cs.cmput301t02project.model.TopLevelCommentList;

public class TopLevelListController {
	
	private TopLevelCommentList list;
	
	public TopLevelListController(Context context) {
		list = TopLevelCommentList.getInstance(context);
	}

	public void add(String text, Bitmap picture, Location location, String username) {
		CommentModel comment = new CommentModel(text, picture, location, username);
		comment.setTopLevelComment(true);
		list.add(comment);
	}
}
