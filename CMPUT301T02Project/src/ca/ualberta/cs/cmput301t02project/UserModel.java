package ca.ualberta.cs.cmput301t02project;

public class UserModel {
    private static String name = "";

    // private ArrayList<FavoritesListModel> favoritesList;
    // private ArrayList<MyCommentsModel> myComments;

    public UserModel(String name) {
    	super();
    	UserModel.name = name;
    	// this.favoritesList = new ArrayList<FavoritesListModel>;
    	// this.myComments = new ArrayList<MyCommentsModel>;
    }

    public static String getName() {
    	return name;
    }

    public static void setName(String name) {
    	UserModel.name = name;
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
