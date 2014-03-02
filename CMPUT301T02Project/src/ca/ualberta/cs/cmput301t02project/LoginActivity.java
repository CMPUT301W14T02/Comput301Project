package ca.ualberta.cs.cmput301t02project;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		// When user presses the login button goto MainActivity
		Button login = (Button) findViewById(R.id.login_button);
		login.setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View v) {
			startActivity(new Intent(LoginActivity.this,
				MainActivity.class));
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
