//use case: Attach Picture to Comment

public void testAddedPicture(CommentModel comment) throws throwable{
	String pic = comment.getPicture();
	assertFalse("The picture string should no longer be null", null==pic);
}
