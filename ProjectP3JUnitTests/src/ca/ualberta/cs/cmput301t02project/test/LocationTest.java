package ca.ualberta.cs.cmput301t02project.test;

import java.util.ArrayList;

import android.app.Activity;
import android.location.Location;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;
import ca.ualberta.cs.cmput301t02project.ProjectApplication;
import ca.ualberta.cs.cmput301t02project.activity.CreateCommentActivity;
import ca.ualberta.cs.cmput301t02project.model.CommentModel;

public class LocationTest extends ActivityInstrumentationTestCase2<CreateCommentActivity> {

	Activity activity;
	Location location;
	double latitude = 27.123;
	double longitude = 35.9;
	
	public LocationTest() {
		super(CreateCommentActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		activity = getActivity();
		location = new Location("mock");
		location.setLatitude(latitude);
		location.setLongitude(longitude);
		ProjectApplication.setCurrentLocation(location);
	}
	
	public void testDefaultLocation() throws Throwable{
		
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
				CommentModel expected = new CommentModel(text, location, username);
				assertTrue("List should contain the just created comment", list.contains(expected));
			}
		});
	}

}
