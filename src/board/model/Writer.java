package board.model;

public class Writer {
	private Integer id;
	private String nickname;
	private String profileImage;

	public Writer(Integer memberId, String nickname, String profileImage) {
		this.id = memberId;
		this.nickname = nickname;
		this.profileImage = profileImage;
	}

	public Integer getId() {
		return id;
	}

	public String getNickname() {
		return nickname;
	}

	public String getProfileImage() {
		return profileImage;
	}

}
