package com.yathraCity.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.razorthink.runtime.ExecException;
import com.razorthink.runtime.ServiceExecutionContext;
import com.yathraCity.cassandra.services.UserOTPServices;
import com.yathraCity.core.ResponseMessage;
import com.yathraCity.services.OTPServiceInterface;
import defaultpkg.ErrorCodes;

/**
 * Car registration with the details
 * 
 * @author ashwing
 *         param registration of the car,booking the car
 */
public class OTPService implements OTPServiceInterface {

	private UserOTPServices otpServices = new UserOTPServices();
	private static Logger logger = LoggerFactory.getLogger(OTPService.class);

	@Override
	public ResponseMessage getOTP( ServiceExecutionContext ctx, String phoneNumber ) throws ExecException
	{
		ResponseMessage response = new ResponseMessage();
		response.setStatus("500");
		response.setMessage("Generating otp failed");
		try
		{
			if( phoneNumber == null || phoneNumber.trim().isEmpty() || phoneNumber.trim().length() > 10
					|| phoneNumber.trim().length() < 10 )
			{
				throw new Exception("Mandatory fields are missing to get OTP");
			}
			// before generating fetch for the otp
			// if exist send the same otp

			// else generate as given below
			String otp = otpServices.generateOTP(phoneNumber);

			if( otp != null )
			{
				response.setStatus("200");
				response.setMessage("success");
			}

		}
		catch( ExecException m )
		{
			logger.error("Error while fetching otp" + m.getMessage());
			throw m;
		}
		catch( Exception e )
		{
			logger.error("Error while fetching otp" + e.getMessage());
			throw new ExecException(ErrorCodes.APPLICATION_ERROR, e, e.getMessage());
		}
		return response;
	}

	@Override
	public ResponseMessage getOTPMatchResponse( ServiceExecutionContext ctx, String phoneNumber, String otp )
			throws ExecException
	{
		ResponseMessage response = new ResponseMessage();
		response.setStatus("500");
		response.setMessage("failed");
		try
		{
			if( phoneNumber == null || phoneNumber.trim().isEmpty() || phoneNumber.trim().length() > 10
					|| phoneNumber.trim().length() < 10 )
			{
				throw new Exception("Mandatory fields are missing to get OTP");
			}

			String otpPresent = otpServices.fetchOtpForPhoneNumber(phoneNumber);

			if( otp.equals(otpPresent) )
			{
				response.setStatus("200");
				response.setMessage("success");
			}

		}
		catch( ExecException m )
		{
			logger.error("Error while fetching otp" + m.getMessage());
			throw m;
		}
		catch( Exception e )
		{
			logger.error("Error while fetching otp" + e.getMessage());
			throw new ExecException(ErrorCodes.APPLICATION_ERROR, e, e.getMessage());
		}
		return response;
	}

}
