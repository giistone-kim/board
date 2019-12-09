package member.service;

public class MailInfo {
	private String email;
	private String registerCode;
	private String content;
	
	public MailInfo() {}
	
	public MailInfo(String email, String registerCode) {
		this.email = email;
		this.registerCode = registerCode;
	}

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public String getRegisterCode() {
		return registerCode;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setRegisterContent() {
		this.content = "<a href="
				+ "'http://localhost:8080/board/register.do?email="+email+"&registerCode="+registerCode+"'>"
				+ "지금 이곳을 눌러 바로 인증하세요.</a>";
	}
	
	public void setPasswordContent() {
		this.content = "<a href="
				+ "'http://localhost:8080/board/setPwd.do?email="+email+"'>"
				+ "지금 이곳을 눌러 비밀번호를 재설정하세요.</a>";
	}

}