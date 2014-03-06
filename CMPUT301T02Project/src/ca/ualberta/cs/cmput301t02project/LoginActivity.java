package ca.ualberta.cs.cmput301t02project;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}
	
	// checks if a name is valid once login_button is pressed -SB
	public void checkIfValid (View v) {
    	
		// message on screen -SB
		TextView message = (TextView) findViewById(R.id.login_message);
		
		// read user input -SB 
    	EditText enteredName = (EditText) findViewById(R.id.login_username);
    	String username = enteredName.getText().toString();
		
    	// if blank username is provided ask user to try again -SB
    	if (username.equals("")){
    		
    		message.setText("Enter valid username");
    	}
    	else {
    		
    		// reset message -SB
    		message.setText("Login Page");
    		setUsername(username);
    		
    		// go to the main menu once user is set -SB
    		startActivity(new Intent(LoginActivity.this, MainMenuActivity.class));

    	}
	}
	
	// set the username for the user -SB
	public void setUsername (String username) {
		
		if (! UserList.findUser(username)){
    		
    		// if user doesn't exist, create a user with that name -SB
    		UserList.createUser(username);
    	}
	}
}
