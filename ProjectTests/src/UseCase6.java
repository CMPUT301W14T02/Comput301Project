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

