package ca.ualberta.cs.cmput301t02project;

import java.util.ArrayList;

public class CommentListModel {

	private ArrayList<CommentModelAbstraction> commentList;
	
	public CommentListModel() {
		commentList = new ArrayList<CommentModelAbstraction>();
	}

	public ArrayList<CommentModelAbstraction> getCommentList() {
		return commentList;
	}

	public void setCommentList(ArrayList<CommentModelAbstraction> commentList) {
		this.commentList = commentList;
	}
	
	public void add(CommentModelAbstraction comment) {
		commentList.add(comment);
	}
	
	public int size() {
	    return commentList.size();
	}
	
}
