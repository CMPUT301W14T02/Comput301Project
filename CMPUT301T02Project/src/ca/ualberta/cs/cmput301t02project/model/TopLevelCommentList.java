package ca.ualberta.cs.cmput301t02project.model;

import java.util.ArrayList;

import android.content.Context;

/**
 * Contains all the top level comments.
 * Includes a method for adding a new top level comment and retrieving an updated list of top level comments.
 */
public class TopLevelCommentList extends CommentListModel {
	
	private Context context;
	private static TopLevelCommentList instance;
	
	private TopLevelCommentList(Context context) {
		this.context = context;
	}
	
	public static TopLevelCommentList getInstance(Context context) {
		if(instance == null) {
			instance = new TopLevelCommentList(context);
		}
		return instance;
	}

	@Override
	public void add(CommentModel comment) {
		Server server = new Server(context);
		server.post(comment);
		notifyViews();
	}

	@Override
	public ArrayList<CommentModel> getList() {
		Server server = new Server(context);
		return server.pullTopLevel();
	}
	
	@Override
	public void refresh() {
		notifyViews();
	}

}
