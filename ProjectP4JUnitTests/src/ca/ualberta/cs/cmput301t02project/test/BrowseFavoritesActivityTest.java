package ca.ualberta.cs.cmput301t02project.test;

import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.test.ViewAsserts;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import ca.ualberta.cs.cmput301t02project.R;
import ca.ualberta.cs.cmput301t02project.activity.BrowseFavoritesActivity;
import ca.ualberta.cs.cmput301t02project.activity.CreateCommentActivity;
import ca.ualberta.cs.cmput301t02project.activity.LoginActivity;
import ca.ualberta.cs.cmput301t02project.activity.MainMenuActivity;
import ca.ualberta.cs.cmput301t02project.controller.TopLevelListController;
import ca.ualberta.cs.cmput301t02project.model.CommentListModel;
import ca.ualberta.cs.cmput301t02project.model.CommentModel;
import ca.ualberta.cs.cmput301t02project.model.CommentServer;
import ca.ualberta.cs.cmput301t02project.model.FavoritesListModel;
import ca.ualberta.cs.cmput301t02project.model.Server;
import ca.ualberta.cs.cmput301t02project.model.StoredCommentListAbstraction;
import ca.ualberta.cs.cmput301t02project.model.TopLevelCommentList;
import ca.ualberta.cs.cmput301t02project.model.User;
import ca.ualberta.cs.cmput301t02project.view.CommentListAdapter;


public class BrowseFavoritesActivityTest extends ActivityInstrumentationTestCase2<BrowseFavoritesActivity> {

	public BrowseFavoritesActivityTest() {
		super(BrowseFavoritesActivity.class);
	}
	private Context context;
	
	@Override
	public void setUp() {
		context = getInstrumentation().getContext();
		User.login("desiredusername", context);
	}

	// not a test, used in test below -SB
	public CommentModel initializeComment() {
		String loc = "Location Intialization";
		Location currentLocation;
		currentLocation = new Location(loc);
		CommentModel comment = new CommentModel("sasha", currentLocation,"username");
		comment.setId("for testing, no need to push to server");
		
		return comment;
	}

	/* Test for Use Case 11 */
	public void testVisibleListView(){
		
		// Check if the ListView shows up on the BrowseFavoritesActivity page
		BrowseFavoritesActivity activity = getActivity();
		ListView view = (ListView) activity.findViewById(R.id.commentListView);
		ViewAsserts.assertOnScreen(activity.getWindow().getDecorView(), view);
	}
	
	/* Test for Use Case 11 */
	@UiThreadTest
	public void testAddFavorite(){
		
		BrowseFavoritesActivity activity = getActivity();
		CommentModel comment = initializeComment();
		
		// manually create FavoritesListModel and add one comment -SB
		FavoritesListModel favorites = new FavoritesListModel(activity);
		favorites.add(comment);
		User.getUser().setFavorites(favorites);
		
		ListView view = (ListView) activity.findViewById(R.id.commentListView);
		CommentListAdapter adapter = (CommentListAdapter) view.getAdapter();
		adapter.add(comment);
		
		assertEquals("1 fave should be added", favorites.getList().size(), User.getUser().getFavorites().getList().size());
		assertEquals("faves should be the same as what they were added as", User.getUser().getFavorites(), favorites);
		assertEquals("fave should be displayed on listview", favorites.getList().size(), adapter.getCount());
	}
	
