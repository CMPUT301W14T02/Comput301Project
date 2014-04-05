package ca.ualberta.cs.cmput301t02project.model;

import java.util.ArrayList;

import android.content.Context;

import com.google.gson.Gson;
import com.searchly.jestdroid.DroidClientConfig;
import com.searchly.jestdroid.JestClientFactory;

public class Server {
	private CommentServer serverProduct = new CommentServer();
	private UserServer userServer = new UserServer();
	private static final String serverUri = "http://cmput301.softwareprocess.es:8080/";
	public Server(Context context) {
		Gson gson = CustomGson.getGson();
		DroidClientConfig clientConfig = new DroidClientConfig.Builder(serverUri).gson(gson).multiThreaded(true).build();
		JestClientFactory jestClientFactory = new JestClientFactory();
		jestClientFactory.setDroidClientConfig(clientConfig);
		serverProduct.setClient(jestClientFactory.getObject());
		serverProduct.setContext(context);
	}

	public void post(final CommentModel comment) {
		serverProduct.post(comment);
	}
	
	public CommentModel pull(final String id) {
		return serverProduct.pull(id, this);
	}
	
	public ArrayList<CommentModel> pullChildrenOf(String parentId) {
		return serverProduct.pullChildrenOf(parentId, this);
	}
	
	public ArrayList<CommentModel> pull(final ArrayList<String> idList) {
		return serverProduct.pull(idList);
	}
	
	public ArrayList<CommentModel> pullTopLevel() {
		return serverProduct.pullTopLevel();
	}
	
	public ArrayList<CommentModel> pullFollowedUserComments(final String username) {
		return serverProduct.pullFollowedUserComments(username);
	}
	
	
	public void postUser(final User user) {
		userServer.postUser(user, serverProduct.getClient());
	}

	public void pullUser(final User user) {
		userServer.pullUser(user, serverProduct.getClient());
	}

	public void addChildren(String parentId, String childId) {
		serverProduct.addChildren(parentId, childId, this);
	}
	
	public boolean isNetworkAvailable() {
		    return serverProduct.isNetworkAvailable();
	}
	
	class ObjectWrapper {
		public Object object;
	}
}
