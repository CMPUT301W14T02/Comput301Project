import android.test.ActivityInstrumentationTestCase2;


public class LoginActivityTest extends ActivityInstrumentationTestCase2<LoginActivity> {

	public LoginActivityTest() {
		super(LoginActivityTest.class);
	}
	
	public void testSetUsername() {
		User user = new User("desiredName");
		setUser(user);
		
		assertEquals("Username should have been set", user.getName(), "desiredName");
	}
}
