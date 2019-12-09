package board.service;


import java.sql.Connection;
import java.sql.SQLException;

import board.dao.ArticleContentDAO;
import board.dao.ArticleDAO;
import board.model.Article;
import board.model.ArticleContent;
import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;

public class DeleteArticleService {
	
	ArticleContentDAO articleContentDAO = new ArticleContentDAO();
	ArticleDAO articleDAO = new ArticleDAO();
	
	public void delete(int no, int memberId) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Article article = articleDAO.selectByNo(conn, no);
			if(article == null) {
				throw new ArticleNotFoundException();
			}
			
			ArticleContent content = articleContentDAO.selectById(conn, no);
			if(content == null) {
				throw new ArticleContentNotFoundException();
			}
			
			if(!PermissionChecker.canDo(memberId, article)) {
				throw new PermissionDeniedException();
			}
			
			articleDAO.delete(conn, no);
			articleContentDAO.delete(conn, no);
			conn.commit();
			
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} catch (PermissionDeniedException e) {
			JdbcUtil.rollback(conn);
			throw e;
		} finally {
			JdbcUtil.close(conn);
		}
		
	}
}