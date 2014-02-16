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
		    ListView listView = (ListView)activity.findViewById((ca.ualberta.cs.project301.R.id.oldTopLevelComments);
		    Adapter adapter = listView.getAdapter();
		    Comment new_comment = new Comment("Let's make a new top-level comment");
		    CreateTopLevelComment(new_comment);
		    Boolean isTopLevel = adapter.getItem(adapter.getCount()-1) instanceof TopLevelCommentModel;
		    assertTrue(isTopLevelComment);
		}
	    });
    }
}
