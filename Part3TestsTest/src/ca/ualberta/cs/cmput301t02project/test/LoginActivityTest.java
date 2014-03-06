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
	public void testSetUsername() {
		
		User user = new User("desiredName");
		CurrentUser.setName(user.getName());
		
		assertEquals("Username should have been set", user.getName(), "desiredName");
	}
}
