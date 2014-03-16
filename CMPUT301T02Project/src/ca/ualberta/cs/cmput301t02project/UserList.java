package ca.ualberta.cs.cmput301t02project;

import java.util.ArrayList;

/**
 * UserList is used to store the list of all users of the app
 * This class will likely be removed once Elastic Search is set up.
 * UserList contains methods to find if a user already exist
 * and create a user to add to the list of all users.
 * UserList is used when a user tries to log in to the app with a username
 */
public class UserList {

	// There should only be one userList -SB
	static ArrayList<User> userList = new ArrayList<User>();

	/**
	 * Creates a new empty list of users
	 */
	public UserList() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Sets the current user if one with that username exists
	 * <p>
	 * If a user with the passed in username exists in the list of users, the
	 * current user is ProjectApplication is set to the user with that username
	 * and true is returned.
	 * If the username does not exist in the list of users createUser is called and false is returned.
	 * This method is used once a user submits a username in LoginActivity. 
	 * Example of a username: "Bob".
	 * <p>
	 * @param username	The username to check for in the list of all users
	 * @return	Returns true if a username was found in the list, false if it creates a new user
	 * @see User	Example of a User
	 */
	public static boolean findUser(String username) {

		// Check if the username exists in the list of all users -SB
		for (int i = 0; i < userList.size(); i++) {
			if (userList.get(i).getName().equals(username)) {
				ProjectApplication.setName(userList.get(i).getName());
				return true;
			}
		}
		
		// If the username is not found in the list of all users, create the user and return false -SB
		createUser(username);
		return false;
	}

	/**
	 * Creates a new user with a given username and adds it to the list of all users.
	 * <p>
	 * Used when a new User needs to be created.
	 * Called from findUser to create a new user if the username doesn't already exist.
	 * Example of a username: "Bob".
	 * <p>
	 * @param username	The desired username of the User
	 * @see User	Example of a User
	 */
	public static void createUser(String username) {
		
		ProjectApplication.setName(username);
		
		// Add new user to the userlist -SB
		userList.add(new User(username));
	}

}
