package com.yathraCity.services;
import com.razorthink.runtime.*;
import javax.ws.rs.core.StreamingOutput;
public interface RegisterDriverInterface{
	public com.yathraCity.core.RegisterCarResponse registerdriver(ServiceExecutionContext ctx,com.yathraCity.core.RegisterCarDetails registerDriver) throws ExecException;


}
