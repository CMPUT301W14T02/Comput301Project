import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ViewAsserts;
import android.widget.TextView;

public class BrowseCommentsActivityTest extends
	ActivityInstrumentationTestCase2<BrowseCommentsActivity> {

    public BrowseCommentsActivityTest() {
	super(BrowseCommentsActivity.class);
    }

    /*
     * Code for Use Case 4 tests start here Test to see if text is being
     * displayed
     */
    public void testDisplayTopLevelComment() {
	Intent intent = new Intent();
	String text = "NewTopLevelComment";
	intent.putExtra(BrowseCommentsActivity.TEXT_KEY, text);
	setActivityIntent(intent);
	BrowseCommentsActivity activity = getActivity();
	TextView textView = (TextView) activity
		.findViewById(ca.ualberta.cs.project301.R.id.comment_item);
	assertEquals("text should be displayed", text, textView.getText()
		.toString());
    }

    // Test to see if default message is displayed when there are no top-level
    // comments
    public void testDefaultMessage() {
	String default_text = "Default";
	Intent intent = new Intent();
	setActivityIntent(intent);
	BrowseCommentsActivity activity = getActivity();
	TextView textView = (TextView) activity
		.findViewById(ca.ualberta.cs.project301.R.id.comment_item);
	assertEquals("text should not be displayed", default_text, textView
		.getText().toString());
    }

    // Test to see if top-level comments are displayed on the screen
    public void testVisibleTopLevelComment() {
	Intent intent = new Intent();
	String text = "top-level comment";
	intent.putExtra(BrowseCommentsActivity.TEXT_KEY, text);
	setActivityIntent(intent);
	BrowseCommentsActivity activity = getActivity();
	TextView textView = (TextView) activity
		.findViewById(ca.ualberta.cs.project301.R.id.comment_item);
	ViewAsserts.assertOnScreen(activity.getWindow().getDecorView(),
		textView);
    }

    // Tests for Use Case 4 end here

    /* Tests for Use Case 5 start here
       Test to see if text is being displayed */
    public void testDisplayReplyComments() {
	Intent intent = new Intent();
	String text = "NewReply";
	intent.putExtra(BrowseRepliesActivity.TEXT_KEY, text);
	setActivityIntent(intent);
	BrowseRepliesActivity activity = getActivity();
	TextView textView = (TextView) activity
		.findViewById(ca.ualberta.cs.project301.R.id.comment_item);
	assertEquals("text should be displayed", text, textView.getText()
		.toString());
    }

    // Test to see if default message is displayed when there are no replies to
    // a specified comment
    public void testDefaultMessage() {
	String default_text = "Default";
	Intent intent = new Intent();
	setActivityIntent(intent);
	BrowseRepliesActivity activity = getActivity();
	TextView textView = (TextView) activity
		.findViewById(ca.ualberta.cs.project301.R.id.comment_item);
	assertEquals("text should not be displayed", default_text, textView
		.getText().toString());
    }

    // Test to see if replies are displayed on the screen
    public void testVisibleReplyComments() {
	Intent intent = new Intent();
	String text = "reply";
	intent.putExtra(BrowseRepliesActivity.TEXT_KEY, text);
	setActivityIntent(intent);
	BrowseRepliesActivity activity = getActivity();
	TextView textView = (TextView) activity
		.findViewById(ca.ualberta.cs.project301.R.id.comment_item);
	ViewAsserts.assertOnScreen(activity.getWindow().getDecorView(),
		textView);
    }
    
    // Tests for Use Case 5 end here
    
    /* Use Case 11
     * How the controller will be accessed from the activity isn't specified yet. */
    
  	public void test addFavorite() {
  		TopLevelCommentModel comment = new TopLevelCommentModel("Some text");
  		favoritesListController.addFavorite(comment);
  		
  		assertTrue("favorites List should contain the just added comment",
  				favoritesListController.getList().contains(comment));
  	}
  	
      // End of Use Case 11 Test

}
