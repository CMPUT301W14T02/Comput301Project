package ca.ualberta.cs.cmput301t02project.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import ca.ualberta.cs.cmput301t02project.ProjectApplication;
import ca.ualberta.cs.cmput301t02project.model.CommentListModel;
import ca.ualberta.cs.cmput301t02project.model.CommentModel;
import android.content.Context;
import android.location.Location;
import android.widget.ArrayAdapter;

public abstract class CommentListAdapterAbstraction extends ArrayAdapter<CommentModel> {

	private CommentListModel model = null;
	private String sortMethod = "Date"; //SHOULD BE DEFAULT ONCE DEFAULT WORKS
	private Comparator<CommentModel> sortByDate;
	private Comparator<CommentModel> sortByLocation;
	private Location myLocation;
	
	public CommentListAdapterAbstraction(Context context, int resource, ArrayList<CommentModel> model) {
		super(context, resource, model);
		
		sortByDate = new Comparator<CommentModel>() {
			public int compare(CommentModel a, CommentModel b) {
				if (a.getDate().before(b.getDate())) {
					return 1;
				} else if (a.getDate().after(b.getDate())) {
					return -1;
				} else {
					return 0;
				}
			}
		};
		sortByLocation = new Comparator<CommentModel>() {
			public int compare(CommentModel a, CommentModel b) {
				Float dist1 = a.getLocation().getLocation().distanceTo(myLocation);
				Float dist2 = a.getLocation().getLocation().distanceTo(myLocation);
				return dist1.compareTo(dist2);
			}
		};
	}

	public void setMethod(String string) {
		this.sortMethod = string;
	}
	
	public String getMethod() {
		return sortMethod;
	}
	
	public void setModel(CommentListModel model) {
		this.model = model;
	}
	
	public CommentListModel getModel() {
		return model;
	}
	
	public void sortByDefault() throws CloneNotSupportedException {
		String loc = "Location Initialization";
		Location currentLocation = new Location(loc);
		currentLocation = ProjectApplication.getCurrentLocation();
		
		// will hold the remaining unsorted CommentModels
		ArrayAdapter<CommentModel> copyAdapter = (CommentListAdapter) this.clone();
		// holds the sorted CommentModels to be passed to sortByLocation
		Object partialAdapter = (CommentListAdapter) this.clone();
		
		int adapterLen = this.getCount();
		//clear original array, since will be replacing with sorted array
		this.clear(); 
		
		double currentLon = currentLocation.getLongitude();
		double currentLat = currentLocation.getLatitude(); 
		double commentLon, commentLat;
		float[] results;
		String ini = "Initialize Location";
        Location commentLocation = new Location(ini);
		
		 for(int i=0; i<adapterLen; i++){
			 CommentModel item = copyAdapter.getItem(i);
			 /*
			 commentLocation = item.getLocation(); // if change from LocationModel to Location, this will work -KW
			 commentLat = commentLocation.getLatitude();
			 commentLon = commentLocation.getLongitude();
			 Location.distanceBetween(commentLat, commentLon, currentLat, currentLon, results);
			 */
			 
        }
		
	}
	/* Check this implementation of location sort.
	//Adapted from http://stackoverflow.com/a/6927640
	public void sortByLocation() {
		String loc = "Location Initialization";
		Location myLocation = new Location(loc);
		myLocation = ProjectApplication.getCurrentLocation();
		this.sort(new Comparator<CommentModel>() {
			public int compare(CommentModel a, CommentModel b) {
				Float dist1 = a.getLocation().distanceTo(myLocation);
				Float dist2 = a.getLocation().distanceTo(myLocation);
				return dist1.compareTo(dist2);
			}
		}
	}
	*/
	
	//Adapted from http://stackoverflow.com/a/8424557 and 
	public void sortByDate() {
		sortMethod = "Date";
		sortList();
	}
	
	public void sortByPicture() {
		sortMethod = "Picture";
		sortList();
	}
	
	public void sortByLocation() {
		sortMethod = "Location";
		sortList();
	}
	
	public void sortByOtherLocation() {
		sortMethod = "Other Location";
		sortList();
	}
	
	public void sortByRanking() {
		sortMethod = "Ranking";
		sortList();
	}
	
	public void sortList() {
		if (model != null) {
			ArrayList<CommentModel> list = model.getCommentList();
			if (sortMethod.equals("Default")) {
 
			} else if (sortMethod.equals("Date")) {
				Collections.sort(list, sortByDate);
				notifyDataSetChanged();
			} else if (sortMethod.equals("Picture")) {

			} else if (sortMethod.equals("Location")) {
				Collections.sort(list, sortByLocation);
				notifyDataSetChanged();
			} else if (sortMethod.equals("Other Location")) {
			
			} else if (sortMethod.equals("Ranking")) {
			
			}
		}

	}
}
