package ca.ualberta.cs.cmput301t02project.test;

import java.util.Date;
import java.util.Random;

import android.content.Context;
import android.graphics.Bitmap;
import android.location.Location;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.test.ViewAsserts;
import android.widget.ListView;
import ca.ualberta.cs.cmput301t02project.R;
import ca.ualberta.cs.cmput301t02project.activity.BrowseFollowedCommentsActivity;
import ca.ualberta.cs.cmput301t02project.activity.BrowseFollowedCommentsActivity;
import ca.ualberta.cs.cmput301t02project.activity.LoginActivity;
import ca.ualberta.cs.cmput301t02project.model.CommentModel;
import ca.ualberta.cs.cmput301t02project.model.CommentServer;
import ca.ualberta.cs.cmput301t02project.model.FollowedUserCommentsListModel;
import ca.ualberta.cs.cmput301t02project.model.FollowedUserCommentsListModel;
import ca.ualberta.cs.cmput301t02project.model.Server;
import ca.ualberta.cs.cmput301t02project.model.User;
import ca.ualberta.cs.cmput301t02project.view.CommentListAdapter;

public class BrowseFollowedCommentsActivityTest extends ActivityInstrumentationTestCase2<BrowseFollowedCommentsActivity> {

	public BrowseFollowedCommentsActivityTest() {
		super(BrowseFollowedCommentsActivity.class);
	}
	
	private Context context;
	
	// for unique username
	Date date = new Date();
	
	String commentAuthor;
	
	@Override
	public void setUp() {
		context = getInstrumentation().getTargetContext();
		
		// unique username
		User.login(new Random(date.getSeconds()).toString(), context);
	}

	// not a test, used in test below -SB
	public CommentModel initializeComment() {
		String loc = "Location Intialization";
		Location currentLocation;
		currentLocation = new Location(loc);
		
		//unique username
		commentAuthor = new Random(date.getSeconds()).toString();
		CommentModel comment = new CommentModel("sasha", currentLocation, commentAuthor);
		comment.setId("for testing, no need to push");
		
		return comment;
	}
	
	/* Test for BrowseFollowedCommentsActivity */
	public void testVisibleListView(){
		
		// Check if the ListView shows up on the BrowseFollowedCommentsActivity page
		BrowseFollowedCommentsActivity activity = getActivity();
		ListView view = (ListView) activity.findViewById(R.id.commentListView);
		ViewAsserts.assertOnScreen(activity.getWindow().getDecorView(), view);
	}
	
	/* Test for BrowseFollowedCommentsActivity */
	@UiThreadTest
	public void testAddFollowedUser(){
		
		BrowseFollowedCommentsActivity activity = getActivity();
		CommentModel comment = initializeComment();

		User.getUser().addFollowedUser(comment);

		ListView view = (ListView) activity.findViewById(R.id.commentListView);

		assertEquals("should be one user in followed users", User.getUser().getFollowedUsernames().size(), 1);
		
		// must post and pull from the server for these to work
		assertEquals("one comment should be displayed on the listview", view.getAdapter().getCount(), 1);
		assertEquals("displayed comment should match the saved comment", view.getAdapter().getItem(0).toString(), User.getUser().getFollowedUsers().getList().get(0).toString());
	}
	
	/* Test for Use Case 21 */
	@UiThreadTest
	public void testViewUsername() {
		BrowseFollowedCommentsActivity activity = getActivity();
		CommentModel comment = initializeComment();

		User.getUser().getFavorites().add(comment);

		ListView view = (ListView) activity.findViewById(R.id.commentListView);
		
		// must post and pull from the server for these to work
		assertNotSame("", view.getAdapter().getCount(), 0);
		assertTrue("username of the comment author should be displayed in the listview",view.getAdapter().getItem(0).toString().contains(comment.getUsername()));

	}
	
	
	/*
	 * TESTS FOR SORTING
	 */

