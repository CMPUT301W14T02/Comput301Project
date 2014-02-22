package ca.ualberta.cs.cmput301t02project;

import android.app.Application;

public class ProjectApplication extends Application {

	// Singleton
	private static CommentListModel commentList;

	public static CommentListModel getCommentList() {
		if(commentList == null) {
			commentList = new CommentListModel();
		}
		return commentList;
	}
	
}
