package ca.ualberta.cs.cmput301t02project.view;

import java.util.ArrayList;

import ca.ualberta.cs.cmput301t02project.model.CommentModel;
import android.content.Context;
import android.widget.ArrayAdapter;

public abstract class CommentListAdapterAbstraction extends ArrayAdapter<CommentModel> implements CommentListViewInterface {

	public CommentListAdapterAbstraction(Context context, int resource,
			ArrayList<CommentModel> model) {
		super(context, resource, model);
		// TODO Auto-generated constructor stub
	}

}
