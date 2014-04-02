package ca.ualberta.cs.cmput301t02project.view;
import java.util.ArrayList;

import android.content.Context;
import ca.ualberta.cs.cmput301t02project.model.CommentListModel;
import ca.ualberta.cs.cmput301t02project.model.CommentModel;

public class RepliesToFavsAdapter extends CommentListAdapterAbstraction {

	public RepliesToFavsAdapter(Context context, int resource, CommentListModel model, CommentModel comment) {
		super(context, resource, model);
		/* Need to change the model being sent to the super constructor to only contain comments 
		 * we want to view 
		ArrayList<CommentModel> list = model.getList();
		if (!list.isEmpty()) {
			if (comment.getChildrenIds().isEmpty())
				list = new ArrayList<CommentModel>();
			for(CommentModel reply: list)
				if (!comment.getChildrenIds().contains(reply.getId()))
					list.remove(reply);
		}
		 */
	
		
	}

}
