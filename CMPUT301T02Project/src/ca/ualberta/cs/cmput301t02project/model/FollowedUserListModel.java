package ca.ualberta.cs.cmput301t02project.model;

import android.content.Context;

public class FollowedUserListModel extends StoredCommentListAbstraction{

	private static FollowedUserListModel instance;
	
	private FollowedUserListModel(Context context) {
		super(context);
	}
	
	public static FollowedUserListModel getInstance(Context context) {
		if(instance == null) {
			instance = new FollowedUserListModel(context);
		}
		return instance;
	}

	@Override
	protected String getPreferencesKey() {
		return "FOLLOWED_LIST_KEY" + User.getUser().getName();
	}

}
