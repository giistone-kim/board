package board.model;

import java.util.Date;

import util.TransferDate;

public class ArticleLike {
	private Integer number;
	private Integer memberId;
	private String nickname;
	private Date regdate;
	private Integer articleNo;

	public ArticleLike(Integer number, Integer memberId, String nickname, Date regdate, Integer articleNo) {
		this.number = number;
		this.memberId = memberId;
		this.nickname = nickname;
		this.regdate = regdate;
		this.articleNo = articleNo;
	}

	public Integer getNumber() {
		return number;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public String getNickname() {
		return nickname;
	}

	public Integer getArticleNo() {
		return articleNo;
	}
	
	public Date getRegdate() {
		return regdate;
	}

	public String getTransferRegDate() {
		return TransferDate.getDate(regdate);
	}
	
	public boolean isLikeIt(Integer memberId) {
		return this.memberId == memberId ? true : false;
	}

}
