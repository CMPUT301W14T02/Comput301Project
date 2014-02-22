package ca.ualberta.cs.cmput301t02project;

import android.graphics.Picture;

public class TopLevelCommentModel extends CommentModelAbstraction {

    public TopLevelCommentModel(String text, Picture image,
	    LocationModel location, String username) {
	super(text, image, location, username);
    }
    
    public TopLevelCommentModel(String text,
	    LocationModel location, String username) {
	super(text, location, username);
    }
}
