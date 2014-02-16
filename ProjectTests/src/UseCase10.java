import android.test.ActivityInstrumentationTestCase2;


public class UseCase10 extends ActivityInstrumentationTestCase2<T> {

    public void testReadCache (Cache cache) {
        TopLevelCommentModel comment = readPost();
        TopLevelCommentModel comment2 = cache.getLatest();
        assertTrue("Comments should be the same if saving to cache is working.", (comment == comment2));
    }
    
    public void testWantReadCache () {
        TopLevelCommentModel comment = wantToReadPost();
        TopLevelCommentModel comment2 = cache.getLatest();
        assertTrue("Comments should be the same.", (comment == comment2));
    }
    
    public TopLevelCommentModel readPost() {
    	
    }
    
    public TopLevelCommentModel wantToReadPost() {
    	
    }
}
