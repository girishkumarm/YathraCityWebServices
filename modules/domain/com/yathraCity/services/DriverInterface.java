package com.yathraCity.services;
import com.razorthink.runtime.*;
import javax.ws.rs.core.StreamingOutput;
public interface DriverInterface{
	public com.yathraCity.core.ResponseMessage registerdriver(ServiceExecutionContext ctx,com.yathraCity.core.DriverDetails driverDetails) throws ExecException;


	public com.yathraCity.core.ResponseMessage updateDriverAvailability(ServiceExecutionContext ctx,com.yathraCity.core.ConfirmDriverAvailability driverDetails) throws ExecException;


}
