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
