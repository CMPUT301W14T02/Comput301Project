package ca.ualberta.cs.cmput301t02project;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

public class TopLevelListActivity extends Activity implements
	OnItemSelectedListener {

    public ListView topLevelCommentListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_top_level_list);
	topLevelCommentListView = (ListView) findViewById(R.id.commentListView);

	// Basedon://www.androidhive.info/2012/04/android-spinner-dropdown-example/

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
	// Creating adapter for spinner
	ArrayAdapter<String> spinner_adapter = new ArrayAdapter<String>(this,
		R.layout.list_item, sortBy);
	// Drop down layout style - list view with radio button
	spinner_adapter
		.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	// attaching data adapter to spinner
	spinner.setAdapter(spinner_adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(R.menu.top_level_list, menu);
	return true;
    }

    public void onStart() {
	super.onStart();
	CommentListModel commentListModel = ProjectApplication.getCommentList();
	ArrayList<CommentModelAbstraction> topLevelCommentList = commentListModel
		.getCommentList();
	// Add comments to adapter
	ArrayAdapter<CommentModelAbstraction> adapter = new ArrayAdapter<CommentModelAbstraction>(
		this, R.layout.list_item, topLevelCommentList);
	// Display comments in adapter
	topLevelCommentListView.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
	    long arg3) {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
	// TODO Auto-generated method stub
	
    }

}
