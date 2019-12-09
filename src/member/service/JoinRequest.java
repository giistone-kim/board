package member.service;

import util.RandomBox;

public class JoinRequest {
	private String email;
	private String password;
	private String registerCode;

	public JoinRequest(String email, String password) {
		this.email = email;
		this.password = password;
		this.registerCode = RandomBox.getRandomRegisterCode();
	}

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getRegisterCode() {
		return registerCode;
	}

}
