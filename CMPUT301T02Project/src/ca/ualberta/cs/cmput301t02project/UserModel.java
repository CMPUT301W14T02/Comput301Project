package ca.ualberta.cs.cmput301t02project;

public class UserModel {
	private String name;
//	private ArrayList<FavoritesListModel> favoritesList;
//	private ArrayList<MyCommentsModel> myComments;
	
	public UserModel(String name) {
		super();
		this.name = name;
//		this.favoritesList = new ArrayList<FavoritesListModel>;
//		this.myComments = new ArrayList<MyCommentsModel>;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	/*
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
	*/
}
