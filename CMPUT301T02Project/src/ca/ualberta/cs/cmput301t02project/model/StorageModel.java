package ca.ualberta.cs.cmput301t02project.model;

import java.lang.reflect.Type;
import java.util.Date;

import com.google.gson.Gson;

public class StorageModel {

	private static Gson gson = new Gson();

	public CommentModel storeComment(CommentModel comment) {

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


		return comment1;

	}
}
