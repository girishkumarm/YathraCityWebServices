package com.yathraCity.services.impl;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.razorthink.runtime.ExecException;
import com.razorthink.runtime.ServiceExecutionContext;
import com.yathraCity.cassandra.dao.BookingDetailsDAO;
import com.yathraCity.cassandra.dao.CarAvailabilityDAO;
import com.yathraCity.cassandra.dao.DriverDetailsDAO;
import com.yathraCity.cassandra.pojo.CarAvailabilityDetails;
import com.yathraCity.cassandra.pojo.DriverDetailsPojo;
import com.yathraCity.core.BookedCarDetails;
import com.yathraCity.core.ConfirmBookedCarDetails;
import com.yathraCity.core.ResponseMessage;
import com.yathraCity.services.CarBookingServiceInterface;
import defaultpkg.ErrorCodes;

public class CarBookingService implements CarBookingServiceInterface {
	
	List<BookedCarDetails> bookingDetails = null;
	private BookingDetailsDAO bookTheCar = new BookingDetailsDAO();
	private CarAvailabilityDAO carAvailabilityDAO = new CarAvailabilityDAO();
	private static Logger logger = LoggerFactory.getLogger(CarBookingService.class);
	private DriverDetailsDAO driverDetails = new DriverDetailsDAO();
	// private DriverDetailsDAO driverDetails = new DriverDetailsDAO();

	@Override
	public ResponseMessage bookingCar( ServiceExecutionContext ctx, BookedCarDetails input ) throws ExecException
	{
		ResponseMessage response = new ResponseMessage();
		ResponseMessage mailMessage = new ResponseMessage();
		response.setMessage("booking unsuccessful");
		response.setStatus("500");
		boolean result = false;
		try
		{
			if( input == null || input.getFromDate() == null || input.getFromDate().trim().isEmpty()
					|| input.getToDate() == null || input.getToDate().trim().isEmpty() || input.getTravelCity() == null
					|| input.getTravelCity().trim().isEmpty() || input.getCarLocation() == null
					|| input.getCarLocation().trim().isEmpty() || input.getCarNumber() == null
					|| input.getCarNumber().trim().isEmpty() || input.getCarType() == null
					|| input.getCarType().trim().isEmpty() || input.getCustomerName() == null
					|| input.getCustomerName().trim().isEmpty() || input.getCustomerNumber() == null
					|| input.getCustomerNumber().trim().isEmpty() )
			{
				throw new Exception("Mandatory fields are missing to get for booking");
			}
			DriverDetailsPojo driverDet = driverDetails.getDriverDetailsForBooking(input);

			if( driverDet != null )
			{
				if( driverDet.getDriverPhoneNumber() != null )
				{
					input.setDrivePhoneNumber(driverDet.getDriverPhoneNumber());
				}
				if( driverDet.getAgencyPhoneNumber() != null )
				{
					input.setCarAgencyPhoneNumber(driverDet.getAgencyPhoneNumber());
				}
				if( driverDet.getDriverName() != null )
				{
					input.setDriverName(driverDet.getDriverName());
				}
			}

			// book a car
			result = bookTheCar.bookCar(input);

			// add the car booked to the availability list and block it on that
			// day
			CarAvailabilityDetails addAvailability = new CarAvailabilityDetails();
			addAvailability.setCarNumber(input.getCarNumber());
			addAvailability.setFromDate(input.getFromDate());
			addAvailability.setToDate(input.getToDate());
			carAvailabilityDAO.addAvailability(addAvailability);

			if( result == true )
			{
				response.setMessage("successfully booked");
				response.setStatus("200");
				
				ConfirmBookedCarDetails confirmBookedCarDetails=new ConfirmBookedCarDetails();
				confirmBookedCarDetails.setCarNumber(input.getCarNumber());
				confirmBookedCarDetails.setCustomerNumber(input.getCustomerNumber());
				confirmBookedCarDetails.setFromDate(input.getFromDate());
				SendEmail sendEmail=new SendEmail();
				sendEmail.mailDetailsToCustomerAndAdmin(confirmBookedCarDetails);
			}
		}
		catch( ExecException m )
		{
			logger.error("Error while booking thhe car" + m.getMessage());
			throw m;
		}
		catch( Exception e )
		{
			logger.error("Error while booking thhe car" + e.getMessage());
			throw new ExecException(ErrorCodes.APPLICATION_ERROR, e, e.getMessage());
		}
		return response;
	}
}
