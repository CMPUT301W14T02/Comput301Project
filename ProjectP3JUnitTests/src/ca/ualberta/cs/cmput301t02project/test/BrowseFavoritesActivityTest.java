package ca.ualberta.cs.cmput301t02project.test;

import ca.ualberta.cs.cmput301t02project.ProjectApplication;
import ca.ualberta.cs.cmput301t02project.R;
import ca.ualberta.cs.cmput301t02project.activity.BrowseFavoritesActivity;
import ca.ualberta.cs.cmput301t02project.model.CommentModel;
import android.location.Location;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.ListView;

public class BrowseFavoritesActivityTest extends ActivityInstrumentationTestCase2<BrowseFavoritesActivity> {

	public BrowseFavoritesActivityTest() {
		super(BrowseFavoritesActivity.class);
	}
	
	public CommentModel initializeComment() {
		String loc = "Location Intialization";
		Location currentLocation;
		Location myLocation;
		currentLocation = new Location(loc);
		myLocation = new Location(loc);

		CommentModel comment = new CommentModel("comment", currentLocation, "username");

		ProjectApplication.setCurrentLocation(myLocation);

		return comment;
	}

	public void testDisplayFavorites() {
		assertTrue(false);
	}

	/* Test for use case 21 */
	public void testDisplayUsername() {
		//Currently throws an error -KW
		CommentModel comment = initializeComment();

		ListView view = (ListView) getActivity().findViewById(R.id.commentListView);
		assertTrue("username should be displayed", view.getAdapter().getItem(0).toString().contains(comment.getUsername()));

		

	}

	public void testVisibleListView() {
		assertTrue(false);

	}
	
	/* test for use case 12 */
	public void testReadFavorites() {
		assertTrue(false);
	}

}
