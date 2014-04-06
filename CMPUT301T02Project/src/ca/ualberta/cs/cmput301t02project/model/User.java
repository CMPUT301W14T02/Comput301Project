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
	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	
	public void setName(String username) {
		this.username = username.toLowerCase();
	}
	
	public String getName() {
		return username;
	}
	
	public void setMyCommentIds(ArrayList<String> myCommentsIds) {
		this.myCommentsIds = myCommentsIds;
	}
	
	public ArrayList<String> getMyCommentIds() {
		return myCommentsIds;
	}
	
	public void setMyComments(MyCommentsListModel myComments) {
		this.myComments = myComments;
	}
	
	public MyCommentsListModel getMyComments() {
		return myComments;
	}
	
	public void setFavorites(FavoritesListModel favorites){
		this.favorites = favorites;
	}
	
	public FavoritesListModel getFavorites(){
		return favorites;
	}
	
	public void setRepliesToFavs(RepliesToFavsListModel repliesToFavs){
		this.repliesToFavs = repliesToFavs;
	}
	
	public RepliesToFavsListModel getRepliesToFavs() {
		return repliesToFavs;
	}
	
	public ArrayList<String> getFavoritesCommentIds() {
		return favorites.getIdList();
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
