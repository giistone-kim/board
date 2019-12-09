package board.service;

import board.model.Article;
import board.model.ArticleContent;

public class ArticleData {
	private Article article;
	private ArticleContent articleContent;
	
	public ArticleData(Article article, ArticleContent articleContent) {
		this.article = article;
		this.articleContent = articleContent;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public ArticleContent getArticleContent() {
		return articleContent;
	}

	public void setArticleContent(ArticleContent articleContent) {
		this.articleContent = articleContent;
	}

}