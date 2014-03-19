package ca.ualberta.cs.cmput301t02project;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.Index;
import android.accounts.NetworkErrorException;
import ca.ualberta.cs.cmput301t02project.model.CommentModel;

import com.searchly.jestdroid.DroidClientConfig;
import com.searchly.jestdroid.JestClientFactory;

public class Server {
	private final String serverUri = "http://cmput301.softwareprocess.es:8080/cmput301w14t02/comments";
	private JestClient client;
	
	public Server() {
		DroidClientConfig clientConfig = new DroidClientConfig.Builder(serverUri).build();
		JestClientFactory jestClientFactory = new JestClientFactory();
		jestClientFactory.setDroidClientConfig(clientConfig);
		client = jestClientFactory.getObject();
	}
	
	public void post(CommentModel comment) {
		Index index = new Index.Builder(comment).id("111").build();
		try {
			JestResult result = client.execute(index);
			if(!result.isSucceeded()) {
				throw new NetworkErrorException();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	public void shutdown() {
		client.shutdownClient();
	}
	
}
