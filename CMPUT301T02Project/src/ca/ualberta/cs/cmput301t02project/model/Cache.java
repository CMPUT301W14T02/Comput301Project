package ca.ualberta.cs.cmput301t02project.model;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

public class Cache {
	private final static String CACHE_KEY = "CacheKey";
	private static Cache cache;
	private Context context;
	
	private Cache(Context context){
		this.context = context;
	}
	
	public static Cache getInstance(Context context) {
		if(cache == null) {
			cache = new Cache(context.getApplicationContext());
		}
		return cache;
	}
	
	public CommentModel getIfPresent(Object arg0) {
		SharedPreferences cache = context.getSharedPreferences(CACHE_KEY, 0);
		String key = (String) arg0;
		String comment = cache.getString(key, null);
		Gson gson = new Gson();
		CommentModel result = gson.fromJson(comment, CommentModel.class);
		return result;
	}
	
	public void put(String key, CommentModel value) {
		Gson gson = new Gson();
		String v = gson.toJson(value);
		SharedPreferences cache = context.getSharedPreferences(CACHE_KEY, 0);
		cache.edit().putString(key, v).commit();
	}
	
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

}
