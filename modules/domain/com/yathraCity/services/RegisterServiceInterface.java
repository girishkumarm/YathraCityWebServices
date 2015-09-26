package com.yathraCity.services;
import com.razorthink.runtime.*;
import javax.ws.rs.core.StreamingOutput;
public interface RegisterServiceInterface{
	public com.yathraCity.core.RegisterUserResponse registerUser(ServiceExecutionContext ctx,com.yathraCity.core.RegisterUser input) throws ExecException;


}
