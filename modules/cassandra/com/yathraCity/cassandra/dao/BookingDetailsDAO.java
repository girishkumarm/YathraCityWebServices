package com.yathraCity.cassandra.dao;

import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.yathraCity.cassandra.session.CassandraQuery;
import com.yathraCity.cassandra.tables.BookingColumns;
import com.yathraCity.cassandra.tables.TableNames;
import com.yathraCity.core.BookedCarDetails;
import com.yathraCity.services.config.ConfigKey;
import com.yathraCity.services.config.Configurator;

public class BookingDetailsDAO
{
	private static Logger logger = LoggerFactory.getLogger(UserDAO.class);
	private static CassandraQuery cassQuery = null;
	private String keyspace;

	// initlizing the session and keyspace
	public BookingDetailsDAO()
	{
		try
		{
			cassQuery = new CassandraQuery();
			keyspace = Configurator.getInstance().getProperty(ConfigKey.CASSANDRA_Keyspace);
		}
		catch( IllegalAccessException e )
		{
			e.printStackTrace();
		}
	}
	
	public boolean bookCar(BookedCarDetails input)
	{
		
		try
		{
		    CarServiceDAO carService=new CarServiceDAO();
		    DriverDetailsDAO driverDetals=new DriverDetailsDAO();
		    driverDetals.driverDetailsForBooking(input);
		    carService.getCarDetails(input);
			Statement insert = QueryBuilder.insertInto(keyspace,TableNames.BOOKING_DETAILS)
					.value(BookingColumns.BOOKING_ID,uniqueBookingId())
					.value(BookingColumns.CAR_AGENCY_NAME,input.getCarAgency())
					.value(BookingColumns.CAR_AGENCY_NUMBER,input.getCarAgencyPhoneNumber())
					.value(BookingColumns.CAR_NUMBER,input.getCarNumber())
					.value(BookingColumns.C0UPON,input.getCoupon())
					.value(BookingColumns.CUSTOMER_NAME,input.getCustomerName())
				    .value(BookingColumns.CUSTOMER_PHONE_NUMBER, input.getCustomerNumber())
				    .value(BookingColumns.DRIVER_NAME, input.getDriverName())
				    .value(BookingColumns.DRIVER_PHONE_NUMBER,input.getDrivePhoneNumber())
				    .value(BookingColumns.FROM_DATE, input.getFromDate())
				    .value(BookingColumns.TO_DATE, input.getToDate())
				    .value(BookingColumns.TRAVELLING_CITY, input.getTravelCity())
				    .value(BookingColumns.PICKUP_CITY, input.getPickUpCity())
				    .value(BookingColumns.ADDRESS, input.getAddress())
			        .value(BookingColumns.CAR_TYPE, input.getCarType())
			        .value(BookingColumns.CAR_LOCATION,input.getCarLocation());
			System.out.println(insert.toString());
			cassQuery.executeFuture(insert);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	private String uniqueBookingId()
	{
		return UUID.randomUUID().toString();
	}
		
}
