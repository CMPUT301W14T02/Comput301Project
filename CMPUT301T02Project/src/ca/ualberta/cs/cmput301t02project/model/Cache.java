package ca.ualberta.cs.cmput301t02project.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.google.common.cache.AbstractCache;

public class Cache extends AbstractCache<String, CommentModel> {
	private final static String FILENAME = "Cache.json";
	private Map<String, CommentModel> map = new HashMap<String, CommentModel>();
	private static Cache cache;
	
	private Cache(){
	}
	
	public static Cache getInstance() {
		if(cache == null) {
			
			cache = new Cache();
			
		}
		return cache;
	}
	
	@Override
	public CommentModel getIfPresent(Object arg0) {
		if(map.containsKey(arg0))
			return map.get(arg0);
		return null;
	}
	
	@Override
	public void put(String key, CommentModel value) {
		map.put(key, value);
	}
	
	public ArrayList<CommentModel> getAllTopLevelPresent() {
		ArrayList<CommentModel> list = new ArrayList<CommentModel>();
		for(Map.Entry<String, CommentModel> entry : map.entrySet()) {
			if(entry.getValue().isTopLevelComment())
				list.add(entry.getValue());
		}
		return list;
	}

}
