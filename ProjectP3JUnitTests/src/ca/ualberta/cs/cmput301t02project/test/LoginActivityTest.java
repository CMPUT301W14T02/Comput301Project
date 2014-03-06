package ca.ualberta.cs.cmput301t02project.test;
import ca.ualberta.cs.cmput301t02project.CurrentUser;
import ca.ualberta.cs.cmput301t02project.LoginActivity;
import ca.ualberta.cs.cmput301t02project.User;
import android.test.ActivityInstrumentationTestCase2;

public class LoginActivityTest extends ActivityInstrumentationTestCase2<LoginActivity> {

	public LoginActivityTest() {
		super(LoginActivity.class);
	}
	
	// Use Case 20
	public void testGetName() {
		
		User user = new User("desiredName");
		
		assertEquals("Username should be saved in the User class", user.getName(), "desiredName");
	}
	
	public void testSetUsername() {
		
		User user = new User("desiredName");
		CurrentUser.setName(user.getName());
		
		assertEquals("CurrentUser name should match current user's username", CurrentUser.getName(), user.getName());
	}
	
	public void testInvalidUsername() {
		
		// blank username is invalid based on our use case -SB
		User user = new User("");
		CurrentUser.setName(user.getName());
		
		assertFalse("Blank usernames should not be set", CurrentUser.getName().equals(user.getName()) );
	}
}
