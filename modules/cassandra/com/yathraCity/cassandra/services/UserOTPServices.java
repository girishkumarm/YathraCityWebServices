package com.yathraCity.cassandra.services;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.yathraCity.cassandra.dao.UserDAO;
import com.yathraCity.cassandra.dao.UserOTPDAO;

// this is the api which act as an interface between the services and the dao
// where actually it contacts database
public class UserOTPServices
{

	private static Logger logger = LoggerFactory.getLogger( UserDAO.class );
	private static UserOTPDAO otpServices = new UserOTPDAO();

	public String generateOTP( String phoneNumber )
	{
		String result = null ;
		try
		{
			// checking for all the mandatory fields required in the service
			if( phoneNumber == null ||phoneNumber.trim().isEmpty()  )
			{
				throw new Exception( "Mandatory fields are missing to generate otp for the provided mobile number" );
			}

			// call dao to generate OTP
			result = otpServices.generateOTP( phoneNumber );
		}
		catch( Exception e )
		{
			logger.error( "Error while generating the otp for an user--->" + e.getMessage() );
		}
		return result;

	}

	public String fetchOtpForPhoneNumber( String phoneNumber )
	{
		String result = null;
		try
		{
			// checking for all the mandatory fields required in the service
			if( phoneNumber == null ||phoneNumber.trim().isEmpty()  )
			{
				throw new Exception( "Mandatory fields are missing to fetch otp" );
			}

			// call dao to get the registered otp for the mobile number
			result = otpServices.fetchOtpForPhoneNumber( phoneNumber );
		}
		catch( Exception e )
		{
			logger.error( "Error while fetching otp for an user--->" + e.getMessage() );
		}
		return result;

	}

}
