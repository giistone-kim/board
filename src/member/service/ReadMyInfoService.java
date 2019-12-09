package member.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.ConnectionProvider;
import member.dao.MemberDAO;
import member.model.Member;

public class ReadMyInfoService {
	MemberDAO memberDAO = new MemberDAO();

	public MyInfo getMyInfo(int memberId) {

		try (Connection conn = ConnectionProvider.getConnection()) {
			Member member = memberDAO.selectByMemberId(conn, memberId);

			if (member == null) {
				throw new MemberNotFoundException();
			}

			return new MyInfo(member.getNickname(), member.getProfileImage());

		} catch (SQLException e) {
			throw new RuntimeException();
		}
		
	}
}