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
        TextView textView = (TextView)activity.findViewById(ca.ualberta.cs.project301.R.id.comment_item);
        assertEquals("text should be displayed", text, textView.getText().toString());
    }

    // Test to see if default message is displayed when there are no replies to a specified comment
    public void testDefaultMessage() {
        String default_text = "Default";
        Intent intent = new Intent();
        setActivityIntent(intent);
        BrowseRepliesActivity activity = getActivity();
        TextView textView = (TextView)activity.findViewById(ca.ualberta.cs.project301.R.id.comment_item);
        assertEquals("text should not be displayed", default_text, textView.getText().toString());
    }

    // Test to see if replies are displayed on the screen
    public void testVisibleReplyComments(){
        Intent intent = new Intent();
        String text = "reply";
        intent.putExtra(BrowseRepliesActivity.TEXT_KEY, text);
        setActivityIntent(intent);
        BrowseRepliesActivity activity = getActivity();
        TextView textView = (TextView)activity.findViewById(ca.ualberta.cs.project301.R.id.comment_item);
        ViewAsserts.assertOnScreen(activity.getWindow().getDecorView(), textView);
    }
}

