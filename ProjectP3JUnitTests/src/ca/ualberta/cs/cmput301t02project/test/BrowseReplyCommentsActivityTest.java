package ca.ualberta.cs.cmput301t02project.test;

import ca.ualberta.cs.cmput301t02project.ProjectApplication;
import ca.ualberta.cs.cmput301t02project.R;
import ca.ualberta.cs.cmput301t02project.activity.BrowseReplyCommentsActivity;
import ca.ualberta.cs.cmput301t02project.model.CommentListModel;
import ca.ualberta.cs.cmput301t02project.model.CommentModel;
import android.location.Location;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ViewAsserts;
import android.widget.ListView;

public class BrowseReplyCommentsActivityTest extends ActivityInstrumentationTestCase2<BrowseReplyCommentsActivity> {
	
	public BrowseReplyCommentsActivityTest () {
		super(BrowseReplyCommentsActivity.class);
	}

	/* Test for use case 4 */
	public void testBrowseReplyComments() {
		CommentListModel comments = new CommentListModel();

		String loc = "Location Intialization";
		Location currentLocation;
		currentLocation = new Location(loc);

		CommentModel comment = new CommentModel("comment", currentLocation, "username");
		comments.add(comment);
		ProjectApplication.setCurrentComment(comment);
	
		ListView view = (ListView) getActivity().findViewById(R.id.replyListView);
		BrowseReplyCommentsActivity activity = getActivity();
		ViewAsserts.assertOnScreen(activity.getWindow().getDecorView(), view);

	}

}
