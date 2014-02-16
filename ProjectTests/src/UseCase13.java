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
