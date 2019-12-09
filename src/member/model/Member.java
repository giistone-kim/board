package member.model;

import java.util.Date;

import member.service.MyInfo;

public class Member {
	private int memberId;
	private String email;
	private String password;
	private String nickname;
	private Date regdate;
	private String profileImage;
	private String registerCode;
	private boolean registerCheck;
	private String rememberToken;
	private String accessToken;
	
	public Member(String email, String password, String nickname, Date regdate, 
			String profileImage, String registerCode, boolean registerCheck, 
			String rememberToken, String accessToken) {
		this.email = email;
		this.password = password;
		this.nickname = nickname;
		this.regdate = regdate;
		this.profileImage = profileImage;
		this.registerCode = registerCode;
		this.registerCheck = registerCheck;
		this.rememberToken = rememberToken;
		this.accessToken = accessToken;
	}
	
	public Member(int memberId, String email, String password, String nickname, Date regdate, 
			String profileImage, String registerCode, boolean registerCheck, 
			String rememberToken, String accessToken) {
		this.memberId = memberId;
		this.email = email;
		this.password = password;
		this.nickname = nickname;
		this.regdate = regdate;
		this.profileImage = profileImage;
		this.registerCode = registerCode;
		this.registerCheck = registerCheck;
		this.rememberToken = rememberToken;
		this.accessToken = accessToken;
	}

	public int getMemberId() {
		return memberId;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getNickname() {
		return nickname;
	}

	public Date getRegdate() {
		return regdate;
	}

	public String getProfileImage() {
		return profileImage;
	}

	public String getRegisterCode() {
		return registerCode;
	}

	public boolean isRegisterCheck() {
		return registerCheck;
	}

	public String getRememberToken() {
		return rememberToken;
	}
	
	public String getAccessToken() {
		return accessToken;
	}

	public boolean matchPassword(String password) {
		return this.password.equals(password);
	}
	
	public boolean matchRegisterCode(String registerCode) {
		return this.registerCode.equals(registerCode);
	}
	
	public void changeMyInfo(MyInfo myInfo) {
		this.nickname = myInfo.getNickname();
		this.profileImage = myInfo.getProfileImage();
	}
	
	public void changePwd(String newPwd) {
		this.password = newPwd;
	}
	
	public void setRegisterCheck(boolean check) {
		this.registerCheck = check;
	}

}