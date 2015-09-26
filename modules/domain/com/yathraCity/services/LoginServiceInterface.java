package com.yathraCity.services;
import com.razorthink.runtime.*;
import javax.ws.rs.core.StreamingOutput;
public interface LoginServiceInterface{
	public com.yathraCity.core.LoginResponse loginUser(ServiceExecutionContext ctx,com.yathraCity.core.LoginInput input) throws ExecException;


}
