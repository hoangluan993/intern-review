package model;

public class Contacts {
	// Declare Variables
	private String id, userName, decription;
	private int avatar;

	public Contacts(String id, int avatar, String userName, String decription) {
		this.id = id;
		this.avatar = avatar;
		this.userName = userName;
		this.decription = decription;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDecription() {
		return decription;
	}

	public void setDecription(String decription) {
		this.decription = decription;
	}

	public int getAvatar() {
		return avatar;
	}

	public void setAvatar(int avatar) {
		this.avatar = avatar;
	}

}
