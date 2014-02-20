package ca.ualberta.cs.cmput301t02project;

public class UserModel {
	String name;
	ArrayList<FavoritesListModel> favoritesList;
	ArrayList<MyCommentsModel> myComments;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public ArrayList<FavoritesListModel> getFavoritesList() {
		return favoritesList;
	}
	
	public void setFavoritesList(ArrayList<FavoritesListModel> favoritesList) {
		this.favoritesList = favoritesList;
	}
	
	public ArrayList<MyCommentsModel> getMyComments() {
		return myComments;
	}
	
	public void setMyComments(ArrayList<MyCommentsModel> myComments) {
		this.myComments = myComments;
	}
	
}
