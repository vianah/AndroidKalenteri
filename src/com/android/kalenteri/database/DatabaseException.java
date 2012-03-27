package com.android.kalenteri.database;

public class DatabaseException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//constructor for exception description
	public DatabaseException(String description)
	{
	  super(description);
	}

	@Override
	public String toString() {
		return this.getMessage().toString(); 
	}
	
	
}
