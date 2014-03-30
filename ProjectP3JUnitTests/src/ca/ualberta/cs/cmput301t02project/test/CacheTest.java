package ca.ualberta.cs.cmput301t02project.test;

import junit.framework.TestCase;
import ca.ualberta.cs.cmput301t02project.model.Cache;
import ca.ualberta.cs.cmput301t02project.model.CommentModel;

public class CacheTest extends TestCase{
	
	public void testCache() {
		Cache cache = new Cache();
		CommentModel model = new CommentModel("text", null, "user");
		assertNotNull(cache);
		cache.put("i", model);
		assertEquals(cache.getIfPresent("i"), model);
	}
}
