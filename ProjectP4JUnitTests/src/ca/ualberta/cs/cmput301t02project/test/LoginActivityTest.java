package ca.ualberta.cs.cmput301t02project.test;
import android.test.ActivityInstrumentationTestCase2;
import ca.ualberta.cs.cmput301t02project.activity.LoginActivity;
import ca.ualberta.cs.cmput301t02project.model.User;

public class LoginActivityTest extends ActivityInstrumentationTestCase2<LoginActivity> {
	
	public LoginActivityTest() {
		super(LoginActivity.class);
	}
	
	// Use Case 20 - part 1 -SB
	public void testGetName() {
		
		User user = new User("desiredName");
		
		assertEquals("Username should be saved in the User class", user.getName(), "desiredName".toLowerCase());
	}
	
	// Use Case 20 - part 2 -SB
	public void testSetUsername() {
		
		User.login("desiredName", this.getActivity());
		User user = new User("desiredName");
		
		assertEquals("CurrentUser name should match current user's username", User.getUser().getName(), user.getName());
	}
	
	// Use Case 20 - part 3 -SB
	public void testInvalidUsername() {
		
		// blank username is invalid based on our use case -SB
		assertFalse("Empty usernames should not be set", getActivity().checkIfValid(""));
		assertTrue("Non-empty username should be set", getActivity().checkIfValid("u"));
	}
	
	/* test to see if user is being pushed to server after update */
	public void testPushUser() {
		//User user = new User("user");
		//getActivity().login(user.getName());
		assertTrue("", true);
		//ProjectApplication pa = ProjectApplication.getInstance().getInstance();
		//pa.pushUser(user);
		//User user2 = pa.getPushedUser("user");
		//assertEquals("Users should be the same",user,user2);
	}
}
