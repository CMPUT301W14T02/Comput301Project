import android.test.ActivityInstrumentationTestCase2;


public class UseCase14 extends ActivityInstrumentationTestCase2<T> {
	public void testShareComment (TopLevelListModel list) {
		TopLevelCommentModel comment = postComment();
		TopLevelCommentModel comment2 = list.getLatest();
		assertTrue("Comments should be the same if posted comment is saved correctly.", (comment == comment2));
	}
	
	public TopLevelCommentModel postComment() {
		
	}
}
