package ca.ualberta.cs.cmput301t02project.model;

import android.content.Context;

public class MyCommentsListModel extends StoredCommentListAbstraction {

	public MyCommentsListModel(Context context) {
		super(context);
	}

	@Override
	protected String getPreferencesKey() {
		return "MY_COMMENTS_KEY";
	}

}
