import android.test.ActivityInstrumentationTestCase2;

public class CommentStorageTest extends
	ActivityInstrumentationTestCase2<CommentStorage> {

    public CommentStorageTest() {
	super(CommentStorage.class);
    }

    /*
     * Use case 10: Cache Comment User story: As a user, I want comments that I
     * read or comments that I've indicated I want to read, to be locally cached
     * so I can read them when I am not on the Internet.
     * 
     * Check if read comments are being stored in the cache
     */
    public void testReadCache(Cache cache) {
	TopLevelCommentModel comment = readPost();
	TopLevelCommentModel comment2 = cache.getLatest();
	assertTrue(
		"Comments should be the same if saving to cache is working.",
		(comment == comment2));
    }

    // Check if marked as "want to read" comments are being stored in cache
    public void testWantReadCache() {
	TopLevelCommentModel comment = wantToReadPost();
	TopLevelCommentModel comment2 = cache.getLatest();
	assertTrue("Comments should be the same.", (comment == comment2));
    }

    public TopLevelCommentModel readPost() {

    }

    public TopLevelCommentModel wantToReadPost() {

    }

    // End of Use Case 10 Tests

    /*
     * Use Case 12 User story: As a user, My favorites and their replies should
     * always be available to me regardless of network connectivity.
     * 
     * Check if favorites can be loaded from memory with and without Internet */
    public void testReadFavorite(Context context, User user) {
	FavoritesListModel storedFaves;

	FileInputStream fileIn = context.openFileInput("localFaves.sav");
	ObjectInputStream objectIn = new ObjectInputStream(fileIn);
	storedFaves = (ArrayList<FavoritesListModel>) objectIn.readObject();
	objectIn.close();
	fileIn.close();

	assertEquals(storedFaves, user.FavoritesList);
    }

    // Check if favorites being stored when marked as a favorite
    public void testStoreFavorite(Context context) {

	FavoritesListModel storeFave = new FavoritesListModel();
	FavoritesListController.addComment(storeFave);

	ArrayList<FavoritesListModel> storedFaves;
	FavoritesListModel lastFave;

	FileInputStream fileIn = context.openFileInput("localFaves.sav");
	ObjectInputStream objectIn = new ObjectInputStream(fileIn);
	storedFaves = (ArrayList<FavoritesListModel>) objectIn.readObject();
	objectIn.close();
	fileIn.close();

	lastFave = storedFaves.get(storedFaves.size() - 1);

	assertEquals(storeFave, lastFave);
    }
    // End of Use Case 12 Tests

}
