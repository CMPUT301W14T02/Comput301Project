package ca.ualberta.cs.cmput301t02project.test;

import ca.ualberta.cs.cmput301t02project.activity.LoginActivity;
import ca.ualberta.cs.cmput301t02project.model.User;
import android.test.ActivityInstrumentationTestCase2;

public class UserTest extends ActivityInstrumentationTestCase2<LoginActivity> {
	
	public UserTest () {
		super(LoginActivity.class);
	}
	
	 /* Use Case 20 tests */
	// Test setting a user's username
	public void testCreateUser(){
		String name = "Schmoopy";
		User.getUser().setName(name);
		
		assertEquals("Creating a user should set the name of the current user in User", User.getUser().getName(), name);
	}
	
}
