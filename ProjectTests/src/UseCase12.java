import android.test.ActivityInstrumentationTestCase2;


public class UseCase12 extends ActivityInstrumentationTestCase2<T> {

    public void testReadFavorite (Context context, User user) {
        FavoritesListModel storedFaves;

        FileInputStream fileIn = context.openFileInput("localFaves.sav");
        ObjectInputStream objectIn = new ObjectInputStream(fileIn);
        storedFaves = (ArrayList<FavoritesListModel>) objectIn.readObject();
        objectIn.close();
        fileIn.close();

        assertEquals(storedFaves, user.FavoritesList);
    }
    
    
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
    
}
