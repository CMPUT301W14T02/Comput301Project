package ca.ualberta.cs.cmput301t02project.test;

import ca.ualberta.cs.cmput301t02project.ProjectApplication;
import ca.ualberta.cs.cmput301t02project.R;
import ca.ualberta.cs.cmput301t02project.activity.BrowseMyCommentsActivity;
import ca.ualberta.cs.cmput301t02project.model.CommentListModel;
import ca.ualberta.cs.cmput301t02project.model.CommentModel;
import android.location.Location;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ViewAsserts;
import android.widget.ListView;

public class BrowseMyCommentsActivityTest extends ActivityInstrumentationTestCase2<BrowseMyCommentsActivity> {

	public BrowseMyCommentsActivityTest() {
		super(BrowseMyCommentsActivity.class);
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

	public void testDisplayMyComments() {
		CommentModel comment = initializeComment();
		CommentListModel myComments = new CommentListModel();
		myComments = ProjectApplication.getUser().getMyComments();
		myComments.add(comment);

		ListView view = (ListView) getActivity().findViewById(R.id.commentListView);
		assertEquals("text should be displayed", comment.toString(), view.getAdapter().getItem(0).toString());

	}

	public void testVisibleListView() {
		ListView view = (ListView) getActivity().findViewById(R.id.commentListView);
		BrowseMyCommentsActivity activity = getActivity();
		ViewAsserts.assertOnScreen(activity.getWindow().getDecorView(), view);

	}

}
