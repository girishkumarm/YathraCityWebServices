package com.yathraCity.cassandra.pojo;

public class User {

	private String userName;
	private String userAccountId;
	private String password;
	private String phoneNumber;

	public User()
	{
	}

	public User( String userName, String userAccountId, String password )
	{
		this.userName = userName;
		this.userAccountId = userAccountId;
		this.password = password;
	}

	public String getUserName()
	{
		return this.userName;
	}

	public void setUserName( String userName )
	{
		this.userName = userName;
	}

	public String getUserAccountId()
	{
		return this.userAccountId;
	}

	public void setUserAccountId( String userAccountId )
	{
		this.userAccountId = userAccountId;
	}

	public String getPassword()
	{
		return this.password;
	}

	public void setPassword( String password )
	{
		this.password = password;
	}

	public String getPhoneNumber()
	{
		return phoneNumber;
	}

	public void setPhoneNumber( String phoneNumber )
	{
		this.phoneNumber = phoneNumber;
	}
	
}
