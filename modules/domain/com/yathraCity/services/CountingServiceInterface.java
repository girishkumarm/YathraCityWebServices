package com.yathraCity.services;
import com.razorthink.runtime.*;
import javax.ws.rs.core.StreamingOutput;
public interface CountingServiceInterface{
	public com.yathraCity.core.Message countNumberOfUsers(ServiceExecutionContext ctx) throws ExecException;


}
