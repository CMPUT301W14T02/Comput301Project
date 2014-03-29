package ca.ualberta.cs.cmput301t02project.test;

import android.location.Location;
import ca.ualberta.cs.cmput301t02project.ProjectApplication;
import ca.ualberta.cs.cmput301t02project.model.CommentModel;
import ca.ualberta.cs.cmput301t02project.model.StorageModel;
import junit.framework.TestCase;

public class StorageModelTest extends TestCase {
	
	public StorageModelTest(String name) {
		super(name);
	}
	
	public CommentModel initializeComment() {
		String loc = "Location Intialization";
		Location currentLocation;
		Location myLocation;
		currentLocation = new Location(loc);
		myLocation = new Location(loc);

		CommentModel comment = new CommentModel("comment", currentLocation, "username");

		ProjectApplication.getInstance().setCurrentLocation(myLocation);

		return comment;
	}

	// Use Case 14
	public void testShareComment () {
		CommentModel comment = initializeComment();
		
		StorageModel sM = new StorageModel();
        //sM.storeMyComment(comment); 
        CommentModel comment2 = sM.getLatest();
        assertTrue("Comments should be the same if posted comment is saved correctly.", (comment == comment2));
    }

	
	
	
}
