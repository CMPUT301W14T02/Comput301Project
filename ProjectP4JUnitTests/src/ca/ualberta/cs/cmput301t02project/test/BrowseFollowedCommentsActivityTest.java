package ca.ualberta.cs.cmput301t02project.test;

import android.test.ActivityInstrumentationTestCase2;
import ca.ualberta.cs.cmput301t02project.activity.BrowseFollowedCommentsActivity;
import ca.ualberta.cs.cmput301t02project.activity.LoginActivity;
import ca.ualberta.cs.cmput301t02project.model.User;

public class BrowseFollowedCommentsActivityTest extends ActivityInstrumentationTestCase2<BrowseFollowedCommentsActivity> {

	public BrowseFollowedCommentsActivityTest() {
		super(BrowseFollowedCommentsActivity.class);
	}
	
	public void login(){
		LoginActivity loginActivity = new LoginActivity();	
		User.login("user", loginActivity.getBaseContext());
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
		login();
		
	//	BrowseFollowedCommentsActivity activity = getActivity();
		//ListView view = (ListView) activity.findViewById(R.id.commentListView);
		//ViewAsserts.assertOnScreen(activity.getWindow().getDecorView(), view); 
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
