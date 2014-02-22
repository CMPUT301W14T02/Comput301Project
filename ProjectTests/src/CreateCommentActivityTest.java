import ca.ualberta.cs.cmput301t02project.CommentModelAbstraction;
import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.ListView;

public class CreateCommentActivityTest extends
	ActivityInstrumentationTestCase2<CreateCommentActivity> {
    Instrumentation instrumentation;
    Activity activity;
    EditText textInput;

    public CreateCommentActivityTest() {
	super(CreateCommentActivity.class);
    }

    // Code written to test Use Case 3 starts here ****
    public void setUp() throws Exception {
	super.setUp();
	instrumentation = getInstrumentation();
	activity = getActivity();
    }

    // Test to see if creating a new top level comment increases the size of the
    // adapter storing top-level comments
    public void testCreateTopLevelComment() {
	runTestOnUiThread(new Runnable() {
	    public void run() {
		ListView listView = (ListView) activity
			.findViewById(ca.ualberta.cs.project301.R.id.oldTopLevelComments);
		Adapter adapter = listView.getAdapter();
		int original_size = adapter.getCount();
		original_size++;
		Comment new_comment = new Comment(
			"Let's make a new top-level comment");
		CreateTopLevelComment(new_comment);
		int new_size = adapter.getCount();
		assertEquals(
			"Size of original adapter+1 should equal size of new adapter",
			original_size, new_size);
	    }
	});
    }

    // Test to see if, after creating a new top-level comment, the newest
    // addition to the adapter storing top-level comments is an instance of the
    // TopLevelCommentModel
    public void testIsTopLevelComment() {
	runTestOnUiThread(new Runnable() {
		public void run() {
		    ListView listView = (ListView)activity.findViewById((ca.ualberta.cs.project301.R.id.oldTopLevelComments);
		    Adapter adapter = listView.getAdapter();
		    Comment new_comment = new Comment("Let's make a new top-level comment");
		    CreateTopLevelComment(new_comment);
		    Boolean isTopLevel = adapter.getItem(adapter.getCount()-1) instanceof TopLevelCommentModel;
		    assertTrue(isTopLevelComment);
		}
	    });
    }

    // Code for Use Case 3 ends here 

    /* Code for Use Case 6 ReplyToComment starts here 
     * 
       Test to see if creating a new reply increases the size of the adapter
       storing replies */
    public void testCreateReplyComment() {
	runTestOnUiThread(new Runnable() {
	    public void run() {
		ListView listView = (ListView) activity
			.findViewById(ca.ualberta.cs.project301.R.id.oldReplyComments);
		Adapter adapter = listView.getAdapter();
		int original_size = adapter.getCount();
		original_size++;
		Comment new_comment = new Comment("Let's make a new reply");
		CreateReplyComment(new_comment);
		int new_size = adapter.getCount();
		assertEquals(
			"Size of original adapter+1 should equal size of new adapter",
			original_size, new_size);
	    }
	});
    }

    // Test to see if, after creating a new reply, the newest addition to the
    // adapter storing replies is an instance of the ReplyCommentModel
    public void testIsReplyComment() {
        runTestOnUiThread(new Runnable() {
            public void run() {
                ListView listView = (ListView)activity.findViewById((ca.ualberta.cs.project301.R.id.oldReplyComments););
                Adapter adapter = listView.getAdapter();
                Comment new_comment = new Comment("Let's make a new reply");
                CreateReplyComment(new_comment);
                Boolean isReply = adapter.getItem(adapter.getCount()-1) instanceof ReplyCommentModel;
                assertTrue(isReply);
            }
	    });
    }
    // Code for Use Case 6 ends here 
    
    //Use Case 7: Attach Picture to Comment
    public void testAddedPicture(CommentModel comment) throws Throwable {
	// which comment model to use?
	String pic = comment.getPicture();
	assertFalse("The picture string should no longer be null", null == pic);
    }

}
