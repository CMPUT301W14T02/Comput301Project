package ca.ualberta.cs.cmput301t02project.model;

import java.util.ArrayList;

import ca.ualberta.cs.cmput301t02project.view.CommentListAdapterAbstraction;

public class CommentListModel {
	
	private StorageModel store = new StorageModel();
	private CommentListAdapterAbstraction adapter = null;
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
		if (adapter != null) {
			adapter.sortList();
			adapter.notifyDataSetChanged();
		}
	}

	public void add(CommentModel comment) {		
		commentList.add(comment);
		if (adapter != null) {
			adapter.sortList();
			adapter.notifyDataSetChanged();
		}
		// TODO push comment to server

	}
	
	public void setAdapter(CommentListAdapterAbstraction adapter) {
		this.adapter = adapter;
	}
	
	public CommentListAdapterAbstraction getAdapter() {
		return adapter;
	}

}
