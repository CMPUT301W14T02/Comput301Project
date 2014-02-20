package ca.ualberta.cs.cmput301t02project;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateCommentActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_comment);
		
		EditText inputComment = (EditText) findViewById(R.id.create_text);
		
		Button post = (Button) findViewById(R.id.create_post);
		
		post.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//String comment = inputComment.getText().toString();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_comment, menu);
		return true;
	}

}
