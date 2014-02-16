//19) Use Case: Load Relevant Comments
/* As a user, if my phone connects to the internet while this app    *
 * is open it should load all the latest and greatest and most       *
 * relevant comments. For example imagine you're on the LRT and you  *
 * get off at Health Sciences and now you have 3G again, you want    *
 * those comments that are new and near Health Sciences.             */

public class LoadRelevantCommentsTests extend 
    ActivityInstrumentationTestCase2<BrowseCommentsActivity> {

    public LoadRelevantCommentsTests() {
	super(LoadRelevantComments.class);
    }

    //Test that comments are by default sorted and displayed by location and then date
    public void testNewAndNear() {
	double lat1 = 53.5222;
	double long1 = 113.6228;
	double lat2 = 53.5244;
	double long2 = 113.5244;
	double lat3 = 53.6303;
	double long3 = 113.6258;
	Location location1 = new Location(MOCK_PROVIDER);
	location1.setLatitude(lat1);
	location1.setLongitude(long1);
	Location location2 = new Location(MOCK_PROVIDER);
	location2.setLatitude(lat2);
	location2.setLongitude(long2);
	Location location3 = new Location(MOCK_PROVIDER);
	location3.setLatitude(lat3);
	location3.setLongitude(long3);
	TopLevelCommentModel topComment1 = new TopLevelCommentModel("name1", location1, System.currentTimeMillis());
	TopLevelCommentModel topComment3 = new TopLevelCommentModel("name3", location2, System.currentTimeMillis());
	TopLevelCommentModel topComment2 = new TopLevelCommentModel("name2", location1, System.currentTimeMillis());
	TopLevelCommentModel topComment4 = new TopLevelCommentModel("name4", location2, System.currentTimeMillis());
	TopLevelCommentModel topComment5 = new TopLevelCommentModel("name5", location3, System.currentTimeMillis());
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
	Sort.sortByDefault(arrayList1);
	assertTrue("The arraylists should be the same after the sort", (arrayList1 == arrayList2));
    }

    //Test that location is updated after internet connection loss
    public void testChangeLocation() {
	Location initial_loc = new Location(LOCATION_PROVIDER);
	// Tester should disconnect from internet and move locations
	Location final_loc = new Location(LOCATION_PROVIDER);
	boolean latEquals = FALSE;
	boolean longEquals = FALSE;
	if (initial_loc.getLatitude == final_loc.getLatitude)
	    latEquals = TRUE;
	if (initial_loc.getLongitude == final_loc.getLongitude)
	    longEquals = TRUE;
	boolean CoordEquals = latEquals && longEquals;
	assertFalse(CoordEquals);
   }
}