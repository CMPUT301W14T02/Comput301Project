package ca.ualberta.cs.cmput301t02project.controller;

import android.content.Context;
import android.graphics.Bitmap;
import android.location.Location;
import ca.ualberta.cs.cmput301t02project.model.CommentModel;
import ca.ualberta.cs.cmput301t02project.model.Server;
import ca.ualberta.cs.cmput301t02project.model.User;

/**
 * This class provides a way to manipulate a CommentModel
 * The CommentModel held by it is retrieved from the server,
 * through the Id provided as a parameter to the constructor
 *
 */
public class CommentController {

	private CommentModel model;
	private Context context;
	
	static final int MAX_BITMAP_DIMENSIONS = 50;
	
	/**
	 * Scales a picture if it is too big
	 * Adapted from
	 * https://github.com/zjullion/PicPosterComplete/blob/master/src/ca/ualberta/cs/picposter/network/ElasticSearchOperations.java
	 * @param picture The picture
	 */
	static void scalePicture(Bitmap picture) {
		if(picture != null) {
			if (picture.getWidth() > MAX_BITMAP_DIMENSIONS || picture.getHeight() > MAX_BITMAP_DIMENSIONS) {
				double scalingFactor = picture.getWidth() * 1.0 / MAX_BITMAP_DIMENSIONS;
				if (picture.getHeight() > picture.getWidth())
				scalingFactor = picture.getHeight() * 1.0 / MAX_BITMAP_DIMENSIONS;
	
				int newWidth = (int)Math.round(picture.getWidth() / scalingFactor);
				int newHeight = (int)Math.round(picture.getHeight() / scalingFactor);
	
				picture = Bitmap.createScaledBitmap(picture, newWidth, newHeight, false);
			}
		}
	}
	
	/**
	 * Constructor
	 * @param commentId the Id of the comment
	 * @param context A context is needed to deal with the server
	 */
	public CommentController(String commentId, Context context) {
		Server server = new Server(context);
		CommentModel comment = server.pull(commentId);
		this.model = comment;
		this.context = context;
	}
	
	/**
	 * Reposts the comment managed by this controller to the server
	 */
	private void update() {
		Server server = new Server(context);
		server.post(model);
	}
	
	/**
	 * Increments the rating of the comment held by this controller
	 */
	public void incrementRating() {
		model.incrementRating();
		update();
	}
	
	/**
	 * Edits the comment held by this controller
	 * @param newText The new text
	 * @param latitude The new latitude
	 * @param longitude The new longitude
	 */
	public void edit(String newText, double latitude, double longitude) {
		model.setText(newText);
		model.getLocation().setLatitude(latitude);
		model.getLocation().setLongitude(longitude);
		User.getUser().getMyComments().put(model.getId(), model);
		update();
	}
	
	/**
	 * Gets the text of the comment held by this controller
	 * @return The text
	 */
	public String getText() {
		return model.getText();
	}
	
	/**
	 * Adds a reply to the comment held by this controller
	 * @param text The text of the comment
	 * @param picture The picture of the comment
	 * @param location The location of the comment
	 * @param user The author of the comment
	 */
	public void addReply(String text, Bitmap picture, Location location, User user) {
		scalePicture(picture);
		CommentModel comment = new CommentModel(text, picture, location, user.getName());
		comment.setTopLevelComment(false);
		Server server = new Server(context);
		server.post(comment);
		user.addMyComment(comment, context);
		this.model.addChildId(comment.getId());
		update();
	}
	
	/**
	 * Gets the comment held by this controller
	 * @return The comment
	 */
	public CommentModel getComment() {
		return model;
	}
}
