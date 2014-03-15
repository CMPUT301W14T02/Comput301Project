package ca.ualberta.cs.cmput301t02project;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
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
	private static Location currentLocation = null;
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
	 * @return Current user's username
	 */
	public static String getName() {
		return currentUser.getName();
	}
	
	/**
	 * Sets the current username.
	 * <p>
	 * A user can set their own username from the login page (LoginActivity).
	 * The list of "MyComments" is determined by the user with that username.
	 * Example of a username: the string "Bob".
	 * <p>
	 * @param name	Current user's username
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
	 * @return Current user information
	 *
	 */
	public static User getUser() {
		return currentUser;
	}
	
	/**
	 * Creates an instance of ProjectApplication.
	 * @return Instance of ProjectApplication
	 */
	public static ProjectApplication getInstance() {
		if (instance == null) {
			instance = new ProjectApplication();
		}
		return instance;
	}
	
	/**
	 * Returns the complete list of all comments.
	 * <p>
	 * A list of all comments in the app is stored in a CommentListModel.
	 * The list of all comments can be retrieved to display comments in BrowseTopLevelCommentsActivity.
	 * Comments in the CommentListModel include information about the text of comments, 
	 * the username of the comment author, the location of the comment, pictures attached to
	 * the comment, and a list of replies to that comment. 
	 * <p>
	 * @return List of all top level comments
	 * @see CommentListModel	Example of a CommentListModel
	 */
	public static CommentListModel getCommentList() {
		if (commentList == null) {
			commentList = new CommentListModel();
		}
		return commentList;
	}
	
	/**
	 * Sets the current list of comments of interest.
	 * <p> 
	 * The list of comments of interest is updated when a comment is selected in order to see its replies in
	 * BrowseTopLevelCommentsActivity and BrowseReplyCommentsActivity. 
	 * The list of comments to be displayed in the future is determined by which CommentListModel is set. 
	 * The list of comments of interest is a sub-set of the comments list that contains all comments in the app.
	 * <p>
	 * @param currentCommentList	The up-to-date comment list of interest
	 * @see CommentListModel	Example of a CommentListModel
	 */
	public static void setCurrentCommentList(CommentListModel currentCommentList) {
		ProjectApplication.currentCommentList = currentCommentList;
	}

	/**
	 * Returns the current list of comments of interest.
	 * <p>
	 * The list of current comments of interest is used to determine which comments to display in 
	 * BrowseTopLevelCommentsActivity and BrowseReplyCommentsActivity. 
	 * The list of comments to be displayed is determined by which CommentListModel is returned. 
	 * The list of comments of interest is a sub-set of the comments list that contains all comments in the app.
	 * <p>
	 * @return List of current comments of interest
	 * @see CommentListModel	Example of a CommentListModel
	 */
	public static CommentListModel getCurrentCommentList() {

		if (currentCommentList == null) {

			// Creating before browsing comments.
			currentCommentList = ProjectApplication.getCommentList();
		}
		return currentCommentList;
	}

	/**
	 * Returns the current comment being viewed.
	 * <p>
	 * The current comment is used to be displayed at the top of the screen in
	 * BrowseTopLevelCommentsActivity and BroweReplyCommentsActivity.
	 * The replies to the comment can also be obtained from the returned CommentModel so that they can be displayed.
	 * @return Current comment of interest
	 * @see CommentModel	Example of a CommentModel
	 */
	public static CommentModel getCurrentComment() {
		return currentComment;
	}

	/**
	 * Sets the current comment to be viewed.
	 * <p>
	 * The current comment is set when a comment is clicked on in 
	 * BrowseTopLevelCommentsActivity and BroweReplyCommentsActivity.
	 * This keeps track of which comment is currently being viewed by the user.
	 * <p>
	 * @param currentComment	Current comment of interest
	 * @see CommentModel	Example of a CommentModel
	 */
	public static void setCurrentComment(CommentModel currentComment) {
		ProjectApplication.currentComment = currentComment;
	}

	/**
	 * Returns the current location of the user.
	 * <p>
	 * The current location of the user is retrieved when posting a comment through
	 * CreateCommentActivity and EditCommentActiviy. 
	 * <p>
	 * @return current user's location
	 */
	public static Location getCurrentLocation() {
		return currentLocation;
	}

	/**
	 * Sets the current location of the user.
	 * <p>
	 * Sets the location directly.
	 * This could be used for overriding the normal behavior,
	 * i.e.: the location being constantly updated by the LocationManager
	 * <p>
	 * @param location	Location to record
	 */
	public static void setCurrentLocation(Location location) {		
		ProjectApplication.currentLocation = location;
	}
	
	/**
	 * Initializes the LocationManager.
	 * <p>
	 * Must be called before requesting the user's location.
	 * The location is automatically updated as the GPS updates.
	 * <p>
	 * @param context	A context is required to get a System Service
	 */
	public static void InitializeLocationManager(Context context) {
		LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
		currentLocation = new Location("");
		
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, 
				new LocationListener() {
					// TODO: override the four methods.
					@Override
					public void onLocationChanged(Location location) {

						if (location != null) {
							ProjectApplication.setCurrentLocation(location);

						} else {
							// do something later here
						}
					}

					@Override
					public void onProviderDisabled(String provider) {

						// TODO
					}

					@Override
					public void onProviderEnabled(String provider) {

						// TODO
					}

					@Override
					public void onStatusChanged(String provider, int status, Bundle extras) {

						// TODO
					}
				});
	}
	
}
