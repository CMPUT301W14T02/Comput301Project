package ca.ualberta.cs.cmput301t02project.model;


import io.searchbox.annotations.JestId;

import java.util.ArrayList;

import android.content.Context;

/**
 * The User class stores information about a user. 
 * The information stored includes 
 * the username of the user, 
 * a list of their comments,
 * a list of their favorite comments and their replies,
 * a list of comments by their followed users,
 * and a list of followed user's usernames.
 */
public class User {

	@JestId
	private String id;
	private String username;
	private ArrayList<String> myCommentsIds;
	private transient MyCommentsListModel myComments;
	private transient FavoritesListModel favorites;
	private transient RepliesToFavsListModel repliesToFavs;
	private transient FollowedUserCommentsListModel followedUsersComments;
	private transient ArrayList<String> followedUsernames = new ArrayList<String>();

	//Singleton
	private static User user;

	/**
	 * Create a new user with a specific username
	 * @param username	the desired name for the user
	 */
	public User(String username) {
		this.username = username.toLowerCase();
	}
	
	/**
	 * Sets the current user and retrieves their lists.
	 * <p>
	 * The server is checked for a user with the specified username.
	 * If the username exists, the users old information is used.
	 * Otherwise a new user is created.
	 * Called from LoginActivity.
	 * <p>
	 * @param username	the username that the user wants to log with
	 * @param context	the context of the calling class
	 */
	public static void login(String username, Context context) {
		Server server = new Server(context);
		user = new User(username);
		server.pullUser(user);
		
		if(user.getId() == null) {
			user.myCommentsIds = new ArrayList<String>();
			server.postUser(user);
		}
		
		user.myComments = MyCommentsListModel.getInstance(context);
		user.favorites = FavoritesListModel.getInstance(context);
		user.repliesToFavs = RepliesToFavsListModel.getInstance(context);
		user.followedUsersComments = FollowedUserCommentsListModel.getInstance(context);
	}
	
	/**
	 * Returns the user that is currently logged in.
	 * @return	the current user if it is set, otherwise throws exception
	 */
	public static User getUser() {
		if(user == null) {
			throw new IllegalAccessError("Can't get user before logging in");
		}
		return user;
	}

	/**
	 * Updates the username for a User.
	 * <p>
	 * Setter that is not used in this app.
	 * <p>
	 * @param username	The username for the User
	 */
	public void setName(String username) {
		this.username = username.toLowerCase();
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
	 * Returns a list of comments composed by a User. 
	 * <p>
	 * The user's comments (CommentModels) are stored in a MyCommentListModel.
	 * The user's comments are retrieved in the 
	 * EditCommentActivity (in order to be edited) and BrowseMyCommentsActivity (in order to be displayed).
	 * <p>
	 * @return	List of comments that the User posted
	 * @see MyCommentListModel	Example of a MyCommentListModel
	 */
	public MyCommentsListModel getMyComments() {
		return myComments;
	}
	
	/**
	 * Adds a comment to the user's list of their own comments and stores the updated user information on the server.
	 * <p>
	 * Called by CreateCommentActivity when a new comment is posted. 
	 * Stores the updated user information containing the comment in the User class.
	 * Also pushes updated user information containing the comment to the server.
	 * <p>
	 * @param comment	the new comment to be stored
	 * @param context	context of the application
	 */
	public void addMyComment(CommentModel comment, Context context) {
		Server server = new Server(context);
		myComments.add(comment);
		myCommentsIds.add(comment.getId());
		server.postUser(user);
	}

	/**
	 * Adds a comment to the user's list of favorite comments.
	 * <p>
	 * Called from BrowseReplyCommentActivity when the "Add to Favorites" button is selected.
	 * Updates the user information in the User class. 
	 * <p>
	 * @param comment	the new comment to be stored
	 */
	public void addFavoriteComment(CommentModel comment, ArrayList<CommentModel> replies) {
		favorites.addFavoriteComment(comment, replies, repliesToFavs);
	}
	
	/**
	 * Stores the username of the comment author as a followed user.
	 * <p>
	 * Called from BrowseReplyCommentActivity when the "Follow" button is selected. 
	 * The real interest is in the username of the comment author, 
	 * which is extracted from the comment and stored in an ArrayList of strings called followedUsernames. 
	 * <p>
	 * @param comment	the current comment when the "Follow" button is pressed
	 */
	public void addFollowedUser(CommentModel comment){
		
		if(!followedUsernames.contains(comment.getUsername())){
			followedUsernames.add(comment.getUsername());
		}
	}
	
	/*
	 * GETTERS AND SETTERS BELOW
	 */
	public FavoritesListModel getFavorites(){
		return favorites;
	}
	
	public RepliesToFavsListModel getRepliesToFavs() {
		return repliesToFavs;
	}
	
	public ArrayList<String> getMyCommentIds() {
		return myCommentsIds;
	}
	
	public ArrayList<String> getFavoritesCommentIds() {
		return favorites.getIdList();
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	
	public void setMyCommentIds(ArrayList<String> myCommentsIds) {
		this.myCommentsIds = myCommentsIds;
	}
	
	/**
	 * Pull and return new comments from followed users on the server.
	 * @return	updated list of followed user comments
	 */
	public FollowedUserCommentsListModel getFollowedUsers() {
		
		if (followedUsernames.size()>0){
			return followedUsersComments.updated(followedUsernames);
		}
		else{
			return followedUsersComments;
		}
	}

	public void setFollowedUsers(FollowedUserCommentsListModel followedUsers) {
		this.followedUsersComments = followedUsers;
	}
}
