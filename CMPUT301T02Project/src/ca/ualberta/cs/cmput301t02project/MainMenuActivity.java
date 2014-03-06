package ca.ualberta.cs.cmput301t02project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import android.location.Location;
public class MainMenuActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.activity_main);

    	TextView welcomeMessage = (TextView) findViewById(R.id.welcome_message);
    	welcomeMessage.setText("Welcome " + CurrentUser.getName().toString() + "!");
    	
    	Button createComment = (Button) findViewById(R.id.create);
    	createComment.setOnClickListener(new View.OnClickListener() {
	    @Override
	    public void onClick(View v) {
	    	startActivity(new Intent(MainMenuActivity.this,
	    		CreateCommentActivity.class));
	    }
	});

	Button browseComment = (Button) findViewById(R.id.browse);
	browseComment.setOnClickListener(new View.OnClickListener() {
	    @Override
	    public void onClick(View v) {
	    	startActivity(new Intent(MainMenuActivity.this,
	    			BrowseTopLevelCommentsActivity.class));
	    }
	});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	// Inflate the menu; this adds items to the action bar if it is present.
    	getMenuInflater().inflate(R.menu.main, menu);
    	return true;
    }

}
