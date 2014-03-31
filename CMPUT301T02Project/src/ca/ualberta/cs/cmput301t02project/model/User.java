package ca.ualberta.cs.cmput301t02project.model;

import java.util.ArrayList;

import android.content.Context;

/**
 * The User class stores information about a user. 
 * The information stored includes 
 * the username of the user, a list of their comments, and a list of their favorite comments.
 */
public class User {

	private String username;
	private String id;
	private ArrayList<String> myCommentsIds;
	private ArrayList<String> favoritesIds;
	private transient MyCommentsListModel myComments;
	private transient FavoritesListModel favorites;
	private transient CommentListModel favoritesToView;
	
	private static User user;

	private User(String username, Context context) {
		this.username = username;
		myComments = new MyCommentsListModel(context);
		favorites = new FavoritesListModel(context);
		favoritesToView = new CommentListModel();
		myCommentsIds = new ArrayList<String>();
		favoritesIds = new ArrayList<String>();
	}
	
	public static void login(String username, Context context) {
		user = new User(username, context);
	}
	
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
	 * Returns a list of comments composed by a User. 
	 * <p>
	 * The user's comments (CommentModels) are stored in a CommentListModel.
	 * The user's comments are retrieved in the 
	 * EditCommentActivity (in order to be edited) and BrowseMyCommentsActivity (in order to be displayed).
	 * <p>
	 * @return	List of comments that the User posted
	 * @see CommentListModel	Example of a CommentListModel
	 */
	public ArrayList<CommentModel> getMyComments() {
		return myComments.getCommentList();
	}
	
	/**
	 * Stores comment on phone storage
	 * <p>
	 * Stores comment created by user to phone by calling 
	 * storeMyComment method in StorageModel class
	 * CreateCommentActivity
	 * <p>
	 * @param comment - comment to be stored
	 * @param context - context of the application
	 */
	public void addMyComment(CommentModel comment) {
		myComments.add(comment);
	}
	
	/**
	 * Returns current favorites list
	 * <p>
	 * Returns the list of favorites or a selected favorite's 
	 * replies to be displayed
	 * BrowseFavoritesActivity
	 * <p>
	 * @return CommentListModel containing comments favorited by user, 
	 * or their replies
	 */
	public CommentListModel getFavoritesToView() {
		return favoritesToView;
	}

	/**
	 * Sets current favorites list
	 * <p>
	 * Sets the list of favorites or a selected favorite's 
	 * replies
	 * BrowseFavoritesActivity
	 * @param CommentListModel containing the list favorites the 
	 * next activity will display
	 * <p>
	 */
	public void setFavoritesToView(CommentListModel favoritesToView) {
		this.favoritesToView = favoritesToView;
	}

	/**
	 * Stores comment on phone storage
	 * <p>
	 * Stores comment favorited by user to phone by calling 
	 * storeFavorite method in StorageModel class
	 * BrowseRepliesActivity
	 * <p>
	 * @param comment - comment to be stored
	 */
	public void addFavoriteComment(CommentModel comment) {
		favorites.add(comment);
	}
	
	/**
	 * Returns list of comments from phone storage
	 * <p>
	 * Retrieves replies of comments favorited by current user from phone storage 
	 * by calling retrieveFavorites method in StorageModel class
	 * BrowseFavoritesActivity
	 * <p>
	 * @param context - context of the application
	 * @param CommentModel - favorited comment selected by the user
	 * @return CommentListModel containing comments favorited by user
	 *
	public CommentListModel getFavoriteReplies(Context context, CommentModel favComment){
		CommentListModel replies = new CommentListModel();
		ArrayList<ArrayList<CommentModel>> favoritesArray;
		favoritesArray = store.retrieveFavorites(context);
		if (favoritesArray == null)
			 favoritesArray = new ArrayList<ArrayList<CommentModel>>();
		for (int i=0; i<favoritesArray.size();i++){
			if (favoritesArray.get(i).get(0).getId().matches(favComment.getId())){
				for (int j=1; j<favoritesArray.get(i).size();j++){
					replies.add(favoritesArray.get(i).get(j));
				}
			}
		}
		return replies;
	}*/
	
	/**
	 * Returns list of comments from phone storage
	 * <p>
	 * Retrieves comments favorited by current user from phone storage 
	 * by calling retrieveFavorites method in StorageModel class
	 * BrowseFavoritesActivity
	 * <p>
	 * @param context - context of the application
	 * @return CommentListModel containing comments favorited by user
	 */
	public ArrayList<CommentModel> getFavorites(){
		return favorites.getCommentList();
	}
	
	public ArrayList<String> getMyCommentIds(String list) {
		return myComments.getIdList();
	}
	
	public ArrayList<String> getFavoritesCommentIds(String list) {
		return favorites.getIdList();
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}

	public void set(User source) {
		this.username = source.username;
		this.id = source.id;
		this.myCommentsIds = source.myCommentsIds;
		this.favoritesIds = source.favoritesIds;
	}
}
