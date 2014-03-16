package ca.ualberta.cs.cmput301t02project.test;

import java.util.ArrayList;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;
import ca.ualberta.cs.cmput301t02project.ProjectApplication;
import ca.ualberta.cs.cmput301t02project.activity.CreateCommentActivity;
import ca.ualberta.cs.cmput301t02project.model.CommentModel;

public class CreateCommentActivityTest extends ActivityInstrumentationTestCase2<CreateCommentActivity> {

	Activity activity;

	protected void setUp() throws Exception {
		super.setUp();
		activity = getActivity();
	}

	public CreateCommentActivityTest() {
		super(CreateCommentActivity.class);
		// TODO Auto-generated constructor stub
	}

	public void testCreateTopLevelComment() throws Throwable {
		runTestOnUiThread(new Runnable() {

			@Override
			public void run() {
				EditText edit = (EditText) activity.findViewById(ca.ualberta.cs.cmput301t02project.R.id.create_text);
				Button button = (Button) activity.findViewById(ca.ualberta.cs.cmput301t02project.R.id.create_post);
				assertNotNull(edit);
				assertNotNull(button);
				String text = "the comment";
				String username = "default";
				ProjectApplication.setName(username);
				edit.setText(text);
				button.performClick();
				ArrayList<CommentModel> list = ProjectApplication.getCommentList().getCommentList();
				CommentModel expected = new CommentModel(text, null, username);
				assertTrue("List should contain the just created comment", list.contains(expected));
				ArrayList<CommentModel> myComments = ProjectApplication.getUser().getMyComments().getCommentList();
				assertTrue("myList should contain the just created comment", myComments.contains(expected));
				
			}
		});
	}
	
	/* Test for use case 14 */
	public void testShareComment() {
		assertTrue(false);
	}
	
	/* test to see if user is being pushed to server after update */
	public void testPushUser() {
		assertTrue(false);
	}

}
