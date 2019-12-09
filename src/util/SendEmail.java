package util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import member.service.MailInfo;

public class SendEmail {
	public static void send(MailInfo mailInfo) {
		String admin = "";
		String password = "";
		
		//네이버 이용
		Properties props = System.getProperties();
		props.put("mail.smtp.user", admin);
		props.put("mail.smtp.host", "smtp.naver.com");
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.debug", "true");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");

		Authenticator auth = new Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication() {
            	return new PasswordAuthentication(admin+"@naver.com", password);
            }
        };
		
		Session mailSession = Session.getDefaultInstance(props, auth);
		Message msg = new MimeMessage(mailSession);

		try {
			InternetAddress to = new InternetAddress(mailInfo.getEmail()); // 수신자 주소 생성
			msg.setFrom(new InternetAddress(admin+"@naver.com")); // 송신자 설정
			msg.setRecipient(javax.mail.Message.RecipientType.TO, to); // 수신자 설정
			msg.setSubject("안녕하세요. 게시판 프로젝트입니다."); // 제목 설정
			msg.setSentDate(new java.util.Date()); // 보내는 날짜 설정
			msg.setContent(mailInfo.getContent(), "text/html;charset=euc-kr"); // 내용 설정 (HTML 형식)
			Transport.send(msg);
		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}

}