	/* Test for Use Case 1 */
	public void testSortByLocation (){
		
		Location currentLocation = new Location("Location Initialization");
		currentLocation.setLatitude(0);
		currentLocation.setLongitude(0);
	
		Location l1 = new Location("Location Initialization");
		l1.setLatitude(1);
		l1.setLongitude(1);

		Location l2 = new Location("Location Initialization");
		l2.setLatitude(20);
		l2.setLongitude(20);

		Location l3 = new Location("Location Initialization");
		l3.setLatitude(300);
		l3.setLongitude(300);
		
		CommentModel comment1 = new  CommentModel("post 1", l1, "schmoop");
		comment1.setId("1");
		
		CommentModel comment2 = new  CommentModel("post 2", l2, "schmoop");
		comment2.setId("2");
		
		CommentModel comment3 = new  CommentModel("post 3", l3, "schmoop");
		comment3.setId("3");
		

		FollowedUserCommentsListModel inOrder = new FollowedUserCommentsListModel(context);
		inOrder.add(comment1);
		inOrder.add(comment2);
		inOrder.add(comment3);
		
		FollowedUserCommentsListModel outOfOrder = new FollowedUserCommentsListModel(context);
		outOfOrder.add(comment3);
		outOfOrder.add(comment2);
		outOfOrder.add(comment1);
	
		CommentListAdapter adapter1;
		CommentListAdapter adapter2;
		
		adapter1 = new CommentListAdapter(context, R.layout.list_item, inOrder);
		adapter2 = new CommentListAdapter(context, R.layout.list_item, outOfOrder);
		
		adapter1.sortByLocation();
		adapter2.sortByLocation();
		
		assertEquals("First items should be in same place", adapter1.getItem(0), adapter2.getItem(0));
		assertEquals("Second items should be in same place", adapter1.getItem(1), adapter2.getItem(1));
		assertEquals("Third items should be in same place", adapter1.getItem(2), adapter2.getItem(2));

		assertEquals("First item's locations should be equal", adapter1.getItem(0).getLocation().toString(), adapter2.getItem(0).getLocation().toString());
		assertEquals("Second item's locations should be equal", adapter1.getItem(1).getLocation().toString(), adapter2.getItem(1).getLocation().toString());
		assertEquals("Third item's locations should be equal", adapter1.getItem(2).getLocation().toString(), adapter2.getItem(2).getLocation().toString());
	}
	
