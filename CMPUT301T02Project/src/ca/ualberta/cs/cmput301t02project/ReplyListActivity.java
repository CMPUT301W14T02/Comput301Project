package ca.ualberta.cs.cmput301t02project;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

public class ReplyListActivity extends Activity implements
	OnItemSelectedListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_reply_list);
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
	ArrayAdapter<String> spinner_adapter = new ArrayAdapter<String>(this,
		R.layout.list_item, sortBy);
	// Drop down layout style - list view with radio button
	spinner_adapter
		.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	// attach adapter to spinner
	spinner.setAdapter(spinner_adapter);
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
