package ca.ualberta.cs.cmput301t02project.activity;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import ca.ualberta.cs.cmput301t02project.R;

/**
 * Contains common code inherited by activities that have an action bar menu.
 * Options on the menus may include help, home, and logout.
 * Contains methods for 
 * setting up an action-bar menu and 
 * dealing with menu items being clicked.
 * Extended by all the Browse* activities, CreateComment, EditComment, and MainMenu activities.
 * Each Class that inherits ActionBarActivity should implement its own help page using the goToHelpPage method.
 */
public class ActionBarActivity extends Activity {
	
	
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
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.comments_list, menu);
		
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
