package com.yathraCity.services.impl;

import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.gson.Gson;
import com.razorthink.runtime.ExecException;
import com.razorthink.runtime.ServiceExecutionContext;
import com.yathraCity.cassandra.dao.BookingDetailsDAO;
import com.yathraCity.core.BookedCarDetails;
import com.yathraCity.core.ConfirmBookedCarDetails;
import com.yathraCity.core.ResponseMessage;
import com.yathraCity.services.SendMailToCustomersInterface;
import com.yathraCity.services.config.Email;

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
			System.out.println("bookingDetails : "+new Gson().toJson(bookingDetails));

			// Body html which need to be sent in the mail
			String bodyHtml = "<h1>TAB</h1><h2>Thank you for booking<h2>" + "booking details:" + "your travel date:"
					+ bookingDetails.get(0).getFromDate() + "your return date:" + bookingDetails.get(0).getToDate()
					+ "source :" + bookingDetails.get(0).getPickUpCity() + "destination:"
					+ bookingDetails.get(0).getTravelCity()
					+ "this is a confirmation mail for the car,all the car details will be sent 1 day prior to your journey"
					+ "text/html";

			// body of the email
			String bodyText = "email body";

			// from address or the email id of the person from whom mail has to
			// be
			// sent
			String userName = "girishkumarm710@gmail.com";

			// password
			String passWord = "9738769973";

			// to address or the receipent of the mail
			String receipent = bookingDetails.get(0).getCustomerEmail();
			
			System.out.println("bodyhtml : "+bodyHtml);
			System.out.println("userName : "+userName);
			System.out.println("passWord : "+passWord);
			System.out.println("receipent : "+receipent);
			System.out.println("bodyText : "+bodyText);

			Email.getInstance().sendEmail(bodyHtml, bodyText, userName, passWord, receipent, "Tab Cars COnfirmation");

		}
		catch( Exception e )
		{
			logger.error("Error while confirming the user" + e.getMessage());
		}
		return respMessage;
	}

}
