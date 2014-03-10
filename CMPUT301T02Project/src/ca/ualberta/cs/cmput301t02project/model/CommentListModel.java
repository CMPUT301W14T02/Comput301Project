package ca.ualberta.cs.cmput301t02project.model;

import java.util.ArrayList;

import android.graphics.Bitmap;

public class CommentListModel {

	private ArrayList<CommentModel> commentList;

	public CommentListModel() {
		commentList = new ArrayList<CommentModel>();
	}

	public ArrayList<CommentModel> getCommentList() {
		return commentList;
	}

	public void setCommentList(ArrayList<CommentModel> commentList) {
		this.commentList = commentList;
	}

	public void addNewComment(String text, String username,
			LocationModel location, Bitmap picture) {
		CommentModel comment = new CommentModel(text, picture, location,
				username);
		commentList.add(comment);
		// push to server
	}

}
