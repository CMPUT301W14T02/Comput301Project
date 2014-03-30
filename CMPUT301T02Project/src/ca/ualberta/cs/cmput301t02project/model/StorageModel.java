package ca.ualberta.cs.cmput301t02project.model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

import android.content.Context;
import android.location.Location;
import ca.ualberta.cs.cmput301t02project.ProjectApplication;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Deals with storing data on the server and caching data locally.
 */
public class StorageModel {

	private static Gson gson = new Gson();
	private transient String MYCOMMENTSFILE = "MyComments.json";
	private transient String FAVORITESFILE = "Favorites.json"; 
	
	
	/**
	 * Stores comment created by user locally.
	 * <p>
	 * Stores a CommentModel created by user on the user's local device. 
	 * User
	 * <p>
	 * @param context - context of the application
	 */
	public void storeMyComment(Context context) {
		try {
			FileOutputStream fos = context.openFileOutput(MYCOMMENTSFILE, 0);
			fos.write((gson.toJson(ProjectApplication.getInstance().getUser().getMyComments().getCommentList()) + "\n").getBytes());
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets comments stored locally.
	 * <p>
	 * Gets comments created by user from user's local device.
	 * User
	 * <p>
	 * @param context - context of the application
	 * @return arraylist of the comments that were stored locally
	 * User
	 */
	public ArrayList<CommentModel> retrieveMyComments(Context context) {
		ArrayList<CommentModel> myCommentsArray = new ArrayList<CommentModel>();
		try {
			System.out.println("Open file");
			FileInputStream fis = context.openFileInput(MYCOMMENTSFILE);
			BufferedReader in = new BufferedReader(new InputStreamReader(fis));
			
			Type type = new TypeToken<ArrayList<CommentModel>>(){}.getType();
			myCommentsArray = gson.fromJson(in, type);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return myCommentsArray;
	}
	
	/**
	 * Stores comment favorited by user locally.
	 * <p>
	 * Stores a CommentModel favorited by user on the user's local device. 
	 * User
	 * <p>
	 * @param context - context of the application
	 */
	public void storeFavorite(CommentModel favoritedComment, Context context) {
		//Pull previously favorited comments -TH
		ArrayList<CommentModel> oldFavs = new ArrayList<CommentModel>();
		oldFavs = retrieveFavorites(context);
		
		//Get replies to favorited comment -TH
		CommentListModel repliesToFav = favoritedComment.pullReplies();
		if (repliesToFav == null) {
			repliesToFav = new CommentListModel();
		}
		
		//Initialize master list -TH
		ArrayList<CommentModel> favoritesAndReplies = new ArrayList<CommentModel>();
		
		//Add all comments to master list -TH
		favoritesAndReplies = repliesToFav.getCommentList();
		favoritesAndReplies.add(0, favoritedComment);
		for(int i=0; i<oldFavs.size(); i++){
			favoritesAndReplies.add(oldFavs.get(i));
		}
		
		try {
			FileOutputStream fos = context.openFileOutput(FAVORITESFILE, 0);
			fos.write((gson.toJson(favoritesAndReplies) + "\n").getBytes());
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets favorites stored locally.
	 * <p>
	 * Gets comments favorited by user from user's local device.
	 * User
	 * <p>
	 * @param context - context of the application
	 * @return arraylist of the comments that were stored locally
	 */
	public ArrayList<CommentModel> retrieveFavorites(Context context) {
		ArrayList<CommentModel> favoritesArray = new ArrayList<CommentModel>();
		try {
			System.out.println("Open file");
			FileInputStream fis = context.openFileInput(FAVORITESFILE);
			BufferedReader in = new BufferedReader(new InputStreamReader(fis));
			
			Type type = new TypeToken<ArrayList<CommentModel>>(){}.getType();
			favoritesArray = gson.fromJson(in, type);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return favoritesArray;
	}
	
	
	/**
	 * Not fully implemented - added for testing for this release only. 
	 * @return	CommentModel containing the current location
	 */
	public CommentModel getLatest() {
	// added for the test -KW
		String loc = "Location Intialization";
		Location currentLocation;
		Location myLocation;
		currentLocation = new Location(loc);
		myLocation = new Location(loc);

		CommentModel comment = new CommentModel("comment", currentLocation, "username");
		return comment;
	}
}
