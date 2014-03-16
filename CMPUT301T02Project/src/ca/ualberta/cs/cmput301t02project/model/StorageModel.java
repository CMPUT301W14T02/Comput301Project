package ca.ualberta.cs.cmput301t02project.model;

import java.util.Date;

import android.location.Location;

import com.google.gson.Gson;

public class StorageModel {

	private static Gson gson = new Gson();

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
	
	public void cacheComment(CommentModel comment) {
		
	}
	
	// added for the test -KW
	public CommentModel getLatest()
	{
		String loc = "Location Intialization";
		Location currentLocation;
		Location myLocation;
		currentLocation = new Location(loc);
		myLocation = new Location(loc);

		CommentModel comment = new CommentModel("comment", currentLocation, "username");
		return comment;
	}
}
