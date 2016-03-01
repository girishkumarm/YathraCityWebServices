package com.yathraCity.services.impl;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.razorthink.runtime.ExecException;
import com.razorthink.runtime.ServiceExecutionContext;
import com.yathraCity.cassandra.dao.BookingDetailsDAO;
import com.yathraCity.core.BookedCarDetails;
import com.yathraCity.core.ConfirmBookedCarDetails;
import com.yathraCity.core.ResponseMessage;
import com.yathraCity.services.SendMailToCustomersInterface;
import com.yathraCity.services.config.Email;
import com.yathraCity.services.utils.ConfirmationMessageUtil;
import defaultpkg.ErrorCodes;

public class SendMailToCustomers implements SendMailToCustomersInterface {

	BookingDetailsDAO details = new BookingDetailsDAO();
	ResponseMessage respMessage = new ResponseMessage();
	private static Logger logger = LoggerFactory.getLogger(SendMailToCustomers.class);

	@Override
	public ResponseMessage mailCustomer( ServiceExecutionContext ctx, ConfirmBookedCarDetails input )
			throws ExecException
	{
		List<BookedCarDetails> bookingDetails = null;
		respMessage.setMessage("bad gateway");
		respMessage.setStatus("500");
		try
		{
			bookingDetails = details.confirmAndGetBookingDetailsForMailing(input);
			if( bookingDetails == null || bookingDetails.size() <= 0 )
			{
				respMessage.setMessage("NOT FOUND BOOKING DETAILS");
				respMessage.setStatus("400");
			}
			respMessage.setMessage("sucess");
			respMessage.setStatus("200");

			String bodyHtml = ConfirmationMessageUtil.getConfirmationMessage(bookingDetails.get(0));

			// body of the email
			String bodyText = "";

			// from address or the email id of the person from whom mail has to
			// be
			// sent
			String userName = "tabcarsconfirmation@gmail.com";

			// password
			String passWord = "tabcars890";

			// to address or the receipent of the mail
			String receipent = bookingDetails.get(0).getCustomerEmail();

			Email.getInstance().sendEmail(bodyHtml, bodyText, userName, passWord, receipent,
					"Tab Cars Booking Confirmation");

		}
		catch( ExecException m )
		{
			logger.error("Error while confirming the user" + m.getMessage());
			throw m;
		}
		catch( Exception e )
		{
			logger.error("Error while confirming the user" + e.getMessage());
			throw new ExecException(ErrorCodes.APPLICATION_ERROR, e, e.getMessage());
		}
		return respMessage;
	}

}
