package board.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import board.dao.ArticleDAO;
import board.model.Article;
import jdbc.ConnectionProvider;

public class ListArticleService {
	private ArticleDAO articleDAO = new ArticleDAO();
	private static final int PAGE_SIZE = 10; // 각 페이지당 글의 개수
	private static final int PAGE_GROUP_SIZE = 7; // 페이지 그룹 사이즈
	private static final String DEFAULT_SORT = "article_no"; 

	public ArticlePage getArticlePage(int pageNum, String sort) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			int total = articleDAO.selectCount(conn);
			
			if (sort == null)
				sort = DEFAULT_SORT;
			
			List<Article> articleList = articleDAO.select(conn, (pageNum - 1) * PAGE_SIZE, PAGE_SIZE, sort);
			return new ArticlePage(total, pageNum, articleList, PAGE_SIZE, PAGE_GROUP_SIZE, sort);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} 
	}
}