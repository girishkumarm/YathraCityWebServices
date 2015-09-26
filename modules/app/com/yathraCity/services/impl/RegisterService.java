package com.yathraCity.services.impl;


import com.razorthink.runtime.ExecException;
import com.razorthink.runtime.ServiceExecutionContext;
import com.yathraCity.cassandra.services.UserService;
import com.yathraCity.core.RegisterUser;
import com.yathraCity.core.RegisterUserResponse;
import com.yathraCity.services.RegisterServiceInterface;

import defaultpkg.ErrorCodes;

public class RegisterService implements RegisterServiceInterface
{

	private UserService registerUserService = new UserService();

	@Override
	public RegisterUserResponse registerUser( ServiceExecutionContext ctx, RegisterUser input ) throws ExecException
	{
		boolean result = false;
		RegisterUserResponse response = new RegisterUserResponse();
		response.setFlag( "false" );
		response.setMessage( "Failed to execute registerUser service" );
		try
		{
			// checking for all the mandatory fields required in the service
			if( input == null || input.getUserName() == null || input.getUserName().trim().isEmpty()
					|| input.getUserAccountId() == null || input.getUserAccountId().trim().isEmpty()
					|| input.getPassword() == null || input.getPassword().trim().isEmpty()
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
			e.printStackTrace();
		}
		return response;
	}
}