	@UiThreadTest
	public void testSortByDate(){
	/*	
		CommentModel comment1 = new  CommentModel("post 1", null, "schmoop");
		comment1.setDate(new Date(1));
		comment1.setId("1");
		
		CommentModel comment2 = new  CommentModel("post 2", null, "schmoop");
		comment2.setDate(new Date(20000));
		comment2.setId("2");
		
		CommentModel comment3 = new  CommentModel("post 3", null, "schmoop");
		comment3.setDate(new Date(300000000));
		comment3.setId("3");
		
		FavoritesListModel outOfOrderComments = new FavoritesListModel(context);
		outOfOrderComments.add(comment1);
		outOfOrderComments.add(comment2);
		outOfOrderComments.add(comment3);

		FavoritesListModel inOrderComments = new FavoritesListModel(context);
		inOrderComments.add(comment3);
		inOrderComments.add(comment2);
		inOrderComments.add(comment1);
	
		CommentListAdapter adapter1;
		CommentListAdapter adapter2;
		adapter1 = new CommentListAdapter(context, 0, outOfOrderComments);
		adapter2 = new CommentListAdapter(context, 0, inOrderComments);
/*
		outOfOrderComments.setAdapter(adapter1);
		inOrderComments.setAdapter(adapter2);
		adapter1.setModel(outOfOrderComments);
		adapter2.setModel(inOrderComments);
		adapter1.sortByDate(); */
		/*
		adapter1.sortByDate();
		adapter2.sortByDate();
		
		assertEquals("First items should be in same place", adapter1.getItem(0), adapter2.getItem(0));
		assertEquals("Second items should be in same place", adapter1.getItem(1), adapter2.getItem(1));
		assertEquals("Third items should be in same place", adapter1.getItem(2), adapter2.getItem(2));

		assertEquals("First item's dates should be equal", adapter1.getItem(0).getDate(), adapter2.getItem(0).getDate());
		assertEquals("Second item's dates should be equal", adapter1.getItem(1).getDate(), adapter2.getItem(1).getDate());
		assertEquals("Third item's dates should be equal", adapter1.getItem(2).getDate(), adapter2.getItem(2).getDate());
		*/
	}
	
