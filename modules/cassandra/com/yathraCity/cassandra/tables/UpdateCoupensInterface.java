package com.yathraCity.cassandra.tables;
import com.razorthink.runtime.*;
import javax.ws.rs.core.StreamingOutput;
public interface UpdateCoupensInterface{
	public com.yathraCity.core.RegisterCarResponse updateCoupen(ServiceExecutionContext ctx,com.yathraCity.core.CoupenDeails coupensDetails) throws ExecException;


}
