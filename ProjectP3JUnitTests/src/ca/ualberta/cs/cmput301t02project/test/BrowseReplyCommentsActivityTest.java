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
import android.widget.TextView;

public class BrowseReplyCommentsActivityTest extends ActivityInstrumentationTestCase2<BrowseReplyCommentsActivity> {
	
	public BrowseReplyCommentsActivityTest () {
		super(BrowseReplyCommentsActivity.class);
	}
	
	/* Test for use case 4 */
	public void testDisplayComment() {
		CommentListModel comments = new CommentListModel();

		String loc = "Location Intialization";
		Location currentLocation;
		currentLocation = new Location(loc);

		CommentModel comment = new CommentModel("comment", currentLocation, "username");
		comments.add(comment);
		ProjectApplication.setCurrentComment(comment);
	
		TextView view = (TextView) getActivity().findViewById(R.id.selected_comment);
		BrowseReplyCommentsActivity activity = getActivity();
		ViewAsserts.assertOnScreen(activity.getWindow().getDecorView(), view);
		assertEquals("text should be displayed", comment.getText(), view.getText().toString());

	}
	
	/* Test for use case 4 */
	/*
	public void testDisplayReplies() {
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
		assertEquals("text should be displayed", comment, view.getAdapter());

	}
	*/
	
	/* Test for use case 4 */
	public void testVisibleListView() {
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
