package auth.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import member.dao.MemberDAO;
import member.model.Member;
import util.TokenGenerator;

public class AutoLoginService {

	MemberDAO memberDAO = new MemberDAO();
	
	public User autoLogin(String token) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Member member = memberDAO.selectByToken(conn, token);
			
			if(member == null) {
				throw new LoginFailException();
			}
			
			boolean passwordCheck = member.getPassword() == null ? false : true;
			
			token = TokenGenerator.getTokenKey(); // 토큰키 재생성해서
			memberDAO.updateToken(conn, member.getMemberId(), token); // 업데이트
			
			conn.commit();
			
			return new User(member.getMemberId(), 
					member.getEmail(), 
					member.isRegisterCheck(), 
					token,
					passwordCheck,
					member.getNickname(), 
					member.getProfileImage());
			
		} catch (SQLException e) {
			JdbcUtil.close(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
		
		
	}
}