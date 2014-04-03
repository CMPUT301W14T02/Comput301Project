package ca.ualberta.cs.cmput301t02project.model;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Observable;
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
 * This class uses the template pattern, in which the classes that inherit from it
 * must specify the PreferencesKey.
 *
 */
public abstract class StoredCommentListAbstraction extends Observable implements CommentListModel {

	private Context context;
	
	/**
	 * Constructor
	 * @param context A context is needed to handle Storage
	 */
	protected StoredCommentListAbstraction(Context context) {
		this.context = context;
	}
	
	/**
	 * The only method that needs to be overriden, used to specify the file used.
	 * @return This should returns a unique String for each list.
	 */
	protected abstract String getPreferencesKey();
	
	private Set<Entry<String, CommentModel>> entrySet() {
		Gson gson = new Gson();
		SharedPreferences storage = context.getSharedPreferences(getPreferencesKey(), 0);
		Map<String, ?> map = storage.getAll();
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
	public CommentModel get(Object id) {
		SharedPreferences storage = context.getSharedPreferences(getPreferencesKey(), 0);
		String key = (String) id;
		String comment = storage.getString(key, null);
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
	public CommentModel put(String id, CommentModel value) {
		Gson gson = new Gson();
		String v = gson.toJson(value);
		SharedPreferences storage = context.getSharedPreferences(getPreferencesKey(), 0);
		String previousValueJson = storage.getString(id, null);
		CommentModel previousValue = gson.fromJson(previousValueJson, CommentModel.class);
		storage.edit().putString(id, v).commit();
		notifyObservers();
		return previousValue;
	}
	
	/**
	 * Adds a comment that already has an id to the list
	 * @param comment The comment to be added
	 */
	public void add(CommentModel comment) {
		if(comment.getId() == null) {
			throw  new IllegalArgumentException("Can't store a comment with null id");
		}
		this.put(comment.getId(), comment);
		notifyObservers();
	}
	
	/**
	 * Adds a list of comments
	 * @param list The list of replies to be added
	 */
	public void addAll(ArrayList<CommentModel> list) {
		for(CommentModel comment:list)
			this.put(comment.getId(), comment);
		notifyObservers();
	}
	
	/**
	 * Returns the list of comment
	 * @return The list
	 */
	public ArrayList<CommentModel> getList() {
		ArrayList<CommentModel> list = new ArrayList<CommentModel>();
		for(Entry<String, CommentModel> entry : this.entrySet()) {
			list.add(entry.getValue());
		}
		return list;
	}
	
	/**
	 * Returns the list of all ids
	 * @return The list of ids
	 */
	public ArrayList<String> getIdList() {
		ArrayList<String> list = new ArrayList<String>();
		for(Entry<String, CommentModel> entry : this.entrySet()) {
			list.add(entry.getKey());
		}
		return list;
	}
	
	/**
	 * Updates the stored list with whatever is newer on the Server
	 */
	public void refresh() {
		ArrayList<String> ids = this.getIdList();
		Server server = new Server(context);
		ArrayList<CommentModel> comments = server.pull(ids);
		for(CommentModel c : comments) {
			this.put(c.getId(), c);
		}
	}

}
