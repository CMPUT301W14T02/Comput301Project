package ca.ualberta.cs.cmput301t02project;

import java.util.ArrayList;

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

	final EditText inputComment = (EditText) findViewById(R.id.create_text);

	Button post = (Button) findViewById(R.id.create_post);

	post.setOnClickListener(new View.OnClickListener() {
	    @Override
	    public void onClick(View v) {
		// Refactor into MVC?
		CommentModel comment = new CommentModel(
			inputComment.getText().toString(), null, null);
		ArrayList<CommentModel> commentList = ProjectApplication
			.getCurrentCommentList();
		commentList.add(comment);
		finish();
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
