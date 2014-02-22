import ca.ualberta.cs.cmput301t02project.CommentModelAbstraction;
import android.test.ActivityInstrumentationTestCase2;

public class EditCommentActivityTest extends
	ActivityInstrumentationTestCase2<EditCommentActivity> {

    public EditCommentActivityTest() {
	super(EditCommentActivity.class);
    }

    // Use Case 7: Attach Picture to Comment
    public void testAddedPicture(CommentModel comment) throws Throwable {
	// which comment model to use?
	String pic = comment.getPicture();
	assertFalse("The picture string should no longer be null", null == pic);
    }

    // Use Case 13
    public void testEditComment(CommentModel comment) throws Throwable {
	runTestOnUiThread(new Runnable() {

	    @Override
	    public void run() {
		ListView listView = (ListView) activity
			.findViewById(ca.ualberta.cs.project301.R.id.oldComment);
		Adapter adapter = listView.getAdapter();
		String oldText = comment.getText();
		String newText = "This comment has been changed!!!";
		editComment(newText);
		assertFalse("The text should not be equal to the old text",
			(oldText == comment.getText()));

	    }
	});
    }
    // End of Use Case 13 Tests

}
