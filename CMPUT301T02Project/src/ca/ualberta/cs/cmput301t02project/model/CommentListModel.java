package ca.ualberta.cs.cmput301t02project.model;

import java.util.Observable;

import android.content.Context;

/**
 * Holds information about a specific list of CommentModels.
 * Some examples of uses of CommentListModels:
 * Storing a list of a user's favorited comments, 
 * Storing a list of a comments that a user has composed, 
 * Storing a list of all top level comments.
 */
public class CommentListModel extends Observable {
	private String parentId;
	private Context context;
	
	public CommentListModel(Context context) {
		this.parentId = null;
		this.context = context;
	}

	public CommentListModel(String parentId, Context context) {
		this.parentId = parentId;
		this.context = context;
	}
	/**
	 * Adds a new comment to the CommentListModel. 
	 * <p>
	 * Adds a specified CommentModel to the CommentListModel. 
	 * Once the new comment is added to the list, if an adapter is currently set
	 * the updated list is sorted by the set sorting method,
	 * and all observers are notified that the data in the model has changed.
	 * Used when a new comment is created by a user in 
	 * CreateCommentActivity.
	 * <p>
	 * @param comment	The new comment to add to the list
	 */
	public void add(CommentModel comment) {
		if(this.parentId == null) {
			comment.setTopLevelComment(true);
		} else {
			comment.setTopLevelComment(false);
		}
		Server server = new Server(context);
		server.post(comment);
		if(this.parentId != null) {
			server = new Server(context);
			server.addChildren(this.parentId, comment.getId());
		}
		notifyObservers();
	}
}
