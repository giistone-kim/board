package member.service;

import java.sql.Connection;
import java.sql.SQLException;

import auth.service.User;
import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import member.dao.MemberDAO;
import member.model.Member;

public class RegisterEmailService {
	MemberDAO memberDAO = new MemberDAO();

	public User register(String email, String registerCode) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			Member member = memberDAO.selectByEmail(conn, email);

			if (member == null) {
				throw new MemberNotFoundException();
			}

			if (!member.matchRegisterCode(registerCode)) {
				throw new RegisterCodeNotMatchException();
			}

			member.setRegisterCheck(true);
			memberDAO.updateMyInfo(conn, member);
			conn.commit();
			boolean passwordCheck = member.getPassword() == null ? false : true;
			
			return new User(member.getMemberId(), 
					member.getEmail(), 
					member.isRegisterCheck(), 
					member.getRememberToken(), 
					passwordCheck,
					member.getNickname(),
					member.getProfileImage());
			
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}

	}
}