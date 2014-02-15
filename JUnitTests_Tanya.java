/********************
 * JUnit Test Cases *
 *******************/
    
// 3) Use Case: CreateTopLevelComment
public class CreateCommentActivityUITest extends
		ActivityInstrumentationTestCase2<CreateCommentActivity> {

    Instrumentation instrumentation;
    Activity activity;
    EditText textInput;

    public CreateCommentActivityUITest() {
	super(CreateCommentActivity.class);
    }

    public void setUp() throws Exception {
	super.setUp();
	instrumentation = getInstrumentation();
	activity = getActivity();
    }

    // Test to see if creating a new top level comment increases the size of the adapter storing top-level comments
    public void testCreateTopLevelComment() {
	runTestOnUiThread(new Runnable() {
		public void run() {
		    ListView listView = (ListView)activity.findViewById(ca.ualberta.cs.project301.R.id.oldTopLevelComments);
		    Adapter adapter = listView.getAdapter();
		    int original_size = adapter.getCount();
		    original_size++;
		    Comment new_comment = new Comment("Let's make a new top-level comment");
		    CreateTopLevelComment(new_comment);
		    int new_size = adapter.getCount();
		    assertEquals("Size of original adapter+1 should equal size of new adapter", original_size, new_size);
		}
	    });
    }

    // Test to see if, after creating a new top-level comment, the newest addition to the adapter storing top-level comments is an instance of the TopLevelCommentModel
    public void testIsTopLevelComment() {
	runTestOnUiThread(new Runnable() {
		public void run() {
		    ListView listView = (ListView)activity.findViewById((ca.ualberta.cs.project301.R.id.oldTopLevelComments););
		    Adapter adapter = listView.getAdapter();
		    Comment new_comment = new Comment("Let's make a new top-level comment");
		    CreateTopLevelComment(new_comment);
		    Boolean isTopLevel = adapter.getItem(adapter.getCount()-1) instanceof TopLevelCommentModel;
		    assertTrue(isTopLevelComment);
		}
	    });
    }
}

//Use Case 4: BrowseTopLevelComments
public class BrowseCommentsActivityTests extend 
                ActivityInstrumentationTestCase2<BrowseCommentsActivity> {

    public BrowseCommentsActivityTests() {
	    super(BrowseCommentsActivity.class);
	}
    // Test to see if text is being displayed
    public void testDisplayTopLevelComment() {	
	Intent intent = new Intent();
	String text = "NewTopLevelComment";
	intent.putExtra(BrowseCommentsActivity.TEXT_KEY, text);
	setActivityIntent(intent);
	BrowseCommentsActivity activity = getActivity();
	TextView textView = (TextView)activity.findViewById(ca.ualberta.cs.project301.R.id.intentText);
	assertEquals("text should be displayed", text, textView.getText().toString());
    }
    
    // Test to see if default message is displayed when there are no top-level comments
    public void testDefaultMessage() {
	String default_text = "Default";
	Intent intent = new Intent();
	setActivityIntent(intent);
	BrowseCommentsActivity activity = getActivity();
	TextView textView = (TextView)activity.findViewById(ca.ualberta.cs.project301.R.id.intentText);
	assertEquals("text should not be displayed", default_text, textView.getText().toString());
    }

    // Test to see if top-level comments are displayed on the screen
    public void testVisibleTopLevelComment(){
	Intent intent = new Intent();
	String text = "top-level comment";
	intent.putExtra(BrowseCommentsActivity.TEXT_KEY, text);
	setActivityIntent(intent);
	BrowseCommentsActivity activity = getActivity();
	TextView textView = (TextView)activity.findViewById(ca.ualberta.cs.project301.R.id.intentText);
	ViewAsserts.assertOnScreen(activity.getWindow().getDecorView(), textView);
    }
}

//Use Case 5: BrowseCommentReplies
public class BrowseRepliesActivityTests extend
    ActivityInstrumentationTestCase2<BrowseRepliesActivity> {

    public BrowseRepliesActivityTests() {
	super(BrowseRepliesActivity.class);
    }

    // Test to see if text is being displayed
    public void testDisplayReplyComments(){
        Intent intent = new Intent();
        String text = "NewReply";
        intent.putExtra(BrowseRepliesActivity.TEXT_KEY, text);
        setActivityIntent(intent);
        BrowseRepliesActivity activity = getActivity();
        TextView textView = (TextView)activity.findViewById(ca.ualberta.cs.project301.R.id.intentText);
        assertEquals("text should be displayed", text, textView.getText().toString());
    }

    // Test to see if default message is displayed when there are no replies to a specified comment
    public void testDefaultMessage() {
        String default_text = "Default";
        Intent intent = new Intent();
        setActivityIntent(intent);
        BrowseRepliesActivity activity = getActivity();
        TextView textView = (TextView)activity.findViewById(ca.ualberta.cs.project301.R.id.intentText);
        assertEquals("text should not be displayed", default_text, textView.getText().toString());
    }

    // Test to see if replies are displayed on the screen
    public void testVisibleReplyComments(){
        Intent intent = new Intent();
        String text = "reply";
        intent.putExtra(BrowseRepliesActivity.TEXT_KEY, text);
        setActivityIntent(intent);
        BrowseRepliesActivity activity = getActivity();
        TextView textView = (TextView)activity.findViewById(ca.ualberta.cs.project301.R.id.intentText);
        ViewAsserts.assertOnScreen(activity.getWindow().getDecorView(), textView);
    }
}

//Use Case 6: ReplyToComment
public class CreateCommentActivityUITest extends
ActivityInstrumentationTestCase2<CreateCommentActivity> {
    
    Instrumentation instrumentation;
    Activity activity;
    EditText textInput;
    
    public CreateCommentActivityUITest() {
        super(CreateCommentActivity.class);
    }
    
    public void setUp() throws Exception {
        super.setUp();
        instrumentation = getInstrumentation();
        activity = getActivity();
    }
    
    // Test to see if creating a new reply increases the size of the adapter storing replies
    public void testCreateReplyComment() {
        runTestOnUiThread(new Runnable() {
            public void run() {
                ListView listView = (ListView)activity.findViewById(ca.ualberta.cs.project301.R.id.oldReplyComments);
                Adapter adapter = listView.getAdapter();
                int original_size = adapter.getCount();
                original_size++;
                Comment new_comment = new Comment("Let's make a new reply");
                CreateReplyComment(new_comment);
                int new_size = adapter.getCount();
                assertEquals("Size of original adapter+1 should equal size of new adapter", original_size, new_size);
            }
	    });
    }
    
    // Test to see if, after creating a new reply, the newest addition to the adapter storing replies is an instance of the ReplyCommentModel
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
}

