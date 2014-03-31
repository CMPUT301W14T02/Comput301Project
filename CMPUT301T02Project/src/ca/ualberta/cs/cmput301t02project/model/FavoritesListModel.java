package ca.ualberta.cs.cmput301t02project.model;

import android.content.Context;

public class FavoritesListModel extends StoredCommentListAbstraction {

	public FavoritesListModel(Context context) {
		super(context);
	}

	@Override
	protected String getPreferencesKey() {
		return "FAVORITES_LIST_KEY";
	}

}
