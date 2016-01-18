package com.yathraCity.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.razorthink.runtime.ExecException;
import com.razorthink.runtime.ServiceExecutionContext;
import com.yathraCity.cassandra.services.UserService;
import com.yathraCity.core.LoginInput;
import com.yathraCity.core.LoginResponse;
import com.yathraCity.services.LoginServiceInterface;

import defaultpkg.ErrorCodes;

/**
 * login service
 * @author ashwing
 * param user credentials
 */
public class LoginService implements LoginServiceInterface {

	private UserService loginUserService = new UserService();
	private static Logger logger = LoggerFactory.getLogger( LoginService.class );
	//Authanticating the user by entering the crediantials
	@Override
	public LoginResponse loginUser( ServiceExecutionContext ctx, LoginInput input ) throws ExecException
	{
		boolean result = false;
		LoginResponse response = new LoginResponse();
		response.setFlag("false");
		response.setMessage("Failed to execute loginUser service");
		try
		{
			if( input == null || input.getUserAccountId() == null || input.getUserAccountId().isEmpty()
					|| input.getPassword() == null || input.getPassword().isEmpty() )
			{
				throw new ExecException(ErrorCodes.MISSING_FIELD, null, "Mandatory Fields are missing");
			}

			// check whether the email id is valid
//			if( CheckIfMailIdIsValid.isEmail(input.getUserAccountId()) )
//			{
//				response.setMessage("Not a valid Email Id");
//				throw new ExecException(ErrorCodes.INVALID_EMAIL_ID, null, "Not a valid Email Id");
//			}

			// check the user is valid and his/her password matches
			result = loginUserService.checkUserExistence(input);

			if( result == true )
			{
				response.setFlag("true");
				response.setMessage("Successfully logged in");
			}
			else
			{
				response.setFlag("false");
				response.setMessage("not logged-in");
			}
		}
		catch( Exception e )
		{
			logger.error( "Error while logging in the coupen"
					+ e.getMessage() );
		}
		return response;
	}

}
