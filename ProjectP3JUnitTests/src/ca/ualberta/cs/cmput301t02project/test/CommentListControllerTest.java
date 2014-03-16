package ca.ualberta.cs.cmput301t02project.test;

import junit.framework.TestCase;
import android.graphics.Bitmap;
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
	
	public void testAddNewCommentWithPicture() {
		Bitmap picture = Bitmap.createBitmap(10, 10, Bitmap.Config.ARGB_8888);
		assertNotNull(picture);
		CommentModel returned = controller.addNewComment("the comment", picture, "username");
		CommentModel expected = new CommentModel("the comment", picture, null, "username");
		assertTrue(model.getCommentList().contains(expected));
		assertEquals(expected, returned);
	}

}