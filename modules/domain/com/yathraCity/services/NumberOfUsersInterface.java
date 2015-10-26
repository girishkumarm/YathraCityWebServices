package com.yathraCity.services;
import com.razorthink.runtime.*;
import javax.ws.rs.core.StreamingOutput;
public interface NumberOfUsersInterface{
	public com.yathraCity.core.TotalNumberOfUsers getNumberOfUsers(ServiceExecutionContext ctx) throws ExecException;


}
