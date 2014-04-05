package ca.ualberta.cs.cmput301t02project.model;


import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.Index;
import io.searchbox.core.Search;

public class UserServer {
	public void postUser(final User user, final JestClient client) {
		Thread thread = new Thread() {
			@Override
			public void run() {
				try {
					Index index = new Index.Builder(user).index("cmput301w14t02").type("users").id(user.getId())
							.build();
					JestResult result = client.execute(index);
					String id = result.getJsonObject().get("_id").getAsString();
					user.setId(id);
					index = new Index.Builder(user).index("cmput301w14t02").type("users").id(user.getId()).build();
					result = client.execute(index);
				} catch (Exception e) {
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

	public void pullUser(final User user, final JestClient client) {
		final String username = user.getName();
		Thread thread = new Thread() {
			@Override
			public void run() {
				String query = String.format("{\"size\": 1000, \"query\": {\"term\": {\"username\": \"%s\"}}}",
						username);
				Search search = new Search.Builder(query).addIndex("cmput301w14t02").addType("users").build();
				JestResult result = null;
				try {
					result = client.execute(search);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (result != null) {
					User temporaryUser = ((User) result.getSourceAsObject(User.class));
					if (temporaryUser != null) {
						user.setId(temporaryUser.getId());
						user.setMyCommentIds(temporaryUser.getMyCommentIds());
					} else {
						user.setId(null);
					}
				} else {
					user.setId(null);
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