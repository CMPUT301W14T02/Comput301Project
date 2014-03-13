package ca.ualberta.cs.cmput301t02project;

import ca.ualberta.cs.cmput301t02project.model.CommentListModel;

public class User {

	private String name;

	// private ArrayList<FavoritesListModel> favoritesList;
	private CommentListModel myComments;

	public User(String name) {
		super();
		this.name = name;
		myComments = new CommentListModel();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setMyComments(CommentListModel myComments) {
		this.myComments = myComments;
	}
	
	public CommentListModel getMyComments() {
		return this.myComments;
	}

	/*
	 * public ArrayList<FavoritesListModel> getFavoritesList() { return
	 * favoritesList; }
	 * 
	 * public void setFavoritesList(ArrayList<FavoritesListModel> favoritesList)
	 * { this.favoritesList = favoritesList; }
	 * 
	 * public ArrayList<MyCommentsModel> getMyComments() { return myComments; }
	 * 
	 * public void setMyComments(ArrayList<MyCommentsModel> myComments) {
	 * this.myComments = myComments; }
	 */

}
