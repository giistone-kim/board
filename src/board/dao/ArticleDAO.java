package board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import board.model.Article;
import board.model.ArticleLike;
import board.model.ArticleReply;
import board.model.Writer;
import jdbc.JdbcUtil;

public class ArticleDAO {

	public int selectCount(Connection conn) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select count(*) from article");
			if (rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
		
	}

	public List<Article> select(Connection conn, int startRow, int size, String sort) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement
					("select * from article order by "+ sort +" desc limit ?, ?");
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, size);
			rs = pstmt.executeQuery();
			List<Article> articleList = new ArrayList<>();
			while (rs.next()) {
				articleList.add(convertArticle(rs, conn));
			}
			
			return articleList;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	private Article convertArticle(ResultSet rs, Connection conn) throws SQLException {
		return new Article(rs.getInt("article_no"), 
				rs.getString("title"), 
				getArticleReplyList(conn, rs.getInt("article_no")), 
				getArticleLikeList(conn, rs.getInt("article_no")), 
				rs.getInt("read_cnt"), 
				toDate(rs.getTimestamp("regdate")),
				toDate(rs.getTimestamp("moddate")), 
				new Writer(rs.getInt("writer_id"), 
						rs.getString("nickname"),
						rs.getString("profile_image")));
	}
	
	private Date toDate(Timestamp timestamp) {
		return timestamp == null? null : new Date(timestamp.getTime());
	}
	
	public Article insert(Connection conn, Article article) throws SQLException {
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement("insert into article "
					+ "(title, tot_reply_cnt, tot_like_cnt, read_cnt, regdate, moddate, writer_id, nickname, profile_image) "
					+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
			pstmt.setString(1, article.getTitle());
			pstmt.setInt(2, article.getArticleReplySize());
			pstmt.setInt(3, article.getArticleLikeSize());
			pstmt.setInt(4, article.getReadCnt());
			pstmt.setTimestamp(5, toTimestamp(article.getRegDate()));
			pstmt.setTimestamp(6, toTimestamp(article.getModDate()));
			pstmt.setInt(7, article.getWriter().getId());
			pstmt.setString(8, article.getWriter().getNickname());
			pstmt.setString(9, article.getWriter().getProfileImage());
			int insertedCount = pstmt.executeUpdate();
			
			if(insertedCount > 0) {
				stmt = conn.createStatement();
				rs = stmt.executeQuery("select last_insert_id() from article");
				if(rs.next()) {
					Integer newNum = rs.getInt(1);
					return new Article(newNum, 
							article.getTitle(), 
							article.getArticleReplyList(), 
							article.getArticleLikeList(), 
							article.getReadCnt(), 
							article.getRegDate(), 
							article.getModDate(), 
							article.getWriter());
				}
			}
			return null;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
			JdbcUtil.close(pstmt);
		}
	}
	
	private Timestamp toTimestamp(Date date) {
		return date == null? null : new Timestamp(date.getTime());
	}
	
	public Article selectByNo(Connection conn, int no) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select * from article where article_no=?");
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			Article article = null;
			if(rs.next()) {
				article = convertArticle(rs, conn);
			}
			return article;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public List<Article> selectListById(Connection conn, int id) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select * from article where writer_id=? order by regdate desc");
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			List<Article> articleList = new ArrayList<>();
			while(rs.next()) {
				articleList.add(convertArticle(rs, conn)) ;
			}
			return articleList;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public List<ArticleLike> selectLikeListById(Connection conn, int id) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select * from article_like where member_id=? order by regdate desc");
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			List<ArticleLike> articleLikeList = new ArrayList<>();
			while(rs.next()) {
				articleLikeList.add(convertArticleLike(rs)) ;
			}
			return articleLikeList;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public List<ArticleReply> selectReplyListById(Connection conn, int id) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select * from article_reply where member_id=? order by regdate desc");
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			List<ArticleReply> articleReplyList = new ArrayList<>();
			while(rs.next()) {
				articleReplyList.add(convertArticleReply(rs)) ;
			}
			return articleReplyList;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public Article insertReply(Connection conn, ArticleReply articleReply) throws SQLException {
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try { 
			pstmt = conn.prepareStatement("insert into article_reply(member_id, nickname, content, regdate, article_no) "
				+ "value(?, ?, ?, ?, ?)");
			pstmt.setInt(1, articleReply.getMemberId());
			pstmt.setString(2, articleReply.getNickname());
			pstmt.setString(3, articleReply.getContent());
			pstmt.setTimestamp(4, toTimestamp(articleReply.getRegdate()));
			pstmt.setInt(5, articleReply.getArticleNo());
			int insertedCount = pstmt.executeUpdate();
			
			if(insertedCount > 0) {
				stmt = conn.createStatement();
				rs = stmt.executeQuery("select * from article where article_no=" + articleReply.getArticleNo());
				if(rs.next()) {
					return convertArticle(rs, conn);
				}
			}
			return null;
			
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
			JdbcUtil.close(pstmt);
		}
	}
	
	public List<ArticleReply> getArticleReplyList(Connection conn, int articleNo) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement("select * from article_reply where article_no=? order by reply_no desc");
			pstmt.setInt(1, articleNo);
			rs = pstmt.executeQuery();
			List<ArticleReply> articleReplyList = new ArrayList<>();
			while(rs.next()) {
				articleReplyList.add(convertArticleReply(rs));
			}
			return articleReplyList;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
	}
	
	public List<ArticleReply> getArticleReplyList(Connection conn, int articleNo, int replyPage, int replySize) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement("select * from article_reply where article_no=? order by reply_no desc limit ?,?");
			pstmt.setInt(1, articleNo);
			pstmt.setInt(2, replyPage);
			pstmt.setInt(3, replySize);
			rs = pstmt.executeQuery();
			List<ArticleReply> articleReplyList = new ArrayList<>();
			while(rs.next()) {
				articleReplyList.add(convertArticleReply(rs));
			}
			return articleReplyList;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
	}
	
	public int deleteArticleReply(Connection conn, int replyNo) throws SQLException {
		try (PreparedStatement pstmt 
				= conn.prepareStatement("delete from article_reply where reply_no=?")) {
			pstmt.setInt(1, replyNo);
			return pstmt.executeUpdate();
		}
	}
	
	private ArticleReply convertArticleReply(ResultSet rs) throws SQLException {
		return new ArticleReply(rs.getInt("reply_no"), rs.getInt("member_id"), 
				rs.getString("nickname"), rs.getString("content"), 
				toDate(rs.getTimestamp("regdate")), rs.getInt("article_no"));
	}
	
	public void increaseReadCount(Connection conn, int no) throws SQLException {
		try (PreparedStatement pstmt 
				= conn.prepareStatement("update article set read_cnt = read_cnt + 1 "
				+ "where article_no=?")) {
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
		}
	}
	
	public ArticleLike insertLike(Connection conn, ArticleLike articleLike) throws SQLException {
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try { 
			pstmt = conn.prepareStatement("insert into article_like(member_id, nickname, regdate, article_no) "
				+ "value(?, ?, ?, ?);");
			pstmt.setInt(1, articleLike.getMemberId());
			pstmt.setString(2, articleLike.getNickname());
			pstmt.setTimestamp(3, toTimestamp(articleLike.getRegdate()));
			pstmt.setInt(4, articleLike.getArticleNo());
			int insertedCount = pstmt.executeUpdate();
			
			if(insertedCount > 0) {
				stmt = conn.createStatement();
				rs = stmt.executeQuery("select last_insert_id() from article_like");
				if(rs.next()) {
					Integer newNum = rs.getInt(1);
					return new ArticleLike(newNum, 
							articleLike.getMemberId(), 
							articleLike.getNickname(), 
							articleLike.getRegdate(), 
							articleLike.getArticleNo());
				}
			}
			return null;
			
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
			JdbcUtil.close(pstmt);
		}
	}

	public List<ArticleLike> getArticleLikeList(Connection conn, int articleNo) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement("select * from article_like where article_no=?");
			pstmt.setInt(1, articleNo);
			rs = pstmt.executeQuery();
			List<ArticleLike> articleLikeList = new ArrayList<>();
			while(rs.next()) {
				articleLikeList.add(convertArticleLike(rs));
			}
			return articleLikeList;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
	}
	
	public ArticleLike getArticleLike(Connection conn, int likeNo) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select * from article_like where like_no=?");
			pstmt.setInt(1, likeNo);
			rs = pstmt.executeQuery();
			ArticleLike articleLike = null;
			if(rs.next()) {
				articleLike = convertArticleLike(rs);
			}
			
			return articleLike;
			
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public int deleteArticleLike(Connection conn, int likeNo) throws SQLException {
		try (PreparedStatement pstmt 
				= conn.prepareStatement("delete from article_like where like_no=?")) {
			pstmt.setInt(1, likeNo);
			return pstmt.executeUpdate();
		}
	}
	
	private ArticleLike convertArticleLike(ResultSet rs) throws SQLException {
		return new ArticleLike(rs.getInt("like_no"), 
				rs.getInt("member_Id"), 
				rs.getString("nickname"),
				toDate(rs.getTimestamp("regdate")),
				rs.getInt("article_no"));
	}
	

	public int update(Connection conn, int no, String title) throws SQLException {
		try (PreparedStatement pstmt 
				= conn.prepareStatement("update article set title = ?, moddate = now() "
						+ "where article_no=?")) {
			pstmt.setString(1, title);
			pstmt.setInt(2, no);
			return pstmt.executeUpdate();
		}
	}
	
	public int updateLikeCnt(Connection conn, int no, int totLikeCnt) throws SQLException {
		try (PreparedStatement pstmt 
				= conn.prepareStatement("update article set tot_like_cnt = ? "
						+ "where article_no=?")) {
			pstmt.setInt(1, totLikeCnt);
			pstmt.setInt(2, no);
			return pstmt.executeUpdate();
		}
	}
	
	public int updateReplyCnt(Connection conn, int no, int totReplyCnt) throws SQLException {
		try (PreparedStatement pstmt 
				= conn.prepareStatement("update article set tot_reply_cnt = ? "
						+ "where article_no=?")) {
			pstmt.setInt(1, totReplyCnt);
			pstmt.setInt(2, no);
			return pstmt.executeUpdate();
		}
	}
	
	public int delete(Connection conn, int no) throws SQLException {
		try (PreparedStatement pstmt 
				= conn.prepareStatement("delete from article where article_no=?")) {
			pstmt.setInt(1, no);
			return pstmt.executeUpdate();
		}
	}
}