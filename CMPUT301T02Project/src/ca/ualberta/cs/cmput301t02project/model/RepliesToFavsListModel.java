package ca.ualberta.cs.cmput301t02project.model;

import android.content.Context;

public class RepliesToFavsListModel extends StoredCommentListAbstraction {

	private static RepliesToFavsListModel instance;
	
	private RepliesToFavsListModel(Context context) {
		super(context);
	}
	
	public static RepliesToFavsListModel getInstance(Context context) {
		if(instance == null) {
			instance = new RepliesToFavsListModel(context);
		}
		return instance;
	}

	@Override
	protected String getPreferencesKey() {
		return "REPLIES_TO_FAVS_LIST_KEY" + User.getUser().getName();
	}

}