package auth.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import member.dao.MemberDAO;
import member.model.Member;

public class KLoginService {
	
	MemberDAO memberDAO = new MemberDAO();
	
	public User login(LoginRequest loginRequest) {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Member member = memberDAO.selectByEmail(conn, loginRequest.getEmail());
			
			if(member == null) {
				memberDAO.insertData(conn, 
						new Member(null,
								null,
								loginRequest.getNickname(),
								new Date(),
								loginRequest.getProfile_image(),
								null,
								true,
								null,
								loginRequest.getAccessToken()));
			} else {
				if(member.getNickname() != null)
					loginRequest.setNickname(member.getNickname());
				if(member.getProfileImage() != null)
					loginRequest.setProfile_image(member.getProfileImage());
					
				memberDAO.updateDataWithKakaoInfo(conn, loginRequest);
				System.out.println("acToken" + loginRequest.getAccessToken());
			}
			
				member = memberDAO.selectByEmail(conn, loginRequest.getEmail());
			
				boolean passwordCheck = member.getPassword() == null ? false : true;
				conn.commit();
				
				return new User(member.getMemberId(),
						member.getEmail(),
						member.isRegisterCheck(),
						member.getRememberToken(),
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
