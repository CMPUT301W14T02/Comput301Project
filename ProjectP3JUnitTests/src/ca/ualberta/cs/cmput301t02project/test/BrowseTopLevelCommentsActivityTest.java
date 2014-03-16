package ca.ualberta.cs.cmput301t02project.test;

import ca.ualberta.cs.cmput301t02project.ProjectApplication;
import ca.ualberta.cs.cmput301t02project.R;
import ca.ualberta.cs.cmput301t02project.activity.BrowseTopLevelCommentsActivity;
import ca.ualberta.cs.cmput301t02project.model.CommentListModel;
import ca.ualberta.cs.cmput301t02project.model.CommentModel;
import android.location.Location;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ViewAsserts;
import android.widget.ListView;

public class BrowseTopLevelCommentsActivityTest extends ActivityInstrumentationTestCase2<BrowseTopLevelCommentsActivity> {

	public BrowseTopLevelCommentsActivityTest() {
		super(BrowseTopLevelCommentsActivity.class);
	}

	public CommentModel initializeComment() {
		String loc = "Location Intialization";
		Location currentLocation;
		Location myLocation;
		currentLocation = new Location(loc);
		myLocation = new Location(loc);

		CommentModel comment = new CommentModel("comment", currentLocation, "username");

		ProjectApplication.setCurrentLocation(myLocation);

		return comment;
	}

	/* Test for use case 4 */
	public void testDisplayTopLevelComments() {
		CommentModel comment = initializeComment();
		CommentListModel comments = new CommentListModel();
		comments = ProjectApplication.getCommentList();
		comments.add(comment);

		ListView view = (ListView) getActivity().findViewById(R.id.commentListView);
		assertEquals("text should be displayed", comment.toString(), view.getAdapter().getItem(0).toString());

	}

	/* Test for use case 4 */
	public void testVisibleListView() {
		ListView view = (ListView) getActivity().findViewById(R.id.commentListView);
		BrowseTopLevelCommentsActivity activity = getActivity();
		ViewAsserts.assertOnScreen(activity.getWindow().getDecorView(), view);

	}

	/* Test for use case 21 */
	public void testDisplayUsername() {
		CommentModel comment = initializeComment();

		ListView view = (ListView) getActivity().findViewById(R.id.commentListView);
		assertTrue("username should be displayed", view.getAdapter().getItem(0).toString().contains(comment.getUsername()));

	}

	/* Test for use case 19 */
	public void testChangeLocation() {
		assertTrue(false);

	}

	/* Test for use case 19 */
	public void testNewAndNear() {
		assertTrue(false);

	}

}