	/*
	 * TESTS FOR SORTING
	 */
	/*
	 * Use Case 1
	 */
/*	public void testSortByLocation (){
		
		
		FavoritesListModel inOrder = new FavoritesListModel(context);
		FavoritesListModel outOfOrder = new FavoritesListModel(context);
		
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
		
		//ProjectApplication projectApplication = ProjectApplication.getInstance().getInstance();
		//ProjectApplication.getInstance().setCurrentLocation(currentLocation);
		
		CommentModel comment1 = new  CommentModel("post 1", l1, "schmoop");
		
		CommentModel comment2 = new  CommentModel("post 2", l2, "schmoop");

		CommentModel comment3 = new  CommentModel("post 3", l3, "schmoop");
		
		inOrder.add(comment3);
		inOrder.add(comment2);
		inOrder.add(comment1);
		
	
		outOfOrder.add(comment1);
		outOfOrder.add(comment2);
		outOfOrder.add(comment3);
	
		CommentListAdapter adapter1;
		CommentListAdapter adapter2;
		
		adapter1 = new CommentListAdapter(getActivity(), R.layout.list_item, inOrder);
		adapter2 = new CommentListAdapter(getActivity(), R.layout.list_item, outOfOrder);
		
		//outOfOrder.setAdapter(adapter1);
		//inOrderComments.setAdapter(adapter2);
		
		adapter1.setModel(inOrder);
		adapter2.setModel(outOfOrder);
		
		adapter2.sortByLocation();
		
		assertEquals("First items should be in same place", adapter1.getItem(0), adapter2.getItem(0));
		assertEquals("Second items should be in same place", adapter1.getItem(1), adapter2.getItem(1));
		assertEquals("Third items should be in same place", adapter1.getItem(2), adapter2.getItem(2));

		assertEquals("First item's locations should be equal", adapter1.getItem(0).getLocation(), adapter2.getItem(0).getLocation());
		assertEquals("Second item's locations should be equal", adapter1.getItem(1).getLocation(), adapter2.getItem(1).getLocation());
		assertEquals("Third item's locations should be equal", adapter1.getItem(2).getLocation(), adapter2.getItem(2).getLocation());
	}
	
	/*
	 * Use Case 2
	 */
	/*public void testSortByCustomLocation (){

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
		
		ProjectApplication projectApplication = ProjectApplication.getInstance().getInstance();
		ProjectApplication.getInstance().setCurrentLocation(currentLocation);
		
		CommentModel comment1 = new  CommentModel("post 1", l1, "schmoop");
		
		CommentModel comment2 = new  CommentModel("post 2", l2, "schmoop");

		CommentModel comment3 = new  CommentModel("post 3", l3, "schmoop");
		
		
		CommentListModel outOfOrderComments = new CommentListModel();
		outOfOrderComments.add(comment3);
		outOfOrderComments.add(comment2);
		outOfOrderComments.add(comment1);
		
		CommentListModel inOrderComments = new CommentListModel();
		inOrderComments.add(comment1);
		inOrderComments.add(comment2);
		inOrderComments.add(comment3);
	
		CommentListAdapter adapter1;
		CommentListAdapter adapter2;
		
		adapter1 = new CommentListAdapter(getActivity(), R.layout.list_item, outOfOrderComments.getList());
		adapter2 = new CommentListAdapter(getActivity(), R.layout.list_item, inOrderComments.getList());
		outOfOrderComments.setAdapter(adapter1);
		inOrderComments.setAdapter(adapter2);
		adapter1.setModel(outOfOrderComments);
		adapter2.setModel(inOrderComments);
		adapter1.sortByOtherLocation();
		
		assertEquals("First items should be in same place", adapter1.getItem(0), adapter2.getItem(0));
		assertEquals("Second items should be in same place", adapter1.getItem(1), adapter2.getItem(1));
		assertEquals("Third items should be in same place", adapter1.getItem(2), adapter2.getItem(2));
	}

	/*
	 * Use Case 8
	 */
	/*public void testSortByPicture (){
		
		Bitmap pic = null;
		
		// Has picture -SB
		CommentModel comment1 = new  CommentModel("post 1", pic, null, "schmoop");
		
		// Does not have picture -SB
		CommentModel comment2 = new  CommentModel("post 2", null, "schmoop");

		// Has picture -SB
		CommentModel comment3 = new  CommentModel("post 3", pic, null, "schmoop");
		
		//  Does not have picture -SB
		CommentModel comment4 = new  CommentModel("post 4", null, "schmoop");
		
		CommentListModel outOfOrderComments = new CommentListModel();
		outOfOrderComments.add(comment1);
		outOfOrderComments.add(comment2);
		outOfOrderComments.add(comment3);
		outOfOrderComments.add(comment4);
	
		CommentListModel inOrderComments = new CommentListModel();
		inOrderComments.add(comment1);
		inOrderComments.add(comment3);
		inOrderComments.add(comment2);
		inOrderComments.add(comment4);
	
		CommentListAdapter adapter1;
		CommentListAdapter adapter2;
		
		adapter1 = new CommentListAdapter(getActivity(), R.layout.list_item, outOfOrderComments.getList());
		adapter2 = new CommentListAdapter(getActivity(), R.layout.list_item, inOrderComments.getList());
		outOfOrderComments.setAdapter(adapter1);
		inOrderComments.setAdapter(adapter2);
		adapter1.setModel(outOfOrderComments);
		adapter2.setModel(inOrderComments);
		
		adapter1.sortByPicture();
		
		assertEquals("First items should be in same place", adapter1.getItem(0), adapter2.getItem(0));
		assertEquals("Second items should be in same place", adapter1.getItem(1), adapter2.getItem(1));
		assertEquals("Third items should be in same place", adapter1.getItem(2), adapter2.getItem(2));
		assertEquals("Forth items should be in same place", adapter1.getItem(3), adapter2.getItem(3));
	}
	
	/*
	 * Use Case 9
	 */
	/*public void testSortByDate() {
		CommentModel comment1 = new  CommentModel("post 1", null, "schmoop");
		comment1.setDate(new Date(1));
		
		CommentModel comment2 = new  CommentModel("post 2", null, "schmoop");
		comment2.setDate(new Date(20000));
		
		CommentModel comment3 = new  CommentModel("post 3", null, "schmoop");
		comment3.setDate(new Date(300000000));
		
		CommentListModel outOfOrderComments = new CommentListModel();
		outOfOrderComments.add(comment1);
		outOfOrderComments.add(comment2);
		outOfOrderComments.add(comment3);

		
		CommentListModel inOrderComments = new CommentListModel();
		inOrderComments.add(comment3);
		inOrderComments.add(comment2);
		inOrderComments.add(comment1);
	
		CommentListAdapter adapter1;
		CommentListAdapter adapter2;
		
		adapter1 = new CommentListAdapter(getActivity(), R.layout.list_item, outOfOrderComments.getList());
		adapter2 = new CommentListAdapter(getActivity(), R.layout.list_item, inOrderComments.getList());
		outOfOrderComments.setAdapter(adapter1);
		inOrderComments.setAdapter(adapter2);
		adapter1.setModel(outOfOrderComments);
		adapter2.setModel(inOrderComments);
		adapter1.sortByDate();
		
		assertEquals("First items should be in same place", adapter1.getItem(0), adapter2.getItem(0));
		assertEquals("Second items should be in same place", adapter1.getItem(1), adapter2.getItem(1));
		assertEquals("Third items should be in same place", adapter1.getItem(2), adapter2.getItem(2));

		assertEquals("First item's dates should be equal", adapter1.getItem(0).getDate(), adapter2.getItem(0).getDate());
		assertEquals("Second item's dates should be equal", adapter1.getItem(1).getDate(), adapter2.getItem(1).getDate());
		assertEquals("Third item's dates should be equal", adapter1.getItem(2).getDate(), adapter2.getItem(2).getDate());	
	}
	
	/*
	 * Use Case 15
	 */
	/*public void testSortByRating (){
		CommentModel comment1 = new  CommentModel("post 1", null, "schmoop");
		comment1.setRating(0);
		
		CommentModel comment2 = new  CommentModel("post 2", null, "schmoop");
		comment2.setRating(2);
		
		CommentModel comment3 = new  CommentModel("post 3", null, "schmoop");
		comment3.setRating(6);
		
		CommentListModel outOfOrderComments = new CommentListModel();
		outOfOrderComments.add(comment3);
		outOfOrderComments.add(comment2);
		outOfOrderComments.add(comment1);

		
		CommentListModel inOrderComments = new CommentListModel();
		inOrderComments.add(comment1);
		inOrderComments.add(comment2);
		inOrderComments.add(comment3);
	
		CommentListAdapter adapter1;
		CommentListAdapter adapter2;
		
		adapter1 = new CommentListAdapter(getActivity(), R.layout.list_item, outOfOrderComments.getList());
		adapter2 = new CommentListAdapter(getActivity(), R.layout.list_item, inOrderComments.getList());
		outOfOrderComments.setAdapter(adapter1);
		inOrderComments.setAdapter(adapter2);
		adapter1.setModel(outOfOrderComments);
		adapter2.setModel(inOrderComments);
		adapter1.sortByRanking();
		
		
		assertEquals("First items should be in same place", adapter1.getItem(0), adapter2.getItem(0));
		assertEquals("Second items should be in same place", adapter1.getItem(1), adapter2.getItem(1));
		assertEquals("Third items should be in same place", adapter1.getItem(2), adapter2.getItem(2));
		

		assertNotSame("First item's ratings should be equal", adapter1.getItem(0).getRating(), adapter2.getItem(0).getRating());
		assertEquals("Second item's ratings should be equal", adapter1.getItem(1).getRating(), adapter2.getItem(1).getRating());
		assertEquals("Third item's ratings should be equal", adapter1.getItem(2).getRating(), adapter2.getItem(2).getRating());
	}
	
	/*
	 * Use Case 16
	 */
	/*public void testSortByDefault (){
		
		Location currentLocation = new Location("Location Initialization");
		currentLocation.setLatitude(0);
		currentLocation.setLongitude(0);
	
		Location l1 = new Location("Location Initialization");
		l1.setLatitude(0.01);
		l1.setLongitude(0);

		Location l2 = new Location("Location Initialization");
		l2.setLatitude(0.5);
		l2.setLongitude(0);

		Location l3 = new Location("Location Initialization");
		l3.setLatitude(120);
		l3.setLongitude(0);
		
		ProjectApplication projectApplication = ProjectApplication.getInstance().getInstance();
		ProjectApplication.getInstance().setCurrentLocation(currentLocation);
		
		CommentModel comment1 = new  CommentModel("post 1", l1, "schmoop");
		comment1.setDate(new Date(1));
		
		CommentModel comment2 = new  CommentModel("post 2", l1, "schmoop");
		comment2.setDate(new Date(20000));
		
		CommentModel comment3 = new  CommentModel("post 3", l1, "schmoop");
		comment3.setDate(new Date(300000000));
		
		CommentModel comment4 = new  CommentModel("post 4", l2, "schmoop");
		comment4.setDate(new Date(1));
		
		CommentModel comment5 = new  CommentModel("post 5", l2, "schmoop");
		comment5.setDate(new Date(20000));
		
		CommentModel comment6 = new  CommentModel("post 6", l2, "schmoop");
		comment6.setDate(new Date(300000000));
		
		CommentModel comment7 = new  CommentModel("post 7", l3, "schmoop");
		comment7.setDate(new Date(1));
		
		CommentModel comment8 = new  CommentModel("post 8", l3, "schmoop");
		comment8.setDate(new Date(20000));
		
		CommentModel comment9 = new  CommentModel("post 9", l3, "schmoop");
		comment9.setDate(new Date(300000000));
		
		CommentListModel outOfOrderComments = new CommentListModel();
		outOfOrderComments.add(comment9);
		outOfOrderComments.add(comment5);
		outOfOrderComments.add(comment4);
		outOfOrderComments.add(comment3);
		outOfOrderComments.add(comment8);
		outOfOrderComments.add(comment6);
		outOfOrderComments.add(comment1);
		outOfOrderComments.add(comment2);
		outOfOrderComments.add(comment7);

		
		CommentListModel inOrderComments = new CommentListModel();
		inOrderComments.add(comment3);
		inOrderComments.add(comment2);
		inOrderComments.add(comment1);
		inOrderComments.add(comment6);
		inOrderComments.add(comment5);
		inOrderComments.add(comment4);
		inOrderComments.add(comment9);
		inOrderComments.add(comment8);
		inOrderComments.add(comment7);
	
		CommentListAdapter adapter1;
		CommentListAdapter adapter2;
		
		adapter1 = new CommentListAdapter(getActivity(), R.layout.list_item, outOfOrderComments.getList());
		adapter2 = new CommentListAdapter(getActivity(), R.layout.list_item, inOrderComments.getList());
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
		assertEquals("Seventh items should be in same place", adapter1.getItem(6), adapter2.getItem(6));
		assertEquals("Eighth items should be in same place", adapter1.getItem(7), adapter2.getItem(7));
		assertEquals("Ninth items should be in same place", adapter1.getItem(8), adapter2.getItem(8));

		assertEquals("First items' dates should be equal", adapter1.getItem(0).getDate(), adapter2.getItem(0).getDate());
		assertEquals("Second items' dates should be equal", adapter1.getItem(1).getDate(), adapter2.getItem(1).getDate());
		assertEquals("Third items' dates should be equal", adapter1.getItem(2).getDate(), adapter2.getItem(2).getDate());
		assertEquals("Fourth items' dates should be equal", adapter1.getItem(3).getDate(), adapter2.getItem(3).getDate());
		assertEquals("Fifth items' dates should be equal", adapter1.getItem(4).getDate(), adapter2.getItem(4).getDate());
		assertEquals("Sixth items' dates should be equal", adapter1.getItem(5).getDate(), adapter2.getItem(5).getDate());
		assertEquals("Seventh items' dates should be equal", adapter1.getItem(6).getDate(), adapter2.getItem(6).getDate());
		assertEquals("Eighth items' dates should be equal", adapter1.getItem(7).getDate(), adapter2.getItem(7).getDate());
		assertEquals("Ninth items' dates should be equal", adapter1.getItem(8).getDate(), adapter2.getItem(8).getDate());
		
		assertEquals("First items' locations should be equal", adapter1.getItem(0).getLocation(), adapter2.getItem(0).getLocation());
		assertEquals("Second items' locations should be equal", adapter1.getItem(1).getLocation(), adapter2.getItem(1).getLocation());
		assertEquals("Third items' locations should be equal", adapter1.getItem(2).getLocation(), adapter2.getItem(2).getLocation());
		assertEquals("Fourth items' locations should be equal", adapter1.getItem(3).getLocation(), adapter2.getItem(3).getLocation());
		assertEquals("Fifth items' locations should be equal", adapter1.getItem(4).getLocation(), adapter2.getItem(4).getLocation());
		assertEquals("Sixth items' locations should be equal", adapter1.getItem(5).getLocation(), adapter2.getItem(5).getLocation());
		assertEquals("Seventh items' locations should be equal", adapter1.getItem(6).getLocation(), adapter2.getItem(6).getLocation());
		assertEquals("Eighth items' locations should be equal", adapter1.getItem(7).getLocation(), adapter2.getItem(7).getLocation());
		assertEquals("Ninth items' locations should be equal", adapter1.getItem(8).getLocation(), adapter2.getItem(8).getLocation());
	}*/
}
