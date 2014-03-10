package ca.ualberta.cs.cmput301t02project.view;

import java.util.ArrayList;

import ca.ualberta.cs.cmput301t02project.model.CommentModel;

import android.content.Context;
import android.widget.ArrayAdapter;

public class CommentListAdapter extends ArrayAdapter<CommentModel> {

	public CommentListAdapter(Context context, int resource,
			ArrayList<CommentModel> model) {
		super(context, resource, model);
	}

	public void sortByLocation() {
		//CreateCommentActivity.onCreate(Bundle);
		//CreateCommentActivity.getCurrentLocation();

	}

}
