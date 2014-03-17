package ca.ualberta.cs.cmput301t02project.model;

import java.util.ArrayList;
import java.util.Date;

import android.location.Location;

import com.google.gson.Gson;

/**
 * Deals with storing data on the server and caching data locally.
 */
public class StorageModel {

	private static Gson gson = new Gson();
	private ArrayList<CommentModel> cacheList = new ArrayList<CommentModel>();

	/**
	 * Push a comment online.
	 * <p>
	 * Converts a specified CommentModel to JSON for for storing on the web. 
	 * Pushes the specified CommentModel to the web.
	 * Used when a comment is created in 
	 * CreateCommentActivity.
	 * @param comment	CommentModel to be pushed
	 */
	public void pushComment(CommentModel comment) {

		/* Some test code for when we work on this method */
		String text = comment.getText();
		String username = comment.getUsername();
		int rating = comment.getRating();
		Date date = comment.getDate();
		/* Note: Can convert type Location to Json *
		 * Can we remove LocationModel? -TH
		 */
		String json = gson.toJson(text);
		String json1 = gson.toJson(username);
		String json2 = gson.toJson(rating);
		String json3 = gson.toJson(date);

		String text1 = gson.fromJson(json, String.class);
		String username1 = gson.fromJson(json1, String.class);
		Integer rating1 = gson.fromJson(json2, Integer.class);
		Date date1 = gson.fromJson(json3, Date.class);

		CommentModel comment1 = new CommentModel(text1, comment.getLocation(), username1);
		comment1.setDate(date1);
		comment1.setRating(rating1);
		comment1.setReplies(comment.getReplies());

	}
	
	/**
	 * Caches a comment locally.
	 * <p>
	 * Caches a specified CommentModel on the user's local device. 
	 * Used when a comment is read or favorited in
	 * BrowseTopLevelCommentsActivity and BrowseReplyCommentsActivity.
	 * @param comment	CommentModel to cache
	 */
	public void cacheComment(CommentModel comment) {
		cacheList.add(comment);
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
