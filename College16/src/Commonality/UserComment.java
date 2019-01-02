package Commonality;

@SuppressWarnings("serial")
public class UserComment implements java.io.Serializable{
	private String CommentContent;
	private String CommentTime;
	private String UserNickname;
	private int UserId;
	private int SourceId;
	public int getUserId() {
		return UserId;
	}
	public void setUserId(int userId) {
		UserId = userId;
	}
	public int getSourceId() {
		return SourceId;
	}
	public void setSourceId(int sourceId) {
		SourceId = sourceId;
	}
	public UserComment(int SourceId,int UserId,String CommentContent)
	{
		this.SourceId=SourceId;
		this.UserId=UserId;
		this.CommentContent=CommentContent;
	}
	public UserComment(String CommentContent,String CommentTime,String UserNickname)
	{
		this.CommentContent=CommentContent;
		this.CommentTime=CommentTime;
		this.UserNickname=UserNickname;
	}
	public UserComment(){}
	public String getCommentContent() {
		return CommentContent;
	}
	public void setCommentContent(String commentContent) {
		CommentContent = commentContent;
	}
	public String getCommentTime() {
		return CommentTime;
	}
	public void setCommentTime(String commentTime) {
		CommentTime = commentTime;
	}
	public String getUserNickname() {
		return UserNickname;
	}
	public void setUserNickname(String userNickname) {
		UserNickname = userNickname;
	}
	
}
