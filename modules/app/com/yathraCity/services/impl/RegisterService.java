package com.yathraCity.services.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.razorthink.runtime.ExecException;
import com.razorthink.runtime.ServiceExecutionContext;
import com.yathraCity.cassandra.services.UserService;
import com.yathraCity.core.RegisterUser;
import com.yathraCity.core.RegisterUserResponse;
import com.yathraCity.services.RegisterServiceInterface;

import defaultpkg.ErrorCodes;

/**
 * Registration of new user by entering the details
 * @author ashwing
 * param register user details
 */
public class RegisterService implements RegisterServiceInterface
{
	RegisterUserResponse response = new RegisterUserResponse();
	private UserService registerUserService = new UserService();
	private static Logger logger = LoggerFactory.getLogger( RegisterService.class );
	
	//registartion of new user
	@Override
	public RegisterUserResponse registerUser( ServiceExecutionContext ctx, RegisterUser input ) throws ExecException
	{
		boolean result = false;
		response.setFlag( "false" );
		response.setMessage( "Failed to execute registerUser service" );
		try
		{
			// checking for all the mandatory fields required in the service
			if( input == null || input.getUserName() == null || input.getUserName().trim().isEmpty()
					|| input.getUserAccountId() == null || input.getUserAccountId().trim().isEmpty()
					|| input.getPhoneNumber() == null || input.getPhoneNumber().trim().isEmpty()
					|| input.getPassword() == null || input.getPassword().trim().isEmpty() )
			{
				throw new ExecException( ErrorCodes.MISSING_FIELD, null, "Mandatory Fields are missing" );
			}

			// check whether the email id is valid
//			if( CheckIfMailIdIsValid.isEmail( input.getUserAccountId() ) )
//			{
//				response.setMessage( "Not a valid Email Id" );
//				throw new ExecException( ErrorCodes.INVALID_EMAIL_ID, null, "Not a valid Email Id" );
//			}

			// save the user in the cassandra database
			result = registerUserService.registerUser( input );

			if( result == true )
			{
				response.setFlag( "true" );
				response.setMessage( "Successfully executed registerUser service" );
			}

		}
		catch( Exception e )
		{
			logger.error( "Error while registration of the user"
					+ e.getMessage() );
		}
		return response;
	}
}
