package ca.ualberta.cs.cmput301t02project.model;

import java.util.ArrayList;

public class CommentListModel {

	private ArrayList<CommentModel> commentList;
	//private CommentListAdapter adapter;

	public CommentListModel() {
		commentList = new ArrayList<CommentModel>();
	}

	public ArrayList<CommentModel> getCommentList() {
		return commentList;
	}

	public void setCommentList(ArrayList<CommentModel> commentList) {
		this.commentList = commentList;
	}

	public void add(CommentModel comment) {
		commentList.add(comment);
		//adapter.notifyDataSetChanged();
		// TODO push comment to server

	}

}
