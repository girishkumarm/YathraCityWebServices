package com.yathraCity.services.core;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * sending the confirmation mail
 * 
 * @author ashwing
 *
 */
public class SendMail {

	public static void main( String[] args )
	{
		// sending the confirmation mail after registration
		try
		{
			String recipient = "+919738769973";
			String message = " Greetings from Mr. Gupta! Have a nice day!";
			String username = "admin";
			String password = "abc123";
			String originator = "+919972254541";
			String requestUrl = "http://127.0.0.1:9501/api?action=sendmessage&" + "username="
					+ URLEncoder.encode(username, "UTF-8") + "&password=" + URLEncoder.encode(password, "UTF-8")
					+ "&recipient=" + URLEncoder.encode(recipient, "UTF-8") + "&messagetype=SMS:TEXT" + "&messagedata="
					+ URLEncoder.encode(message, "UTF-8") + "&originator=" + URLEncoder.encode(originator, "UTF-8")
					+ "&serviceprovider=GSMModem1" + "&responseformat=html";
			URL url = new URL(requestUrl);
			HttpURLConnection uc = (HttpURLConnection) url.openConnection();
			uc.disconnect();
		}
		catch( Exception ex )
		{
			ex.printStackTrace();
		}
	}
}
