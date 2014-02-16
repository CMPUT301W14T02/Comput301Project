import android.location.Location;
import android.test.ActivityInstrumentationTestCase2;

public class EditCommentActivityTests extends
		ActivityInstrumentationTestCase2<EditCommentActivity> {

	public EditCommentActivityTests() {
		super(EditCommentActivity.class);
	}

	//How the commentController will be accessed from the activity isn't specified yet.
	public void testChangeLocation() {
		commentController.setLocation(10.123, 20.321);

		Location location = commentController.getLocation();

		assertTrue("Latitude should 10.123 and Lonngitude should be 20.321",
				location.getLatitude() == 10.123 && location.getLongitude == 20.321);
	}
}
