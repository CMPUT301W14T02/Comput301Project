package ca.ualberta.cs.cmput301t02project.model;

import java.util.ArrayList;
import java.util.Observable;

import android.content.Context;

public class ReplyList extends Observable implements CommentListModel {

	private Context context;
	private String parentId;
	
	public ReplyList(String parentId, Context context) {
		if(parentId == null) {
			throw new IllegalArgumentException("Parent Id Can't be null");
		}
		this.context = context;
		this.parentId = parentId;
	}

	@Override
	public void add(CommentModel comment) {
		Server server = new Server(context);
		server.post(comment);
		server.addChildren(parentId, comment.getId());
		notifyObservers();
	}

	@Override
	public ArrayList<CommentModel> getList() {
		Server server = new Server(context);
		return server.pullChildrenOf(parentId);
	}

}
