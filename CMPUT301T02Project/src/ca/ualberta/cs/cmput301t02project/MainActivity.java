package ca.ualberta.cs.cmput301t02project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);

	Button createComment = (Button) findViewById(R.id.create);
	createComment.setOnClickListener(new View.OnClickListener() {
	    @Override
	    public void onClick(View v) {
		startActivity(new Intent(MainActivity.this,
			CreateCommentActivity.class));
	    }
	});

	Button browseComment = (Button) findViewById(R.id.browse);
	browseComment.setOnClickListener(new View.OnClickListener() {
	    @Override
	    public void onClick(View v) {
		startActivity(new Intent(MainActivity.this,
			TopLevelListActivity.class));
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
