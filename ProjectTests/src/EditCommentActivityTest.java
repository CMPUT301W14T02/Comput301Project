import ca.ualberta.cs.cmput301t02project.CommentModelAbstraction;
import android.test.ActivityInstrumentationTestCase2;

public class EditCommentActivityTest extends
	ActivityInstrumentationTestCase2<EditCommentActivity> {
    
    public EditCommentActivityTest() {
	super(EditCommentActivity.class);
    }
    
  //Use Case 7: Attach Picture to Comment
    public void testAddedPicture(CommentModelAbstraction comment) throws Throwable {
	String pic = comment.getPicture();
	assertFalse("The picture string should no longer be null", null == pic);
    }

}
