package ca.ualberta.cs.cmput301t02project.view;

import java.util.ArrayList;

import ca.ualberta.cs.cmput301t02project.model.CommentModel;

import android.content.Context;


public class CommentListAdapter extends CommentListAdapterAbstraction {

	public CommentListAdapter(Context context, int resource, ArrayList<CommentModel> model) {
		super(context, resource, model);
	}


}
