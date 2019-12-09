package member.model;

import java.util.List;

import auth.service.User;
import board.model.Article;
import board.model.ArticleLike;
import board.model.ArticleReply;

public class UserInfo {
	private User user;
	private List<Article> articleList;
	private List<ArticleLike> likeList;
	private List<ArticleReply> replyList;
	
	public UserInfo(User user, List<Article> articleList, List<ArticleLike> likeList, List<ArticleReply> replyList) {
		this.user = user;
		this.articleList = articleList;
		this.likeList = likeList;
		this.replyList = replyList;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Article> getArticleList() {
		return articleList;
	}

	public void setArticleList(List<Article> articleList) {
		this.articleList = articleList;
	}

	public List<ArticleLike> getLikeList() {
		return likeList;
	}

	public void setLikeList(List<ArticleLike> likeList) {
		this.likeList = likeList;
	}

	public List<ArticleReply> getReplyList() {
		return replyList;
	}

	public void setReplyList(List<ArticleReply> replyList) {
		this.replyList = replyList;
	}
	
	public int getArticleListSize() {
		return this.articleList.size();
	}
	
	public int getLikeListSize() {
		return this.likeList.size();
	}
	
	public int getReplyListSize() {
		return this.replyList.size();
	}
}