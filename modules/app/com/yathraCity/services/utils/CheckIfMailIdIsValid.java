package com.yathraCity.services.utils;


public class CheckIfMailIdIsValid
{

	public static boolean isEmail( String email )
	{
		String emailPattern = "^([A-Za-z0-9_\\-\\.])+\\@([A-Za-z0-9_\\-\\.])+\\.([A-Za-z]{2,4})";
		return email.matches( emailPattern );
	}

}
