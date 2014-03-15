package ca.ualberta.cs.cmput301t02project.test;

import java.util.ArrayList;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;
import ca.ualberta.cs.cmput301t02project.ProjectApplication;
import ca.ualberta.cs.cmput301t02project.activity.CreateCommentActivity;
import ca.ualberta.cs.cmput301t02project.model.CommentListModel;
import ca.ualberta.cs.cmput301t02project.model.CommentModel;

public class CreateReplyTest extends ActivityInstrumentationTestCase2<CreateCommentActivity> {

	public CreateReplyTest() {
		super(CreateCommentActivity.class);
		// TODO Auto-generated constructor stub
	}
	Activity activity;

	protected void setUp() throws Exception {
		super.setUp();
		CommentModel comment = new CommentModel("text", null, null, "user");
		ProjectApplication.setCurrentComment(comment);
		activity = getActivity();
	}
	
	public void testCreateReply() throws Throwable {
		runTestOnUiThread(new Runnable() {

			@Override
			public void run() {
				//CommentModel comment = new CommentModel("text", null, null, "user");
				String username = "default";
				ProjectApplication.setName(username);
				//ProjectApplication.setCurrentComment(comment);
				ProjectApplication.InitializeLocationManager(activity.getApplicationContext());
				
				EditText edit = (EditText) activity.findViewById(ca.ualberta.cs.cmput301t02project.R.id.create_text);
				Button button = (Button) activity.findViewById(ca.ualberta.cs.cmput301t02project.R.id.create_post);
				assertNotNull(edit);
				assertNotNull(button);
				String text = "the comment";
				edit.setText(text);
				button.performClick();
				CommentListModel m = ProjectApplication.getCurrentComment().getReplies();
				ArrayList<CommentModel> list = ProjectApplication.getCurrentComment().getReplies().getCommentList();
				assertTrue("List should contain the just created comment", list.contains(new CommentModel(text, null, username)));
			}
		});
	}
	
}