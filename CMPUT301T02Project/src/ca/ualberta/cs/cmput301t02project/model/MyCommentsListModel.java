package ca.ualberta.cs.cmput301t02project.model;

import android.content.Context;

public class MyCommentsListModel extends StoredCommentListAbstraction {

	private static MyCommentsListModel instance;
	
	private MyCommentsListModel(Context context) {
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
		return "MY_COMMENTS_KEY";
	}

}
