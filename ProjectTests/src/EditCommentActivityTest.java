import ca.ualberta.cs.cmput301t02project.CommentModelAbstraction;
import android.test.ActivityInstrumentationTestCase2;

public class EditCommentActivityTest extends
	ActivityInstrumentationTestCase2<EditCommentActivity> {
    
    public EditCommentActivityTest() {
	super(EditCommentActivity.class);
    }
    
  //Use Case 7: Attach Picture to Comment
    public void testAddedPicture(CommentModel comment) throws Throwable {
	//which comment model to use?
	String pic = comment.getPicture();
	assertFalse("The picture string should no longer be null", null == pic);
    }

}
