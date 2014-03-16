package ca.ualberta.cs.cmput301t02project.test;

import ca.ualberta.cs.cmput301t02project.ProjectApplication;
import ca.ualberta.cs.cmput301t02project.UserList;
import ca.ualberta.cs.cmput301t02project.activity.LoginActivity;
import android.test.ActivityInstrumentationTestCase2;

public class UserListTest extends ActivityInstrumentationTestCase2<LoginActivity> {
	
	public UserListTest () {
		super(LoginActivity.class);
	}
	
	 /* Use Case 20 tests */
	// Test the createUser method in UserList -SB
	public void testCreateUser(){
		String name = "Schmoopy";
		UserList.createUser(name);
		
		assertEquals("Creating a user should set the name in project application", ProjectApplication.getName(), name);
	}
	
	// Test the findUser method in UserList -SB
	public void testFindUser(){
		String name = "Schmoopy";
		UserList.createUser(name);
		
		assertTrue("User should be found in userlist when created", UserList.findUser(name));
	}
	
}
