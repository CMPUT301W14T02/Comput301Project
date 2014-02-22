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
    
    /* This test may need to be added to the tests for the favoriteCommentActivity, however
     * this check would also need to be done here
     */
  	public void test addFavorite() {
  		TopLevelCommentModel comment = new TopLevelCommentModel("Some text");
  		favoritesListController.addFavorite(comment);
  		
  		assertTrue("favorites List should contain the just added comment",
  				favoritesListController.getList().contains(comment));
  	}
  	
      // End of Use Case 11 Test
  	
  	// Use Case 21: View Commentor Username
  	public void testDisplayUsername() {
  	        Intent intent = new Intent();
  	        String text = "ThisIsMyName";
  	        intent.putExtra(BrowsingCommentsActivity.TEXT_KEY, text);
  	        setActivityIntent(intent);
  	        BrowsingCommentsActivity activity = getActivity();
  	        TextView textView = (TextView)activity.findViewById(ca.ualberta.cs.project301.R.id.commenterUsername);
  	        assertEquals("Username TextView should be visible", text, textView.getText().toString());
  	    } 
  	// End of Use Case 21 test

}
