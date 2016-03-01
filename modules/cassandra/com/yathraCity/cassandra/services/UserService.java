package com.yathraCity.cassandra.services;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.yathraCity.cassandra.dao.UserDAO;
import com.yathraCity.core.LoginInput;
import com.yathraCity.core.RegisterUser;

// this is the api which act as an interface between the services and the dao
// where actually it contacts database
public class UserService
{

	private static Logger logger = LoggerFactory.getLogger( UserDAO.class );
	private static UserDAO userDAO = new UserDAO();

	public boolean registerUser( RegisterUser user )
	{
		boolean result = false;
		try
		{
			// checking for all the mandatory fields required in the service
			if( user == null || user.getUserName() == null || user.getUserName().trim().isEmpty()
					|| user.getUserAccountId() == null || user.getUserAccountId().trim().isEmpty()
					|| user.getPassword() == null || user.getPassword().trim().isEmpty() || user.getPassword() == null
					|| user.getPassword().trim().isEmpty() )
			{
				throw new Exception( "Mandatory fields are missing to register the user:" );
			}

			// call dao to save user
			result = userDAO.addUser( user );
		}
		catch( Exception e )
		{
			logger.error( "Error while inserting the user data into the cassandra db while registering user--->"
					+ e.getMessage() );
		}
		return result;

	}

	public boolean checkUserExistence( LoginInput user )
	{
		try
		{
			// checking for all the mandatory fields required in the service
			if( user == null || user.getUserAccountId() == null || user.getUserAccountId().isEmpty()
					|| user.getPassword() == null || user.getPassword().isEmpty() )
			{
				throw new Exception( "Mandatory fields are missing to register the user:" );
			}

			// call dao to fatch user and check if it is null
			if( userDAO.fetchUser( user ) != null )
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		catch( Exception e )
		{
			
			logger.error( "Error while inserting the user data into the cassandra db while registering user--->"
					+ e.getMessage() );
			return false;
		}
		
	}

}
