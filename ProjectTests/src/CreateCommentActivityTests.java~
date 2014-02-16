import android.test.ActivityInstrumentationTestCase2;

public class CreateCommentActivityTest extends
		ActivityInstrumentationTestCase2<CreateCommentActivity> {

	public CreateCommentActivityTest() {
		super(CreateCommentActivityTest.class);
	}

	//How the controller will be accessed from the activity isn't specified yet.
	public void testDefaultLocation() {
		TopLevelCommentModel comment = new TopLevelCommentModel();
		topLevelListController.addTopLevelComment(comment);
		int newCommentIndex = topLevelListController.getList().size - 1;
		assertEquals("New added comment should have the user location", user.getLocation(),
				topLevelListController.getList().get(newCommentIndex).getLocation());
	}
}
