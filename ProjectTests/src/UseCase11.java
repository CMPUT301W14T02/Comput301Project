import android.test.ActivityInstrumentationTestCase2;

public class BrowseCommentsActivityTests extends
		ActivityInstrumentationTestCase2<BrowserCommentsActivity> {

	public BrowseCommentsActivityTests() {
		super(BrowserCommentsActivity.class);
	}
	
	//How the controller will be accessed from the activity isn't specified yet.
	public void test addFavorite() {
		TopLevelCommentModel comment = new TopLevelCommentModel("Some text");
		favoritesListController.addFavorite(comment);
		
		assertTrue("favorites List should contain the just added comment",
				favoritesListController.getList().contains(comment));
	}
}
