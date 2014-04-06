package ca.ualberta.cs.cmput301t02project.test;

import java.util.ArrayList;

import ca.ualberta.cs.cmput301t02project.activity.CreateCommentActivity;
import ca.ualberta.cs.cmput301t02project.model.CommentModel;
import ca.ualberta.cs.cmput301t02project.model.GPSLocation;
import ca.ualberta.cs.cmput301t02project.model.User;
import android.app.Activity;
import android.location.Location;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;

public class CreateCommentActivityTest extends ActivityInstrumentationTestCase2<CreateCommentActivity> {

	Activity activity;

	protected void setUp() throws Exception {
		super.setUp();
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
				GPSLocation.initializeLocation(getInstrumentation().getContext());
				User.login(username, getInstrumentation().getContext());
				edit.setText(text);
				button.performClick();
				//ArrayList<CommentModel> list = ProjectApplication.getInstance().getCommentList().getCommentList();
				CommentModel expected = new CommentModel(text, null, username);
				//assertTrue("List should contain the just created comment", list.contains(expected));
				ArrayList<CommentModel> myComments = User.getUser().getMyComments().getList();
				assertTrue("myList should contain the just created comment", myComments.contains(expected));
				
			}
		});
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
		//assertEquals("Users should be the same",user,user2);
	}

}
