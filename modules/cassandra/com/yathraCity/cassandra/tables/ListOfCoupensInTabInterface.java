package com.yathraCity.cassandra.tables;
import com.razorthink.runtime.*;
import javax.ws.rs.core.StreamingOutput;
public interface ListOfCoupensInTabInterface{
	public com.yathraCity.core.ListOfCoupensUsed getallthecupons(ServiceExecutionContext ctx) throws ExecException;


}
