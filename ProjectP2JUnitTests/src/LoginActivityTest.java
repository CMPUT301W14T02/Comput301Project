import android.test.ActivityInstrumentationTestCase2;

public class LoginActivityTest extends ActivityInstrumentationTestCase2<LoginActivity> {

	public LoginActivityTest() {
		super(LoginActivity.class);
	}
	
	// Use Case 20
	public void testSetUsername() {
		User user = new User("desiredName");
		setUser(user);
		
		assertEquals("Username should have been set", user.getName(), "desiredName");
	}
}
