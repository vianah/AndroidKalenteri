package com.android.kalenteri.database;

public class User {
	
	private int userID;
	private String username;
	private int userType;
	
	public User(int Id, String uName, int uType) {
		// TODO Auto-generated constructor stub
		userID = Id;
		username = uName;
		userType = uType;
	}

	public String getUsername() {
		return username;
	}

	public int getUserType() {
		return userType;
	}
	
	public int getUserID() {
		return userID;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "ID: " + userID + " Username: " + username;
	}
	
	

}
