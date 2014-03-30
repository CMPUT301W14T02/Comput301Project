package ca.ualberta.cs.cmput301t02project;

import java.util.ArrayList;

import android.content.Context;

import ca.ualberta.cs.cmput301t02project.model.CommentListModel;
import ca.ualberta.cs.cmput301t02project.model.CommentModel;
import ca.ualberta.cs.cmput301t02project.model.StorageModel;

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
	private transient CommentListModel myComments;
	private transient CommentListModel favorites;
	private transient CommentListModel favoritesToView;
	private transient StorageModel store = new StorageModel();
	

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
		favorites = new CommentListModel();
		myCommentsIds = new ArrayList<String>();
		favoritesIds = new ArrayList<String>();
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
	 * in the BrowseMyCommentsController.
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
	 * EditCommentActivity (in order to be edited) and BrowseMyCommentsActivity (in order to be displayed).
	 * <p>
	 * @return	List of comments that the User posted
	 * @see CommentListModel	Example of a CommentListModel
	 */
	public CommentListModel getMyComments() {
		return this.myComments;
	}
	
	/**
	 * Returns list of comments from phone storage
	 * <p>
	 * Retrieves comments created by current user from phone storage 
	 * by calling retrieveMyComments method in StorageModel class
	 * BrowseMyCommentsActivity
	 * <p>
	 * @param context - context of the application
	 * @return CommentListModel containing comments created by user
	 */
	public CommentListModel getMyComments(Context context){
		ArrayList<CommentModel> myCommentsArray = new ArrayList<CommentModel>();
		myCommentsArray = store.retrieveMyComments(context);
		myComments.setCommentList(myCommentsArray);
		return myComments;
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
	public void addMyComment(CommentModel comment, Context context) {
		getMyComments(context);
		myComments.add(comment);
		myCommentsIds.add(comment.getId());
		store.storeMyComment(context);

	}
	
	public CommentListModel getFavoritesToView() {
		return favoritesToView;
	}

	public void setFavoritesToView(CommentListModel favoritesToView) {
		this.favoritesToView = favoritesToView;
	}

	public void addFavoriteComment(CommentModel comment, Context context) {
		getFavorites(context);
		// Need to retrieve favorites list from storage before checking id
		if (!favoritesIds.contains(comment.getId())) {
			favorites.add(comment);
			favoritesIds.add(comment.getId());
			store.storeFavorite(comment, context);
		}
	}
	
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
	public CommentListModel getFavorites(Context context){
		ArrayList<ArrayList<CommentModel>> favoritesArray;;
		favoritesArray = store.retrieveFavorites(context);
		// Create new list each time otherwise same comments will continuously
		// be added to favorites -TH
		favorites = new CommentListModel();
		if (favoritesArray == null)
			 favoritesArray = new ArrayList<ArrayList<CommentModel>>();
		for (int i=0; i<favoritesArray.size();i++){
			favorites.add(favoritesArray.get(i).get(0));
		}
		
		//favorites.setCommentList(favoritesArray);
		return favorites;
	}
	
	/**
	 * Sets the list of comments favorited by a User. 
	 * <p>
	 * The user's favorite comments (CommentModels) are stored in a CommentListModel.
	 * The user's favorite comments are not set using this method in the app. 
	 * Instead, new favorite comments are added to the CommentListModel (initialized in the constructor)
	 * in the FavoritesController.
	 * <p>
	 * @param myFavorites	List of the User's favorite comments
	 * @see CommentListModel	Example of a CommentListModel
	 */
	public void setFavorites(CommentListModel favorites) {
		this.favorites = favorites;
	}
	
	/**
	 * Returns a list of comments favorited by a User. 
	 * <p>
	 * The user's favorite comments (CommentModels) are stored in a CommentListModel.
	 * The user's favorite comments are retrieved in the 
	 * BrowseFavoritesActivity (in order to be displayed).
	 * <p>
	 * @return List of the User's favorite comments
	 * @see CommentListModel	Example of a CommentListModel
	 */
	public CommentListModel getFavorites() {
		return this.favorites;
	}
	
	public ArrayList<String> getMyCommentIds(String list) {
		return myCommentsIds;
	}
	
	public ArrayList<String> getFavoritesCommentIds(String list) {
		return favoritesIds;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
}
