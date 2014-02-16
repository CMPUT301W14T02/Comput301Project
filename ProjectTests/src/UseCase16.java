import android.test.ActivityInstrumentationTestCase2;


public class UseCase16 extends ActivityInstrumentationTestCase2<T> {

	public void testDefaultSort(BrowseCommentsActivity activity) {
	    ArrayList<TopLevelCommentModel> arrayList1 = new ArrayList<TopLeveLCommentModel>;
	    for(TopLevelCommentModel comment : activity.arrayList){
	        arrayList1.add((TopLevelCommentModel) comment.copy());
	    }
	    Sort.sortByDate(arrayList1);
	    assertTrue("The arraylists should be the same to ensure default sort.", (arrayList1 == activity.arraylist);
	}
}
