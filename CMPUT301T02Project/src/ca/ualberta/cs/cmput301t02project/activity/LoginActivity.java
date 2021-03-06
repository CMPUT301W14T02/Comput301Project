package ca.ualberta.cs.cmput301t02project.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import ca.ualberta.cs.cmput301t02project.R;
import ca.ualberta.cs.cmput301t02project.model.User;

/**
 * Allows a user to log in to the app with a selected username.
 * If their username already exists, the previous data associated with that user is restored.
 * If their username does not already exist, it is created.
 * Called when the app starts or when "Logout" is selected from any class that extends ActionBarActivity.
 */
public class LoginActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// set up the screen display -SB		
		setContentView(R.layout.activity_login);
	}

	/**
	 * Reads and relays user's username input.
	 * <p>
	 * Called when login_button on the screen is pressed. 
	 * Takes a view as a parameter because of conventions for onClick in the the layout.
	 * <p>
	 * @param v	The current view. Variable is not used anywhere
	 */
	public void submitName(View v) {

		// Read user input -SB
		EditText enteredName = (EditText) findViewById(R.id.login_username);

		// Test if the input is allowed -SB
		login(enteredName.getText().toString());
	}

	/**
	 * Sets the current user's username.
	 * <p>
	 * Sets the current username in User.
	 * The current username is posted along with any comments the user makes. 
	 * The user's information including 
	 * old posted comments, favorite comments, and followed users can be restored after the user is set. 
	 * If an invalid username was entered, a message is printed to the screen.
	 * <p>
	 * @param username	The user's username input
	 */
	public void login(String username) {
		
		// Message on screen -SB
		TextView message = (TextView) findViewById(R.id.login_message);
		
		// If valid username set it -SB
		if (checkIfValid(username)) {

			// Reset message -SB
			message.setText("Login Page");
			
			// If user doesn't exist, create a user with that name -SB
			User.login(username, getApplicationContext());
			
			// Go to the main menu once user is set -SB
			startActivity(new Intent(LoginActivity.this, MainMenuActivity.class));
		}

		// If invalid username is provided ask user to try again -SB
		else {
			message.setText("Please enter a valid username");
		}
	}
	
	/**
	 * Checks if the username is valid.
	 * <p>
	 * If a invalid username is entered, false is returned. 
	 * Otherwise true is returned. 
	 * Example of an invalid username submission: "".
	 * Example of a valid username submission: "Bob".
	 * @param username	The username entered by the user
	 * @return	Returns false for invalid username, else true
	 */
	public boolean checkIfValid(String username) {
		return !username.equals("");
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    
	    switch (item.getItemId()) {
	    	case R.id.help_page:
	    		goToHelpPage();
	    		return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	/**
	 * Redirects to the login help page.
	 * <p>
	 * Called when the "Help" menu item from the action bar is pressed.
	 * <p>
	 */
	public void goToHelpPage(){
		
		// redirect to help page for logging in -SB
		Intent viewIntent = new Intent("android.intent.action.VIEW",Uri.parse(
				"https://rawgithub.com/CMPUT301W14T02/Comput301Project/master/Help%20Pages/login.html"));
		startActivity(viewIntent);
	}
}