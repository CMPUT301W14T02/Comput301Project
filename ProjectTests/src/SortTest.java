
import java.util.ArrayList;

import ca.ualberta.cs.cmput301t02project.LocationModel;
import ca.ualberta.cs.cmput301t02project.TopLevelCommentModel;
import android.test.ActivityInstrumentationTestCase2;


public class SortTest extends ActivityInstrumentationTestCase2<Sort> {
    //Sort class doesn't currently exist. If name changes, this will need to be modified to account for it
    public SortTest() {
	super(Sort.class);

    }
    
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
    
    
}
