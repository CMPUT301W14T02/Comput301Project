package ca.ualberta.cs.cmput301t02project.test;

import java.util.Date;

import ca.ualberta.cs.cmput301t02project.ProjectApplication;
import ca.ualberta.cs.cmput301t02project.R;
import ca.ualberta.cs.cmput301t02project.activity.BrowseTopLevelCommentsActivity;
import ca.ualberta.cs.cmput301t02project.model.CommentListModel;
import ca.ualberta.cs.cmput301t02project.model.CommentModel;
import ca.ualberta.cs.cmput301t02project.model.StorageModel;
import ca.ualberta.cs.cmput301t02project.view.CommentListAdapter;
import android.location.Location;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ViewAsserts;
import android.widget.ListView;

public class BrowseTopLevelCommentsActivityTest extends ActivityInstrumentationTestCase2<BrowseTopLevelCommentsActivity> {

	public BrowseTopLevelCommentsActivityTest() {
		super(BrowseTopLevelCommentsActivity.class);
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

	/* Test for use case 4 */
	public void testDisplayTopLevelComments() {
		CommentModel comment = initializeComment();
		CommentListModel comments = new CommentListModel();
		//comments = ProjectApplication.getInstance().getCommentList();
		comments.add(comment);

		ListView view = (ListView) getActivity().findViewById(R.id.commentListView);
		assertEquals("text should be displayed", comment.toString(), view.getAdapter().getItem(0).toString());

	}

	/* Test for use case 4 */
	public void testVisibleListView() {
		ListView view = (ListView) getActivity().findViewById(R.id.commentListView);
		BrowseTopLevelCommentsActivity activity = getActivity();
		ViewAsserts.assertOnScreen(activity.getWindow().getDecorView(), view);

	}

	/* Test for use case 21 */
	public void testDisplayUsername() {
		CommentModel comment = initializeComment();

		ListView view = (ListView) getActivity().findViewById(R.id.commentListView);
		assertTrue("username should be displayed", view.getAdapter().getItem(0).toString().contains(comment.getUsername()));

	}
	
	/* Test for use case 10 */
	public void testWritingReadCache() {
		StorageModel storage = new StorageModel();
		Location l = new Location("Location Initialization");
		l.setLatitude(0);
		l.setLongitude(0);
		CommentModel comment = new CommentModel("post",l,"user");
		//storage.cacheComment(comment);
		//CommentModel comment2 = storage.getCacheList().get(0);
		//assertEquals("Comments should be the same",comment,comment2);
	}
	
	/* test for use case 10 */
	public void testWritingWantToReadCache() {
		StorageModel storage = new StorageModel();
		Location l = new Location("Location Initialization");
		CommentModel comment = new CommentModel("post",l,"user");
		//storage.cacheComment(comment);
		//CommentModel comment2 = storage.getCacheList().get(0);
		//assertEquals("Comments should be the same",comment,comment2);
	}

	/* Test for use case 19 */
	public void testChangeLocation() {
	    Location initial_loc = new Location("Location Initialization");
	    // Tester should disconnect from internet and move locations, then reconnect to internet
	    Location final_loc = new Location("Location Initialization");
	    boolean latEquals = false;
	    boolean longEquals = false;
	    if (initial_loc.getLatitude() == final_loc.getLatitude())
	        latEquals = true;
	    if (initial_loc.getLongitude()  == final_loc.getLongitude())
	        longEquals = true;
	    boolean CoordEquals = latEquals && longEquals;
	    assertFalse(CoordEquals);
	}

	/* Test for use case 19 */
	public void testNewAndNear() {
		Location currentLocation = new Location("Location Initialization");
		currentLocation.setLatitude(0.02);
		currentLocation.setLongitude(0);
	
		Location l1 = new Location("Location Initialization");
		l1.setLatitude(0.01);
		l1.setLongitude(0);

		Location l2 = new Location("Location Initialization");
		l2.setLatitude(0.5);
		l2.setLongitude(0);

		Location l3 = new Location("Location Initialization");
		l3.setLatitude(20);
		l3.setLongitude(0);
		
		Location l4 = new Location("Location Initialization");
		l3.setLatitude(120);
		l3.setLongitude(0);
		
		ProjectApplication projectApplication = ProjectApplication.getInstance().getInstance();
		ProjectApplication.getInstance().setCurrentLocation(currentLocation);
		
		CommentModel comment1 = new  CommentModel("post 1", l1, "schmoop");
		comment1.setDate(new Date(1));
		
		CommentModel comment2 = new  CommentModel("post 2", l1, "schmoop");
		comment2.setDate(new Date(20000));
		
		CommentModel comment3 = new  CommentModel("post 3", l2, "schmoop");
		comment3.setDate(new Date(1));
		
		CommentModel comment4 = new  CommentModel("post 4", l2, "schmoop");
		comment4.setDate(new Date(20000));
		
		CommentModel comment5 = new  CommentModel("post 5", l3, "schmoop");
		comment5.setDate(new Date(1));
		
		CommentModel comment6 = new  CommentModel("post 6", l3, "schmoop");
		comment6.setDate(new Date(20000));
		
		CommentModel comment7 = new  CommentModel("post 7", l4, "schmoop");
		comment7.setDate(new Date(1));
		
		CommentModel comment8 = new  CommentModel("post 8", l4, "schmoop");
		comment8.setDate(new Date(20000));
		
		
		CommentListModel outOfOrderComments = new CommentListModel();
		outOfOrderComments.add(comment5);
		outOfOrderComments.add(comment4);
		outOfOrderComments.add(comment3);
		outOfOrderComments.add(comment6);
		outOfOrderComments.add(comment1);
		outOfOrderComments.add(comment2);

		CommentListModel inOrderComments = new CommentListModel();
		inOrderComments.add(comment2);
		inOrderComments.add(comment1);
		inOrderComments.add(comment4);
		inOrderComments.add(comment3);
		inOrderComments.add(comment6);
		inOrderComments.add(comment5);
	
		CommentListAdapter adapter1;
		CommentListAdapter adapter2;
		
		adapter1 = new CommentListAdapter(getActivity(), R.layout.list_item, outOfOrderComments.getCommentList());
		adapter2 = new CommentListAdapter(getActivity(), R.layout.list_item, inOrderComments.getCommentList());
		outOfOrderComments.setAdapter(adapter1);
		inOrderComments.setAdapter(adapter2);
		adapter1.setModel(outOfOrderComments);
		adapter2.setModel(inOrderComments);
		adapter1.sortByDefault();
		
		assertEquals("First items should be in same place", adapter1.getItem(0), adapter2.getItem(0));
		assertEquals("Second items should be in same place", adapter1.getItem(1), adapter2.getItem(1));
		assertEquals("Third items should be in same place", adapter1.getItem(2), adapter2.getItem(2));
		assertEquals("Fourth items should be in same place", adapter1.getItem(3), adapter2.getItem(3));
		assertEquals("Fifth items should be in same place", adapter1.getItem(4), adapter2.getItem(4));
		assertEquals("Sixth items should be in same place", adapter1.getItem(5), adapter2.getItem(5));


		assertEquals("First items' dates should be equal", adapter1.getItem(0).getDate(), adapter2.getItem(0).getDate());
		assertEquals("Second items' dates should be equal", adapter1.getItem(1).getDate(), adapter2.getItem(1).getDate());
		assertEquals("Third items' dates should be equal", adapter1.getItem(2).getDate(), adapter2.getItem(2).getDate());
		assertEquals("Fourth items' dates should be equal", adapter1.getItem(3).getDate(), adapter2.getItem(3).getDate());
		assertEquals("Fifth items' dates should be equal", adapter1.getItem(4).getDate(), adapter2.getItem(4).getDate());
		assertEquals("Sixth items' dates should be equal", adapter1.getItem(5).getDate(), adapter2.getItem(5).getDate());

		
		assertEquals("First items' locations should be equal", adapter1.getItem(0).getLocation(), adapter2.getItem(0).getLocation());
		assertEquals("Second items' locations should be equal", adapter1.getItem(1).getLocation(), adapter2.getItem(1).getLocation());
		assertEquals("Third items' locations should be equal", adapter1.getItem(2).getLocation(), adapter2.getItem(2).getLocation());
		assertEquals("Fourth items' locations should be equal", adapter1.getItem(3).getLocation(), adapter2.getItem(3).getLocation());
		assertEquals("Fifth items' locations should be equal", adapter1.getItem(4).getLocation(), adapter2.getItem(4).getLocation());
		assertEquals("Sixth items' locations should be equal", adapter1.getItem(5).getLocation(), adapter2.getItem(5).getLocation());

		outOfOrderComments.add(comment7);
		outOfOrderComments.add(comment8);
		
		inOrderComments.getCommentList().clear();
		inOrderComments.add(comment4);
		inOrderComments.add(comment3);
		inOrderComments.add(comment2);
		inOrderComments.add(comment1);
		inOrderComments.add(comment6);
		inOrderComments.add(comment5);
		inOrderComments.add(comment8);
		inOrderComments.add(comment7);
		
		ProjectApplication.getInstance().getCurrentLocation().setLatitude(0.5);
		ProjectApplication.getInstance().getCurrentLocation().setLongitude(0);
		
		assertEquals("First items should be in same place", adapter1.getItem(0), adapter2.getItem(0));
		assertEquals("Second items should be in same place", adapter1.getItem(1), adapter2.getItem(1));
		assertEquals("Third items should be in same place", adapter1.getItem(2), adapter2.getItem(2));
		assertEquals("Fourth items should be in same place", adapter1.getItem(3), adapter2.getItem(3));
		assertEquals("Fifth items should be in same place", adapter1.getItem(4), adapter2.getItem(4));
		assertEquals("Sixth items should be in same place", adapter1.getItem(5), adapter2.getItem(5));
		assertEquals("Seventh items should be in same place", adapter1.getItem(6), adapter2.getItem(6));
		assertEquals("Eighth items should be in same place", adapter1.getItem(7), adapter2.getItem(7));

		assertEquals("First items' dates should be equal", adapter1.getItem(0).getDate(), adapter2.getItem(0).getDate());
		assertEquals("Second items' dates should be equal", adapter1.getItem(1).getDate(), adapter2.getItem(1).getDate());
		assertEquals("Third items' dates should be equal", adapter1.getItem(2).getDate(), adapter2.getItem(2).getDate());
		assertEquals("Fourth items' dates should be equal", adapter1.getItem(3).getDate(), adapter2.getItem(3).getDate());
		assertEquals("Fifth items' dates should be equal", adapter1.getItem(4).getDate(), adapter2.getItem(4).getDate());
		assertEquals("Sixth items' dates should be equal", adapter1.getItem(5).getDate(), adapter2.getItem(5).getDate());
		assertEquals("Seventh items' dates should be equal", adapter1.getItem(6).getDate(), adapter2.getItem(6).getDate());
		assertEquals("Eighth items' dates should be equal", adapter1.getItem(7).getDate(), adapter2.getItem(7).getDate());
		
		assertEquals("First items' locations should be equal", adapter1.getItem(0).getLocation(), adapter2.getItem(0).getLocation());
		assertEquals("Second items' locations should be equal", adapter1.getItem(1).getLocation(), adapter2.getItem(1).getLocation());
		assertEquals("Third items' locations should be equal", adapter1.getItem(2).getLocation(), adapter2.getItem(2).getLocation());
		assertEquals("Fourth items' locations should be equal", adapter1.getItem(3).getLocation(), adapter2.getItem(3).getLocation());
		assertEquals("Fifth items' locations should be equal", adapter1.getItem(4).getLocation(), adapter2.getItem(4).getLocation());
		assertEquals("Sixth items' locations should be equal", adapter1.getItem(5).getLocation(), adapter2.getItem(5).getLocation());
		assertEquals("Seventh items' locations should be equal", adapter1.getItem(6).getLocation(), adapter2.getItem(6).getLocation());
		assertEquals("Eighth items' locations should be equal", adapter1.getItem(7).getLocation(), adapter2.getItem(7).getLocation());
		
		
	}

	/*
	 * See CommentListAdapterActivityTest for sorting tests.
	 */
}
