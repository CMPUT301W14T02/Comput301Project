package ca.ualberta.cs.cmput301t02project.model;

import io.searchbox.annotations.JestId;

import java.util.ArrayList;
import java.util.Date;

import ca.ualberta.cs.cmput301t02project.Server;

import android.graphics.Bitmap;
import android.location.Location;

/**
 * Model of the comment.
 * Instances of this class are stored in the server, and in the cache.
 */
public class CommentModel {
	
	@JestId
	private String id;
	private boolean topLevelComment;
	private Date date;
	private Location location; //transient until convertible to Json
	private String text;
	private transient Bitmap picture; //transient until convertible to Json
	private int rating;
	private transient CommentListModel replies; //to be changed to ArrayList<String> childrenIds
	private ArrayList<String> childrenIds;
	private String username;
	//private CommentModel parent;
	private transient Server server = new Server();

	
	
	/**
	 * Creates a new Comment.
	 * <p>
	 * No args constructor, all fields are initialized to null.
	 * <p>
	 */
	public CommentModel() {	
	}

	/**
	 * Creates a new Comment.
	 * <p>
	 * One of the parameters is the picture.
	 * <p>
	 * @param text	The comment body
	 * @param picture	The picture of the comment
	 * @param location	The location of the comment
	 * @param username	The user that the comment belongs to
	 */
	public CommentModel(String text, Bitmap picture, Location location, String username) {
		this.text = text;
		this.picture = picture;
		this.location = location;
		this.username = username;
		this.rating = 0;
		this.replies = new CommentListModel(this);
		this.date = new Date();
		this.childrenIds = new ArrayList<String>();
	}

	/**
	 * Creates a new Comment.
	 * <p>
	 * The picture is set to null
	 * <p>
	 * @param text	The comment body
	 * @param location	The location of the comment
	 * @param username	The user that the comment belongs to
	 */
	public CommentModel(String text, Location location, String username) {
		this.text = text;
		this.picture = null;
		this.location = location;
		this.username = username;
		this.rating = 0;
		this.replies = new CommentListModel(this);
		this.date = new Date();
		this.childrenIds = new ArrayList<String>();
	}
	
	/**
	 * Gets the comment id
	 * @return The id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the comment id
	 * @param id The id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets the comment date.
	 * @return The date
	 */
	public Date getDate() {
		return date;
	}
	
	/**
	 * Sets the comment date.
	 * @param date	The date
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * Gets the comment location.
	 * @return The location
	 */
	public Location getLocation() {
		return location;
	}
	
	/**
	 * Sets the comment location.
	 * @param location	The location
	 */
	public void setLocation(Location location) {
		this.location = location;
	}

	/**
	 * Gets the comment body text.
	 * @return The text
	 */
	public String getText() {
		return text;
	}

	/**
	 * Sets the comment body text.
	 * @param text	The text
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * Gets the comment picture.
	 * @return The picture
	 */
	public Bitmap getImage() {
		return picture;
	}

	/**
	 * Sets the comment picture.
	 * @param image	the picture
	 */
	public void setImage(Bitmap image) {
		this.picture = image;
	}

	/**
	 * Gets the comment rating.
	 * @return The rating
	 */
	public int getRating() {
		return rating;
	}

	/**
	 * Sets the comment rating.
	 * @param rating	The rating
	 */
	public void setRating(int rating) {

	}

	/**
	 * Gets the comment's list of replies.
	 * @return The reply list
	 */
	public CommentListModel getReplies() {
		return replies;
	}

	/**
	 * Sets the comment's list of replies.
	 * @param replies	The reply list
	 */
	public void setReplies(CommentListModel replies) {
		this.replies = replies;
	}

	/**
	 * Gets the comment author's username.
	 * @return The username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the comment author's username.
	 * @param username	The username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Returns a string that represents the comment.
	 * @return The string
	 */
	@Override
	public String toString() {

		// If statement to fix grammar issue of a singular reply vs replies
		// plural -SB
		if (replies.getCommentList().size() == 1) {
			return text + " (by " + username + ", 1 reply)";
		} 
		else {
			return text + " (by " + username + ", " + replies.getCommentList().size() + " replies)";
		}
	}

	public boolean isTopLevelComment() {
		return topLevelComment;
	}

	public void setTopLevelComment(boolean topLevelComment) {
		this.topLevelComment = topLevelComment;
	}
/*
	public CommentModel getParent() {
		return parent;
	}

	public void setParent(CommentModel parent) {
		this.parent = parent;
	}
*/
	
	/**
	 * Enables comparison for equality between Comments.
	 * <p>
	 * The text, the image and the location are used for the comparison.
	 * <p>
	 * @return Whether the comments are equal
	 */
	@Override
	public boolean equals(Object commentModel) {
		CommentModel comment = (CommentModel) commentModel;
		
		return comment.getText().equals(this.getText()) && comment.getUsername().equals(this.getUsername())
				&& ((comment.getImage() == null && this.picture == null) || comment.getImage().equals(this.picture))
				&& ((comment.getLocation() == null && this.location == null) || comment.getLocation().equals(this.location));
	}

	public ArrayList<String> getChildrenIds() {
		return childrenIds;
	}

	public void setChildrenIds(ArrayList<String> childrenIds) {
		this.childrenIds = childrenIds;
	}
	
	public void addChildId(String id) {
		this.childrenIds.add(id);
	}
	
	public CommentListModel pullReplies() {
		CommentListModel replies;
		replies = new CommentListModel();
		replies.setCommentList(server.pull(childrenIds));
		return replies;
	}
	
}
