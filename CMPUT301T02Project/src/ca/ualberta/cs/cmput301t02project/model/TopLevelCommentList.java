package ca.ualberta.cs.cmput301t02project.model;

import java.util.ArrayList;
import java.util.Observable;

import android.content.Context;

public class TopLevelCommentList extends Observable implements CommentListModel{
	
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
		notifyObservers();
	}

	@Override
	public ArrayList<CommentModel> getList() {
		Server server = new Server(context);
		return server.pullTopLevel();
	}

}
