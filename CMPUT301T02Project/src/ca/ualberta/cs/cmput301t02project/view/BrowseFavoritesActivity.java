package ca.ualberta.cs.cmput301t02project.view;

import ca.ualberta.cs.cmput301t02project.model.CommentListModel;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;


public class BrowseFavoritesActivity extends Activity implements OnItemSelectedListener {

	private CommentListModel favoritesList;
	//private FavoritesAdapter favoritesListAdapter;

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

}
