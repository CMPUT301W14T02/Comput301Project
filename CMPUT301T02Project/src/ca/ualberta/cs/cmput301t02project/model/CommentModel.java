package ca.ualberta.cs.cmput301t02project.model;

import io.searchbox.annotations.JestId;

import java.util.ArrayList;
import java.util.Date;

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
	private Location location;
	private String text;
	private Bitmap picture;
	private int rating;
	private ArrayList<String> childrenIds;
	private String username;
	
	/**
	 * Creates a new Comment.
	 * <p>
	 * No args constructor, all fields are initialized to null.
	 * <p>
	 */
	public CommentModel() {	
	}

	/**
	 * Creates a new Comment with a picture.
	 * <p>
	 * Includes a parameter for a picture.
	 * <p>
	 * @param text		The comment body
	 * @param picture	The picture attached to the comment
	 * @param location	The location of the comment
	 * @param username	The user that the comment belongs to
	 */
	public CommentModel(String text, Bitmap picture, Location location, String username) {
		this.picture = picture;
		this.text = text;
		this.location = location;
		this.username = username;
		this.rating = 0;
		this.date = new Date();
		this.childrenIds = new ArrayList<String>();
	}
	
	/**
	 * Creates a new Comment with no picture.
	 * <p>
	 * The picture is set to null.
	 * <p>
	 * @param text		The comment body
	 * @param location	The location of the comment
	 * @param username	The user that the comment belongs to
	 */
	public CommentModel(String text, Location location, String username) {
		this.text = text;
		this.picture = null;
		this.location = location;
		this.username = username;
		this.rating = 0;
		this.date = new Date();
		this.childrenIds = new ArrayList<String>();
	}
	
	/**
	 * Determines whether the comment has a picture or not.
	 * <p>
	 * Returns false for no picture attached, 
	 * returns true for a picture attached.
	 * <p>
	 * @return true if the comment has a picture, false if not
	 */
	public Boolean hasPicture(){
		if (picture == null){
			return false;
		}
		return true;
	}


	/**
	 * Returns a string that represents the comment.
	 * @return The string representing a comment
	 */
	@Override
	public String toString() {

		// If statement to fix grammar issue of a singular reply vs replies plural -SB
		if (childrenIds.size() == 1) {
			return text + " (by " + username + ", 1 reply)";
		} 
		else {
			return text + " (by " + username + ", " + childrenIds.size() + " replies)";
		}
	}

	/**
	 * Determines whether a comment is a top level comment or a reply.
	 * @return	true if it is top level, else false
	 */
	public boolean isTopLevelComment() {
		return topLevelComment;
	}

	
	/**
	 * Add a commentId to the array list of childrenIds. 
	 * <p>
	 * Called when adding a reply to a comment. 
	 * <p>
	 * @param id	id string to add
	 */
	public void addChildId(String id) {
		if(id == null) {
			throw new IllegalArgumentException("A comment can't have a children whose id is null");
		}
		this.childrenIds.add(id);
	}
	
	/**
	 * Increases the rating of a comment by one.
	 * <p>
	 * Called when a comment is added to a user's favorites.
	 * <p>
	 */
	public void incrementRating() {
		this.rating++;
	}

	/**
	 * Tests two comments for equality.
	 * <p>
	 * Only the username and and the text are used for comparison.
	 * @param obj The CommentModel to be compared to.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}	
		if (obj == null) {
			return false;
		}	
		if (getClass() != obj.getClass()){
			return false;
		}
			
		CommentModel other = (CommentModel) obj;
		
		if (text == null) {
			if (other.text != null){
				return false;
			}	
		} 
		else if (!text.equals(other.text)) {
			return false;
		}	
		if (username == null) {
			if (other.username != null) {
				return false;
			}	
		} 
		else if (!username.equals(other.username)) {
			return false;
		}
		return true;
	}	
	
	/* 
	 * GETTERS AND SETTERS LISTED BELOW
	 */
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}

	public Location getLocation() {
		return location;
	}
	
	public void setLocation(Location location) {
		this.location = location;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Bitmap getPicture() {
		return this.picture;
	}

	public void setPicture(Bitmap image) {
		this.picture = image;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setTopLevelComment(boolean topLevelComment) {
		this.topLevelComment = topLevelComment;
	}

	public ArrayList<String> getChildrenIds() {
		return childrenIds;
	}

	public void setChildrenIds(ArrayList<String> childrenIds) {
		this.childrenIds = childrenIds;
	}
}



