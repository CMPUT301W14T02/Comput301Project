package ca.ualberta.cs.cmput301t02project.test;

import junit.framework.TestCase;
import ca.ualberta.cs.cmput301t02project.controller.CommentListController;
import ca.ualberta.cs.cmput301t02project.model.CommentListModel;
import ca.ualberta.cs.cmput301t02project.model.CommentModel;

public class CommentListControllerTest extends TestCase {

	CommentListController controller;
	CommentListModel model;

	public CommentListControllerTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		model = new CommentListModel();
		controller = new CommentListController(model);

	}

	public void testAddNewComment() {
		CommentModel returned = controller.addNewComment("the comment", null, "username");
		CommentModel expected = new CommentModel("the comment", null, "username");
		assertTrue(model.getCommentList().contains(expected));
		assertEquals(expected, returned);
	}

}