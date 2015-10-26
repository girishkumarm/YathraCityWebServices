package com.yathraCity.services;
import com.razorthink.runtime.*;
import javax.ws.rs.core.StreamingOutput;
public interface EnterCoupensInterface{
	public com.yathraCity.core.RegisterCarResponse coupens(ServiceExecutionContext ctx,com.yathraCity.core.CoupenDeails coupensDetails) throws ExecException;


}
