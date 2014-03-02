package ca.ualberta.cs.cmput301t02project;

public class CurrentUser{

    private static String currentName = "";
	
	public CurrentUser(String name) {
		// TODO Auto-generated constructor stub
	}

    public static String getName(){
    	return currentName;
    }

    public static void setName(String name) {
    	CurrentUser.currentName = name;
    }
    

}
