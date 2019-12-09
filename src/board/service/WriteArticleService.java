package board.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import board.dao.ArticleContentDAO;
import board.dao.ArticleDAO;
import board.model.Article;
import board.model.ArticleContent;
import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;

public class WriteArticleService {
	ArticleDAO articleDAO = new ArticleDAO();
	ArticleContentDAO articleContentDAO = new ArticleContentDAO();
	
	public Integer write(WriteRequest writeRequest) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Article article = toArticle(writeRequest);
			Article savedArticle = articleDAO.insert(conn, article);
			
			if (savedArticle == null) {
				throw new RuntimeException("fail to insert to article");
			}
			
			ArticleContent articleContent = new ArticleContent(savedArticle.getNumber(), writeRequest.getContent());
			ArticleContent savedArticleContent = articleContentDAO.insert(conn, articleContent);
			
			if(savedArticleContent == null) {
				throw new RuntimeException("fail to insert article_content");
			}
			
			conn.commit();
			
			return savedArticle.getNumber();
			
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} catch (RuntimeException e) {
			JdbcUtil.rollback(conn);
			throw e;
		} finally {
			JdbcUtil.close(conn);
		}
		
	}

	private Article toArticle(WriteRequest writeRequest) {
		Date now = new Date();
		return new Article(null,  // article_no
				writeRequest.getTitle(), // title 
				new ArrayList<>(), // reply_list
				new ArrayList<>(), // like_list
				0, // read_cnt
				now, // regdate
				null, //moddate
				writeRequest.getWriter()); // writer
	}
}