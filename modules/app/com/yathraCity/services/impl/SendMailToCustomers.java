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
import com.razorthink.runtime.ExecException;
import com.razorthink.runtime.ServiceExecutionContext;
import com.yathraCity.cassandra.dao.BookingDetailsDAO;
import com.yathraCity.core.BookedCarDetails;
import com.yathraCity.core.ConfirmBookedCarDetails;
import com.yathraCity.core.ResponseMessage;
import com.yathraCity.services.SendMailToCustomersInterface;

public class SendMailToCustomers implements SendMailToCustomersInterface {

	BookingDetailsDAO details = new BookingDetailsDAO();
	List<BookedCarDetails> bookingDetails = null;

	@Override
	public ResponseMessage mailCustomer( ServiceExecutionContext ctx, ConfirmBookedCarDetails input )
			throws ExecException
	{
		ResponseMessage respMessage = new ResponseMessage();
		respMessage.setMessage("bad gateway");
		respMessage.setStatus("500");
		try
		{
			bookingDetails = details.getBookingDetailsForMailing(input);
			if( bookingDetails == null || bookingDetails.size() <= 0 )
			{
				respMessage.setMessage("NOT FOUND BOOKING DETAILS");
				respMessage.setStatus("400");
			}
			respMessage.setMessage("sucess");
			respMessage.setStatus("200");

			// Recipient's email ID needs to be mentioned.
			String to = bookingDetails.get(0).getCustomerEmail();// change
																	// accordingly

			// Sender's email ID needs to be mentioned
			String from = "care.tab.booking@gmail.com";// change accordingly
			final String username = "care.tab.booking";// change accordingly
			final String password = "takeabreak";// change accordingly

			// Assuming you are sending email through relay.jangosmtp.net
			String host = "smtp.gmail.com";

			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.port", "587");

			// Get the Session object.
			Session session = Session.getInstance(props, new javax.mail.Authenticator() {

				protected PasswordAuthentication getPasswordAuthentication()
				{
					return new PasswordAuthentication(username, password);
				}
			});

			try
			{
				// Create a default MimeMessage object.
				Message message = new MimeMessage(session);

				// Set From: header field of the header.
				message.setFrom(new InternetAddress(from));

				// Set To: header field of the header.
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

				// Set Subject: header field
				message.setSubject("Booking Confirmation");

				// Now set the actual message
				message.setContent("<h1>TAB</h1><h2>Thank you for booking<h2>"
						+"booking details:"
						+"your travel date:"+bookingDetails.get(0).getFromDate()
						+"your return date:"+bookingDetails.get(0).getToDate()
						+"source :"+bookingDetails.get(0).getPickUpCity()
						+"destination:"+bookingDetails.get(0).getTravelCity()
						+"this is a confirmation mail for the car,all the car details will be sent 1 day prior to your journey", 
						"text/html");

				// Send message
				Transport.send(message);

				System.out.println("Sent message successfully....");

			}
			catch( MessagingException e )
			{
				throw new RuntimeException(e);
			}

		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		return respMessage;
	}

}
