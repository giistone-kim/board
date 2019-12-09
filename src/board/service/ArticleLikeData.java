package board.service;

import board.model.ArticleLike;

public class ArticleLikeData {
	private int totCnt;
	private ArticleLike articleLike;

	public ArticleLikeData(int totCnt, ArticleLike articleLike) {
		this.totCnt = totCnt;
		this.articleLike = articleLike;
	}

	public int getTotCnt() {
		return totCnt;
	}

	public void setTotCnt(int totCnt) {
		this.totCnt = totCnt;
	}

	public ArticleLike getArticleLike() {
		return articleLike;
	}

	public void setArticleLike(ArticleLike articleLike) {
		this.articleLike = articleLike;
	}

}