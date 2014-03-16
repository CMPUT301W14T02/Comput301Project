package ca.ualberta.cs.cmput301t02project.test;

import java.util.ArrayList;

import android.test.ActivityInstrumentationTestCase2;
import ca.ualberta.cs.cmput301t02project.R;
import ca.ualberta.cs.cmput301t02project.activity.BrowseReplyCommentsActivity;
import ca.ualberta.cs.cmput301t02project.activity.BrowseTopLevelCommentsActivity;
import ca.ualberta.cs.cmput301t02project.model.CommentModel;
import ca.ualberta.cs.cmput301t02project.view.CommentListAdapter;


public class CommentListAdapterAbstractionTest extends ActivityInstrumentationTestCase2<BrowseTopLevelCommentsActivity> {
	
	public CommentListAdapterAbstractionTest(){
		super(BrowseTopLevelCommentsActivity.class);
	}
	
	public void testSortByDate(){
		
		ArrayList<CommentModel> comments = new ArrayList<CommentModel>();
		//comments.add(new CommentModel("post 1", null, "schmoop"));
		
		CommentListAdapter adapter;
		
		adapter = new CommentListAdapter(getActivity(), R.layout.list_item, comments);
		assertTrue("", adapter.isEmpty());
		
	}
}
