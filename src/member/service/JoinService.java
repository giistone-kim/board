package member.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import member.dao.MemberDAO;
import member.model.Member;
import util.SendEmail;

public class JoinService {

	private MemberDAO memberDAO = new MemberDAO();

	public void join(JoinRequest joinRequest) {
		Connection conn = null;
		Member member = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
	
			member = memberDAO.selectByEmail(conn, joinRequest.getEmail());
			
			if (member != null) {
				JdbcUtil.rollback(conn);
				throw new DuplicateIdException();
			}
			
			memberDAO.insertData(conn, 
					new Member(
							joinRequest.getEmail(), 
							joinRequest.getPassword(),
							null, // 유저 닉네임 생성
							new Date(), // 가입일 
							null, // 유저 기본 프로필 설정
							joinRequest.getRegisterCode(), // 이메일 인증 코드
							false, // 이메일 인증 체크 기본 false
							null, // 로그인 유지 토큰
							null)); // 접근 토큰 //*나중에 생성하기
			
			sendEmail(joinRequest.getEmail(), joinRequest.getRegisterCode());
			conn.commit();
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(conn);
			
		}
	}
	
	public void sendEmail(String email, String registerCode) { // 이메일 전송 메소드
		Connection conn = null;
		Member member = null;
		try {
			conn = ConnectionProvider.getConnection();
			
			if (registerCode == null) { // 이메일 재전송 시 기존 발급했던 인증 코드 DB에서 가져옴
				member = memberDAO.selectByEmail(conn, email);
				registerCode = member.getRegisterCode();
			}

			MailInfo mailInfo = new MailInfo(email, registerCode); // 메일 정보 
			mailInfo.setRegisterContent(); // 메일 설정 (인증)
			SendEmail.send(mailInfo); // 이메일 전송 관련 static 메소드
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(conn);
		}

	}
}
