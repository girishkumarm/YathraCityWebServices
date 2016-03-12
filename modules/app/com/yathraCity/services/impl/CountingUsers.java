package com.yathraCity.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.razorthink.runtime.ExecException;
import com.razorthink.runtime.ServiceExecutionContext;
import com.yathraCity.cassandra.dao.CountingUsersDAO;
import com.yathraCity.core.ResponseMessage;
import com.yathraCity.core.TotalNumberOfUsers;
import com.yathraCity.services.CountingUsersInterface;

public class CountingUsers implements CountingUsersInterface {

	private ResponseMessage message = new ResponseMessage();
	CountingUsersDAO countUsers = new CountingUsersDAO();
	private static Logger logger = LoggerFactory.getLogger(Car.class);

	@Override
	public ResponseMessage countNumberOfUsers( ServiceExecutionContext ctx ) throws ExecException
	{
		message.setMessage("didnt updated the table");
		message.setStatus("500");
		try
		{
			countUsers.countUsers();
			message.setMessage("successfully updated");
			message.setStatus("200");
		}
		catch( Exception e )
		{
			logger.error("error while calling the counting service"+e.getMessage());
		}
		return message;
	}

	@Override
	public TotalNumberOfUsers getNumberOfUsers( ServiceExecutionContext ctx ) throws ExecException
	{
		TotalNumberOfUsers totalNumberOfUsers = new TotalNumberOfUsers();
		try
		{
			int numberOfUsers = countUsers.getNumberOfUsers();
			totalNumberOfUsers.setCount(numberOfUsers);
		}
		catch( Exception e )
		{
			logger.error("error while calling the getting service"+e.getMessage());
		}
		return totalNumberOfUsers;
	}

}