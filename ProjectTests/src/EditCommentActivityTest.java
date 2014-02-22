import ca.ualberta.cs.cmput301t02project.CommentModelAbstraction;
import android.location.Location;
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

    /*
     * Use Case 18 
     * How the commentController will be accessed from the activity isn't specified yet.
     */
    public void testChangeLocation() {
	commentController.setLocation(10.123, 20.321);

	Location location = commentController.getLocation();

	assertTrue("Latitude should 10.123 and Lonngitude should be 20.321",
		location.getLatitude() == 10.123
			&& location.getLongitude == 20.321);
    }

}
