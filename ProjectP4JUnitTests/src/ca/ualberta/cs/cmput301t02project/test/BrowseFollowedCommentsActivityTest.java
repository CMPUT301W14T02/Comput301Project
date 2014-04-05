package ca.ualberta.cs.cmput301t02project.test;

import ca.ualberta.cs.cmput301t02project.R;
import ca.ualberta.cs.cmput301t02project.activity.BrowseFavoritesActivity;
import ca.ualberta.cs.cmput301t02project.activity.BrowseFollowedCommentsActivity;
import ca.ualberta.cs.cmput301t02project.activity.BrowseMyCommentsActivity;
import ca.ualberta.cs.cmput301t02project.model.CommentListModel;
import ca.ualberta.cs.cmput301t02project.model.CommentModel;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ViewAsserts;
import android.widget.ListView;

public class BrowseFollowedCommentsActivityTest extends ActivityInstrumentationTestCase2<BrowseFollowedCommentsActivity> {

	public BrowseFollowedCommentsActivityTest() {
		super(BrowseFollowedCommentsActivity.class);
	}
	
	public void testDisplayFollowedComments(){
		// add comment for user
		// follow user
		// check listview
		/*CommentModel comment = initializeComment();
		CommentListModel myComments = new CommentListModel();
		myComments = ProjectApplication.getInstance().getUser().getMyComments();
		myComments.add(comment);
		*/

		//ListView view = (ListView) getActivity().findViewById(R.id.commentListView);
		//assertEquals("text should be displayed", comment.toString(), view.getAdapter().getItem(0).toString());
	}
	
	public void testVisibleListView() {
		ListView view = (ListView) getActivity().findViewById(R.id.commentListView);
		BrowseFollowedCommentsActivity activity = getActivity();
		ViewAsserts.assertOnScreen(activity.getWindow().getDecorView(), view);
	}
	
	public void testSortByDefault (){
		
	}
	
	/* 
	 * Sorting tests
	 */
	public void testSortByDate(){
		
	}
	
	public void testSortByPicture(){
		
	}
	
	public void testSortByMyLocation(){
		
	}
	
	public void testSortByOtherLocation(){
		
	}
	
	public void testSortByFavorites(){
		
	}
}
