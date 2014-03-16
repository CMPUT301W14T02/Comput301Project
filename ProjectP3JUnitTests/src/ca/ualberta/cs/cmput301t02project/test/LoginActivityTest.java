package ca.ualberta.cs.cmput301t02project.test;
import ca.ualberta.cs.cmput301t02project.ProjectApplication;
import ca.ualberta.cs.cmput301t02project.activity.LoginActivity;
import ca.ualberta.cs.cmput301t02project.User;
import android.test.ActivityInstrumentationTestCase2;

public class LoginActivityTest extends ActivityInstrumentationTestCase2<LoginActivity> {

	public LoginActivityTest() {
		super(LoginActivity.class);
	}
	
	// Use Case 20 - part 1 -SB
	public void testGetName() {
		
		User user = new User("desiredName");
		
		assertEquals("Username should be saved in the User class", user.getName(), "desiredName");
	}
	
	// Use Case 20 - part 2 -SB
	public void testSetUsername() {
		
		User user = new User("desiredName");
		ProjectApplication.setName(user.getName());
		
		assertEquals("CurrentUser name should match current user's username", ProjectApplication.getName(), user.getName());
	}
	
	// Use Case 20 - part 3 -SB
	public void testInvalidUsername() {

		// start the loginActivity and input blank name
		LoginActivity login = new LoginActivity();
		
		// blank username is invalid based on our use case -SB
		assertFalse("Empty usernames should not be set", login.checkIfValid(""));
		assertTrue("Non-empty username should be set", login.checkIfValid("u"));
	}
	
	/* test to see if user is being pushed to server after update */
	public void testPushUser() {
		assertTrue(false);
	}
}
