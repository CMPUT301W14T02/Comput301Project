package ca.ualberta.cs.cmput301t02project;

import java.util.ArrayList;

public class UserList {

	// there should only be one userList
	static ArrayList<User> userList;
	
	public UserList() {
		// TODO Auto-generated constructor stub
	}
	
	// returns user if one with that username exists, else returns null
	public static User findUser (String username){
		
		for(int i=0; i<userList.size()-1; i++){
			if(userList.get(i).getName() == username){
				return userList.get(i);
			}
		}
		
		return null;
	}
	
	public static void createUser (String username){
		User.setCurrentName(username);
	}

}
