package auth.service;

public class User {
	private int id;
	private String email;
	private boolean registerCheck;
	private String rememberToken;
	private boolean hasPassword;
	private String nickname;
	private String profileImage; // *

	public User(int memberId, String email, String nickname, String profileImage) {
		this.id = memberId;
		this.email = email;
		this.nickname = nickname;
		this.profileImage = profileImage;
	}
	
	public User(int memberId, String email, boolean registerCheck, String rememberToken,
			boolean hasPassword, String nickname, String profileImage) {
		this.id = memberId;
		this.email = email;
		this.registerCheck = registerCheck;
		this.rememberToken = rememberToken;
		this.hasPassword = hasPassword;
		this.nickname = nickname;
		this.profileImage = profileImage;
	}

	public int getId() {
		return id;
	}
	
	public String getEmail() {
		return email;
	}

	public boolean isRegister_check() {
		return registerCheck;
	}
	
	public String getRememberToken() {
		return rememberToken;
	}
	
	public boolean getHasPassword() {
		return hasPassword;
	}
	
	public void setHasPassword(boolean check){
		this.hasPassword = check;
	}
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public String getNickname() {
		return nickname;
	}

	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	
	
}