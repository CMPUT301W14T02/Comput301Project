package ca.ualberta.cs.cmput301t02project;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		// when user presses the login button goto MainActivity -SB
		Button login = (Button) findViewById(R.id.login_button);
		login.setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View v) {
		    	
		    	// set username -SB 
		    	EditText userName = (EditText) findViewById(R.id.login_username);
		    	
		    	// find if user exists -SB
		    	if (! UserList.findUser(userName.getText().toString())){
		    		
		    		// if user doesn't exist, create a user with that name -SB
		    		UserList.createUser(userName.getText().toString());
		    	}
				
				startActivity(new Intent(LoginActivity.this, MainMenuActivity.class));
		    }
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

}
