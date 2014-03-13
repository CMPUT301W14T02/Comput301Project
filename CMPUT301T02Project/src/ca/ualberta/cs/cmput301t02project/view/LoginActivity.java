package ca.ualberta.cs.cmput301t02project.view;

import ca.ualberta.cs.cmput301t02project.R;
import ca.ualberta.cs.cmput301t02project.UserList;
import ca.ualberta.cs.cmput301t02project.R.id;
import ca.ualberta.cs.cmput301t02project.R.layout;
import ca.ualberta.cs.cmput301t02project.R.menu;
import ca.ualberta.cs.cmput301t02project.activity.MainMenuActivity;
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

	// accessed when login_button is pressed -SB
	public void submitName(View v) {

		// read user input -SB
		EditText enteredName = (EditText) findViewById(R.id.login_username);

		// test if the input is allowed -SB
		setUsername(enteredName.getText().toString());
	}

	// return false for invalid usernames, true for valid ones -SB
	public boolean checkIfValid(String username) {
		if (username.equals("")) {
			return false;
		} else {
			return true;
		}
	}

	// checks if a name passed in is valid -SB
	public void setUsername(String username) {

		// message on screen -SB
		TextView message = (TextView) findViewById(R.id.login_message);

		// if valid username set it -SB
		if (checkIfValid(username)) {

			// reset message -SB
			message.setText("Login Page");

			if (!UserList.findUser(username)) {

				// if user doesn't exist, create a user with that name -SB
				UserList.createUser(username);
			}

			// go to the main menu once user is set -SB
			startActivity(new Intent(LoginActivity.this, MainMenuActivity.class));
		}

		// if invalid username is provided ask user to try again -SB
		else {
			message.setText("Please enter a valid username");
		}
	}
}
