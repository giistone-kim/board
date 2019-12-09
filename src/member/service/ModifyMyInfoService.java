package member.service;

import java.sql.Connection;
import java.sql.SQLException;

import auth.service.User;
import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import member.dao.MemberDAO;
import member.model.Member;

public class ModifyMyInfoService {
	MemberDAO memberDAO = new MemberDAO();

	public User modify(int memberId, MyInfo myInfo) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			Member member = memberDAO.selectByMemberId(conn, memberId);

			if (member == null) { // 회원 존재 여부 체크
				JdbcUtil.rollback(conn);
				new MemberNotFoundException();
			}
			
			boolean isDuplicatedNickname = false;
			
			if (!myInfo.compareNickname(member.getNickname())) {
				isDuplicatedNickname = memberDAO.selectByNickname(conn, myInfo.getNickname());
			}
			
			if (isDuplicatedNickname) {
				JdbcUtil.rollback(conn);
				throw new DuplicatedNickNameException();
			}
			
			member.changeMyInfo(myInfo);
			memberDAO.updateMyInfo(conn, member);
			
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
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
}