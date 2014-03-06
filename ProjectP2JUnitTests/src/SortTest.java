import java.util.ArrayList;

import ca.ualberta.cs.cmput301t02project.LocationModel;
import ca.ualberta.cs.cmput301t02project.TopLevelCommentModel;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Adapter;
import android.widget.ListView;

public class SortTest extends ActivityInstrumentationTestCase2<Sort> {
    // Sort class doesn't currently exist. If name changes, this will need to be
    // modified to account for it
    public SortTest() {
	super(Sort.class);

    }

    // Use Case 1
    public void testSortByMyLocation(ArrayList<LocationModel> locationList) {
	/*I wasn't sure it the ArrayList was supposed to compose of the default android Location or the LocationModel
	in the function parameters. I changed it to LocationModel */
	    TopLevelCommentModel topComment1 = new TopLevelCommentModel("name1", locationList.get(1));
	    TopLevelCommentModel topComment2 = new TopLevelCommentModel("name2", locationList.get(2));
	    TopLevelCommentModel topComment3 = new TopLevelCommentModel("name3", locationList.get(3));
	    TopLevelCommentModel topComment4 = new TopLevelCommentModel("name4", locationList.get(4));
	    TopLevelCommentModel topComment5 = new TopLevelCommentModel("name5", locationList.get(5));
	    ArrayList<TopLevelCommentModel> arrayList1 = new ArrayList<TopLevelCommentModel>;
	    ArrayList<TopLevelCommentModel> arrayList2 = new ArrayList<TopLevelCommentModel>;
	    arrayList1.add(topComment2);
	    arrayList1.add(topComment1);
	    arrayList1.add(topComment5);
	    arrayList1.add(topComment4);
	    arrayList1.add(topComment3);
	    arrayList2.add(topComment1);
	    arrayList2.add(topComment2);
	    arrayList2.add(topComment3);
	    arrayList2.add(topComment4);
	    arrayList2.add(topComment5);
	    Sort.sortByMyLocation(arrayList1, locationList.get(0));
	    assertTrue("The arraylists should be the same after the sort", (arrayList1 == arrayList2));
	}

    // Use Case 2
    public void testSortByAlternateLocation(ArrayList<LocationModel> locationList) {
	/* Same change made as commented for SortByMyLocation */ 
	    TopLevelCommentModel topComment1 = new TopLevelCommentModel("name1", locationList.get(1));
	    TopLevelCommentModel topComment2 = new TopLevelCommentModel("name2", locationList.get(2));
	    TopLevelCommentModel topComment3 = new TopLevelCommentModel("name3", locationList.get(3));
	    TopLevelCommentModel topComment4 = new TopLevelCommentModel("name4", locationList.get(4));
	    TopLevelCommentModel topComment5 = new TopLevelCommentModel("name5", locationList.get(5));
	    ArrayList<TopLevelCommentModel> arrayList1 = new ArrayList<TopLevelCommentModel>;
	    ArrayList<TopLevelCommentModel> arrayList2 = new ArrayList<TopLevelCommentModel>;
	    arrayList1.add(topComment2);
	    arrayList1.add(topComment1);
	    arrayList1.add(topComment5);
	    arrayList1.add(topComment4);
	    arrayList1.add(topComment3);
	    arrayList2.add(topComment1);
	    arrayList2.add(topComment2);
	    arrayList2.add(topComment3);
	    arrayList2.add(topComment4);
	    arrayList2.add(topComment5);
	    Sort.sortByAlternateLocation(arrayList1, locationList.get(0));
	    assertTrue("The arraylists should be the same after the sort", (arrayList1 == arrayList2));
	}

    // Use Case 8: View Comments by Pictures
    public void testSortedByPictures(CommentListModel picComments)
    // is CommentListModel correct?
	    throws Throwable {
	runTestOnUiThread(new Runnable() {

	    @Override
	    public void run() {
		ListView listView = (ListView) activity
			.findViewById(ca.ualberta.cs.project301.R.id.oldComment);
		Adapter adapter = listView.getAdapter();
		CommentListModel unsorted = picComments;
		CommentListModel picSorted = sortByPictures(picComments);
		assertFalse("The text should not be equal to the old text",
			(unsorted == picSorted));

	    }
	});

    }

