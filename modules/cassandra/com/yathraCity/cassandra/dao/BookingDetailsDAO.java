package com.yathraCity.cassandra.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.datastax.driver.core.ResultSetFuture;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.yathraCity.cassandra.session.CassandraQuery;
import com.yathraCity.cassandra.tables.BookingColumns;
import com.yathraCity.cassandra.tables.TableNames;
import com.yathraCity.core.BookedCarDetails;
import com.yathraCity.core.CancelBooking;
import com.yathraCity.core.ConfirmBookedCarDetails;
import com.yathraCity.services.config.ConfigKey;
import com.yathraCity.services.config.Configurator;

public class BookingDetailsDAO
{
	private static CassandraQuery cassQuery = null;
	private String keyspace;
	private static Logger logger = LoggerFactory.getLogger(BookingDetailsDAO.class);
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
			        .value(BookingColumns.CAR_LOCATION,input.getCarLocation())
			        .value(BookingColumns.BOOKING_CONFIRMED,false)
					.value(BookingColumns.CUSTOMER_EMAIL, input.getCustomerEmail());
			System.out.println(insert.toString());
			cassQuery.executeFuture(insert);
			return true;
		}
		catch(Exception e)
		{
			logger.error( "Error while checking the booking car"
					+ e.getMessage() );
			return false;
		}
	}
	
	public List<BookedCarDetails> getBookingDetailsForMailing(ConfirmBookedCarDetails bookingDetails)
	{
		List<BookedCarDetails> unConfirmedBookingDetails=null;
		try
		{
			Statement update=QueryBuilder.update(keyspace, TableNames.BOOKING_DETAILS)
					.with(QueryBuilder.set(BookingColumns.BOOKING_CONFIRMED, true))
					.where(QueryBuilder.eq(BookingColumns.FROM_DATE, bookingDetails.getFromDate()))
					.and(QueryBuilder.eq(BookingColumns.CAR_NUMBER, bookingDetails.getCarNumber()))
					.and(QueryBuilder.eq(BookingColumns.CUSTOMER_PHONE_NUMBER, bookingDetails.getCustomerNumber()));
			cassQuery.executeFuture(update);
			
			Statement getDetails=QueryBuilder.select().all().from(keyspace, TableNames.BOOKING_DETAILS).allowFiltering()
					.where(QueryBuilder.eq(BookingColumns.FROM_DATE, bookingDetails.getFromDate()))
					.and(QueryBuilder.eq(BookingColumns.CAR_NUMBER, bookingDetails.getCarNumber()))
					.and(QueryBuilder.eq(BookingColumns.CUSTOMER_PHONE_NUMBER, bookingDetails.getCustomerNumber()))
					.and(QueryBuilder.eq(BookingColumns.BOOKING_CONFIRMED,true));
			ResultSetFuture result=cassQuery.executeFuture(getDetails);
			unConfirmedBookingDetails=processBookingDetails(result);
			
		}
		catch(Exception e)
		{
			logger.error( "Error while booking details for mailing"
					+ e.getMessage() );
		}
		return unConfirmedBookingDetails;
	}
	
	public List<BookedCarDetails> getAllBookingDetails(ConfirmBookedCarDetails bookingDetails)
	{
		List<BookedCarDetails> unConfirmedBookingDetails=null;
		try
		{
			Statement getDetails=QueryBuilder.select().all().from(keyspace, TableNames.BOOKING_DETAILS);
			ResultSetFuture result=cassQuery.executeFuture(getDetails);
			unConfirmedBookingDetails=processBookingDetails(result);
			
		}
		catch(Exception e)
		{
			logger.error( "Error while getting all the booking details"
					+ e.getMessage() );
		}
		return unConfirmedBookingDetails;
	}
	
	public String getAllBookingDetailsOfTheParticularCar(CancelBooking bookingDetails)
	{
		String carNumb=null;
		try
		{
			Statement getDetails=QueryBuilder.select().all().from(keyspace, TableNames.BOOKING_DETAILS)
					.where(QueryBuilder.eq(BookingColumns.FROM_DATE, bookingDetails.getFromDate()))
					.and(QueryBuilder.eq(BookingColumns.CUSTOMER_PHONE_NUMBER, bookingDetails.getCustomerNumber()));
			ResultSetFuture result=cassQuery.executeFuture(getDetails);
			Row r=(Row) result.getUninterruptibly();
			carNumb=r.getString(BookingColumns.CAR_NUMBER);
			
		}
		catch(Exception e)
		{
			logger.error( "Error while getting all the booking details"
					+ e.getMessage() );
		}
		return carNumb;
	}
	
	public List<BookedCarDetails> getAllBookingDetailsNotConfirmed(ConfirmBookedCarDetails bookingDetails)
	{
		List<BookedCarDetails> unConfirmedBookingDetails=null;
		try
		{
			Statement getDetails=QueryBuilder.select().all().from(keyspace, TableNames.BOOKING_DETAILS)
					.where(QueryBuilder.eq(BookingColumns.FROM_DATE, bookingDetails.getFromDate()))
					.and(QueryBuilder.eq(BookingColumns.CAR_NUMBER, bookingDetails.getCarNumber()))
					.and(QueryBuilder.eq(BookingColumns.CUSTOMER_PHONE_NUMBER, bookingDetails.getCustomerNumber()))
					.and(QueryBuilder.eq(BookingColumns.BOOKING_CONFIRMED, false));
			ResultSetFuture result=cassQuery.executeFuture(getDetails);
			unConfirmedBookingDetails=processBookingDetails(result);
			
		}
		catch(Exception e)
		{
			logger.error( "Error while not confirmed booking"
					+ e.getMessage() );
		}
		return unConfirmedBookingDetails;
	}
	
	private String uniqueBookingId()
	{
		return UUID.randomUUID().toString();
	}
	
	private List<BookedCarDetails> processBookingDetails(ResultSetFuture result)
	{
		List<BookedCarDetails> carDetails=new ArrayList<BookedCarDetails>();
		for(Row r: result.getUninterruptibly())
		{
			BookedCarDetails car=new BookedCarDetails();
			car.setAddress(r.getString(BookingColumns.ADDRESS));
			car.setCarAgency(r.getString(BookingColumns.CAR_AGENCY_NAME));
			car.setCarNumber(r.getString(BookingColumns.CAR_NUMBER));
			car.setCarAgencyPhoneNumber(r.getString(BookingColumns.CAR_AGENCY_NUMBER));
			car.setPickUpCity(r.getString(BookingColumns.PICKUP_CITY));
			car.setTravelCity(r.getString(BookingColumns.TRAVELLING_CITY));
			car.setCarType(r.getString(BookingColumns.CAR_TYPE));
			car.setDriverName(r.getString(BookingColumns.DRIVER_NAME));
			car.setDrivePhoneNumber(r.getString(BookingColumns.DRIVER_PHONE_NUMBER));
			car.setFromDate(r.getString(BookingColumns.FROM_DATE));
			car.setToDate(r.getString(BookingColumns.TO_DATE));
			car.setCustomerEmail(r.getString(BookingColumns.CUSTOMER_EMAIL));
			carDetails.add(car);
		}
		return carDetails;
	}
}