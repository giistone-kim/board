package board.service;


public class ModifyRequest {
	private Integer memberId;
	private int articleNumber;
	private String title;
	private String content;
	
	public ModifyRequest(Integer userId, int articleNumber, String title, String content) {
		this.memberId = userId;
		this.articleNumber = articleNumber;
		this.title = title;
		this.content = content;
	}
	
	public Integer getUserId() {
		return memberId;
	}

	public int getArticleNumber() {
		return articleNumber;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}
	
}