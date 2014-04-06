package ca.ualberta.cs.cmput301t02project.model;

import java.util.ArrayList;

import android.content.Context;

import com.google.gson.Gson;
import com.searchly.jestdroid.DroidClientConfig;
import com.searchly.jestdroid.JestClientFactory;

public class Server {
	private CommentServer commentServer = new CommentServer();
	private UserServer userServer = new UserServer();
	private static final String serverUri = "http://cmput301.softwareprocess.es:8080/";
	public Server(Context context) {
		Gson gson = CustomGson.getGson();
		DroidClientConfig clientConfig = new DroidClientConfig.Builder(serverUri).gson(gson).multiThreaded(true).build();
		JestClientFactory jestClientFactory = new JestClientFactory();
		jestClientFactory.setDroidClientConfig(clientConfig);
		commentServer.setClient(jestClientFactory.getObject());
		commentServer.setContext(context);
	}

	public void post(final CommentModel comment) {
		commentServer.post(comment);
	}
	
	/**
	 * Pulls the comment with the specified id from the server.
	 * @param id The id.
	 * @return The Comment.
	 */
	public CommentModel pull(final String id) {
		return commentServer.pull(id, this);
	}
	
	/**
	 * Pulls all the children of the comment whose id is parentId.
	 * @param parentId the parent id.
	 * @return The list of comments.
	 */
	public ArrayList<CommentModel> pullChildrenOf(String parentId) {
		return commentServer.pullChildrenOf(parentId, this);
	}
	
	/**
	 * Pulls each of the comments that have each of the ids in idList.
	 * @param idList the list of ids.
	 * @return The list of comments.
	 */
	public ArrayList<CommentModel> pull(final ArrayList<String> idList) {
		return commentServer.pull(idList);
	}
	
	/**
	 * Pulls all top level comments.
	 * @return The list of top level comments.
	 */
	public ArrayList<CommentModel> pullTopLevel() {
		return commentServer.pullTopLevel();
	}
	
	/**
	 * Returns a list of the comments made by the User whose username is the one specified.
	 * @param username The wanted User's username.
	 * @return The list of comments.
	 */
	public ArrayList<CommentModel> pullFollowedUserComments(final String username) {
		return commentServer.pullFollowedUserComments(username);
	}
	
	/**
	 * Posts the given user to the Server.
	 * @param user The user.
	 */
	public void postUser(final User user) {
		userServer.postUser(user, commentServer.getClient());
	}

	/**
	 * Pulls the given user from the server.
	 * <p>
	 * The username of the specified User is used to search for the wanted User.
	 * And the provided User's id is set to that of the User in the server.
	 * @param user The User whose username is already set, and whose id will be set.
	 */
	public void pullUser(final User user) {
		userServer.pullUser(user, commentServer.getClient());
	}

	/**
	 * Sets the comment whose id is childId as a children of the comment whose id is parentId. 
	 * @param parentId The parent id.
	 * @param childId The children id.
	 */
	public void addChildren(String parentId, String childId) {
		commentServer.addChildren(parentId, childId, this);
	}
	
	/**
	 * Checks if there is connection available.
	 * @return Whether there is connection available.
	 */
	public boolean isNetworkAvailable() {
		return commentServer.isNetworkAvailable();
	}
	
	/**
	 * Wrapper for setting an object inside a new thread.
	 *
	 */
	class ObjectWrapper {
		public Object object;
	}
}