	/* Test for Use Case 2 */
	public void testSortByOtherLocation(){
	
		Location currentLocation = new Location("Location Initialization");
		currentLocation.setLatitude(0);
		currentLocation.setLongitude(0);
	
		Location l1 = new Location("Location Initialization");
		l1.setLatitude(1);
		l1.setLongitude(1);

		Location l2 = new Location("Location Initialization");
		l2.setLatitude(20);
		l2.setLongitude(20);

		Location l3 = new Location("Location Initialization");
		l3.setLatitude(300);
		l3.setLongitude(300);
		
		CommentModel comment1 = new  CommentModel("post 1", l1, "schmoop");
		comment1.setId("1");
		
		CommentModel comment2 = new  CommentModel("post 2", l2, "schmoop");
		comment2.setId("2");
		
		CommentModel comment3 = new  CommentModel("post 3", l3, "schmoop");
		comment3.setId("3");
		

		FollowedUserCommentsListModel inOrder = new FollowedUserCommentsListModel(context);
		inOrder.add(comment3);
		inOrder.add(comment2);
		inOrder.add(comment1);
		
		FollowedUserCommentsListModel outOfOrder = new FollowedUserCommentsListModel(context);
		outOfOrder.add(comment1);
		outOfOrder.add(comment2);
		outOfOrder.add(comment3);
		
		CommentListAdapter adapter1;
		CommentListAdapter adapter2;
		
		adapter1 = new CommentListAdapter(context, R.layout.list_item, inOrder);
		adapter2 = new CommentListAdapter(context, R.layout.list_item, outOfOrder);
		
		adapter1.sortByOtherLocation(l3);
		adapter2.sortByOtherLocation(l3);
		
		assertEquals("First items should be in same place", adapter1.getItem(0), adapter2.getItem(0));
		assertEquals("Second items should be in same place", adapter1.getItem(1), adapter2.getItem(1));
		assertEquals("Third items should be in same place", adapter1.getItem(2), adapter2.getItem(2));

		assertEquals("First item's locations should be equal", adapter1.getItem(0).getLocation().toString(), adapter2.getItem(0).getLocation().toString());
		assertEquals("Second item's locations should be equal", adapter1.getItem(1).getLocation().toString(), adapter2.getItem(1).getLocation().toString());
		assertEquals("Third item's locations should be equal", adapter1.getItem(2).getLocation().toString(), adapter2.getItem(2).getLocation().toString());
	}
	
	
	/* Test for Use Case 8 */
	public void testSortByPicture (){
		
		// From http://stackoverflow.com/questions/5663671/creating-an-empty-bitmap-and-drawing-though-canvas-in-android
		// retrived April 6th 2014.
		Bitmap.Config conf = Bitmap.Config.ARGB_4444; 
		Bitmap pic = Bitmap.createBitmap(10, 10, conf);
		
		// Has picture -SB
		CommentModel comment1 = new  CommentModel("post 1", pic, null, "schmoop");
		comment1.setId("1");
		
		// Does not have picture -SB
		CommentModel comment2 = new  CommentModel("post 2", null, "schmoop");
		comment2.setId("2");
		
		// Has picture -SB
		CommentModel comment3 = new  CommentModel("post 3", pic, null, "schmoop");
		comment3.setId("3");
		
		//  Does not have picture -SB
		CommentModel comment4 = new  CommentModel("post 4", null, "schmoop");
		comment4.setId("4");
		
		FollowedUserCommentsListModel outOfOrderComments = new FollowedUserCommentsListModel(context);
		outOfOrderComments.add(comment1);
		outOfOrderComments.add(comment2);
		outOfOrderComments.add(comment3);
		outOfOrderComments.add(comment4);
	
		FollowedUserCommentsListModel inOrderComments = new FollowedUserCommentsListModel(context);
		inOrderComments.add(comment1);
		inOrderComments.add(comment3);
		inOrderComments.add(comment2);
		inOrderComments.add(comment4);
	
		CommentListAdapter adapter1;
		CommentListAdapter adapter2;
	
		adapter1 = new CommentListAdapter(context, 0, outOfOrderComments);
		adapter2 = new CommentListAdapter(context, 0, inOrderComments);
		
		adapter1.sortByPicture();
		adapter2.sortByPicture();
		
		// 2 comments with pictures, 2 without. top 2 should have pictures, bottom two should not. Don't have to be exactly the same order.
		assertTrue("First item should have a picture", adapter1.getItem(0).hasPicture());
		assertEquals("First items should have pictures", adapter1.getItem(0).hasPicture(), adapter2.getItem(0).hasPicture());
		assertTrue("Second item should have a picture", adapter1.getItem(1).hasPicture());
		assertEquals("Second items should have pictures", adapter1.getItem(1).hasPicture(), adapter2.getItem(1).hasPicture());
		assertFalse("Third item should not have a picture", adapter1.getItem(2).hasPicture());
		assertEquals("Third items should not have pictures", adapter1.getItem(2).hasPicture(), adapter2.getItem(2).hasPicture());
		assertFalse("Forth item should not have a picture", adapter1.getItem(3).hasPicture());
		assertEquals("Forth items should not have pictures", adapter1.getItem(3).hasPicture(), adapter2.getItem(3).hasPicture());
	}
	
	
	/* Test for Use Case 9 */
	public void testSortByDate(){
	
		CommentModel comment1 = new  CommentModel("post 1", null, "schmoop");
		comment1.setDate(new Date(1));
		comment1.setId("1");
		
		CommentModel comment2 = new  CommentModel("post 2", null, "schmoop");
		comment2.setDate(new Date(20000));
		comment2.setId("2");
		
		CommentModel comment3 = new  CommentModel("post 3", null, "schmoop");
		comment3.setDate(new Date(300000000));
		comment3.setId("3");
		
		FollowedUserCommentsListModel outOfOrderComments = new FollowedUserCommentsListModel(context);
		outOfOrderComments.add(comment1);
		outOfOrderComments.add(comment2);
		outOfOrderComments.add(comment3);

		FollowedUserCommentsListModel inOrderComments = new FollowedUserCommentsListModel(context);
		inOrderComments.add(comment3);
		inOrderComments.add(comment2);
		inOrderComments.add(comment1);
	
		CommentListAdapter adapter1;
		CommentListAdapter adapter2;
		adapter1 = new CommentListAdapter(context, 0, outOfOrderComments);
		adapter2 = new CommentListAdapter(context, 0, inOrderComments);
		
		adapter1.sortByDate();
		adapter2.sortByDate();
		
		assertEquals("First items should be in same place", adapter1.getItem(0), adapter2.getItem(0));
		assertEquals("Second items should be in same place", adapter1.getItem(1), adapter2.getItem(1));
		assertEquals("Third items should be in same place", adapter1.getItem(2), adapter2.getItem(2));

		assertEquals("First item's dates should be equal", adapter1.getItem(0).getDate(), adapter2.getItem(0).getDate());
		assertEquals("Second item's dates should be equal", adapter1.getItem(1).getDate(), adapter2.getItem(1).getDate());
		assertEquals("Third item's dates should be equal", adapter1.getItem(2).getDate(), adapter2.getItem(2).getDate());
	}
	
	
	/* Test for Use Case 15 */
	public void testSortByFaves (){
		CommentModel comment1 = new  CommentModel("post 1", null, "schmoop");
		comment1.setRating(0);
		comment1.setId("1");
		
		CommentModel comment2 = new  CommentModel("post 2", null, "schmoop");
		comment2.setRating(2);
		comment2.setId("2");
		
		CommentModel comment3 = new  CommentModel("post 3", null, "schmoop");
		comment3.setRating(6);
		comment3.setId("3");
		
		FollowedUserCommentsListModel outOfOrderComments = new FollowedUserCommentsListModel(context);
		outOfOrderComments.add(comment3);
		outOfOrderComments.add(comment2);
		outOfOrderComments.add(comment1);

		
		FollowedUserCommentsListModel inOrderComments = new FollowedUserCommentsListModel(context);
		inOrderComments.add(comment1);
		inOrderComments.add(comment2);
		inOrderComments.add(comment3);
	
		CommentListAdapter adapter1;
		CommentListAdapter adapter2;
		
		adapter1 = new CommentListAdapter(context, R.layout.list_item, outOfOrderComments);
		adapter2 = new CommentListAdapter(context, R.layout.list_item, inOrderComments);
		
		adapter1.sortByFaves();
		adapter2.sortByFaves();
		
		assertEquals("First items should be in same place", adapter1.getItem(0), adapter2.getItem(0));
		assertEquals("Second items should be in same place", adapter1.getItem(1), adapter2.getItem(1));
		assertEquals("Third items should be in same place", adapter1.getItem(2), adapter2.getItem(2));
		
		assertEquals("First item's ratings should be equal", adapter1.getItem(0).getRating(), adapter2.getItem(0).getRating());
		assertEquals("Second item's ratings should be equal", adapter1.getItem(1).getRating(), adapter2.getItem(1).getRating());
		assertEquals("Third item's ratings should be equal", adapter1.getItem(2).getRating(), adapter2.getItem(2).getRating());
	}
	
