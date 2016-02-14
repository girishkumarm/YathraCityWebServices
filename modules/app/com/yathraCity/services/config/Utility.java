package com.yathraCity.services.config;


import java.util.ArrayList;
import java.util.List;

public class Utility
{

	public static List<StringClass> changeStringToStringClass( List<String> stringList )
	{
		List<StringClass> stringClassList = new ArrayList<StringClass>();
		for( String s : stringList )
		{
			StringClass stringClass = new StringClass( s );
			stringClassList.add( stringClass );
		}
		return stringClassList;
	}

	public static List<String> changeStringClassToString( List<StringClass> stringClassList )
	{
		List<String> stringList = new ArrayList<String>();
		for( StringClass s : stringClassList )
		{
			stringList.add( s.getValue() );
		}
		return stringList;
	}

}
