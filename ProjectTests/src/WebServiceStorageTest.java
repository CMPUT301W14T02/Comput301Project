import android.test.ActivityInstrumentationTestCase2;

public class WebServiceStorageTest extends
	ActivityInstrumentationTestCase2<WebServiceStorage> {

    public WebServiceStorageTest() {
	super(WebServiceStorage.class);
    }

    // Use Case 14
    public void testShareComment(TopLevelListModel list) {
	TopLevelCommentModel comment = postComment();
	TopLevelCommentModel comment2 = list.getLatest();
	assertTrue(
		"Comments should be the same if posted comment is saved correctly.",
		(comment == comment2));
    }

    public TopLevelCommentModel postComment() {

    }

}
