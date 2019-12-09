package member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import auth.service.LoginRequest;
import jdbc.JdbcUtil;
import member.model.Member;

public class MemberDAO {
	
	public Member selectByEmail(Connection conn, String email) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select * from members where email=?");
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			Member member = null;
			if (rs.next()) {
				member = transeferResultsetToMember(rs);
			}
			return member;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public void updateToken(Connection conn, int memberId, String token) throws SQLException { // 토큰 삽입
		try (PreparedStatement pstmt 
				= conn.prepareStatement("update members set remember_token=? where member_id=?")) {
			pstmt.setString(1, token);
			pstmt.setInt(2, memberId);
			pstmt.executeUpdate();
		}
	}
	
	public Member selectByToken(Connection conn, String token) throws SQLException { // 토큰으로 정보 찾기
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select * from members where remember_token=?");
			pstmt.setString(1, token);
			rs = pstmt.executeQuery();
			Member member = null;
			if (rs.next()) {
				member = transeferResultsetToMember(rs);
			}
			return member;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	private Member transeferResultsetToMember(ResultSet rs) throws SQLException {
		return new Member(rs.getInt("member_id"), 
				rs.getString("email"), 
				rs.getString("password"), 
				rs.getString("nickname"), 
				new Date(rs.getTimestamp("regdate").getTime()), 
				rs.getString("profile_image"), 
				rs.getString("register_code"), 
				rs.getBoolean("register_check"),
				rs.getString("remember_token"),
				rs.getString("access_token"));
	}
	
	public void insertData(Connection conn, Member member) throws SQLException {
		try (PreparedStatement pstmt = conn.prepareStatement
				("insert into members(email, password, nickname, "
						+ "regdate, profile_image, register_code, register_check, "
						+ "access_token) "
						+ "values(?, ?, ?, ?, ?, ?, ?, ?)")) {
			pstmt.setString(1, member.getEmail());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getNickname());
			pstmt.setTimestamp(4, new Timestamp(member.getRegdate().getTime()));
			pstmt.setString(5, member.getProfileImage());
			pstmt.setString(6, member.getRegisterCode());
			pstmt.setBoolean(7, member.isRegisterCheck());
			pstmt.setString(8, member.getAccessToken());
			pstmt.executeUpdate();
		}
	}
	
	public void updateDataWithNaverInfo(Connection conn, LoginRequest loginRequest) 
			throws SQLException { // 네아로 정보 업뎃
		try (PreparedStatement pstmt = conn.prepareStatement
				("update members set nickname= ?, profile_image= ?, access_token= ? where email = ?")) {
			pstmt.setString(1, loginRequest.getNickname());
			pstmt.setString(2, loginRequest.getProfile_image());
			pstmt.setString(3, loginRequest.getAccessToken());
			pstmt.setString(4, loginRequest.getEmail());
			pstmt.executeUpdate();
		}
	}
	
	public void updateDataWithFacebookInfo(Connection conn, LoginRequest loginRequest) 
			throws SQLException { // 페북 정보 업뎃
		try (PreparedStatement pstmt = conn.prepareStatement
				("update members set nickname= ?, profile_image= ?, access_token= ? where email = ?")) {
			pstmt.setString(1, loginRequest.getName());
			pstmt.setString(2, loginRequest.getProfile_image());
			pstmt.setString(3, loginRequest.getAccessToken());
			pstmt.setString(4, loginRequest.getEmail());
			pstmt.executeUpdate();
		}
	}
	
	
	public void updateDataWithKakaoInfo(Connection conn, LoginRequest loginRequest)
		throws SQLException {
		try (PreparedStatement pstmt = conn.prepareStatement
				("update members set nickname = ?, profile_image= ?, access_token= ? where email = ?")){
			pstmt.setString(1, loginRequest.getNickname());
			pstmt.setString(2, loginRequest.getProfile_image());
			pstmt.setString(3, loginRequest.getAccessToken());
			pstmt.setString(4, loginRequest.getEmail());
			pstmt.executeUpdate();
			}
	}
	
	public Member selectByMemberId(Connection conn, int memberId) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select * from members where member_id = ?");
			pstmt.setInt(1, memberId);
			rs = pstmt.executeQuery();
			Member member = null;
			if (rs.next()) {
				member = transeferResultsetToMember(rs);
			}
			return member;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public void deleteMyAccount(Connection conn, int memberId) throws SQLException {
		try (PreparedStatement pstmt = conn.prepareStatement
				("delete from members where member_id = ?")) {
			pstmt.setInt(1, memberId);
			pstmt.executeUpdate();
		}
	}
	
	public void updateMyInfo(Connection conn, Member member) throws SQLException {
		try (PreparedStatement pstmt = conn.prepareStatement
				("update members set password = ?, nickname = ?, profile_image = ?, register_check = ? where member_id = ?")) {
			
			pstmt.setString(1, member.getPassword());
			pstmt.setString(2, member.getNickname());
			pstmt.setString(3, member.getProfileImage());
			pstmt.setBoolean(4, member.isRegisterCheck());
			pstmt.setInt(5,  member.getMemberId());
			pstmt.executeUpdate();
		}
	}
	
	public boolean selectByNickname(Connection conn, String nickname) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select * from members where nickname=?");
			pstmt.setString(1, nickname);
			rs = pstmt.executeQuery();
			boolean isDuplicated = false;
			if (rs.next()) {
				isDuplicated = true;
			}
			return isDuplicated;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
}

