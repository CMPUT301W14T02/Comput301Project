package ca.ualberta.cs.cmput301t02project.test;

import java.util.ArrayList;

import android.test.ActivityInstrumentationTestCase2;
import ca.ualberta.cs.cmput301t02project.R;
import ca.ualberta.cs.cmput301t02project.activity.BrowseTopLevelCommentsActivity;
import ca.ualberta.cs.cmput301t02project.model.CommentModel;
import ca.ualberta.cs.cmput301t02project.view.CommentListAdapter;


public class CommentListAdapterAbstractionTest extends ActivityInstrumentationTestCase2<BrowseTopLevelCommentsActivity> {
	
	public CommentListAdapterAbstractionTest(){
		super(BrowseTopLevelCommentsActivity.class);
	}
	
	public void testSortByDate(){
		
		ArrayList<CommentModel> outOfOrderComments = new ArrayList<CommentModel>();
		outOfOrderComments.add(new CommentModel("post 1", null, "schmoop"));
		outOfOrderComments.add(new CommentModel("post 2", null, "schmoop"));
		outOfOrderComments.add(new CommentModel("post 3", null, "schmoop"));
		
		outOfOrderComments.set(0, new CommentModel("later comment", null, "schmoop"));

		
		ArrayList<CommentModel> inOrderComments = new ArrayList<CommentModel>();
		inOrderComments.add(new CommentModel("post 2", null, "schmoop"));
		inOrderComments.add(new CommentModel("post 3", null, "schmoop"));
		inOrderComments.add(new CommentModel("later comment", null, "schmoop"));
	
		
		CommentListAdapter adapter1;
		CommentListAdapter adapter2;
		
		adapter1 = new CommentListAdapter(getActivity(), R.layout.list_item, outOfOrderComments);
		adapter2 = new CommentListAdapter(getActivity(), R.layout.list_item, inOrderComments);
		adapter1.sortByDate();
		
		assertEquals("", adapter1.getItem(0), adapter2.getItem(0));
		assertEquals("", adapter1.getItem(1), adapter2.getItem(1));
		assertEquals("", adapter1.getItem(2), adapter2.getItem(2));
		
	}
}
