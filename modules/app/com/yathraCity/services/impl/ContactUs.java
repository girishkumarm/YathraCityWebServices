package com.yathraCity.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.razorthink.runtime.ExecException;
import com.razorthink.runtime.ServiceExecutionContext;
import com.yathraCity.cassandra.dao.ContactUsDAO;
import com.yathraCity.core.ResponseMessage;
import com.yathraCity.services.ContactUsInterface;

public class ContactUs implements ContactUsInterface {

	private ResponseMessage response = new ResponseMessage();
	private static Logger logger = LoggerFactory.getLogger(ContactUs.class);

	@Override
	public ResponseMessage contactUs( ServiceExecutionContext ctx, com.yathraCity.core.ContactUs contactUs )
			throws ExecException
	{
		if( contactUs == null || contactUs.getEmailId() == null || contactUs.getEmailId().isEmpty()
				|| contactUs.getName() == null || contactUs.getName().isEmpty() || contactUs.getMessage().isEmpty()
				|| contactUs.getMessage() == null )
			try
			{
				response.setMessage("internal server error");
				response.setStatus("500");
				ContactUsDAO contact = new ContactUsDAO();
				boolean result = contact.storeMessage(contactUs);
				if( result == true )
				{
					response.setMessage("success");
					response.setStatus("200");
				}
			}
			catch( Exception e )
			{
				logger.error("Error while storing the message from the user" + e.getMessage());
			}
		return response;
	}

}
