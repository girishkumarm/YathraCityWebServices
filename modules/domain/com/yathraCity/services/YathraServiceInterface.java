package com.yathraCity.services;
import com.razorthink.runtime.*;
import javax.ws.rs.core.StreamingOutput;
public interface YathraServiceInterface{
	public com.yathraCity.core.ResponseMessage bookingCar(ServiceExecutionContext ctx,com.yathraCity.core.BookedCarDetails input) throws ExecException;


	public com.yathraCity.core.ResponseMessage getOTP(ServiceExecutionContext ctx,String phoneNumber) throws ExecException;


	public com.yathraCity.core.ResponseMessage getOTPMatchResponse(ServiceExecutionContext ctx,String phoneNumber,String otp) throws ExecException;


}
