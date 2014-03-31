package ca.ualberta.cs.cmput301t02project.model;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

/**
 * Abstracts a CommentList that's stored on the disk.
 * <p>
 * With this, you can abstract the storage details and treat it like a Map.
 * All the CommentLists that are stored on the phone should inherit from this
 * and override the getPreferencesKey method.
 * @author alex
 *
 */
public abstract class StoredCommentListAbstraction extends AbstractMap<String, CommentModel> {

	private Context context;
	
	/**
	 * Constructor
	 * @param context A context is needed to handle Storage
	 */
	public StoredCommentListAbstraction(Context context) {
		this.context = context;
	}
	
	/**
	 * The only method that needs to be overriden, used to specify the file used.
	 * @return This should returns a unique String for each list.
	 */
	protected abstract String getPreferencesKey();
	
	@Override
	public Set<Entry<String, CommentModel>> entrySet() {
		Gson gson = new Gson();
		SharedPreferences cache = context.getSharedPreferences(getPreferencesKey(), 0);
		Map<String, ?> map = cache.getAll();
		Set<Entry<String, CommentModel>> entrySet = new HashSet<Entry<String, CommentModel>>();
		for(Entry<String, ?> entry : map.entrySet()) {
			String key = entry.getKey();
			String value = (String) entry.getValue();
			CommentModel comment = gson.fromJson(value, CommentModel.class);
			Entry<String, CommentModel> newEntry = new SimpleEntry<String, CommentModel>(key, comment);
			entrySet.add(newEntry);
		}
		return entrySet;
	}

	/**
	 * Gets a CommentModel with the specified id, if it doesn't exists.
	 * @param id The id.
	 * @return If the id is present, the comment, else, null.
	 */
	@Override
	public CommentModel get(Object id) {
		SharedPreferences cache = context.getSharedPreferences(getPreferencesKey(), 0);
		String key = (String) id;
		String comment = cache.getString(key, null);
		Gson gson = new Gson();
		CommentModel result = gson.fromJson(comment, CommentModel.class);
		return result;
	}

	/**
	 * Puts a CommentModel with the specified id as it's index.
	 * @param id The id.
	 * @param value The CommentModel.
	 * @return If the id already existed, return the comment that was there, else returns null.
	 */
	@Override
	public CommentModel put(String id, CommentModel value) {
		Gson gson = new Gson();
		String v = gson.toJson(value);
		SharedPreferences cache = context.getSharedPreferences(getPreferencesKey(), 0);
		String previousValueJson = cache.getString(id, null);
		CommentModel previousValue = gson.fromJson(previousValueJson, CommentModel.class);
		cache.edit().putString(id, v).commit();
		return previousValue;
	}
	
	public void add(CommentModel comment) {
		if(comment.getId() == null) {
			throw  new IllegalArgumentException("Can't store a comment with null id");
		}
		this.put(comment.getId(), comment);
	}
	
	public ArrayList<CommentModel> getCommentList() {
		ArrayList<CommentModel> list = new ArrayList<CommentModel>();
		list.addAll(this.values());
		return list;
	}
	
	public ArrayList<String> getIdList() {
		ArrayList<String> list = new ArrayList<String>();
		list.addAll(this.keySet());
		return list;
	}

}
