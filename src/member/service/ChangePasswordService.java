package member.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import member.dao.MemberDAO;
import member.model.Member;

public class ChangePasswordService {
	MemberDAO memberDAO = new MemberDAO();
	
	public boolean changePwd(int memberId, String curPwd, String newPwd) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Member member = memberDAO.selectByMemberId(conn, memberId);

			if (member == null) {
				JdbcUtil.rollback(conn);
				throw new MemberNotFoundException();
			}
			
			if(curPwd != null) {
				if (!member.matchPassword(curPwd)) {
					throw new InvalidPasswordException();
				}
			}
			
			member.changePwd(newPwd);
			memberDAO.updateMyInfo(conn, member);
			conn.commit();
			return true;
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
	
	public boolean setPwd(String email, String newPwd) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Member member = memberDAO.selectByEmail(conn, email);

			if (member == null) {
				JdbcUtil.rollback(conn);
				throw new MemberNotFoundException();
			}
			
			member.changePwd(newPwd);
			memberDAO.updateMyInfo(conn, member);
			conn.commit();
			return true;
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
}

