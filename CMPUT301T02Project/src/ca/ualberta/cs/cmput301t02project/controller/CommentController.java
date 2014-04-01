package ca.ualberta.cs.cmput301t02project.controller;

import android.content.Context;
import android.graphics.Bitmap;
import android.location.Location;
import ca.ualberta.cs.cmput301t02project.model.CommentModel;
import ca.ualberta.cs.cmput301t02project.model.FavoritesListModel;
import ca.ualberta.cs.cmput301t02project.model.MyCommentsListModel;
import ca.ualberta.cs.cmput301t02project.model.Server;
import ca.ualberta.cs.cmput301t02project.model.User;

public class CommentController {

	private CommentModel model;
	private Context context;
	
	public CommentController(String commentId, Context context) {
		Server server = new Server(context);
		CommentModel comment = server.pull(commentId);
		this.model = comment;
		this.context = context;
	}
	
	private void update() {
		Server server = new Server(context);
		server.post(model);
		User user = User.getUser();
		FavoritesListModel favorites = user.getFavorites();
		MyCommentsListModel myComments = user.getMyComments();
		favorites.update(model.getId(), model);
		myComments.update(model.getId(), model);
	}
	
	public void incrementRating() {
		model.incrementRating();
		update();
	}
	
	public void edit(String newText) {
		model.setText(newText);
		update();
	}
	
	public String getText() {
		return model.getText();
	}
	
	public void addReply(String text, Bitmap picture, Location location, String username) {
		CommentModel comment = new CommentModel(text, picture, location, username);
		Server server = new Server(context);
		server.post(comment);
		this.model.addChildId(comment.getId());
		update();
	}
	
	public CommentModel getComment() {
		return model;
	}
}
