package com.yathraCity.services.impl;

import com.razorthink.runtime.ExecException;
import com.razorthink.runtime.ServiceExecutionContext;
import com.yathraCity.cassandra.dao.BookingDetailsDAO;
import com.yathraCity.cassandra.dao.CarAvailabilityDAO;
import com.yathraCity.cassandra.pojo.CarAvailabilityDetails;
import com.yathraCity.core.BookedCarDetails;
import com.yathraCity.core.ResponseMessage;
import com.yathraCity.services.CarBookingServiceInterface;

public class CarBookingService implements CarBookingServiceInterface {

	private BookingDetailsDAO bookTheCar = new BookingDetailsDAO();
	private CarAvailabilityDAO carAvailabilityDAO = new CarAvailabilityDAO();

	@Override
	public ResponseMessage bookingCar( ServiceExecutionContext ctx, BookedCarDetails input ) throws ExecException
	{
		ResponseMessage response = new ResponseMessage();
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
			result = bookTheCar.bookCar(input);

			CarAvailabilityDetails addAvailability = new CarAvailabilityDetails();
			addAvailability.setCarNumber(input.getCarNumber());
			addAvailability.setFromDate(input.getFromDate());
			addAvailability.setToDate(input.getToDate());
			carAvailabilityDAO.addAvailability(addAvailability);

			if( result == true )
			{
				response.setMessage("successfully booked");
				response.setStatus("200");

			}

		}
		catch( Exception e )
		{
			e.printStackTrace();
			return response;
		}
		return response;
	}
}
