package com.yathraCity.services;
import com.razorthink.runtime.*;
import javax.ws.rs.core.StreamingOutput;
public interface YathraServiceInterface{
	public com.yathraCity.core.ResponseMessage registerCar(ServiceExecutionContext ctx,com.yathraCity.core.RegisterCarInput input) throws ExecException;


	public com.yathraCity.core.ResponseMessage bookCar(ServiceExecutionContext ctx,com.yathraCity.core.RegisterBookingInput input) throws ExecException;


	public com.yathraCity.core.ResponseMessage checkCarAvailability(ServiceExecutionContext ctx,com.yathraCity.core.CheckAvailabilityInput input) throws ExecException;


	public com.yathraCity.core.ListOfAvailableCars getCarDetails(ServiceExecutionContext ctx,String pickUpPoint,String capacity) throws ExecException;


	public com.yathraCity.core.ResponseMessage getOTP(ServiceExecutionContext ctx,String phoneNumber) throws ExecException;


	public com.yathraCity.core.ResponseMessage getOTPMatchResponse(ServiceExecutionContext ctx,String phoneNumber,String otp) throws ExecException;


}
