package com.android.kalenteri.database;

public class User {

	private String username;
	private int userType;
	
	public User(String uName, int uType) {
		// TODO Auto-generated constructor stub
		username = uName;
		userType = uType;
	}

	public String getUsername() {
		return username;
	}

	public int getUserType() {
		return userType;
	}
	
	

}
