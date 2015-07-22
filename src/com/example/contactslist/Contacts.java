package com.example.contactslist;

public class Contacts {
	private String id,userName,decription;
	
	public Contacts(String id,String userName,String decription){
		this.id = id;
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

	
	
}
