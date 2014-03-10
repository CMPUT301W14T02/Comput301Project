package ca.ualberta.cs.cmput301t02project.view;

import java.util.ArrayList;

import ca.ualberta.cs.cmput301t02project.ProjectApplication;
import ca.ualberta.cs.cmput301t02project.model.CommentModel;

import android.content.Context;
import android.location.Location;
import android.widget.ArrayAdapter;

public class CommentListAdapter extends ArrayAdapter<CommentModel> {

	public CommentListAdapter(Context context, int resource,
			ArrayList<CommentModel> model) {
		super(context, resource, model);
	}

	public void sortByLocation() {
		String loc = "Initializing location";
		Location currentLocation = new Location(loc);
		currentLocation = ProjectApplication.getCurrentLocation();

	}

}
