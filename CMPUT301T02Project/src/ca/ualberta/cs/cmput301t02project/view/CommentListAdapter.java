package ca.ualberta.cs.cmput301t02project.view;

import java.util.ArrayList;

import android.content.Context;
import ca.ualberta.cs.cmput301t02project.model.CommentModel;

/**
 * View for comment lists.
 * Some examples:
 * List of top level comments, 
 * List of replies to a comment.
 */
public class CommentListAdapter extends CommentListAdapterAbstraction {

	public CommentListAdapter(Context context, int resource, ArrayList<CommentModel> model) {
		super(context, resource, model);
	}


}
