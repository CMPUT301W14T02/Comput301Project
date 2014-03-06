package ca.ualberta.cs.cmput301t02project;

import android.graphics.Bitmap;

public class CommentListController {

    private CommentListModel model;
    
    public CommentListController(CommentListModel model) {
	this.model = model;
    }
    
    public void addNewComment(String text, String username, LocationModel location, Bitmap picture) {
	model.addNewComment(text, username, location, picture);
    }
}
