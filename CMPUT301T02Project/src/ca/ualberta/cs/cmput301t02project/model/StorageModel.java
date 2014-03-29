package ca.ualberta.cs.cmput301t02project.model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;

import android.content.Context;
import android.location.Location;

import ca.ualberta.cs.cmput301t02project.ProjectApplication;

import com.google.gson.Gson;

/**
 * Deals with storing data on the server and caching data locally.
 */
public class StorageModel {

	private static Gson gson = new Gson();
	private ArrayList<CommentModel> cacheList = new ArrayList<CommentModel>();
	
	/**
	 * Caches a comment locally.
	 * <p>
	 * Caches a specified CommentModel on the user's local device. 
	 * Used when a comment is read or favorited in
	 * BrowseTopLevelCommentsActivity and BrowseReplyCommentsActivity.
	 * @param comment	CommentModel to cache
	 */
	public void cacheComment(Context context, String FILENAME) {
		try {
			FileOutputStream fos = context.openFileOutput(FILENAME, 0);
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
	
	public ArrayList<CommentModel> retrieveCachedComments(Context context, String FILENAME) {
		ArrayList<CommentModel> myCommentsArray = new ArrayList<CommentModel>();
		try {
			System.out.println("Open file");
			FileInputStream fis = context.openFileInput(FILENAME);
			BufferedReader in = new BufferedReader(new InputStreamReader(fis));
			String line = in.readLine();
			while (line != null) {
				myCommentsArray.add(CommentModel.fromJSON(line));
				line = in.readLine();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return myCommentsArray;
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
	
	/**
	 * Returns a list of the cached comments.
	 * @return	ArrayList of cached CommentModels
	 */
	public ArrayList<CommentModel> getCacheList() {
		return cacheList;
	}
}
