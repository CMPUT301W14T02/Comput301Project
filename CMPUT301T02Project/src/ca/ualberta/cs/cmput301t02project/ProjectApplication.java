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
	 * <p>
	 * Username can be checked against a list of all users (UserList) to see if the user already exists. 
	 * If the username already exists, the user's previous data can be retrieved.
	 * Username is displayed next to a user's comment. 
	 * Example of a username: the string "Bob".
	 * <p>
	 * @return current user's username
	 */
	public static String getName() {
		return currentUser.getName();
	}
	
	/**
	 * Sets the current username.
	 * <p>
	 * A user can set their own username from the login page.
	 * The list of "MyComments" is determined by the user with that username.
	 * Example of a username: the string "Bob".
	 * <p>
	 * @param name	username of the current user
	 */
	public static void setName(String name) {
		currentUser.setName(name);
	}
	
	/**
	 * Returns the current User.
	 * <p>
	 * The current user information is stored in a User. 
	 * This includes the current user's username, a list of their own comments and their favorite comments.
	 * The user information can be retrieved for determining which comments to display in
	 * BrowseMyCommentsActivity, EditCommentActivity, and BrowseFavoritesActivity.
	 * <p>
	 * @see User	Example of a User
	 * @return current user information
	 *
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
