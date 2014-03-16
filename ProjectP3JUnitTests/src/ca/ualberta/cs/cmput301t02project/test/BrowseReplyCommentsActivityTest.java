package ca.ualberta.cs.cmput301t02project.test;

import ca.ualberta.cs.cmput301t02project.ProjectApplication;
import ca.ualberta.cs.cmput301t02project.R;
import ca.ualberta.cs.cmput301t02project.User;
import ca.ualberta.cs.cmput301t02project.activity.BrowseReplyCommentsActivity;
import ca.ualberta.cs.cmput301t02project.controller.FavoritesController;
import ca.ualberta.cs.cmput301t02project.model.CommentListModel;
import ca.ualberta.cs.cmput301t02project.model.CommentModel;
import ca.ualberta.cs.cmput301t02project.model.StorageModel;
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
		CommentListModel model = new CommentListModel();
		FavoritesController controller = new FavoritesController(model);
		CommentModel comment = new CommentModel("original text", null ,"username");
		int oldRating = comment.getRating();
		controller.favoriteComment(comment);
		int newRating = comment.getRating(); 
		assertEquals((oldRating+1), newRating);
	}
	
	/* Test for use case 10 */
	public void testWritingReadCache() {
		StorageModel storage = new StorageModel();
		Location l = new Location("Location Initialization");
		l.setLatitude(0);
		l.setLongitude(0);
		CommentModel comment = new CommentModel("post",l,"user");
		storage.cacheComment(comment);
		CommentModel comment2 = storage.getCacheList().get(0);
		assertEquals("Comments should be the same",comment,comment2);
	}
	
	/* test for use case 10 */
	public void testWritingWantToReadCache() {
		StorageModel storage = new StorageModel();
		Location l = new Location("Location Initialization");
		CommentModel comment = new CommentModel("post",l,"user");
		storage.cacheComment(comment);
		CommentModel comment2 = storage.getCacheList().get(0);
		assertEquals("Comments should be the same",comment,comment2);
	}
	
	/* test to see if user is being pushed to server after update */
	public void testPushUser() {
		User user = new User("user");
		ProjectApplication pa = ProjectApplication.getInstance();
		pa.pushUser(user);
		User user2 = pa.getPushedUser("user");
		assertEquals("Users should be the same",user,user2);
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
