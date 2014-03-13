package ca.ualberta.cs.cmput301t02project;

import android.location.Location;
import ca.ualberta.cs.cmput301t02project.model.CommentListModel;
import ca.ualberta.cs.cmput301t02project.model.CommentModel;

/**
 * ProjectApplication holds static variables used by the application
 */
public class ProjectApplication {

	// Singleton
	private static ProjectApplication instance = null;
	private static CommentListModel commentList;
	private static CommentListModel currentCommentList;
	private static CommentModel currentComment;
	private static Location currentLocation;
	private static User currentUser = new User("default");

	// Singleton code adapted from http://www.javaworld.com/article/2073352/core-java/simply-singleton.html
	protected ProjectApplication() {
		// Exists only to defeat instantiation.
	}

	/**
	 * Returns the current username. 
	 * 
	 * 
	 * Username is also stored in a list of all users which is used to check if a user already exists. 
	 * Example of a username: the string "Bob".
	 * 
	 * @return current user's username
	 */
	public static String getName() {
		return currentUser.getName();
	}
	
	/**
	 * Sets the current username.
	 * 
	 * Username is used when comment is displayed.
	 * Example of a username: the string "Bob".
	 * User can set their own username from the login page.
	 * 
	 * @param name	username of the current user
	 */
	public static void setName(String name) {
		currentUser.setName(name);
	}
	
	/**
	 * 
	 * @return current user information
	 */
	public static User getUser() {
		return currentUser;
	}
	
	/**
	 * 
	 * @return instance of ProjectApplication
	 */
	public static ProjectApplication getInstance() {
		if (instance == null) {
			instance = new ProjectApplication();
		}
		return instance;
	}
	
	/**
	 * Returns the complete list of all comments
	 * @return list of all top level comments
	 */
	public static CommentListModel getCommentList() {
		if (commentList == null) {
			commentList = new CommentListModel();
		}
		return commentList;
	}
	
	/**
	 * 
	 * @param currentCommentList	the up-to-date comment list of interest
	 */
	public static void setCurrentCommentList(CommentListModel currentCommentList) {
		ProjectApplication.currentCommentList = currentCommentList;
	}

	/**
	 * 
	 * @return list of current comments of interest
	 */
	public static CommentListModel getCurrentCommentList() {

		if (currentCommentList == null) {

			// Creating before browsing comments.
			currentCommentList = ProjectApplication.getCommentList();
		}
		return currentCommentList;
	}

	/**
	 * 
	 * @return current comment of interest
	 */
	public static CommentModel getCurrentComment() {
		return currentComment;
	}

	/**
	 * 
	 * @param currentComment	current comment of interest
	 */
	public static void setCurrentComment(CommentModel currentComment) {
		ProjectApplication.currentComment = currentComment;
	}

	/**
	 * 
	 * @return current user's location
	 */
	public static Location getCurrentLocation() {
		return currentLocation;
	}

	/**
	 * 
	 * @param location	location to record
	 */
	public static void setCurrentLocation(Location location) {
		if (currentLocation==null) {
			String loc = "Location Intialization";
			currentLocation = new Location(loc);
		}
		
		ProjectApplication.currentLocation = location;
	}
}
