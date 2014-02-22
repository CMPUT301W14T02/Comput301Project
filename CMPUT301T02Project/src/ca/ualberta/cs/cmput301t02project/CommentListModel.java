package ca.ualberta.cs.cmput301t02project;

import java.util.ArrayList;

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

    public void add(CommentModel comment) {
	commentList.add(comment);
    }

}
