package ca.ualberta.cs.cmput301t02project.test;

import junit.framework.TestCase;
import ca.ualberta.cs.cmput301t02project.Server;
import ca.ualberta.cs.cmput301t02project.model.CommentModel;

public class ServerTest extends TestCase {

	public void testPostComment() {
		Server server =  new Server();
		CommentModel comment = new CommentModel("text", null, "the author");
		
		server.post(comment);
		
		server.shutdown();
		
		//Asserted by looking in browser
	}
	
}
