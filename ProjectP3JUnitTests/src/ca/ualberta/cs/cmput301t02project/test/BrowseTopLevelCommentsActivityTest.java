package ca.ualberta.cs.cmput301t02project.test;

import ca.ualberta.cs.cmput301t02project.activity.BrowseTopLevelCommentsActivity;
import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ViewAsserts;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class BrowseTopLevelCommentsActivityTest extends ActivityInstrumentationTestCase2<BrowseTopLevelCommentsActivity> {
	
	Instrumentation instrumentation;
	Activity activity;
	EditText textInput;
	
	public BrowseTopLevelCommentsActivityTest () {
		super(BrowseTopLevelCommentsActivity.class);
	}
	
	protected void setUp() throws Exception {
		super.setUp();
		instrumentation = getInstrumentation();
		activity = getActivity();

		textInput = ((EditText) activity.findViewById(ca.ualberta.cs.cmput301t02project.R.id.create_text));
	}
	
	
	public void testMakeTweet() throws Throwable{
		runTestOnUiThread(new Runnable()
		{
			
			@Override
			public void run() {
				ListView listView = (ListView)activity.findViewById(ca.ualberta.cs.cmput301t02project.R.id.commentListView);
				Adapter adapter = listView.getAdapter();
				int original_size = adapter.getCount();
				original_size++;
				String tweet = "TDD 4 LYFE #YOLO";
				createComment(tweet);
				int new_size = adapter.getCount();
				assertEquals("Size of original adapter should be increased by 1", original_size, new_size);
				
				
			}
		});
	}
	
	private void createComment(String text) {
		assertNotNull(activity.findViewById(ca.ualberta.cs.cmput301t02project.R.id.create_post));
		textInput.setText(text);
		((Button) activity.findViewById(ca.ualberta.cs.cmput301t02project.R.id.create_post)).performClick();
	}
	
	/*
     * Code for Use Case 4 tests start here Test to see if text is being
     * displayed
     */
    public void testDisplayTopLevelComment() {
    /*	
    	Intent intent = new Intent();
    	String text = "NewTopLevelComment";
    	intent.putExtra(BrowseTopLevelCommentsActivity.TEXT_KEY, text);
    	
    	setActivityIntent(intent);
    	
    	BrowseTopLevelCommentsActivity activity = getActivity();
    	
    	ListView listView = (ListView) activity.findViewById(ca.ualberta.cs.cmput301t02project.R.id.commentListView);
	
    	assertEquals("text should be displayed", text, listView.getItemAtPosition(0).toString());
    	
    	
    	BrowseTopLevelCommentsActivity act = new BrowseTopLevelCommentsActivity();
    	act.addContentView(act.findViewById(ca.ualberta.cs.cmput301t02project.R.id.commentListView), null);
    	
    	Intent intent = new Intent();
    	String text = "NewTopLevelComment";
    	setActivityIntent(intent);
    	
    	BrowseTopLevelCommentsActivity activity = getActivity();
    	ListView listView = (ListView) activity.findViewById(ca.ualberta.cs.cmput301t02project.R.id.commentListView);
    	assertEquals("text should be displayed", text, listView.getItemAtPosition(0).toString());
    	
    	*/
    }

}
