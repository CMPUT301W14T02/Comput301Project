
import android.test.ActivityInstrumentationTestCase2;
/* 2) Use case: Store Favorite
 * User story: As a user, My favorites and their replies should always be available to me regardless of 
 * network connectivity.
 */

public class UseCase12 extends ActivityInstrumentationTestCase2<T> {

	// Check if favorites can be loaded from memory with and without internet
    public void testReadFavorite (Context context, User user) {
        FavoritesListModel storedFaves;

        FileInputStream fileIn = context.openFileInput("localFaves.sav");
        ObjectInputStream objectIn = new ObjectInputStream(fileIn);
        storedFaves = (ArrayList<FavoritesListModel>) objectIn.readObject();
        objectIn.close();
        fileIn.close();

        assertEquals(storedFaves, user.FavoritesList);
    }
    
 // Check if favorites being stored when marked as a favorite
    public void testStoreFavorite (Context context) {

    	FavoritesListModel storeFave = new FavoritesListModel();
    	FavoritesListController.addComment(storeFave);

    	ArrayList <FavoritesListModel> storedFaves;
    	FavoritesListModel lastFave;

    	FileInputStream fileIn = context.openFileInput("localFaves.sav");
    	ObjectInputStream objectIn = new ObjectInputStream(fileIn);
    	storedFaves = (ArrayList<FavoritesListModel>) objectIn.readObject();
    	objectIn.close();
    	fileIn.close();

    	lastFave = storedFaves.get(storedFaves.size() - 1);

    	assertEquals(storeFave, lastFave);
    }
    
