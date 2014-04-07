package ca.ualberta.cs.cmput301t02project.controller;

import android.content.Context;
import android.graphics.Bitmap;
import android.location.Location;
import ca.ualberta.cs.cmput301t02project.model.CommentModel;
import ca.ualberta.cs.cmput301t02project.model.Server;
import ca.ualberta.cs.cmput301t02project.model.User;

/**
 * This class provides a way to manipulate a CommentModel.
 * The CommentModel held by it is retrieved from the server,
 * through the Id provided as a parameter to the constructor.
 * It includes methods for: 
 * scaling a Bitmap image,
 * pushing the CommentModel to the server, 
 * retrieving the CommentModel from the server based on its ID,
 * updating the rating for the CommentModel,
 * updating the edited version of the CommentModel on the server, 
 * and adding a reply to the CommentModel.
 */
public class CommentController {

	private CommentModel model;
	private Context context;
	
	// appropriate size for scaling a Bitmap image -SB
	private static final int MAX_BITMAP_DIMENSIONS = 50;
	
	/**
	 * Constructor that pulls a comment from the server.
	 * <p>
	 * The comment to pull is based on its id number specified in the parameters.
	 * <p>
	 * @param commentId	the Id of the comment
	 * @param context	A context is needed to deal with the server
	 */
	public CommentController(String commentId, Context context) {
		Server server = new Server(context);
		CommentModel comment = server.pull(commentId);
		this.model = comment;
		this.context = context;
	}
	
	/**
	 * Scales a picture if it is too big.
	 * <p>
	 * Adapted from
	 * * https://github.com/zjullion/PicPosterComplete/blob/master/src/ca/ualberta/cs/picposter/network/ElasticSearchOperations.java
	 * retrieved April 3, 2014 *
	 * <p>
	 * @param picture	the Bitmap image to scale
	 */
	static void scalePicture(Bitmap picture) {
		
		// if the picture exists and it's length or hight is larger than MAX_BITMAP_DIMENSIONS, scale it -SB
		if(picture != null) {
			if (picture.getWidth() > MAX_BITMAP_DIMENSIONS || picture.getHeight() > MAX_BITMAP_DIMENSIONS) {
				double scalingFactor = picture.getWidth() * 1.0 / MAX_BITMAP_DIMENSIONS;
				
				if (picture.getHeight() > picture.getWidth()){
					scalingFactor = picture.getHeight() * 1.0 / MAX_BITMAP_DIMENSIONS;
				}
	
				int newWidth = (int)Math.round(picture.getWidth() / scalingFactor);
				int newHeight = (int)Math.round(picture.getHeight() / scalingFactor);
	
				picture = Bitmap.createScaledBitmap(picture, newWidth, newHeight, false);
			}
		}
	}
	
	/**
	 * Reposts the comment managed by this controller to the server.
	 */
	private void update() {
		Server server = new Server(context);
		server.post(model);
	}
	
	/**
	 * Increments the rating of the comment held by this controller.
	 */
	public void incrementRating() {
		model.incrementRating();
		update();
	}
	
	/**
	 * Edits the comment held by this controller.
	 * @param newText	The new text
	 * @param latitude	The new latitude
	 * @param longitude	The new longitude
	 */
	public void edit(String newText, Bitmap picture, double latitude, double longitude) {
		
		// update text -SB
		model.setText(newText);
		
		// only update the picture if a new one is taken -SB
		if(picture != null){
			model.setPicture(picture);
		}
		
		// update location -SB
		model.getLocation().setLatitude(latitude);
		model.getLocation().setLongitude(longitude);
		
		// store updated model -SB
		User.getUser().getMyComments().put(model.getId(), model);
		
		// update the changed model on the server -SB
		update();
	}
	
	/**
	 * Gets the text of the comment held by this controller.
	 * @return The comment's text
	 */
	public String getText() {
		return model.getText();
	}
	
	/**
	 * Adds a reply to the comment held by this controller.
	 * <p>
	 * The Bitmap picture is scaled before it is stored. 
	 * The reply is created using the method parameters. 
	 * The newly created reply is stored on the server and in the User class.
	 * <p>
	 * @param text		The text of the comment
	 * @param picture	The picture of the comment
	 * @param location	The location of the comment
	 * @param user		The author of the comment
	 */
	public void addReply(String text, Bitmap picture, Location location, User user) {
		
		// scale the picture -SB
		scalePicture(picture);
		
		// create the new reply comment using the parameters -SB
		CommentModel comment = new CommentModel(text, picture, location, user.getName());
		comment.setTopLevelComment(false);
		
		// push the comment to the server -SB
		Server server = new Server(context);
		server.post(comment);
		
		// add the comment to the user's list of comments -SB
		user.addMyComment(comment, context);
		this.model.addChildId(comment.getId());
		update();
	}
	
	/**
	 * Returns the comment held by this controller.
	 * @return	The comment
	 */
	public CommentModel getComment() {
		return model;
	}
}
