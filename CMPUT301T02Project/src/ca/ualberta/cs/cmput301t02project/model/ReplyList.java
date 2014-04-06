package ca.ualberta.cs.cmput301t02project.model;

import java.util.ArrayList;

import android.content.Context;

/**
 * Contains the replies to a comment.
 * Includes a method for adding a new reply and retrieving an updated list of replies.
 */
public class ReplyList extends CommentListModel {

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
		notifyViews();
	}

	@Override
	public ArrayList<CommentModel> getList() {
		Server server = new Server(context);
		return server.pullChildrenOf(parentId);
	}
	
	public void refresh() {
		notifyViews();
	}
	
}
