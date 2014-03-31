package ca.ualberta.cs.cmput301t02project.controller;

import android.content.Context;
import ca.ualberta.cs.cmput301t02project.model.CommentModel;
import ca.ualberta.cs.cmput301t02project.model.Server;

public class CommentController {

	private CommentModel model;
	private Context context;
	
	public CommentController(CommentModel comment, Context context) {
		this.model = comment;
		this.context = context;
	}
	
	private void update() {
		Server server = new Server(context);
		server.post(model);
	}
	
	public void incrementRating() {
		model.incrementRating();
		update();
	}
}
