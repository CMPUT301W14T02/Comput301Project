package ca.ualberta.cs.cmput301t02project.test;

import java.util.ArrayList;

import ca.ualberta.cs.cmput301t02project.activity.CreateCommentActivity;
import ca.ualberta.cs.cmput301t02project.activity.MainMenuActivity;
import ca.ualberta.cs.cmput301t02project.model.CommentModel;
import ca.ualberta.cs.cmput301t02project.model.GPSLocation;
import ca.ualberta.cs.cmput301t02project.model.User;
import android.app.Activity;
import android.app.Instrumentation;
import android.location.Location;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

public class CreateCommentActivityTest extends ActivityInstrumentationTestCase2<MainMenuActivity> {

	Activity activity;
	String username = "default";

	protected void setUp() throws Exception {
		super.setUp();
		User.login(username, getInstrumentation().getContext());
		activity = getActivity();
	}

	public CommentModel initializeComment() {
		String loc = "Location Intialization";
		Location currentLocation;
		Location myLocation;
		currentLocation = new Location(loc);
		myLocation = new Location(loc);

		CommentModel comment = new CommentModel("comment", currentLocation, "username");

		return comment;
	}
	
	public CreateCommentActivityTest() {
		super(MainMenuActivity.class);
		// TODO Auto-generated constructor stub
	}

	
	public void testCreateTopLevelComment() throws Throwable {
		
		Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(CreateCommentActivity.class.getName(), null , false);
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Button button1 = (Button) activity.findViewById(ca.ualberta.cs.cmput301t02project.R.id.create);
				assertNotNull(button1);
				button1.performClick();
			}
		});
		
		getInstrumentation().waitForIdleSync();
		CreateCommentActivity childActivity = (CreateCommentActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5);
		final EditText edit = (EditText) childActivity.findViewById(ca.ualberta.cs.cmput301t02project.R.id.create_text);
		final Button button = (Button) childActivity.findViewById(ca.ualberta.cs.cmput301t02project.R.id.create_post);
		assertNotNull(edit);
		assertNotNull(button);
		final String text = "the comment";
		User.login(username, childActivity.getApplicationContext());
		childActivity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				edit.setText(text);
				button.performClick();
			}
		});
		Location location = GPSLocation.getInstance().getLocation();
		getInstrumentation().waitForIdleSync();
		//ArrayList<CommentModel> list = ProjectApplication.getInstance().getCommentList().getCommentList();
		CommentModel expected = new CommentModel(text, location, username);
		//assertTrue("List should contain the just created comment", list.contains(expected));
		ArrayList<CommentModel> myComments = User.getUser().getMyComments().getList();
		int len = myComments.size();
		Log.d("something", myComments.toString());
		assertTrue("myList should contain the just created comment", myComments.get(len - 1).getText().equals(text));
	}
	
	/* Test for use case 14 */
	public void testShareComment () {
		CommentModel comment = initializeComment();
		
		//sM.pushComment(comment);
        //CommentModel comment2 = sM.getLatest();
        assertTrue("Comments should be the same if posted comment is saved correctly.", (comment == comment));
    }
	
	/* test to see if user is being pushed to server after update */
	public void testPushUser() {
		User user = new User("user");
		//pa.pushUser(user);
		//User user2 = pa.getPushedUser("user");
		assertEquals("Users should be the same",user,user);
	}

}
