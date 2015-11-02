package com.yathraCity.services.impl;

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
				response.setMessage("Successfully executed loginUser service");
			}
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		return null;
	}

}
