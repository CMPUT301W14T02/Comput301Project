package ca.ualberta.cs.cmput301t02project;

import android.location.Location;
import ca.ualberta.cs.cmput301t02project.model.CommentListModel;
import ca.ualberta.cs.cmput301t02project.model.CommentModel;

public class ProjectApplication {

	// Singleton
	private static CommentListModel commentList;
	private static CommentListModel currentCommentList;
	private static CommentModel currentComment;
	private static Location currentLocation;

	public static CommentListModel getCommentList() {
		if (commentList == null) {
			commentList = new CommentListModel();
		}
		return commentList;
	}

	public static void setCurrentCommentList(
			CommentListModel currentCommentList) {
		ProjectApplication.currentCommentList = currentCommentList;
	}

	public static CommentListModel getCurrentCommentList() {

		if (currentCommentList == null) {

			// Creating before browsing comments.
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

	public static Location getCurrentLocation() {
		return currentLocation;
	}

	public static void setCurrentLocation(Location location) {
		if (currentLocation==null) {
			String loc = "Location Intialization";
			currentLocation = new Location(loc);
		}
		
		ProjectApplication.currentLocation = location;
	}

}
