package ca.ualberta.cs.cmput301t02project.controller;

import ca.ualberta.cs.cmput301t02project.model.CommentListModel;
import ca.ualberta.cs.cmput301t02project.model.CommentModel;

public class FavoritesController extends CommentListControllerAbstraction {
	public FavoritesController(CommentListModel model) {
		this.model = model;
	}

	public void favoriteComment(CommentModel comment) {

		model.add(comment);
	}

}
