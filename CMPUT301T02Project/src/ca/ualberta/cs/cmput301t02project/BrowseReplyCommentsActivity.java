package ca.ualberta.cs.cmput301t02project;

import java.util.ArrayList;

import ca.ualberta.cs.cmput301t02project.model.CommentModel;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class BrowseReplyCommentsActivity extends Activity implements OnItemSelectedListener {

	//TODO: Refactor using new classes
	//private CommentListModel replyList;
	//private CommentListAdapter replyListAdapter;
	//private CommentModel selectedComment;
	
    private ListView replyCommentListView;
    private TextView selectedComment;
    private ArrayAdapter<CommentModel> adapter;
    private ArrayList<CommentModel> replyCommentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.activity_reply_list);
    	replyCommentListView = (ListView) findViewById(R.id.replyListView);
    	selectedComment = (TextView) findViewById(R.id.selected_comment);

    	// Display selected comment
    	selectedComment.setText(ProjectApplication.getCurrentComment().getText());

    	// Based on:
    	// //www.androidhive.info/2012/04/android-spinner-dropdown-example/
    	// Spinner element
    	Spinner spinner = (Spinner) findViewById(R.id.spinner);
    	
    	// Spinner click listener
    	spinner.setOnItemSelectedListener(this);
	
    	// Spinner Drop down elements
    	ArrayList<String> sortBy = new ArrayList<String>();
    	sortBy.add("Date");
    	sortBy.add("Picture");
    	sortBy.add("My Location");
    	sortBy.add("Other Location");
    	sortBy.add("Ranking");
	
    	// Create adapter for spinner
    	ArrayAdapter<String> spinner_adapter = new ArrayAdapter<String>(this, R.layout.list_item, sortBy);
	
    	// Drop down layout style - list view with radio button
    	spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	
    	// attach adapter to spinner
    	spinner.setAdapter(spinner_adapter);

    	// Get the comment list of replies to selected comment
    	replyCommentList = ProjectApplication.getCurrentCommentList();
	
    	// Add comments to adapter
    	adapter = new ArrayAdapter<CommentModel>(this, R.layout.list_item, replyCommentList);
	
    	// Display comments in adapter
    	replyCommentListView.setAdapter(adapter);

    	// If replying to comment -SB
    	Button replyComment = (Button) findViewById(R.id.reply_button);
    	replyComment.setOnClickListener(new View.OnClickListener() {
    		@Override
    		public void onClick(View v) {
    			startActivity(new Intent(BrowseReplyCommentsActivity.this, CreateCommentActivity.class));
    		}
    	});

    	// To view the replies of a reply -SB
    	replyCommentListView.setOnItemClickListener(new OnItemClickListener() {
	    @Override
	    	public void onItemClick(AdapterView<?> l, View v, int position, long id) {
	    		// Refactor into MVC?
	    		CommentModel nestedComment = (CommentModel) adapter.getItem(position);
	    		ProjectApplication.setCurrentComment(nestedComment);
	    		ArrayList<CommentModel> nestedCommentList = nestedComment.getReplies();
	    		ProjectApplication.setCurrentCommentList(nestedCommentList);

	    		Intent goToReplyListActivity = new Intent(getApplicationContext(), BrowseReplyCommentsActivity.class);
	    		startActivity(goToReplyListActivity);
	    	}
	    });
    }

    
    @Override
    public void onResume() {
    	super.onResume();
    	// Get the comment list of replies to selected comment
    	//ArrayList<CommentModel> replyCommentList = ProjectApplication
    	//	.getCurrentCommentList();
    	
    	// Add comments to adapter
    	ProjectApplication.setCurrentCommentList(replyCommentList);
    	adapter = new ArrayAdapter<CommentModel>(this, R.layout.list_item, replyCommentList);
	
    	// Display comments in adapter
    	replyCommentListView.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
	// TODO Auto-generated method stub
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
	// TODO Auto-generated method stub
    }
}
