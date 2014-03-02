package ca.ualberta.cs.cmput301t02project;

public class User {
    private static String currentName = "";
    
    private String name;

    // private ArrayList<FavoritesListModel> favoritesList;
    // private ArrayList<MyCommentsModel> myComments;

    public User(String name) {
    	super();
    	this.name = name;
    }
    
    public String getName(){
    	return name;
    }
    
    public void setName(String name) {
    	this.name = name;
    }
    

    public static String getCurrentName(){
    	return currentName;
    }

    public static void setCurrentName(String name) {
    	User.currentName = name;
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
