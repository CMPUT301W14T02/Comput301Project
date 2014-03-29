package ca.ualberta.cs.cmput301t02project;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.Get;
import io.searchbox.core.Index;
import io.searchbox.core.Search;

import java.util.ArrayList;

import android.accounts.NetworkErrorException;
import ca.ualberta.cs.cmput301t02project.model.CommentModel;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.searchly.jestdroid.DroidClientConfig;
import com.searchly.jestdroid.JestClientFactory;

public class Server {
	private static final String serverUri = "http://cmput301.softwareprocess.es:8080/";
	private JestClient client;
	
	public Server() {
		//TODO Add custom Gson here to clientConfig.Builder
		DroidClientConfig clientConfig = new DroidClientConfig.Builder(serverUri).multiThreaded(true).build();
		JestClientFactory jestClientFactory = new JestClientFactory();
		jestClientFactory.setDroidClientConfig(clientConfig);
		client = jestClientFactory.getObject();
		
	}
	
	public void post(final CommentModel comment) {
		Thread thread = new Thread() {
			@Override
			public void run() {
				
				try {
					Index index = new Index.Builder(comment).index("cmput301w14t02").type("comments").id(comment.getId()).build();
					
					JestResult result = client.execute(index);
					String id = result.getJsonObject().get("_id").getAsString();
					comment.setId(id);
					
					index = new Index.Builder(comment).index("cmput301w14t02").type("comments").id(comment.getId()).build();
					result = client.execute(index);
					if(!result.isSucceeded()) {
						throw new NetworkErrorException();
					}
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException();
				} finally {
					client.shutdownClient();
				}
			}
			
		};
		thread.start();
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<CommentModel> pull(final ArrayList<String> idList) {
		final ArrayList<CommentModel> commentList = new ArrayList<CommentModel>();
		Thread thread = new Thread() {
			@Override
			public void run() {
				for(String Id:idList) {
					Get get = new Get.Builder("cmput301w14t02", Id).type("comments").build();
					try {
						JestResult result = client.execute(get);
						CommentModel comment = result.getSourceAsObject(CommentModel.class);
						commentList.add(comment);
					} catch (Exception e) {
						
					}
					
				}
			
			}
		};
		thread.start();
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return commentList;
	}
	
	public ArrayList<CommentModel> pullTopLevel() {
		final ArrayList<CommentModel> commentList = new ArrayList<CommentModel>();
		Thread thread = new Thread() {
			@Override
			public void run() {
				String query = "{\"size\": 1000, \"query\": {\"term\": {\"topLevelComment\": \"True\"}}}";
				Search search = new Search.Builder(query).addIndex("cmput301w14t02").addType("comments").build();
				try {
					JestResult result = client.execute(search);
					commentList.addAll((ArrayList<CommentModel>) result.getSourceAsObjectList(CommentModel.class));
				} catch (Exception e) {
					//TODO: Add cached topLevelComments to commentList
				}
			}
		};
		thread.start();

		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return commentList;
	}
	
	public void postUser(final User user) {
		Thread thread = new Thread() {
			@Override
			public void run() {
				
				try {
					Index index = new Index.Builder(user).index("cmput301w14t02").type("users").id(user.getId()).build();
					
					JestResult result = client.execute(index);
					String id = result.getJsonObject().get("_id").getAsString();
					user.setId(id);
					
					index = new Index.Builder(user).index("cmput301w14t02").type("users").id(user.getId()).build();
					result = client.execute(index);
					if(!result.isSucceeded()) {
						throw new NetworkErrorException();
					}
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException();
				} finally {
					client.shutdownClient();
				}
			}
			
		};
		thread.start();
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
