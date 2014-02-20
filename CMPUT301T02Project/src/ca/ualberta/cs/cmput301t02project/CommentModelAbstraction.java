package ca.ualberta.cs.cmput301t02project;

import java.util.ArrayList;
import java.util.Date;

public abstract class CommentModelAbstraction {

	protected Date date;
	protected LocationModel location;
	protected String text;
	protected String image;
	protected int rating;
	protected ArrayList <ReplyCommentModel> replies;
	protected String username;
	
	public CommentModelAbstraction (String text, String image, LocationModel location, String username) {
		this.text = text;
		this.image = image;
		this.location = location;
		this.username = username;
		this.rating = 0;
		this.replies = new ArrayList<ReplyCommentModel>();
		this.date = new Date();
	}
	
	public Date getDate(){
		return date;
	}
		
	public LocationModel getLocation(){
		return location;
	}
	
	public void setLocation(LocationModel location, double lat, double lng){
		// this will need to change, depending on how we decide to store the location, in lat or long and how we want to be able to access them
		this.location.setLatitude(lat);
		this.location.setLongitude(lng);
	}
	
	public String getText(){
		return text;
	}
	
	public void setText(String text){
		this.text = text;
	}
	
	public String getImage(){
		return image;
	}
	
	public void setImage(String image){
		this.image = image;
	}
	
	public int getRating(){
		return rating;
	}
	
	public void setRating(int rating){
		this.rating = rating;
	}
	
	public ArrayList<ReplyCommentModel> getReplies(){
		return replies;
	}
	
	public void setReplies(ArrayList<ReplyCommentModel> replies){
		this.replies = replies;
	}
	
	public String getUsername(){
		return username;
	}
	
	public void setUsername(String username){
		this.username = username;
	}
	


}
