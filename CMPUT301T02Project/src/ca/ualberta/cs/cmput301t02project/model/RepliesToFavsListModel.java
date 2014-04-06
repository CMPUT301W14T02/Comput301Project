package ca.ualberta.cs.cmput301t02project.model;

import java.util.ArrayList;

import android.content.Context;

/**
 * Contains the replies to the favorited comments of a User.
 * Includes a method for retrieving an ArrayList of replies.
 */
public class RepliesToFavsListModel extends StoredCommentListAbstraction {

	private static RepliesToFavsListModel instance;
	private CommentModel parent;
	
	private RepliesToFavsListModel(Context context) {
		super(context);
	}
	
	public static RepliesToFavsListModel getInstance(Context context) {
		if(instance == null) {
			instance = new RepliesToFavsListModel(context);
		}
		return instance;
	}

	@Override
	protected String getPreferencesKey() {
		return "REPLIES_TO_FAVS_LIST_KEY" + User.getUser().getName();
	}
	
	@Override
	public ArrayList<CommentModel> getList() {
		ArrayList<CommentModel> list = new ArrayList<CommentModel>();
		
		for(String id: parent.getChildrenIds()) {
			
			for (CommentModel comment: super.getList()) {
				
				if (comment.getId().contentEquals(id)) {
					list.add(comment);
				}
			}
		}
		return list;
	}

	public CommentModel getParent() {
		return parent;
	}

	public void setParent(CommentModel parent) {
		this.parent = parent;
	}

}