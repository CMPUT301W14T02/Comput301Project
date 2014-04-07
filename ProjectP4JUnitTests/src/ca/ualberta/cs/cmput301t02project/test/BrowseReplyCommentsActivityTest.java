package ca.ualberta.cs.cmput301t02project.test;

import ca.ualberta.cs.cmput301t02project.R;
import ca.ualberta.cs.cmput301t02project.activity.BrowseReplyCommentsActivity;
import ca.ualberta.cs.cmput301t02project.activity.CreateCommentActivity;
import ca.ualberta.cs.cmput301t02project.model.CommentModel;
import ca.ualberta.cs.cmput301t02project.model.ReplyList;
import ca.ualberta.cs.cmput301t02project.model.TopLevelCommentList;
import ca.ualberta.cs.cmput301t02project.model.User;
import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.location.Location;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

public class BrowseReplyCommentsActivityTest extends ActivityInstrumentationTestCase2<BrowseReplyCommentsActivity> {
	
	
	Activity activity;
	protected void setUp() throws Exception {
		super.setUp();
		User.login("username", getInstrumentation().getTargetContext());
		CommentModel comment = initializeComment();
		comment.setTopLevelComment(true);
		TopLevelCommentList comments = TopLevelCommentList.getInstance(getInstrumentation().getTargetContext());
		comments.add(comment);
		ReplyList replies = new ReplyList(comment.getId(), getInstrumentation().getTargetContext());
		CommentModel comment2 = initializeReply();
		replies.add(comment2);
		Intent goToReplyListActivity = new Intent();
		goToReplyListActivity.putExtra("CommentId", comment.getId());
		setActivityIntent(goToReplyListActivity);
		activity = getActivity();
	}
	
	public BrowseReplyCommentsActivityTest() {
		super(BrowseReplyCommentsActivity.class);
	}

	public CommentModel initializeComment() {
		String loc = "Location Intialization";
		Location currentLocation;
		currentLocation = new Location(loc);
		CommentModel comment = new CommentModel("commentForTopLevelTest", currentLocation, "username");
		
		return comment;
	}
	public CommentModel initializeReply() {
		String loc = "Location Intialization";
		Location currentLocation;
		currentLocation = new Location(loc);
		CommentModel comment = new CommentModel("commentForReplylist", currentLocation, "username");
		
		return comment;
	}
	
	/* Test for use case 5 */
	public void testDisplayReplies() {
		ListView view = (ListView) getActivity().findViewById(R.id.replyListView);
		ListAdapter adapter = view.getAdapter();
		Boolean hasComment = false;
		if (adapter.getItem(0).toString().contains("commentForReplylist")){
			hasComment = true;
		}
		assertTrue(hasComment);

	}
	
	/* Test for use case 6 */
	public void testReplyToComment() {
		Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(CreateCommentActivity.class.getName(), null , false);
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Button button1 = (Button) activity.findViewById(ca.ualberta.cs.cmput301t02project.R.id.reply_button);
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
				edit.setText("newReplytoComment");
				button.performClick();
			}
		});
		getInstrumentation().waitForIdleSync();
		
		ListView view = (ListView) getActivity().findViewById(R.id.replyListView);
		ListAdapter adapter = view.getAdapter();
		Boolean hasComment = false;
		for (int i=0; i<adapter.getCount(); i++){
			if (adapter.getItem(i).toString().contains("newReplytoComment")){
				hasComment = true;
			}	
		}
		assertTrue(hasComment);
	}
}
