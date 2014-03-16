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

	public BrowseReplyCommentsActivityTest() {
		super(BrowseReplyCommentsActivity.class);
	}

	public CommentModel initializeComment() {
		String loc = "Location Intialization";
		Location currentLocation;
		Location myLocation;
		currentLocation = new Location(loc);
		myLocation = new Location(loc);

		CommentModel comment = new CommentModel("comment", currentLocation, "username");
		
		CommentListModel comments = new CommentListModel();
		comments.add(comment);

		ProjectApplication.setCurrentComment(comment);
		ProjectApplication.setCurrentCommentList(comments);
		ProjectApplication.setCurrentLocation(myLocation);

		return comment;
	}

	/* Test for use case 5 */
	public void testDisplaySelectedComment() {
		CommentModel comment = initializeComment();
		TextView view = (TextView) getActivity().findViewById(R.id.selected_comment);
		assertEquals("text should be displayed", comment.getText(), view.getText().toString());
	}

	/* Test for use case 5 */
	public void testVisibleTextView() {
		TextView view = (TextView) getActivity().findViewById(R.id.selected_comment);
		BrowseReplyCommentsActivity activity = getActivity();
		ViewAsserts.assertOnScreen(activity.getWindow().getDecorView(), view);

	}

	/* Test for use case 5 */
	public void testDisplayReplies() {
		CommentModel comment = initializeComment();

		ListView view = (ListView) getActivity().findViewById(R.id.replyListView);
		assertEquals("text should be displayed", comment.toString(), view.getAdapter().getItem(0).toString());

	}

	/* Test for use case 5 */
	public void testVisibleListView() {
		ListView view = (ListView) getActivity().findViewById(R.id.replyListView);
		BrowseReplyCommentsActivity activity = getActivity();
		ViewAsserts.assertOnScreen(activity.getWindow().getDecorView(), view);

	}

	/* Test for use case 21 */
	public void testDisplayUsername() {
		CommentModel comment = initializeComment();

		ListView view = (ListView) getActivity().findViewById(R.id.replyListView);
		assertTrue("username should be displayed", view.getAdapter().getItem(0).toString().contains(comment.getUsername()));

	}

	/* Test for use case 10 and 11 */
	public void testAddFavorite () {
		assertTrue(false);
	}
	
	/* Test for use case 10 */
	public void testReadCache() {
		assertTrue(false);
	}
	
	/* test for use case 10 */
	public void wantToReadCache() {
		assertTrue(false);
	}
	
	/* test to see if user is being pushed to server after update */
	public void testPushUser() {
		assertTrue(false);
	}
	
}
