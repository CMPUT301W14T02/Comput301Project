package ca.ualberta.cs.cmput301t02project.test;

import junit.framework.TestCase;
import ca.ualberta.cs.cmput301t02project.controller.FavoritesController;
import ca.ualberta.cs.cmput301t02project.model.CommentListModel;
import ca.ualberta.cs.cmput301t02project.model.CommentModel;

public class FavoritesControllerTest extends TestCase {
	
	FavoritesController controller;
	CommentListModel model;
	
	public FavoritesControllerTest(String name) {
		super(name);
	}
	
	protected void setUp() throws Exception {
		super.setUp();
		model = new CommentListModel();
		controller = new FavoritesController(model);
	}
	
	// Use Case 11 - KW 
	public void favoriteCommentTest() {
		CommentModel comment = new CommentModel("original text", null ,"username");
		controller.favoriteComment(comment);
		assertTrue(model.getCommentList().contains(comment));
	}
		
		
}
	
	
	
	
	


