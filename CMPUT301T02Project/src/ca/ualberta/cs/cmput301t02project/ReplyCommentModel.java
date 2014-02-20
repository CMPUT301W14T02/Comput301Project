package ca.ualberta.cs.cmput301t02project;

import java.util.ArrayList;

public class ReplyCommentModel extends CommentModelAbstraction {

	public ReplyCommentModel (String text, String image, LocationModel location, String username) {
		super();
		this.text = text;
		this.image = image;
		this.location = location;
		this.username = username;
		this.rating = 0;
		this.replies = new ArrayList<ReplyCommentModel>();
	}
}
