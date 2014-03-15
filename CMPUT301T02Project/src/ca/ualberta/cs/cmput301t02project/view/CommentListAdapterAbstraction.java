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
	private String sortMethod = "Default";
	private Comparator<CommentModel> sortByDate;
	private Comparator<CommentModel> sortByLocation;
	private Location myLocation = null;
	
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
				String loc = "Location Initialization";
				myLocation = new Location(loc);
				myLocation = ProjectApplication.getCurrentLocation();
				Float dist1 = a.getLocation().distanceTo(myLocation);
				Float dist2 = b.getLocation().distanceTo(myLocation);
				if (dist1 < dist2) {
					return -1;
				} else if (dist1 > dist2) {
					return 1;
				} else {
					return 0;
				}
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
	
	public void sortByDefaultMethod() {
		String loc = "Location Initialization";
		myLocation = new Location(loc);
		myLocation = ProjectApplication.getCurrentLocation();
		
		
		// will hold the remaining unsorted CommentModels
		ArrayList<CommentModel> list = (ArrayList<CommentModel>) model.getCommentList();
		// holds the sorted CommentModels to be passed to sortByLocation
		ArrayList<CommentModel> list2 = new ArrayList<CommentModel>();
		// contains the final list
		ArrayList<CommentModel> finalList = new ArrayList<CommentModel>();
		
		int i;
		
		for (i = 0; i<list.size(); i++) {
			if (list.get(i).getLocation().distanceTo(myLocation) < 5000) {
				list2.add(list.get(i));
				list.remove(i);
			}
		}
		
		Collections.sort(list2, sortByDate);
		for (i = 0; i<list2.size(); i++) {
			finalList.add(list2.get(i));
		}
		list2 = new ArrayList<CommentModel>();
		
		for (i = 0; i<list.size(); i++) {
			if (list.get(i).getLocation().distanceTo(myLocation) < 100000) {
				list2.add(list.get(i));
				list.remove(i);
			}
		}
		
		Collections.sort(list2, sortByDate);
		for (i = 0; i<list2.size(); i++) {
			finalList.add(list2.get(i));
		}
		list2 = new ArrayList<CommentModel>();
		
		for (i = 0; i<list.size(); i++) {
			if (list.get(i).getLocation().distanceTo(myLocation) < 5000000) {
				list2.add(list.get(i));
				list.remove(i);
			}
		}
		
		Collections.sort(list2, sortByDate);
		for (i = 0; i<list2.size(); i++) {
			finalList.add(list2.get(i));
		}
		list2 = new ArrayList<CommentModel>();
		
		// rest of list
		for (i = 0; i<list.size(); i++) {
			list2.add(list.get(i));
			list.remove(i);
			
		}
		
		Collections.sort(list2, sortByDate);
		for (i = 0; i<list2.size(); i++) {
			finalList.add(list2.get(i));
		}
		for (i = 0; i<finalList.size(); i++) {
			list.add(finalList.get(i));
		}

		
	}
	
	
	public void sortByDefault() {
		sortMethod = "Default";
		sortList();
	}
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
				sortByDefaultMethod();
				notifyDataSetChanged();
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
