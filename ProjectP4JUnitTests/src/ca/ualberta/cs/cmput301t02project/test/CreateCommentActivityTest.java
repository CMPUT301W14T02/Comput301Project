package ca.ualberta.cs.cmput301t02project.test;

import java.util.ArrayList;

import ca.ualberta.cs.cmput301t02project.activity.CreateCommentActivity;
import ca.ualberta.cs.cmput301t02project.activity.MainMenuActivity;
import ca.ualberta.cs.cmput301t02project.model.CommentModel;
import ca.ualberta.cs.cmput301t02project.model.GPSLocation;
import ca.ualberta.cs.cmput301t02project.model.TopLevelCommentList;
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
	String text = "the comment";

	protected void setUp() throws Exception {
		super.setUp();
		User.login(username, getInstrumentation().getTargetContext());
		activity = getActivity();
	}

	public void initializeComment() {
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
		childActivity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				edit.setText(text);
				button.performClick();
			}
		});
		getInstrumentation().waitForIdleSync();
	}
	
	public void initializeCustomLocationComment(final int latInt, final int longInt) {
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
		final EditText latitude = (EditText) childActivity.findViewById(ca.ualberta.cs.cmput301t02project.R.id.latitude_box);
		final EditText longitude = (EditText) childActivity.findViewById(ca.ualberta.cs.cmput301t02project.R.id.longitude_box);
		final EditText edit = (EditText) childActivity.findViewById(ca.ualberta.cs.cmput301t02project.R.id.create_text);
		final Button button = (Button) childActivity.findViewById(ca.ualberta.cs.cmput301t02project.R.id.create_post);
		assertNotNull(edit);
		assertNotNull(button);
		assertNotNull(latitude);
		assertNotNull(longitude);
		childActivity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				latitude.setText(latInt+"");
				longitude.setText(longInt+"");
				edit.setText("customLocationTest");
				button.performClick();
			}
		});
		getInstrumentation().waitForIdleSync();
	}
	

	public CreateCommentActivityTest() {
		super(MainMenuActivity.class);
		// TODO Auto-generated constructor stub
	}

	/* Test for use case 3 */
	public void testCreateTopLevelComment() throws Exception {
		initializeComment();
		Thread.sleep(2000);
		ArrayList<CommentModel> list = User.getUser().getMyComments().getList();
		boolean hasText = false;
		boolean hasUsername = false;
		boolean isTopLevel = false;
		for (int i=0; i<list.size(); i++){
			if (list.get(i).toString().contains(text)) {
				if (list.get(i).toString().contains(username)) {
					if (list.get(i).isTopLevelComment()) {
						hasText = true;
						hasUsername = true;
						isTopLevel = true;
					}
				}
			}	
		}
		
		assertTrue("list comment should have same text", hasText);
		assertTrue("list comment should have same username", hasUsername);
		assertTrue("comment should be marked as top level", isTopLevel);
	}
	
	/* Test for use case 17 */
	public void testGeoLocationOfComment() throws Exception {
		initializeComment();
		Thread.sleep(2000);
		ArrayList<CommentModel> list = User.getUser().getMyComments().getList();
		int len = list.size();
		assertEquals("comment lat should be equal to gps lat", GPSLocation.getInstance().getLocation().getLatitude(), list.get(len-1).getLocation().getLatitude());
		assertEquals("comment long should be equal to gps long", GPSLocation.getInstance().getLocation().getLongitude(), list.get(len-1).getLocation().getLongitude());
		
	}
	
	/* Test for use case 14 */
	public void testShareComment () throws Exception {
		int size1 = TopLevelCommentList.getInstance(activity.getApplicationContext()).getList().size();
		initializeComment();
		Thread.sleep(2000);
		int size2 = TopLevelCommentList.getInstance(activity.getApplicationContext()).getList().size();
		size1++;
        assertEquals("Size of top level comment list should be increased by 1 when new comment is added", size1, size2);
    }
	
	public void testCreateCustomLocationComment() throws Exception {
		int latInt = 57;
		int longInt = 113;
		initializeCustomLocationComment(latInt, longInt);
		Thread.sleep(2000);
		ArrayList<CommentModel> list = User.getUser().getMyComments().getList();
		boolean hasText = false;
		boolean sameLong = false;
		boolean sameLat = false;
		for (int i=0; i<list.size(); i++){
			if (list.get(i).toString().contains("customLocationTest")) {
				int comLat = (int) list.get(i).getLocation().getLatitude();
				int comLong = (int) list.get(i).getLocation().getLongitude();
				if (comLat == latInt) {
					if (comLong == longInt) {
						hasText = true;
						sameLong = true;
						sameLat = true;
					}
				}
			}	
		}
		assertTrue("list comment should have same text", hasText);
		assertTrue("list comment should have custom lat", sameLat);
		assertTrue("list comment should have custom long", sameLong);
	}
	
	/* test to see if user is being pushed to server after update */
	public void testPushUser() throws Exception {
		int size1 = User.getUser().getMyCommentIds().size();
		initializeComment();
		Thread.sleep(2000);
		User.login(username, activity.getApplicationContext());
		int size2 = User.getUser().getMyCommentIds().size();
		size1++;
		assertEquals("Number of comments by user pulled from the server after adding comment should be increased by 1",size1, size2);
	}

}
