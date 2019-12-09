package board.model;

import java.util.Date;
import java.util.List;

import util.TransferDate;

public class Article {

	private Integer number;
	private String title;
	private List<ArticleReply> articleReplyList;
	private List<ArticleLike> articleLikeList;
	private int readCnt;
	private Date regDate;
	private Date modDate;
	private Writer writer;

	public Article(Integer number, String title, List<ArticleReply> articleReplyList, List<ArticleLike> articleLikeList, int readCnt, Date regDate, Date modDate,
			Writer writer) {
		this.number = number;
		this.title = title;
		this.articleReplyList = articleReplyList;
		this.articleLikeList = articleLikeList;
		this.readCnt = readCnt;
		this.regDate = regDate;
		this.modDate = modDate;
		this.writer = writer;
	}

	public Integer getNumber() {
		return number;
	}

	public String getTitle() {
		return title;
	}

	public List<ArticleReply> getArticleReplyList() {
		return articleReplyList;
	}

	public List<ArticleLike> getArticleLikeList() {
		return articleLikeList;
	}

	public int getReadCnt() {
		return readCnt;
	}

	public Date getRegDate() {
		return regDate;
	}
	
	public String getTransferRegDate() {
		return TransferDate.getDate(regDate);
	}

	public Date getModDate() {
		return modDate;
	}
	
	public String getTransferModDate() {
		return TransferDate.getRegularDate(modDate);
	}

	public Writer getWriter() {
		return writer;
	}
	
	public int getArticleLikeSize() {
		return this.articleLikeList.size();
	}
	
	public boolean hasArticleLikeSize() {
		return this.articleLikeList.size() > 0;
	}
	
	public int getArticleReplySize() {
		return this.articleReplyList.size();
	}
	
	public boolean hasArticleReplySize() {
		return this.articleReplyList.size() > 0;
	}

}