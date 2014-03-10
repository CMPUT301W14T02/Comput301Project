package ca.ualberta.cs.cmput301t02project.view;

import java.util.ArrayList;
import java.util.Comparator;

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
	
	
	//Adapted from http://stackoverflow.com/a/8424557 and 
	public void sortByDate() {
	
		this.sort(new Comparator<CommentModel>() {
			public int compare(CommentModel a, CommentModel b) {
				if (a.getDate().before(b.getDate())) {
					return -1;
				} else if (a.getDate().after(b.getDate())) {
					return 1;
				} else {
					return 0;
				}
			}
		});
	}
	

}
