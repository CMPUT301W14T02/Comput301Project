package ca.ualberta.cs.cmput301t02project.view;

import java.util.ArrayList;

import android.content.Context;
import ca.ualberta.cs.cmput301t02project.model.CommentModel;


/**
 * View for lists of comments written by the current user.
 */
public class MyCommentsAdapter extends CommentListAdapterAbstraction {

	public MyCommentsAdapter(Context context, int resource, ArrayList<CommentModel> model) {
		super(context, resource, model);
		// TODO Auto-generated constructor stub
	}

}
