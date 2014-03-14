package ca.ualberta.cs.cmput301t02project;

import ca.ualberta.cs.cmput301t02project.model.CommentListModel;

/**
 * The User class stores information about a user. 
 * The information stored includes 
 * the username of the user, a list of their comments, and a list of their favorite comments.
 */
public class User {

	private String username;
	private CommentListModel myFavorites;
	private CommentListModel myComments;

	public User(String username) {
		super();
		this.username = username;
		myComments = new CommentListModel();
	}

	public void setName(String username) {
		this.username = username;
	}
	
	public String getName() {
		return username;
	}

	public void setMyFavoriteComments(CommentListModel myFavorites) {
		this.myFavorites = myFavorites;
	}
	
	public CommentListModel getMyFavoritesComments() {
		return this.myFavorites;
	}
	
	public void setMyComments(CommentListModel myComments) {
		this.myComments = myComments;
	}
	
	public CommentListModel getMyComments() {
		return this.myComments;
	}
}
