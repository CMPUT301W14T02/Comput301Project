package ca.ualberta.cs.cmput301t02project.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;
import java.util.Observable;
import java.util.Observer;

import android.content.Context;
import android.location.Location;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import ca.ualberta.cs.cmput301t02project.model.CommentListModel;
import ca.ualberta.cs.cmput301t02project.model.CommentModel;
import ca.ualberta.cs.cmput301t02project.model.GPSLocation;

/**
 * CommentListAdapterAbstraction is a subclass of ArrayAdapter that is extended by
 * MyCommentsAdapter, FavoritesAdapter and CommentListAdapter. It is considered a
 * view under MVC. CommentListAdapterAbstraction implements sorting functionality
 * and is called on by either an activity, to change sorting method, or by its model,
 * to update the view and/or resort the list due to changes to the model.
 */
public class CommentListAdapter extends ArrayAdapter<CommentModel> implements Observer {

	private CommentListModel model;
	private String sortMethod = "Default";
	private Comparator<CommentModel> sortByDate;
	private Comparator<CommentModel> sortByLocation;
	private Comparator<CommentModel> sortByFaves;
	private Comparator<CommentModel> sortByPicture;
	private Comparator<CommentModel> sortByOtherLocation;
	private Location myLocation = null;
	private Location otherLocation = null;
	private ArrayList<CommentModel> list;
	

	/**
	 * Constructs a new instance of ArrayAdapter<CommentModel>
	 * <p>
	 * The constructor is adapted from the original ArrayAdapter<> constructor, using the
	 * same inputs and calling super() with those inputs. The changes are made with the 
	 * instantiation of the comparators for sortByDate and sortByLocation. These comparators
	 * are used to sort the ArrayList<CommentModel> that is within the model this adapter displays.
	 * <p>
	 * @param context The current context of the application
	 * @param resource The resource ID for the layout file containing the ListView to use
	 * @param model Objects to represent in the ListView
	 */
	public CommentListAdapter(Context context, int resource, CommentListModel model) {
		super(context, resource, model.getList());
		this.model = model;
		this.list = model.getList();
		sortByDate = new Comparator<CommentModel>() {
			public int compare(CommentModel a, CommentModel b) {
				if (a.getDate().before(b.getDate())) {
					return 1;
				} 
				else if (a.getDate().after(b.getDate())) {
					return -1;
				} 
				else {
					return 0;
				}
			}
		};
		
		sortByFaves = new Comparator<CommentModel>() {
			public int compare(CommentModel a, CommentModel b) {
				if (a.getRating() < (b.getRating())) {
					return 1;
				} 
				else if (a.getRating() > (b.getRating())) {
					return -1;
				} 
				else {
					return 0;
				}
			}
		};
		
		sortByLocation = new Comparator<CommentModel>() {
			public int compare(CommentModel a, CommentModel b) {
				myLocation = GPSLocation.getInstance().getLocation();
				Float dist1 = a.getLocation().distanceTo(myLocation);
				Float dist2 = b.getLocation().distanceTo(myLocation);
				if (dist1 < dist2) {
					return -1;
				} 
				else if (dist1 > dist2) {
					return 1;
				} 
				else {
					return 0;
				}
			}
		};
		
		sortByOtherLocation = new Comparator<CommentModel>() {
			public int compare(CommentModel a, CommentModel b) {
				Float dist1 = a.getLocation().distanceTo(otherLocation);
				Float dist2 = b.getLocation().distanceTo(otherLocation);
				if (dist1 < dist2) {
					return -1;
				} 
				else if (dist1 > dist2) {
					return 1;
				} 
				else {
					return 0;
				}
			}
		};
		
		sortByPicture = new Comparator<CommentModel>(){

			@Override
			public int compare(CommentModel left, CommentModel right) {
				if (!left.hasPicture() && right.hasPicture()){
					return 1;
				}
				else if (!right.hasPicture() && left.hasPicture()){
					return -1;
				}
				else {
					return 0;
				}
			}
			
		};
	}
	
	@Override
	public void notifyDataSetChanged() {
		//setNotifyOnChange(false);
		this.list = model.getList();
		//this.clear();
		this.sortList();
		//setNotifyOnChange(false);
		//this.addAll(this.list);
		//super.notifyDataSetChanged();
		//setNotifyOnChange(true);
	}
	
	@Override
	public void update(Observable o, Object data) {
		this.notifyDataSetChanged();
	}
	
	/**
	 * Returns the current sorting method.
	 * <p>
	 * @return current sort method
	 */
	public String getMethod() {
		return sortMethod;
	}
	
	/**
	 * Sets the model that this adapter is displaying.
	 * <p>
	 * This allows the current list within the model to be accessed by the 
	 * adapter for sorting purposes.
	 * <p>
	 * @param model Sets model for adapter instance
	 */
	public void setModel(CommentListModel model) {
		this.model = model;
	}
	
	/**
	 * Returns the current model that the adapter is displaying.
	 * <p>
	 * @return Model set for adapter instance
	 */
	public CommentListModel getModel() {
		return model;
	}
	
