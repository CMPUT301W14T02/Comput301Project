package ca.ualberta.cs.cmput301t02project;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TopLevelListActivity extends Activity {

    public ListView topLevelCommentListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_top_level_list);
	topLevelCommentListView = (ListView) findViewById(R.id.commentListView);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(R.menu.top_level_list, menu);
	return true;
    }

    public void onStart() {
	super.onStart();
	CommentListModel topLevelCommentList = ProjectApplication
		.getCommentList();
	ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
		R.layout.list_item);
	for (int i = 0; i < topLevelCommentList.size(); i++) {
	    //adapter.add();
	}
    }

}
