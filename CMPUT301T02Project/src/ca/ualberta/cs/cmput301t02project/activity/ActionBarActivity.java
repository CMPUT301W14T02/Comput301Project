package ca.ualberta.cs.cmput301t02project.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import ca.ualberta.cs.cmput301t02project.R;

/**
 * Contains common code inherited by activities that have an action bar menu.
 * Options on the menus may include help, home, and logout.
 * Contains methods for 
 * setting up an action-bar menu and 
 * dealing with menu items being clicked.
 * Extended by all the Browse* activities, CreateComment, EditComment, and MainMenu activities.
 * Also contains a method for displaying a popup message on the screen so that all activities can use it. 
 * Each Class that inherits ActionBarActivity should implement its own help page using the goToHelpPage method.
 */
public class ActionBarActivity extends Activity {

	/**
	 * Prints a pop-up message on the screen.
	 * <p>
	 * The screen and message are specified in the parameters.
	 * context determines which activity to display the message. 
	 * An example input of the context parameter is "BrowseReplyCommentsActivity.this".
	 * message specifies what to print. 
	 * An example input of the message parameter is "No internet connection :(".
	 * This method is called from the Browse* activities that extend BrowseCommentsActivityAbstraction.
	 * <p>
	 * @param context	the calling activity's context
	 * @param message	the desired message to print
	 */
	public void showMessage(Context context, String message){
		int duration = Toast.LENGTH_SHORT;
		Toast toast = Toast.makeText(context, message, duration);
		toast.show();
	}
	
	/**
	 * Redirects to the help page for that activity
	 * <p>
	 * If goToHelpPage method is not re-implemented in a class that extends this class then default help page is used.
	 * Any classes that extend BrowseCommentsActivityAbstraction should override this method to set their own specific help page.
	 * All the Browse* classes override this method to set their own specific help page
	 * <p>
	 */
	public void goToHelpPage(){
		// default help page -SB
		Intent viewIntent = new Intent("android.intent.action.VIEW",Uri.parse(
				"https://rawgithub.com/CMPUT301W14T02/Comput301Project/master/Help%20Pages/help.html"));
		startActivity(viewIntent);
	}
	
	/**
	 * Return to the login screen.
	 * <p>
	 * Redirects page to the login page (LoginActivity).
	 * Currently does not unset the User. 
	 * Called by any classes with a logout button on their menu that extend BrowseCommentsActivityAbstraction, 
	 * a.k.a. all the Browse* activities.
	 * <p>
	 */
	public void logout(){
		Intent logoutActivity = new Intent(getApplicationContext(),LoginActivity.class);
		startActivity(logoutActivity);
	}
	
	/**
	 * Return to the main menu (homepage).
	 * <p>
	 * Redirects page to the homepage (MainMenuActivity).
	 * Called by any classes with a home button on their menu that extend BrowseCommentsActivityAbstraction, 
	 * a.k.a. all the Browse* activities.
	 * <p>
	 */
	public void returnToHomepage(){
		Intent goHome = new Intent(getApplicationContext(),MainMenuActivity.class);
		startActivity(goHome);
	}
	
	// This method is overriden by MainMenuActivity, which uses a different menu that does not include home.-SB
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		/*
		 *  default_menu is the default menu that contains options to
		 *  go to a help page
		 *  return to the home page
		 *  log out
		 */
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.default_menu, menu);
		
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    
	    switch (item.getItemId()) {
	    	case R.id.help_page:
	    		goToHelpPage();
	    		return true;
	    	case R.id.home:
	    		returnToHomepage();
	    		return true;
	        case R.id.logout:
	            logout();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
}
