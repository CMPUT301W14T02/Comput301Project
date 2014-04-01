package ca.ualberta.cs.cmput301t02project.model;

import android.content.Context;

public class FavoritesListModel extends StoredCommentListAbstraction {

	private static FavoritesListModel instance;
	
	private FavoritesListModel(Context context) {
		super(context);
	}
	
	public static FavoritesListModel getInstance(Context context) {
		if(instance == null) {
			instance = new FavoritesListModel(context);
		}
		return instance;
	}

	@Override
	protected String getPreferencesKey() {
		return "FAVORITES_LIST_KEY";
	}

}
