package com.yathraCity.cassandra.tables;
import com.razorthink.runtime.*;
import javax.ws.rs.core.StreamingOutput;
public interface DeleteCoupensExpiredInterface{
	public com.yathraCity.core.RegisterCarResponse deleteCoupens(ServiceExecutionContext ctx) throws ExecException;


}
