package ca.ualberta.cs.cmput301t02project.model;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

/**
 * Cache class used by the Server as a fallback if there's no internet.
 */
public class Cache {
	private final static String CACHE_KEY = "CacheKey";
	private static Cache cache;
	private Context context;
	
	private Cache(Context context){
		this.context = context;
	}
	
	/**
	 * This class is a singleton
	 * @param context A context is needed for storage
	 * @return The singleton instance
	 */
	public static Cache getInstance(Context context) {
		if(cache == null) {
			cache = new Cache(context.getApplicationContext());
		}
		return cache;
	}
	
	/**
	 * If the comment is present in the cache, returns it, else return null
	 * @param arg0 The id of the comment
	 * @return If the comment is present in the cache, returns it, else return null
	 */
	public CommentModel getIfPresent(Object arg0) {
		SharedPreferences cache = context.getSharedPreferences(CACHE_KEY, 0);
		String key = (String) arg0;
		String comment = cache.getString(key, null);
		Gson gson = new Gson();
		CommentModel result = gson.fromJson(comment, CommentModel.class);
		return result;
	}
	
	/**
	 * Adds a key value pair to the cache
	 * @param key The comment id
	 * @param value The comment
	 */
	public void put(String key, CommentModel value) {
		Gson gson = new Gson();
		String v = gson.toJson(value);
		SharedPreferences cache = context.getSharedPreferences(CACHE_KEY, 0);
		cache.edit().putString(key, v).commit();
	}
	
	/**
	 * Returns a list of all comments present on the cache
	 * @return The list of comments
	 */
	public ArrayList<CommentModel> getAllTopLevelPresent() {
		Gson gson = new Gson();
		SharedPreferences cache = context.getSharedPreferences(CACHE_KEY, 0);
		Map<String, ?> map = cache.getAll();
		ArrayList<CommentModel> list = new ArrayList<CommentModel>();
		for(Entry<String, ?> entry : map.entrySet()) {
			String value = (String) entry.getValue();
			CommentModel comment = gson.fromJson(value, CommentModel.class);
			if(comment.isTopLevelComment()) {
				list.add(comment);
			}
		}
		return list;
	}
	
	
	/**
	 * Returns a list of all comments present on the cache
	 * @return The list of comments
	 */
	public ArrayList<CommentModel> getAllFollowedInCache() {
		Gson gson = new Gson();
		SharedPreferences cache = context.getSharedPreferences(
				FollowedUserCommentsListModel.getInstance(context).getPreferencesKey(), 0);
		Map<String, ?> map = cache.getAll();
		ArrayList<CommentModel> list = new ArrayList<CommentModel>();
		for(Entry<String, ?> entry : map.entrySet()) {
			String value = (String) entry.getValue();
			CommentModel comment = gson.fromJson(value, CommentModel.class);
			list.add(comment);
		}
		return list;
	}

}
