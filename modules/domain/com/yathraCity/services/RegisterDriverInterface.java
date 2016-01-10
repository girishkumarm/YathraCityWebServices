package com.yathraCity.services;
import com.razorthink.runtime.*;
import javax.ws.rs.core.StreamingOutput;
public interface RegisterDriverInterface{
	public com.yathraCity.core.ResponseMessage registerdriver(ServiceExecutionContext ctx,com.yathraCity.core.RegisterDriverDetails registerDriver) throws ExecException;


}