	/**
	 * Sorts the ArrayList<CommentModel> within model using the default method.
	 * <p>
	 * The default method of sorting uses a combination of date sorting and location sorting.
	 * This is done by sorting comments into distance categories based on how far away the user
	 * is from the distance category, and then sorting these categories by date.
	 * <p>
	 * The current list is obtained from the model. It is then subjected to serveral for loops
	 * which determine which comments are in which distance category. These comments are added
	 * to list2, which is sorted by date after each for loop, added to finalList,
	 * and then cleared before the next for loop. Once the method has been through all the
	 * for loops, it clears the comments currently in list and
	 * adds the sorted list of comments contained in finalList.
	 */
	public void sortByDefaultMethod() {
		myLocation = GPSLocation.getInstance().getLocation();
		
		// holds the sorted CommentModels to be passed to sortByLocation
		ArrayList<CommentModel> list2 = new ArrayList<CommentModel>();
		// contains the final list
		ArrayList<CommentModel> finalList = new ArrayList<CommentModel>();
		Integer i;
		int l = list.size();
		//distances grouping comments by
		int[] calcDistances = {5000,100000,5000000};
		
		for (int e = 0; e<(calcDistances.length); e++ )
		{
			for (i = 0; i<l; i++) {
				if ((list.get(i).getLocation().distanceTo(myLocation) < calcDistances[e]) && (!finalList.contains(list.get(i)))) {
					list2.add(list.get(i));
				}
			}
			Collections.sort(list2, sortByDate);
			finalList.addAll(list2);
			list2.clear();
		}
			
		// rest of list
		for (i = 0; i<l; i++) {
			if (!finalList.contains(list.get(i))) {
				list2.add(list.get(i));
			}
		}

		list.clear();
		Collections.sort(list2, sortByDate);
		finalList.addAll(list2);
		list2.clear();
		list.addAll(finalList);

		
	}
	
	/**
	 *  Sets the sortMethod string to Default.
	 *  <p>
	 *  This allows sortList to know that the list should be sorted by Default
	 *  when called.
	 */
	public void sortByDefault() {
		sortMethod = "Default";
		sortList();
	}
	
	/**
	 *  Sets the sortMethod string to Date.
	 *  <p>
	 *  This allows sortList to know that the list should be sorted by Date
	 *  when called.
	 */
	public void sortByDate() {
		sortMethod = "Date";
		sortList();
	}
	
	/**
	 *  Sets the sortMethod string to Picture.
	 *  <p>
	 *  This allows sortList to know that the list should be sorted by Picture
	 *  when called.
	 */
	public void sortByPicture() {
		sortMethod = "Picture";
		sortList();
	}
	
	/**
	 *  Sets the sortMethod string to Location.
	 *  <p>
	 *  This allows sortList to know that the list should be sorted by Location
	 *  when called.
	 */
	public void sortByLocation() {
		sortMethod = "Location";
		sortList();
	}
	
	/**
	 *  Sets the sortMethod string to Other Location.
	 *  <p>
	 *  This allows sortList to know that the list should be sorted by Other Location
	 *  when called.
	 */
	public void sortByOtherLocation(Location otherLoc) {
		sortMethod = "Other Location";
		this.setOtherLocation(otherLoc);
		sortList();
	}
	
	/**
	 *  Sets the sortMethod string to Ranking.
	 *  <p>
	 *  This allows sortList to know that the list should be sorted by Ranking
	 *  when called.
	 */
	public void sortByRanking() {
		sortMethod = "Ranking";
		sortList();
	}
	
	
	/**
	 * Sorts the list by the method currently set in sortMethod.
	 * <p>
	 * The method checks to see if a model is currently associated
	 * with the adapter and then checks to see which sortMethod is currently
	 * selected. After determining how to sort, it either uses the comparator for
	 * that sorting type to sort the list using the Collections.sort() method
	 * or calls the method responsible for that sort type. After the sort is complete,
	 * it calls the notifyDataSetChanged() method on the adapter in order to update
	 * the view.
	 */
	public void sortList() {
		if (list != null) {
			super.setNotifyOnChange(false);
			super.clear();
			if (sortMethod.equals("Default")) {
				sortByDefaultMethod();
			} 
			else if (sortMethod.equals("Date")) {
				Collections.sort(list, sortByDate);
			} 
			else if (sortMethod.equals("Picture")) {
				Collections.sort(list, sortByPicture);

			} 
			else if (sortMethod.equals("Location")) {
				Collections.sort(list, sortByLocation);
			} 
			else if (sortMethod.equals("Other Location")) {
				Collections.sort(list, sortByOtherLocation);
			} 
			else if (sortMethod.equals("Faves")) {
				Collections.sort(list, sortByFaves);
			}
			super.addAll(this.list);
			super.notifyDataSetChanged();
		}

	}
	
	/**
	 * Builds the view shown in the ListView.
	 * <p>
	 * Inspired on
	 * https://github.com/zjullion/PicPosterComplete/blob/master/src/ca/ualberta/cs/picposter/view/PicPostModelAdapter.java
	 * Accessed April 1 2014
	 * @return The view
	 * <p>
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView == null) {
			convertView = new TextView(this.getContext());
		}
		CommentModel comment = this.getItem(position);
		
		String location = String.format(Locale.getDefault(), "%.4f,%.4f",
				comment.getLocation().getLatitude(), comment.getLocation().getLongitude());
		
		int repliesCount = comment.getChildrenIds().size();
		int ratingCount = comment.getRating();
		
		String hasPicture = "";
		if(comment.hasPicture()){
			hasPicture = " | image attatched";
		}
		String replies = Integer.toString(repliesCount) + ' ';
		String rating = Integer.toString(ratingCount) + " Faves";
		replies += (repliesCount == 1) ? "reply" : "replies";
		String text = comment.getText() + "\n(by " 
				+ comment.getUsername() + " | " + location + " | " + replies + " | " + rating + hasPicture +')';
		
		((TextView)convertView).setText(text);
		
		return convertView;

	}

	public void setOtherLocation(Location otherLocation) {
		this.otherLocation = otherLocation;
	}
	
}
