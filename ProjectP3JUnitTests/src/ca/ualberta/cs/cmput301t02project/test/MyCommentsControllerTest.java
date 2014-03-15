package ca.ualberta.cs.cmput301t02project.test;

import junit.framework.TestCase;
import ca.ualberta.cs.cmput301t02project.controller.MyCommentsController;
import ca.ualberta.cs.cmput301t02project.model.CommentListModel;
import ca.ualberta.cs.cmput301t02project.model.CommentModel;

public class MyCommentsControllerTest extends TestCase {

	MyCommentsController controller;
	CommentListModel model;
	
	public MyCommentsControllerTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		model = new CommentListModel();
		controller = new MyCommentsController(model);
	}
	
	public void testAddNewComment() {
		CommentModel comment = new CommentModel("original text", null ,"username");
		controller.addNewComment(comment);
		assertTrue(model.getCommentList().contains(comment));
	}

	public void testChangeText() {
		CommentModel comment = new CommentModel("original text", null ,"username");
		controller.changeText(comment, "new text");
		assertEquals("new text", comment.getText());
	}

}
