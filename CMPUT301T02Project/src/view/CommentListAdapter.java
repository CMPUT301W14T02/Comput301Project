package view;

import java.util.ArrayList;

import model.CommentModel;
import android.content.Context;
import android.widget.ArrayAdapter;

public class CommentListAdapter extends ArrayAdapter<CommentModel> {

    public CommentListAdapter(Context context, int resource, ArrayList<CommentModel> model) {
	super(context, resource, model);
    }
}
