package member.service;

import util.RandomBox;

public class MyInfo {
	private String nickname;
	private String profileImage;
	
	public MyInfo(String nickname, String profile_image) {
		this.nickname = nickname;
		this.profileImage = profile_image;
	}
	
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	

	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profile_image) {
		this.profileImage = profile_image;
	}
	
	public void setDefaultProfile() {
		this.profileImage = RandomBox.getRandomImage();
	}
	
	public boolean compareNickname(String nickname) {
		return this.nickname.equals(nickname);
	}

	
	

}