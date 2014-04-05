package ca.ualberta.cs.cmput301t02project.model;

import java.util.ArrayList;

import android.content.Context;

public class FollowedUserListCommentsModel extends StoredCommentListAbstraction{

	private static FollowedUserListCommentsModel instance;
	
	private FollowedUserListCommentsModel(Context context) {
		super(context);
	}
	
	public static FollowedUserListCommentsModel getInstance(Context context) {
		if(instance == null) {
			instance = new FollowedUserListCommentsModel(context);
		}
		return instance;
	}

	@Override
	protected String getPreferencesKey() {
		return "FOLLOWED_LIST_KEY" + User.getUser().getName();
	}
	
	public FollowedUserListCommentsModel updated(ArrayList<String> names){
		Server server = new Server(context);

		FollowedUserListCommentsModel l = new FollowedUserListCommentsModel(context); 
		/*
		for(int i = 0; i < names.size(); i++){
			server.pullFollowedUserComments(names.get(i));
		}
		*/
		if(names.get(0)!=null){
			ArrayList<CommentModel> list = server.pullFollowedUserComments(names.get(0));
			l.addAll(list);
		}
		return l;
	}


}
