package com.yathraCity.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.razorthink.runtime.ExecException;
import com.razorthink.runtime.ServiceExecutionContext;
import com.yathraCity.cassandra.dao.ContactUsDAO;
import com.yathraCity.core.ResponseMessage;
import com.yathraCity.services.ContactUsInterface;
import defaultpkg.ErrorCodes;

public class ContactUs implements ContactUsInterface {

	private ResponseMessage response = new ResponseMessage();
	private static Logger logger = LoggerFactory.getLogger(ContactUs.class);
	private final ContactUsDAO contact = new ContactUsDAO();

	@Override
	public ResponseMessage contactUs( ServiceExecutionContext ctx, com.yathraCity.core.ContactUs contactUs )
			throws ExecException
	{
		response.setMessage("internal server error");
		response.setStatus("500");
		try
		{
			if( contactUs == null || contactUs.getEmailId() == null || contactUs.getEmailId().isEmpty()
					|| contactUs.getName() == null || contactUs.getName().isEmpty() || contactUs.getMessage().isEmpty()
					|| contactUs.getMessage() == null )
			{
				throw new ExecException(ErrorCodes.MISSING_FIELD, null, "Mandatory Fields are missing");
			}

			boolean result = contact.storeMessage(contactUs);
			if( result == true )
			{
				response.setMessage("success");
				response.setStatus("200");
			}
		}
		catch( ExecException m )
		{
			logger.error("Error while saving the message from the user" + m.getMessage());
			throw m;
		}
		catch( Exception e )
		{
			logger.error("Error while saving the message from the user" + e.getMessage());
			throw new ExecException(ErrorCodes.APPLICATION_ERROR, e, e.getMessage());
		}
		return response;
	}

}
