package ca.ualberta.cs.cmput301t02project.view;

import java.util.ArrayList;
import java.util.Comparator;

import ca.ualberta.cs.cmput301t02project.ProjectApplication;
import ca.ualberta.cs.cmput301t02project.model.CommentModel;

import android.content.Context;
import android.location.Location;
import android.widget.ArrayAdapter;

public class CommentListAdapter extends CommentListAdapterAbstraction {

	public CommentListAdapter(Context context, int resource,
			ArrayList<CommentModel> model) {
		super(context, resource, model);
	}


}
