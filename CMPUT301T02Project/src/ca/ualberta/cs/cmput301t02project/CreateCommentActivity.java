package ca.ualberta.cs.cmput301t02project;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
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

	Button post = (Button) findViewById(R.id.create_post);
	
	final Context context = this;  //used to pass to LocationModel -KW

	post.setOnClickListener(new View.OnClickListener() {
	    @Override
	    public void onClick(View v) {
	    	
	    	EditText inputComment = (EditText) findViewById(R.id.create_text);
	    	
	    	/*I'm don't think that this is supposed to go here (I think we said CommentListController), 
	    	but since the new CommentModel creation is happening here, I'm gonna put it here for now
	    	and move it into the correct file later on -KW
	    	*/ 
	    	
	    	LocationModel locMod = new LocationModel(context);
	    	Location loc = locMod.getLocation(); // Value of loc here is null. Is onLocationChanged not being called? -KW
	    	
	    	/*double lat = loc.getLatitude();
	    	String strLat = String.valueOf(lat);
	    	double lon = loc.getLongitude();
	    	String strLon = String.valueOf(lon); */
	    	
	    	
	    	// Refactor into MVC?
	    	// Username is temporarily in front of the comment. Can redesign later -SB
	    	CommentModel comment = new CommentModel(CurrentUser.getName().toString() +
	    			": " + inputComment.getText().toString(), null, 
	    			CurrentUser.getName().toString() );//+ "\nLocation: "+strLat+", "+strLon);
	    	ArrayList<CommentModel> commentList = ProjectApplication.getCurrentCommentList();
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
