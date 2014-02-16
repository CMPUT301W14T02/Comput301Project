//use case: Attach Picture to Comment

public void testAddedPicture(CommentModel comment) throws throwable{
	String pic = comment.getPicture();
	assertFalse("The picture string should no longer be null", null==pic);
}

// use case: Edit Comment

public void testEditComment(CommentModel comment) throws Throwable{
		runTestOnUiThread(new Runnable()
		{

			@Override
			public void run() {
				ListView listView = (ListView)activity.findViewById(ca.ualberta.cs.project301.R.id.oldComment);
				Adapter adapter = listView.getAdapter();
				String oldText = comment.getText(); 
				String newText = "This comment has been changed!!!";
				editComment(newText);
				assertFalse("The text should not be equal to the old text", ( oldText==comment.getText() ) );


			}
		});
	}	
		
// use case: View Comments by Pictures

public void testSortedByPictures(CommentListModel picComments) throws Throwable{
		runTestOnUiThread(new Runnable()
		{

			@Override
			public void run() {
				ListView listView = (ListView)activity.findViewById(ca.ualberta.cs.project301.R.id.oldComment);
				Adapter adapter = listView.getAdapter();
				CommentListModel unsorted = picComments; 
				CommentListModel picSorted = sortByPictures(picComments);
				assertFalse("The text should not be equal to the old text", ( unsorted==picSorted ) );


			}
		});
		
	}	
		
// use case: View Commentor Username

public void testDisplayUsername() {
        Intent intent = new Intent();
        String text = "ThisIsMyName";
        intent.putExtra(BrowsingCommentsActivity.TEXT_KEY, text);
        setActivityIntent(intent);
        BrowsingCommentsActivity activity = getActivity();
        TextView textView = (TextView)activity.findViewById(ca.ualberta.cs.project301.R.id.commenterUsername);
        assertEquals("Username TextView should be visible", text, textView.getText().toString());
    }  