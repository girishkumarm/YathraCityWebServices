package com.yathraCity.services.impl;


import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendSMS
{
	public SendSMS ()
	{
	}

	// create an account on ipipi.com with the given username and password

	public void msgsend()
	{
		String username = "surajSMS";
		// Your Credentials
		String password = "suraj123";
		String smtphost = "ipipi.com";
		// Ip/Name of Server
		String compression = "None";
		// I dont want any compression
		String from = "surajSMS@ipipi.com";
		// ur userid@ipipi.com
		// This mobile number need not be registered with ipipi.com
		String to = "919861098610@sms.ipipi.com"; // mobile number where u want
													// to send sms
		String body = "Hi This Msg is sent through Java Code";

		Transport tr = null;
		try
		{
			Properties props = System.getProperties();
			props.put( "mail.smtp.auth", "true" );
			// Get a Session object
			Session mailSession = Session.getDefaultInstance( props, null );
			// construct the message
			Message msg = new MimeMessage( mailSession );
			// Set message attributes
			msg.setFrom( new InternetAddress( from ) );
			InternetAddress[] address = { new InternetAddress( to ) };
			msg.setRecipients( Message.RecipientType.TO, address );
			msg.setSubject( compression );
			msg.setText( body );
			msg.setSentDate( new Date() );
			tr = mailSession.getTransport( "smtp" );
			// try to connect
			tr.connect( smtphost, username, password );
			msg.saveChanges();
			// send msg to all recipients
			tr.sendMessage( msg, msg.getAllRecipients() );
			tr.close();
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
	}

	public static void main( String[] argv )
	{
		SendSMS sms = new SendSMS();
		sms.msgsend();
		System.out.println( "Successfull" );
	}
}