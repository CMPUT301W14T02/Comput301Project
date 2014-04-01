package ca.ualberta.cs.cmput301t02project.view;

import android.content.Context;
import ca.ualberta.cs.cmput301t02project.model.CommentListModel;

/**
 * View for lists of comments favorited by the current user.
 */
public class FavoritesAdapter extends CommentListAdapterAbstraction {

	public FavoritesAdapter(Context context, int resource, CommentListModel model) {
		super(context, resource, model);
	}

}
