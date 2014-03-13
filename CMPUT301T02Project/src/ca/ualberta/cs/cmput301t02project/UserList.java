package ca.ualberta.cs.cmput301t02project;

import java.util.ArrayList;

public class UserList {

	// there should only be one userList -SB
	static ArrayList<User> userList = new ArrayList<User>();

	public UserList() {
		// TODO Auto-generated constructor stub
	}

	// sets current user if one with that username exists, else returns false
	// -SB
	public static boolean findUser(String username) {

		for (int i = 0; i < userList.size(); i++) {
			if (userList.get(i).getName().equals(username)) {
				ProjectApplication.setName(userList.get(i).getName());
				return true;
			}
		}
		return false;
	}

	public static void createUser(String username) {
		ProjectApplication.setName(username);
	}

}
