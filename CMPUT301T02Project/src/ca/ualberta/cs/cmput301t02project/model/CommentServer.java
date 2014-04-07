package ca.ualberta.cs.cmput301t02project.model;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.Get;
import io.searchbox.core.Index;
import io.searchbox.core.Search;

import java.util.ArrayList;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import ca.ualberta.cs.cmput301t02project.model.Server.ObjectWrapper;

/**
 * Used to deal with storing comments on the server.
 * Includes methods for:
 * pulling specific comments from the server, 
 * posting specific comments to the server, 
 * and checking if the internet is connected.
 */
public class CommentServer {

	private JestClient client;
	private Context context;

	public JestClient getClient() {
		return client;
	}

	public void setClient(JestClient client) {
		this.client = client;
	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	/**
	 * Retrieves the comments that are children of the comment represented by the parentId
	 * <p>
	 * The children of a comment are the replies to that comment. 
	 * The children are pulled from the server.
	 * <p>
	 * @param parentId	the id for the parent comment to get the children of
	 * @param server	server that was created with the appropriate context for the calling class
	 * @return			an arraylist of CommentModels that are replies to the parent specified by parentId
	 */
	public ArrayList<CommentModel> pullChildrenOf(String parentId, Server server) {
		if (parentId != null) {
			CommentModel parent = this.pull(parentId, server);
			return this.pull(parent.getChildrenIds());
		} 
		else {
			return this.pullTopLevel();
		}
	}

	/**
	 * Checks if the current device is connected to the internet.
	 * <p>
	 * * Retrieved from http://stackoverflow.com/a/4239019 on April 2 2014 *
	 * @return	true if internet is connected, else false
	 */
	public boolean isNetworkAvailable() {
		ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
		return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}

	/**
	 * Pushes a specified comment to the server. 
	 * <p>
	 * Assigns the comment an id to be referenced by when pulling from the server.
	 * <p>
	 * @param comment	the CommentModel to post on the server
	 */
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
				} 
				catch (Exception e) {
					e.printStackTrace();
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

	/**
	 * Pulls a comment from the server based on its string Id.
	 * <p>
	 * The comment is retrieved by searching the server for the comment's assigned Id.
	 * The comment is also stored in the cache after it has been pulled. 
	 * <p>
	 * @param id		the id of the CommentModel to pull from the server
	 * @param server	a server created with the appropriate context for that class
	 * @return			the pulled CommentModel
	 */
	public CommentModel pull(final String id, Server server) {
		final Cache cache = Cache.getInstance(context);
		final ObjectWrapper wrapper = server.new ObjectWrapper();
		
		Thread thread = new Thread() {
			@Override
			public void run() {
				Get get = new Get.Builder("cmput301w14t02", id).type("comments").build();
				CommentModel comment;
				
				try {
					JestResult result = client.execute(get);
					comment = result.getSourceAsObject(CommentModel.class);
					cache.put(id, comment);
				} 
				catch (Exception e) {
					comment = cache.getIfPresent(id);
				}
				wrapper.object = comment;
			}
		};
		thread.start();
		try {
			thread.join();
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		return (CommentModel) wrapper.object;
	}

	/**
	 * Pulls an ArrayList of comments from the server based on the corresponding ArrayList of Id strings.
	 * <p>
	 * The comments are retrieved by searching the server for each comment's assigned Id.
	 * The comments are also stored in the cache after it has been pulled. 
	 * @param idList	an ArrayList of Ids to pull from the server.
	 * @return			an ArrayList of CommentModels corresponding to those Ids
	 */
	public ArrayList<CommentModel> pull(final ArrayList<String> idList) {
		final Cache cache = Cache.getInstance(context);
		final ArrayList<CommentModel> commentList = new ArrayList<CommentModel>();
		
		Thread thread = new Thread() {
			@Override
			public void run() {
				
				for (String Id : idList) {
					Get get = new Get.Builder("cmput301w14t02", Id).type("comments").build();
					
					try {
						JestResult result = client.execute(get);
						CommentModel comment = result.getSourceAsObject(CommentModel.class);
						commentList.add(comment);
						cache.put(Id, comment);
					} 
					catch (Exception e) {
						CommentModel comment = cache.getIfPresent(Id);
						if (comment != null) {
							commentList.add(comment);
						}
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

	/**
	 * Pulls all the top level comments from the server.
	 * <p>
	 * Only retrieves comments that are marked as top level comments (eg. isTopLevel should return true).
	 * The comments are also stored in the cache once they are retrieved.
	 * Used to create the list to display for the user when they are browsing top level comments.
	 * <p>
	 * @return	an ArrayList of CommentModels representing all the top level comments on the server
	 */
	public ArrayList<CommentModel> pullTopLevel() {
		final Cache cache = Cache.getInstance(context);
		final ArrayList<CommentModel> commentList = new ArrayList<CommentModel>();
		
		Thread thread = new Thread() {
			@Override
			public void run() {
				String query = "{\"size\": 1000, \"query\": {\"term\": {\"topLevelComment\": \"true\"}}}";
				Search search = new Search.Builder(query).addIndex("cmput301w14t02").addType("comments").build();
				
				try {
					JestResult result = client.execute(search);
					commentList.addAll((ArrayList<CommentModel>) result.getSourceAsObjectList(CommentModel.class));
					for (CommentModel c : commentList) {
						cache.put(c.getId(), c);
					}
				} 
				catch (Exception e) {
					commentList.addAll(cache.getAllTopLevelPresent());
				}
			}
		};
		thread.start();
		try {
			thread.join();
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		return commentList;
	}

	/**
	 * Pulls all the comments belonging to a specified username from the server.
	 * <p>
	 * Only retrieves comments that are authored by the username parameter.
	 * The comments are also stored in the cache once they are retrieved.
	 * Used to populate the list of followed user's comments.
	 * <p>
	 * @param username	the username whose comments will be pulled.
	 * @return			the ArrayList of the username's CommentModels pulled from the server
	 */
	public ArrayList<CommentModel> pullFollowedUserComments(final String username) {
		final Cache cache = Cache.getInstance(context);
		final ArrayList<CommentModel> commentList = new ArrayList<CommentModel>();
		
		Thread thread = new Thread() {
			@Override
			public void run() {
				String query = String.format("{\"size\": 1000, \"query\": {\"term\": {\"username\": \"%s\"}}}", username);
				Search search = new Search.Builder(query).addIndex("cmput301w14t02").addType("comments").build();
				
				try {
					JestResult result = client.execute(search);
					commentList.addAll((ArrayList<CommentModel>) result.getSourceAsObjectList(CommentModel.class));
					for (CommentModel c : commentList) {
						cache.put(c.getId(), c);
					}
				} 
				catch (Exception e) {
					commentList.addAll(cache.getAllFollowedInCache());
				}
			}
		};
		thread.start();
		try {
			thread.join();
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		return commentList;
	}

	/**
	 * Pushes a child of a comment to the server.
	 * <p>
	 * The comment is represented by parentId, 
	 * the new child to add is represented by childId.
	 * The parent is a comment, its child is a reply to it. 
	 * parentId must be a valid string.
	 * <p>
	 * @param parentId		the Id of the comment who's child to push to the server
	 * @param childId		the Id of the child of the parent comment to push to the server
	 * @param server		a server created with the appropriate context for the calling class
	 */
	public void addChildren(String parentId, String childId, Server server) {
		if (parentId == null) {
			throw new IllegalArgumentException("Parent Id can't be null");
		}
		CommentModel comment = this.pull(parentId, server);
		comment.addChildId(childId);
		this.post(comment);
	}
}