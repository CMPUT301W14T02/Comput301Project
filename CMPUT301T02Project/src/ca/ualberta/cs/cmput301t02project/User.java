package ca.ualberta.cs.cmput301t02project;

import ca.ualberta.cs.cmput301t02project.model.CommentListModel;

/**
 * The User class stores information about a user. 
 * The information stored includes 
 * the username of the user, a list of their comments, and a list of their favorite comments.
 */
public class User {

	private String username;
	private CommentListModel myComments;
	private CommentListModel myFavorites;

	/**
	 * Creates a new User.
	 * <p>
	 * Creates a new User given a username. 
	 * Sets the username to the given username and initializes the
	 * list of their comments and list of their favorite comments.
	 * Used to create a user in LoginActivity when the username doesn't already exist.
	 * Example of a newly created User: 
	 * username = "Bob", my comments = empty CommentListModel, favorite comments = empty
	 * <p>
	 * @param username	The username for the User
	 */
	public User(String username) {
		super();
		this.username = username;
		myComments = new CommentListModel();
		myFavorites = new CommentListModel();
	}

	/**
	 * Updates the username for a User.
	 * <p>
	 * Setter that is not used in this app.
	 * <p>
	 * @param username	The username for the User
	 */
	public void setName(String username) {
		this.username = username;
	}
	
	/**
	 * Returns the username of a User.
	 * <p>
	 * Username is used to identify a User. 
	 * Username can be checked against a list of all users (UserList) to see if the user already exists. 
	 * If the username already exists, the user's previous data can be retrieved.
	 * Username is displayed next to a user's comment in all of the Browse __ Activities. 
	 * Example of a username: the string "Bob".
	 * <p>
	 * @return Username of the User
	 */
	public String getName() {
		return username;
	}

	/**
	 * Sets the list of comments composed by a User. 
	 * <p>
	 * The user's comments (CommentModels) are stored in a CommentListModel.
	 * The user's comments are not set using this method in the app. 
	 * Instead, new comments are added to the CommentListModel (initialized in the constructor)
	 * in the MyCommentsController.
	 * <p>
	 * @param myComments	List of comments that the User posted
	 * @see CommentListModel	Example of a CommentListModel
	 */
	public void setMyComments(CommentListModel myComments) {
		this.myComments = myComments;
	}
	
	/**
	 * Returns a list of comments composed by a User. 
	 * <p>
	 * The user's comments (CommentModels) are stored in a CommentListModel.
	 * The user's comments are retrieved in the 
	 * EditCommentActivity (in order to be edited) and MyCommentsActivity (in order to be displayed).
	 * <p>
	 * @return	List of comments that the User posted
	 * @see CommentListModel	Example of a CommentListModel
	 */
	public CommentListModel getMyComments() {
		return this.myComments;
	}
	
	/**
	 * 
	 * @param myFavorites	List of the User's favorite comments
	 */
	public void setFavorites(CommentListModel myFavorites) {
		this.myFavorites = myFavorites;
	}
	
	/**
	 * 
	 * @return List of the User's favorite comments
	 */
	public CommentListModel getFavorites() {
		return this.myFavorites;
	}
}
