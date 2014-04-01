package ca.ualberta.cs.cmput301t02project.model;

import java.util.ArrayList;

public interface CommentListModel {

	public void add(CommentModel comment);
	public ArrayList<CommentModel> getList();

}