	/* Test for Use Case 16 */
	public void testSortByDefault (){
		
		
		Location currentLocation = new Location("Location Initialization");
		currentLocation.setLatitude(0);
		currentLocation.setLongitude(0);
	
		Location l1 = new Location("Location Initialization");
		l1.setLatitude(1);
		l1.setLongitude(1);

		Location l2 = new Location("Location Initialization");
		l2.setLatitude(20);
		l2.setLongitude(20);

		Location l3 = new Location("Location Initialization");
		l3.setLatitude(300);
		l3.setLongitude(300);
		
		CommentModel comment1 = new  CommentModel("post 1", l3, "schmoop");
		comment1.setDate(new Date(1));
		comment1.setId("1");
		
		CommentModel comment2 = new  CommentModel("post 2", l2, "schmoop");
		comment2.setDate(new Date(20000));
		comment2.setId("2");
		
		CommentModel comment3 = new  CommentModel("post 3", l1, "schmoop");
		comment3.setDate(new Date(300000000));
		comment3.setId("3");
		
		FollowedUserCommentsListModel outOfOrderComments = new FollowedUserCommentsListModel(context);
		outOfOrderComments.add(comment3);
		outOfOrderComments.add(comment2);
		outOfOrderComments.add(comment1);
		
		
		FollowedUserCommentsListModel inOrderComments = new FollowedUserCommentsListModel(context);
		inOrderComments.add(comment1);
		inOrderComments.add(comment2);
		inOrderComments.add(comment3);
	
		CommentListAdapter adapter1;
		CommentListAdapter adapter2;
		
		adapter1 = new CommentListAdapter(context, R.layout.list_item, outOfOrderComments);
		adapter2 = new CommentListAdapter(context, R.layout.list_item, inOrderComments);
		
		adapter1.sortByDefault();
		adapter2.sortByDefault();
		
		assertEquals("First items should be in same place", adapter2.getItem(0), adapter1.getItem(0));
		assertEquals("Second items should be in same place", adapter2.getItem(1), adapter1.getItem(1));
		assertEquals("Third items should be in same place", adapter2.getItem(2), adapter1.getItem(2));
	}
}
