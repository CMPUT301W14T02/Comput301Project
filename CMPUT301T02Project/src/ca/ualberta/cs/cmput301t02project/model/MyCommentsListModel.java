package ca.ualberta.cs.cmput301t02project.model;

import android.content.Context;

/**
 * Contains comments written by a specific user.
 */
public class MyCommentsListModel extends StoredCommentListAbstraction {

	private static MyCommentsListModel instance;
	
	public MyCommentsListModel(Context context) {
		super(context);
	}
	
	public static MyCommentsListModel getInstance(Context context) {
		if(instance == null) {
			instance = new MyCommentsListModel(context);
		}
		return instance;
	}

	@Override
	protected String getPreferencesKey() {
		return "MY_COMMENTS_KEY" + User.getUser().getName();
	}

}
