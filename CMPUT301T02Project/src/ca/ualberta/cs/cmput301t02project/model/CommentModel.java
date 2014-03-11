package ca.ualberta.cs.cmput301t02project.model;

import java.util.Date;

import android.graphics.Bitmap;

public class CommentModel {
	private Date date;
	private LocationModel location;
	private String text;
	private Bitmap picture;
	private int rating;
	private CommentListModel replies;
	private String username;

	// Post with picture
	public CommentModel(String text, Bitmap picture, LocationModel location,
			String username) {
		this.text = text;
		this.picture = picture;
		this.location = location;
		this.username = username;
		this.rating = 0;
		this.replies = new CommentListModel();
		this.date = new Date();
	}

	// Post without picture
	public CommentModel(String text, LocationModel location, String username) {
		this.text = text;
		this.picture = null;
		this.location = location;
		this.username = username;
		this.rating = 0;
		this.replies = new CommentListModel();
		this.date = new Date();
	}
	
	
	public Date getDate() {
		return date;
	}
	
	// Added for testing- TH
	public void setDate(Date date) {
		this.date = date;
	}

	public LocationModel getLocation() {
		return location;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Bitmap getImage() {
		return picture;
	}

	public void setImage(Bitmap image) {
		this.picture = image;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {

	}

	public CommentListModel getReplies() {
		return replies;
	}

	public void setReplies(CommentListModel replies) {
		this.replies = replies;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {

		// If statement to fix grammar issue of a singular reply vs replies
		// plural -SB
		if (replies.getCommentList().size() == 1) {
			return text + " (by " + username + ", 1 reply)";
		} else {
			return text + " (by " + username + ", " + replies.getCommentList().size()
					+ " replies)";
		}
	}

}
