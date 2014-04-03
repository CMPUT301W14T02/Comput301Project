package ca.ualberta.cs.cmput301t02project.test;

import android.test.ActivityInstrumentationTestCase2;
import junit.framework.TestCase;
import ca.ualberta.cs.cmput301t02project.model.Server;
import ca.ualberta.cs.cmput301t02project.model.CommentModel;

public class ServerTest extends ActivityInstrumentationTestCase2<T> {

	public void testPostComment() {
		Server server =  new Server(getInstrumentation().getContext());
		CommentModel comment = new CommentModel("text", null, "the author");
		
		server.post(comment);
		
		//Asserted by looking in browser
	}
	
}
