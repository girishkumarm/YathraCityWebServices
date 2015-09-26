package com.yathraCity.cassandra.services;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yathraCity.cassandra.dao.BookingServiceDAO;
import com.yathraCity.cassandra.dao.UserDAO;
import com.yathraCity.core.RegisterBookingInput;

// this is the api which act as an interface between the services and the dao
// where actually it contacts database
public class BookingService
{

	private static Logger logger = LoggerFactory.getLogger( UserDAO.class );
	private static BookingServiceDAO bookingServiceDAO = new BookingServiceDAO();

	public boolean bookCarForAnUser( RegisterBookingInput bookingInput )
	{
		boolean result = false;
		try
		{
			// checking for all the mandatory fields required in the service
			if( bookingInput == null || bookingInput.getPhoneNumber() == 0 || bookingInput.getBookedCarName() == null
					|| bookingInput.getBookedCarName().trim().isEmpty() || bookingInput.getBookedCarNumber() == null
					|| bookingInput.getBookedCarNumber().trim().isEmpty() || bookingInput.getPickUpPoint() == null
					|| bookingInput.getPickUpPoint().toString().isEmpty() )
			{
				throw new Exception( "Mandatory fields are missing to book the car" );
			}

			// call dao to save booking
			result = bookingServiceDAO.addBookingDetails( bookingInput );
		}
		catch( Exception e )
		{
			logger.error( "Error while inserting the user data into the cassandra db while registering user--->"
					+ e.getMessage() );
		}
		return result;

	}

}
