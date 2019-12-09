package board.model;

import java.util.Date;

import util.TransferDate;

public class ArticleReply {
	private Integer replyNo;
	private Integer memberId;
	private String nickname;
	private String content;
	private Date regdate;
	private Integer articleNo;

	public ArticleReply(Integer replyNo, Integer memberId, String nickname, String content, Date regdate, Integer articleNo) {
		this.replyNo = replyNo;
		this.memberId = memberId;
		this.nickname = nickname;
		this.content = content;
		this.regdate = regdate;
		this.articleNo = articleNo;
	}

	public int getReplyNo() {
		return replyNo;
	}

	public void setReplyNo(int replyNo) {
		this.replyNo = replyNo;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public int getArticleNo() {
		return articleNo;
	}

	public void setArticleNo(int articleNo) {
		this.articleNo = articleNo;
	}
	
	public String getTransferRegDate() {
		return TransferDate.getDate(regdate);
	}

}