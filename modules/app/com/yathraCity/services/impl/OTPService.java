package com.yathraCity.services.impl;

import com.razorthink.runtime.ExecException;
import com.razorthink.runtime.ServiceExecutionContext;
import com.yathraCity.cassandra.services.UserOTPServices;
import com.yathraCity.core.ResponseMessage;
import com.yathraCity.services.OTPServiceInterface;

/**
 * Car registration with the details
 * 
 * @author ashwing
 *         param registration of the car,booking the car
 */
public class OTPService implements OTPServiceInterface {

	private UserOTPServices otpServices = new UserOTPServices();

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
		catch( Exception e )
		{
			e.printStackTrace();
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
		catch( Exception e )
		{
			e.printStackTrace();
		}
		return response;
	}

}
