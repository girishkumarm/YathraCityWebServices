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
import com.razorthink.runtime.ExecException;
import com.yathraCity.cassandra.dao.BookingDetailsDAO;
import com.yathraCity.core.BookedCarDetails;
import com.yathraCity.core.ConfirmBookedCarDetails;
import com.yathraCity.core.ResponseMessage;

public class SendEmail {

	BookingDetailsDAO details = new BookingDetailsDAO();
	List<BookedCarDetails> bookingDetails = null;
	ResponseMessage mailMessage = new ResponseMessage();
	private static Logger logger = LoggerFactory.getLogger(SendMailToCustomers.class);

	public ResponseMessage mailDetailsToCustomerAndAdmin( ConfirmBookedCarDetails input ) throws ExecException
	{

		try
		{

			bookingDetails = details.getBookingDetailsForMailing(input);
			if( bookingDetails == null || bookingDetails.size() <= 0 )
			{
				mailMessage.setMessage("NOT FOUND BOOKING DETAILS");
				mailMessage.setStatus("400");
			}
			mailMessage.setMessage("sucess");
			mailMessage.setStatus("200");

			// Recipient's email ID needs to be mentioned.
			String to = bookingDetails.get(0).getCustomerEmail();// change
																	// accordingly

			// Sender's email ID needs to be mentioned
			String from = "tabcarsconfirmation@gmail.com";// change accordingly
			final String username = "tabcarsconfirmation";// change accordingly
			final String password = "tabcars890";// change accordingly

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
				message.addRecipients(Message.RecipientType.TO, InternetAddress.parse("girishkumarm710@gmail.com"));
				message.addRecipients(Message.RecipientType.CC, InternetAddress.parse("ashwin.gadam@gmail.com"));
				//message.addRecipients(Message.RecipientType.BCC, InternetAddress.parse("ashwingadam@gmail.com"));
				//message.addRecipients(Message.RecipientType.BCC, InternetAddress.parse("girishkumarm710@gmail.com"));
				// Set Subject: header field
				message.setSubject("Booking Arrived");

				// Now set the actual message
				message.setContent("<h1>TAB</h1><br/><h2>Booking Details<h2><br/><br/><br/>" + "<table>" + "<tr>"
						+ "<td>Customer Name</td>" + "<td>" + bookingDetails.get(0).getCustomerName() + "</td>"
						+ "</tr>" + "<tr>" + "<td>Customer Number</td>" + "<td>"
						+ bookingDetails.get(0).getCustomerNumber() + "</td>" + "</tr>" + "<tr>"
						+ "<td>Pickup City</td>" + "<td>" + bookingDetails.get(0).getPickUpCity() + "</td>" + "</tr>"
						+ "<tr>" + "<td>Travelling To</td>" + "<td>" + bookingDetails.get(0).getTravelCity() + "</td>"
						+ "</tr>" + "<tr>" + "<td>Car Type</td>" + "<td>" + bookingDetails.get(0).getCarType() + "</td>"
						+ "</tr>" + "<tr>" + "<td>Driver Name</td>" + "<td>" + bookingDetails.get(0).getDriverName()
						+ "</td>" + "</tr>" + "<tr>" + "<td>Driver Phone Number</td>" + "<td>"
						+ bookingDetails.get(0).getDrivePhoneNumber() + "</td>" + "</tr>" + " </table>", "text/html");

				// Send message
				Transport.send(message);
			}
			catch( MessagingException e )
			{
				logger.error("Error while sending mail" + e.getMessage());
			}

		}
		catch( Exception e )
		{
			logger.error("Error while confirming the user" + e.getMessage());
		}
		return mailMessage;
	}

}