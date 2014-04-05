package ca.ualberta.cs.cmput301t02project.test;

import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;
import ca.ualberta.cs.cmput301t02project.activity.LoginActivity;
import ca.ualberta.cs.cmput301t02project.model.User;

public class UserTest extends ActivityInstrumentationTestCase2<LoginActivity> {
	
	private Context context;
	public UserTest () {
		super(LoginActivity.class);

		
	}
	public void setUp() {
		context = getInstrumentation().getContext();
	}
	
	 /* Use Case 20 tests */
	// Test setting a user's username
	public void testCreateUser(){
		String name = "schmoopy";
		User.login(name, context);
		User.getUser().setName(name);
		
		assertEquals("Creating a user should set the name of the current user in User", User.getUser().getName(), name);
	}
	
}
