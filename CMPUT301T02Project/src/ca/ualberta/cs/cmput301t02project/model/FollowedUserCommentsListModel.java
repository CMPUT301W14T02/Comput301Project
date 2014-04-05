package ca.ualberta.cs.cmput301t02project.model;

import java.util.ArrayList;

import android.content.Context;

public class FollowedUserCommentsListModel extends StoredCommentListAbstraction{

	private static FollowedUserCommentsListModel instance;
	
	private FollowedUserCommentsListModel(Context context) {
		super(context);
	}
	
	public static FollowedUserCommentsListModel getInstance(Context context) {
		if(instance == null) {
			instance = new FollowedUserCommentsListModel(context);
		}
		return instance;
	}

	@Override
	protected String getPreferencesKey() {
		return "FOLLOWED_LIST_KEY" + User.getUser().getName();
	}
	
	public FollowedUserCommentsListModel updated(ArrayList<String> names){
		Server server = new Server(context);

		FollowedUserCommentsListModel l = new FollowedUserCommentsListModel(context); 
		
		for(int i = 0; i < names.size(); i++){
			ArrayList<CommentModel> list = server.pullFollowedUserComments(names.get(i));
			l.addAll(list);
		}
		
		return l;
	}


}
