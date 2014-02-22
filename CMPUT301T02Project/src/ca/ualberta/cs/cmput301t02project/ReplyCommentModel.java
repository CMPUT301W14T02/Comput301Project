package ca.ualberta.cs.cmput301t02project;

import android.graphics.Picture;

public class ReplyCommentModel extends CommentModelAbstraction {

    public ReplyCommentModel(String text, Picture image, LocationModel location,
	    String username) {
	super(text, image, location, username);
    }
    
    public ReplyCommentModel(String text,
	    LocationModel location, String username) {
	super(text, location, username);
    }
}
