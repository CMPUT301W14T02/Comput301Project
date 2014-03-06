package ca.ualberta.cs.cmput301t02project;

import java.util.ArrayList;

import android.app.Application;

public class ProjectApplication {

    // Singleton
    private static ArrayList<CommentModel> commentList;
    private static ArrayList<CommentModel> currentCommentList;
    private static CommentModel currentComment;

    public static ArrayList<CommentModel> getCommentList() {
    	if (commentList == null) {
    		commentList = new ArrayList<CommentModel>();
    	}
    	return commentList;
    }

    public static void setCurrentCommentList(ArrayList<CommentModel> currentCommentList) {
    	ProjectApplication.currentCommentList = currentCommentList;
    }

    public static ArrayList<CommentModel> getCurrentCommentList() {

    	if (currentCommentList == null) { 
		
    		//Creating before browsing comments.
    		currentCommentList = ProjectApplication.getCommentList();
    	}
		return currentCommentList;
    }

    public static CommentModel getCurrentComment() {
    	return currentComment;
    }

    public static void setCurrentComment(CommentModel currentComment) {
    	ProjectApplication.currentComment = currentComment;
    }

}
