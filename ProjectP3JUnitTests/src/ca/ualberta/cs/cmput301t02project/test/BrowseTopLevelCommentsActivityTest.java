package ca.ualberta.cs.cmput301t02project.test;

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
		currentLocation = new Location(loc);

		CommentModel comment = new CommentModel("comment", currentLocation, "username");

		return comment;
	}


	/* Test for use case 4 */
	public void testBrowseTopLevelComments() {
		CommentListModel comments = new CommentListModel();

		CommentModel comment = initializeComment();
		comments.add(comment);

		ListView view = (ListView) getActivity().findViewById(R.id.commentListView);
		BrowseTopLevelCommentsActivity activity = getActivity();
		ViewAsserts.assertOnScreen(activity.getWindow().getDecorView(), view);

	}

	/* Test for use case 4 */
	public void testVisibleListView() {
		ListView view = (ListView) getActivity().findViewById(R.id.commentListView);
		BrowseTopLevelCommentsActivity activity = getActivity();
		ViewAsserts.assertOnScreen(activity.getWindow().getDecorView(), view);

	}
}
