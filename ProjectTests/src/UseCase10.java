
import android.test.ActivityInstrumentationTestCase2;
/* 10) Use case: Cache Comment
 * User story: As a user, I want comments that I read or comments that I've indicated I want to read, 
 * to be locally cached so I can read them when I am not on the internet.
 */

public class UseCase10 extends ActivityInstrumentationTestCase2<T> {
	// Check if read comments are being stored in the cache
    public void testReadCache (Cache cache) {
        TopLevelCommentModel comment = readPost();
        TopLevelCommentModel comment2 = cache.getLatest();
        assertTrue("Comments should be the same if saving to cache is working.", (comment == comment2));
    }
    
    // Check if marked as "want to read" comments are being stored in cache
    public void testWantReadCache () {
        TopLevelCommentModel comment = wantToReadPost();
        TopLevelCommentModel comment2 = cache.getLatest();
        assertTrue("Comments should be the same.", (comment == comment2));
    }
    
    public TopLevelCommentModel readPost() {
    	
    }
    
    public TopLevelCommentModel wantToReadPost() {
    	
    }
