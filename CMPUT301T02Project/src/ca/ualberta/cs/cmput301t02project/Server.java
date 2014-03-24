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
					Index index = new Index.Builder(comment).id(comment.getId()).build();
					
					JestResult result = client.execute(index);
					String id = result.getJsonObject().get("_id").getAsString();
					comment.setId(id);
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
