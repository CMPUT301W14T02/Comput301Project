package ca.ualberta.cs.cmput301t02project.test;

import java.util.ArrayList;
import java.util.Date;

import android.test.ActivityInstrumentationTestCase2;
import ca.ualberta.cs.cmput301t02project.R;
import ca.ualberta.cs.cmput301t02project.activity.BrowseTopLevelCommentsActivity;
import ca.ualberta.cs.cmput301t02project.model.CommentModel;
import ca.ualberta.cs.cmput301t02project.view.CommentListAdapter;


public class CommentListAdapterAbstractionTest extends ActivityInstrumentationTestCase2<BrowseTopLevelCommentsActivity> {
	
	public CommentListAdapterAbstractionTest(){
		super(BrowseTopLevelCommentsActivity.class);
	}
	
	public void testSortByDate() throws InterruptedException{
		CommentModel comment1 = new  CommentModel("post 1", null, "schmoop");
		comment1.setDate(new Date(1));
		
		CommentModel comment2 = new  CommentModel("post 2", null, "schmoop");
		comment2.setDate(new Date(20000));
		
		CommentModel comment3 = new  CommentModel("post 3", null, "schmoop");
		comment3.setDate(new Date(300000000));
		
		ArrayList<CommentModel> outOfOrderComments = new ArrayList<CommentModel>();
		outOfOrderComments.add(comment3);
		outOfOrderComments.add(comment2);
		outOfOrderComments.add(comment1);

		
		ArrayList<CommentModel> inOrderComments = new ArrayList<CommentModel>();
		inOrderComments.add(comment1);
		inOrderComments.add(comment2);
		inOrderComments.add(comment3);
	
		CommentListAdapter adapter1;
		CommentListAdapter adapter2;
		
		adapter1 = new CommentListAdapter(getActivity(), R.layout.list_item, outOfOrderComments);
		adapter2 = new CommentListAdapter(getActivity(), R.layout.list_item, inOrderComments);
		adapter1.sortByDefault();
		adapter2.sortByDefault();
		
		assertEquals("First items should be in same place", adapter1.getItem(0).getDate(), adapter2.getItem(0).getDate());
		assertEquals("Second items should be in same place", adapter1.getItem(1), adapter2.getItem(1));
		assertEquals("Third items should be in same place", adapter1.getItem(2), adapter2.getItem(2));
		
	}
}
