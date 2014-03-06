package controller;

import model.CommentListModel;
import model.LocationModel;
import android.graphics.Bitmap;

public class CommentListController extends CommentListControllerAbstraction {

    public CommentListController(CommentListModel model) {
	this.model = model;
    }
    
    public void addNewComment(String text, String username, LocationModel location, Bitmap picture) {
	model.addNewComment(text, username, location, picture);
    }
}
