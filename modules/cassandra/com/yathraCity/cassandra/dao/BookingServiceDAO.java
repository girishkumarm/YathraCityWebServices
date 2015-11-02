package com.yathraCity.cassandra.dao;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datastax.driver.core.ConsistencyLevel;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.yathraCity.cassandra.session.CassandraQuery;
import com.yathraCity.cassandra.tables.BookingColumns;
import com.yathraCity.cassandra.tables.TableNames;
import com.yathraCity.core.RegisterBookingInput;
import com.yathraCity.services.config.ConfigKey;
import com.yathraCity.services.config.Configurator;
/**
 * Booking a car
 * @author ashwing
 * param details of the cardetails and the customer details
 */
public class BookingServiceDAO
{
	private static Logger logger = LoggerFactory.getLogger( UserDAO.class );
	private static CassandraQuery cassQuery = null;
	private String keyspace;

	public BookingServiceDAO ()
	{
		try
		{
			cassQuery = new CassandraQuery();
			keyspace = Configurator.getInstance().getProperty( ConfigKey.CASSANDRA_Keyspace );
		}
		catch( IllegalAccessException e )
		{
			e.printStackTrace();
		}
	}

	//storing the details of the booking
	public boolean addBookingDetails( RegisterBookingInput bookingInput )
	{
		boolean result = false;
		try
		{
			//Query to store the details of the booking
			Statement insert = QueryBuilder.insertInto( keyspace, TableNames.CAR_BOOKING )
					.value( BookingColumns.BOOKED_CAR_NAME, bookingInput.getBookedCarName() )
					.value( BookingColumns.BOOKED_CAR_NUMBER, bookingInput.getBookedCarNumber() )
					.value( BookingColumns.BOOKED_CAR_OWNER, bookingInput.getBookedCarOwner() )
					.value( BookingColumns.BOOKING_CONFIRMATION, "false" )
					.value( BookingColumns.CAR_BOOKED_AT, bookingInput.getCarBookedAt() )
					.value( BookingColumns.DISCOUNT_OFFERED, bookingInput.getDiscountOffered() )
					.value( BookingColumns.EMAIL_ID, bookingInput.getEmailId() )
					.value( BookingColumns.NUMBER_OF_DAYS, bookingInput.getNumberOfDays() )
					.value( BookingColumns.NUMBER_OF_PEOPLES, bookingInput.getNumberOfPeoples() )
					.value( BookingColumns.PHONE_NUMBER, bookingInput.getPhoneNumber() )
					.value( BookingColumns.PICK_UP_POINT, bookingInput.getPickUpPoint() )
					.value( BookingColumns.USER_NAME, bookingInput.getUserName() )
					.setConsistencyLevel( ConsistencyLevel.QUORUM ).enableTracing();
			System.out.println( insert.toString() );
			cassQuery.executeFuture( insert );
			result = true;
		}
		catch( Exception e )
		{
			logger.error( "Error while inserting the user data into the cassandra db while booking car--->"
					+ e.getMessage() );
		}
		return result;

	}

}