    // Use Case 9
    public void testSortByDate(ArrayList<Location> locationList) {
	    TopLevelCommentModel topComment1 = new TopLevelCommentModel("name1", locationList.get(1));
	    TopLevelCommentModel topComment2 = new TopLevelCommentModel("name2", locationList.get(2));
	    TopLevelCommentModel topComment3 = new TopLevelCommentModel("name3", locationList.get(3));
	    TopLevelCommentModel topComment4 = new TopLevelCommentModel("name4", locationList.get(4));
	    TopLevelCommentModel topComment5 = new TopLevelCommentModel("name5", locationList.get(5));
	    ArrayList<TopLevelCommentModel> arrayList1 = new ArrayList<TopLevelCommentModel>;
	    ArrayList<TopLevelCommentModel> arrayList2 = new ArrayList<TopLevelCommentModel>;
	    arrayList1.add(topComment2);
	    arrayList1.add(topComment1);
	    arrayList1.add(topComment5);
	    arrayList1.add(topComment4);
	    arrayList1.add(topComment3);
	    arrayList2.add(topComment1);
	    arrayList2.add(topComment2);
	    arrayList2.add(topComment3);
	    arrayList2.add(topComment4);
	    arrayList2.add(topComment5);
	    Sort.sortByDate(arrayList1, locationList.get(0));
	    assertTrue("The arraylists should be the same after the sort", (arrayList1 == arrayList2));
	}

    // Use Case 15
    public void testSortByRank(ArrayList<Location> locationList) {
	    TopLevelCommentModel topComment1 = new TopLevelCommentModel("name1", locationList.get(1));
	    TopLevelCommentModel topComment2 = new TopLevelCommentModel("name2", locationList.get(2));
	    topComment2.increaseRating;
	    TopLevelCommentModel topComment3 = new TopLevelCommentModel("name3", locationList.get(3));
	    topComment3.increaseRating;
	    topComment3.increaseRating;
	    TopLevelCommentModel topComment4 = new TopLevelCommentModel("name4", locationList.get(4));
	    topComment4.increaseRating;
	    topComment4.increaseRating;
	    topComment4.increaseRating;
	    TopLevelCommentModel topComment5 = new TopLevelCommentModel("name5", locationList.get(5));
	    topComment5.increaseRating;
	    topComment5.increaseRating;
	    topComment5.increaseRating;
	    topComment5.increaseRating;
	    ArrayList<TopLevelCommentModel> arrayList1 = new ArrayList<TopLevelCommentModel>;
	    ArrayList<TopLevelCommentModel> arrayList2 = new ArrayList<TopLevelCommentModel>;
	    arrayList1.add(topComment2);
	    arrayList1.add(topComment1);
	    arrayList1.add(topComment5);
	    arrayList1.add(topComment4);
	    arrayList1.add(topComment3);
	    arrayList2.add(topComment1);
	    arrayList2.add(topComment2);
	    arrayList2.add(topComment3);
	    arrayList2.add(topComment4);
	    arrayList2.add(topComment5);
	    Sort.sortByRank(arrayList1, locationList.get(0));
	    assertTrue("The arraylists should be the same after the sort", (arrayList1 == arrayList2));
	}

    // End of test for Use Case 15

    // Use Case 16
    public void testDefaultSort(BrowseCommentsActivity activity) {
	    ArrayList<TopLevelCommentModel> arrayList1 = new ArrayList<TopLeveLCommentModel>;
	    for(TopLevelCommentModel comment : activity.arrayList){
	        arrayList1.add((TopLevelCommentModel) comment.copy());
	    }
	    Sort.sortByDate(arrayList1);
	    assertTrue("The arraylists should be the same to ensure default sort.", (arrayList1 == activity.arraylist);
	}
}
