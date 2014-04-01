package ca.ualberta.cs.cmput301t02project.model;

import java.util.ArrayList;
import java.util.Observable;

import android.content.Context;

public class TopLevelCommentList extends Observable implements CommentListModel{

	private Context context;
	
	public TopLevelCommentList(Context context) {
		this.context = context;
	}

	@Override
	public void add(CommentModel comment) {
		Server server = new Server(context);
		server.post(comment);
		this.notifyAll();
	}

	@Override
	public ArrayList<CommentModel> getList() {
		Server server = new Server(context);
		return server.pullTopLevel();
	}

}
