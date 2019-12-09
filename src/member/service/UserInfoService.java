package member.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import auth.service.User;
import board.dao.ArticleDAO;
import board.model.Article;
import board.model.ArticleLike;
import board.model.ArticleReply;
import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import member.dao.MemberDAO;
import member.model.Member;
import member.model.UserInfo;

public class UserInfoService {
	private MemberDAO memberDAO = new MemberDAO();
	private ArticleDAO articleDAO = new ArticleDAO();

	public UserInfo selectUser(int userId) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();

			Member member = memberDAO.selectByMemberId(conn, userId);

			if (member == null) {
				throw new MemberNotFoundException();
			}

			List<Article> articleList = articleDAO.selectListById(conn, userId);

			List<ArticleLike> articleLikeList = articleDAO.selectLikeListById(conn, userId);

			List<ArticleReply> articleReplyList = articleDAO.selectReplyListById(conn, userId);

			return new UserInfo(
					new User(member.getMemberId(), member.getEmail(), member.getNickname(), member.getProfileImage()),
					articleList, articleLikeList, articleReplyList);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}

	}
}