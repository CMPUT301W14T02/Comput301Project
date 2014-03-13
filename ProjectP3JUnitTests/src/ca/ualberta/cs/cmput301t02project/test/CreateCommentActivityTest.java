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
	
	public void testCreateComment() throws Throwable{
		runTestOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				EditText edit = (EditText)activity.findViewById(ca.ualberta.cs.cmput301t02project.R.id.create_text);
				Button button = (Button)activity.findViewById(ca.ualberta.cs.cmput301t02project.R.id.create_post);
				assertNotNull(edit);
				assertNotNull(button);
				String text = "the commnent";
				String username ="default";
				edit.setText(text);
				button.performClick();
				ArrayList<CommentModel> list = ProjectApplication.getCommentList().getCommentList();
				assertTrue("List should contain the just created comment", list.contains(new CommentModel(text, null, null, username)));
			}
		});
	}

}
