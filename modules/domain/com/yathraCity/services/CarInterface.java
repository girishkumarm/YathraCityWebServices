package com.yathraCity.services;
import com.razorthink.runtime.*;
import javax.ws.rs.core.StreamingOutput;
public interface CarInterface{
	public com.yathraCity.core.ResponseMessage registerCar(ServiceExecutionContext ctx,com.yathraCity.core.RegisterCarInput input) throws ExecException;


	public com.yathraCity.core.ResponseMessage checkCarAvailability(ServiceExecutionContext ctx,com.yathraCity.core.CheckAvailabilityInput input) throws ExecException;


	public com.yathraCity.core.ListOfAvailableCars getCarDetails(ServiceExecutionContext ctx,com.yathraCity.core.FetchCarDetails input) throws ExecException;


}
