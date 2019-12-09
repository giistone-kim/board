package board.service;

import java.util.List;

import board.model.Article;

public class ArticlePage {

	private int total;
	private int currentPage; //현재 페이지
	private List<Article> articleList;
	private int totalPages;// 페이지 개수
	private int startPage; // 각 페이지 그룹의 첫번째 페이지
	private int endPage; // 각 페이지 그룹의 마지막 페이지
	private int pageGroupSize;
	private String sort;
	
	public ArticlePage(int total, int currentPage, List<Article> articleList, int pageSize, int pageGroupSize, String sort) {
		this.total = total;
		this.currentPage = currentPage;
		this.articleList = articleList;
		this.pageGroupSize = pageGroupSize;
		this.sort = sort;
		
		if (total == 0) {
			totalPages = 0;
			startPage = 0;
			endPage = 0;
		} else {
			totalPages = total / pageSize;
			if (total % pageSize > 0) {
				totalPages++;
			}

			int pageGroupNumber = (int) Math.ceil((double) currentPage / pageGroupSize);
			
			if (currentPage < pageGroupSize) {
				startPage = (pageGroupNumber - 1) * pageGroupSize + 1;
				endPage = startPage + pageGroupSize - 1;
			} else {
				int pageUpdate = pageGroupSize / 2;
				startPage = currentPage - pageUpdate;
				endPage = currentPage + pageUpdate;
			}
			
			if (endPage > totalPages) {
				endPage = totalPages;
			}
			
		}

	}

	public boolean hasArticles() {
		return total > 0;
	}

	public boolean hasNoArticles() {
		return total == 0;
	}

	public int getTotal() {
		return total;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public List<Article> getArticleList() {
		return articleList;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public int getStartPage() {
		return startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public int getPageGroupSize() {
		return pageGroupSize;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}
	
	

}
