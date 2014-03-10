package ca.ualberta.cs.cmput301t02project;

public class CurrentUser {

	private static User currentUser = new User("default");

	public CurrentUser(String name) {
		// TODO Auto-generated constructor stub
	}

	public static String getName() {
		return currentUser.getName();
	}

	public static void setName(String name) {
		currentUser.setName(name);
	}

}
