package com.yathraCity.services.config;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.apache.log4j.Logger;

public class Email
{
	Logger logger = Logger.getLogger( Email.class );
	private static Email instance = null;

	private Email ()
	{
	}

	public static Email getInstance()
	{
		if( instance == null )
		{
			instance = new Email();
		}
		return instance;
	}

	public void sendEmail( String emailTemplate, String bodyText, String userName, String passWord, String recipient,
			String subject )
	{
		final String username = userName;
		final String password = passWord;

		try
		{
			Properties props = System.getProperties();
			String host = "smtp.gmail.com";

			props.put( "mail.smtp.starttls.enable", "true" );
			props.put( "mail.smtp.ssl.trust", host );
			props.put( "mail.smtp.user", username );
			props.put( "mail.smtp.password", password );
			props.put( "mail.smtp.port", "587" );
			props.put( "mail.smtp.auth", "true" );

			// get the session of the user
			Session session = Session.getDefaultInstance( props );

			SendEmailInput sendEmailInput = new SendEmailInput();

			sendEmailInput.setFromAddress( username );
			List<StringClass> to = new ArrayList<StringClass>();
			for( String toAddress : recipient.split( "," ) )
			{
				to.add( new StringClass( toAddress.trim() ) );
			}
			sendEmailInput.settoAddresses( to );

			sendEmailInput.setSubject( subject );

			String bodyHTML = emailTemplate;

			sendEmailInput.setBodyText( bodyText );
			sendEmailInput.setBodyHTML( bodyHTML );
			Message message = formMessageContent( sendEmailInput, session, username );

			// send email notification for the user
			logger.info( "Sending email notification : " + recipient );
			Transport transport = session.getTransport( "smtp" );
			transport.connect( host, userName, passWord );
			transport.sendMessage( message, message.getAllRecipients() );
			transport.close();
			System.out.println( "Mail sent" );
		}
		catch( MessagingException e )
		{
			logger.error( "Error: " + e.getMessage() );
			throw new RuntimeException( e );
		}
	}

	public MimeMessage formMessageContent( SendEmailInput sendEmailInput, Session session, String userAccountId )
	{
		MimeMessage msg = new MimeMessage( session );
		try
		{
			String charset_UTF8 = "UTF-8", header = "";
			header = "text/HTML; charset=" + charset_UTF8;

			if( sendEmailInput.getBodyText() == null )
			{
				sendEmailInput.setBodyText( "" );
			}
			if( sendEmailInput.getSubject() == null )
			{
				sendEmailInput.setSubject( "" );
			}
			msg.addHeader( "format", "flowed" );
			msg.addHeader( "Content-Transfer-Encoding", "8bit" );
			msg.setFrom( new InternetAddress( userAccountId, sendEmailInput.getFullName() ) );
			msg.setReplyTo( InternetAddress.parse( userAccountId, false ) );
			msg.setSubject( sendEmailInput.getSubject(), charset_UTF8 );
			msg.setSentDate( new Date() );
			String toAddresses = "", ccAddresses = "", bccAddresses = "";

			if( sendEmailInput.getToAddresses() != null )
				if( !sendEmailInput.getToAddresses().isEmpty() )
					for( String toAdd : Utility.changeStringClassToString( sendEmailInput.getToAddresses() ) )
					{
						if( !toAdd.trim().isEmpty() )
							if( isEmail( toAdd.trim() ) )
							{
								toAddresses = toAddresses + "," + toAdd;
							}
							else
								throw new Exception( "wrong toAddress Pattern ---> " + toAdd );
					}
			toAddresses = toAddresses.replaceFirst( ",", "" );

			msg.setRecipients( Message.RecipientType.TO, InternetAddress.parse( toAddresses, false ) );
			// msg.setReplyTo( InternetAddress.parse( replyTo, false ) );

			if( !ccAddresses.trim().isEmpty() )
				msg.setRecipients( Message.RecipientType.CC, InternetAddress.parse( ccAddresses, false ) );
			if( !bccAddresses.trim().isEmpty() )
				msg.setRecipients( Message.RecipientType.BCC, InternetAddress.parse( bccAddresses, false ) );
			Multipart multipart = new MimeMultipart();

			if( sendEmailInput.getBodyText() != null && !sendEmailInput.getBodyText().isEmpty() )
			{
				// Create the message body part
				BodyPart messageBodyPart = new MimeBodyPart();
				messageBodyPart.setContent( sendEmailInput.getBodyText(), header );
				multipart.addBodyPart( messageBodyPart );
			}
			if( sendEmailInput.getBodyHTML() != null && !sendEmailInput.getBodyHTML().isEmpty() )
			{
				BodyPart bodyHTMLPart = new MimeBodyPart();
				bodyHTMLPart.setContent( sendEmailInput.getBodyHTML(), "text/HTML; charset=" + charset_UTF8 );
				multipart.addBodyPart( bodyHTMLPart );
			}
			msg.setContent( multipart );
		}
		catch( Exception e )
		{
			logger.error( "Error while forming the notification email object: " + e.getMessage() );
			e.printStackTrace();
		}
		return msg;
	}

	public boolean isEmail( String email )
	{
		String emailPattern = "^([A-Za-z0-9_\\-\\.])+\\@([A-Za-z0-9_\\-\\.])+\\.([A-Za-z]{2,4})";
		return email.matches( emailPattern );
	}

}