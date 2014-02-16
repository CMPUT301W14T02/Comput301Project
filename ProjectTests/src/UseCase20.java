import android.test.ActivityInstrumentationTestCase2;

public class LoginActivityTests extends ActivityInstrumentationTestCase2<LoginActivity> {

	public LoginActivityTests() {
		super(LoginActivity.class);
	}
	
	public void testSetUsername() {
		User user = new User("desiredName");
		setUser(user);
		
		assertEquals("Username should have been set", user.getName(), "desiredName");
	}
}