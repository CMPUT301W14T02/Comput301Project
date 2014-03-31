package ca.ualberta.cs.cmput301t02project.controller;

import ca.ualberta.cs.cmput301t02project.model.CommentModel;
import ca.ualberta.cs.cmput301t02project.model.Server;

public class CommentController {

	private Server server = new Server();
	private CommentModel model;
	
	public CommentController(CommentModel comment) {
		this.model = comment;
	}
	
	private void update() {
		server.post(model);
	}
	
	public void incrementRating() {
		model.incrementRating();
		update();
	}
}
