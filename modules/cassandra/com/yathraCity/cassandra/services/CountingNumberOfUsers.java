package com.yathraCity.cassandra.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.yathraCity.cassandra.dao.CountingDAO;
import com.yathraCity.cassandra.dao.UserDAO;

public class CountingNumberOfUsers 
{
	private static Logger logger = LoggerFactory.getLogger( UserDAO.class );
	private static CountingDAO count=null;
	
	public void countingMethod()
	{
		count=new CountingDAO();
		try
		{
			count.countUsers();
		}
		catch( Exception e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
