package ca.ualberta.cs.cmput301t02project.test;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;
import ca.ualberta.cs.cmput301t02project.ProjectApplication;
import ca.ualberta.cs.cmput301t02project.User;
import ca.ualberta.cs.cmput301t02project.activity.EditCommentActivity;
import ca.ualberta.cs.cmput301t02project.model.CommentModel;

public class EditCommentActivityTest extends ActivityInstrumentationTestCase2<EditCommentActivity> {

	Activity activity;
	String username = "username";

	public EditCommentActivityTest() {
		super(EditCommentActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		ProjectApplication.getInstance().setCurrentComment(new CommentModel("the original comment", null, username), activity.getApplicationContext());
		activity = getActivity();
	}

	public void testEditComment() throws Throwable {
		runTestOnUiThread(new Runnable() {

			@Override
			public void run() {
				EditText edit = (EditText) activity.findViewById(ca.ualberta.cs.cmput301t02project.R.id.edit_text);
				Button button = (Button) activity.findViewById(ca.ualberta.cs.cmput301t02project.R.id.edit_post);
				assertNotNull(edit);
				assertNotNull(button);
				String text = "edited text";
				edit.setText(text);
				button.performClick();
				CommentModel expectedComment = new CommentModel(text, null, username);
				assertEquals("The comment should be equal to the edited one",
						ProjectApplication.getInstance().getCurrentComment(), expectedComment);
			}
		});

	}
	
	/* test to see if user is being pushed to server after update */
	public void testPushUser() {
		User user = new User("user");
		ProjectApplication pa = ProjectApplication.getInstance().getInstance();
		//pa.pushUser(user);
		//User user2 = pa.getPushedUser("user");
		//assertEquals("Users should be the same",user,user2);
	}
}
