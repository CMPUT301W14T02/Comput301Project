package ca.ualberta.cs.cmput301t02project.test;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import ca.ualberta.cs.cmput301t02project.activity.LoginActivity;
import ca.ualberta.cs.cmput301t02project.model.Cache;
import ca.ualberta.cs.cmput301t02project.model.CommentModel;

public class CacheTest extends ActivityInstrumentationTestCase2<LoginActivity>{
	
	public CacheTest() {
		super(LoginActivity.class);
	}
	
	public void testCache() {
		Activity activity = getActivity();
		assertNotNull(activity);
		Cache cache = Cache.getInstance(activity.getApplicationContext());
		assertNotNull(cache);
		CommentModel model = new CommentModel("text", null, "user");
		cache.put("i", model);
		assertNotNull(cache.getIfPresent("i"));
		assertEquals(model, cache.getIfPresent("i"));
	}
}
