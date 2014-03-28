package ca.ualberta.cs.cmput301t02project.model;

import java.util.ArrayList;

import android.util.Log;
import ca.ualberta.cs.cmput301t02project.ProjectApplication;
import ca.ualberta.cs.cmput301t02project.Server;
import ca.ualberta.cs.cmput301t02project.view.CommentListAdapterAbstraction;

import com.google.gson.Gson;

/**
 * Holds information about a specific list of CommentModels.
 * Some examples of uses of CommentListModels:
 * Storing a list of a user's favorited comments, 
 * Storing a list of a comments that a user has composed, 
 * Storing a list of all top level comments.
 */
public class CommentListModel {
	
	private transient StorageModel store = new StorageModel();
	private transient CommentListAdapterAbstraction adapter = null;
	private ArrayList<CommentModel> commentList;
	private transient CommentModel parent;
	//private CommentListAdapter adapter;
	private transient Server server = new Server();

	public CommentListModel() {
		commentList = new ArrayList<CommentModel>();
		this.parent = null;
	}

	public CommentListModel(CommentModel parent) {
		commentList = new ArrayList<CommentModel>();
		this.parent=parent;
	}

	/**
	 * Returns a list of the comments stored in the CommentListModel.
	 * <p>
	 * Returns an ArrayList of CommentModels representing comments in the CommentListModel.
	 * Used whenever the CommentModels need to be accessed directly.
	 * <p>
	 * @return	The comments stored in the CommentListModel
	 */
	public ArrayList<CommentModel> getCommentList() {
		return commentList;
	}

	/**
	 * Sets a list of comments to store in the CommentListModel.
	 * <p>
	 * Updates the current ArrayList of CommentModels to a specified list.
	 * Once the list of comments has been changed, if an adapter is currently set
	 * the updated list is sorted by the set sorting method,
	 * and all observers are notified that the data in the model has changed.
	 * <p> 
	 * @param commentList	The new list of comments to replace the old list
	 */
	public void setCommentList(ArrayList<CommentModel> commentList) {
		this.commentList = commentList;
		if (adapter != null) {
			adapter.sortList();
			adapter.notifyDataSetChanged();
		}
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
		if(ProjectApplication.getInstance().getCurrentComment() == null) {
		//if(this.parent == null) {
			comment.setTopLevelComment(true);
		} else {
			comment.setTopLevelComment(false);
			this.parent=ProjectApplication.getInstance().getCurrentComment();
			//comment.setParent(ProjectApplication.getInstance().getCurrentComment());
		}
		commentList.add(comment);
		Server server = new Server();
		server.post(comment);
		if(!comment.isTopLevelComment()) {
			parent.addChildId(comment.getId());
			server = new Server();
			server.post(this.parent);
		}
		if (adapter != null) {
			adapter.sortList();
			adapter.notifyDataSetChanged();
		}
	}
	
	/**
	 * Sets the adapter for the CommentListModel.
	 * <p>
	 * Assigns a specified adapter to the CommentListModel.
	 * <p>
	 * @param adapter	The adapter to set to the CommentListModel
	 */
	public void setAdapter(CommentListAdapterAbstraction adapter) {
		this.adapter = adapter;
	}
	
	/**
	 * Returns the CommentListModel's current adapter.
	 * @return	The adapter assigned to the CommentListModel
	 */
	public CommentListAdapterAbstraction getAdapter() {
		return adapter;
	}
	
	/**
	 * Returns the complete list of top level comments. -TH
	 * Comments are stored on the server. This method uses the server class to
	 * pull all comments from the server that have the topLevelComment 
	 * attribute set to true.
	 * <p>
	 * @return The CommentListModel containing top level comments
	 */
	public CommentListModel getTopLevelComments() {
		CommentListModel topLevelComments;
		topLevelComments = new CommentListModel();
		topLevelComments.setCommentList(server.pullTopLevel());
		return topLevelComments;
	}

}
