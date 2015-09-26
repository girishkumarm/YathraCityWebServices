package com.yathraCity.cassandra.services;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yathraCity.cassandra.dao.CarServiceDAO;
import com.yathraCity.cassandra.dao.UserDAO;
import com.yathraCity.cassandra.pojo.CarDetails;
import com.yathraCity.core.CheckAvailabilityInput;
import com.yathraCity.core.RegisterCarInput;

// this is the api which act as an interface between the services and the dao
// where actually it contacts database
public class CarService
{

	private static Logger logger = LoggerFactory.getLogger( UserDAO.class );
	private static CarServiceDAO carBookingServiceDAO = new CarServiceDAO();

	public boolean addCarDetails( RegisterCarInput carDetails )
	{
		boolean result = false;
		try
		{
			// checking for all the mandatory fields required in the service
			if( carDetails == null || carDetails.getCarCapacity() == 0 || carDetails.getCarModel() == null
					|| carDetails.getCarModel().trim().isEmpty() || carDetails.getCarName() == null
					|| carDetails.getCarName().trim().isEmpty() || carDetails.getCarNumber() == null
					|| carDetails.getCarNumber().trim().isEmpty() || carDetails.getCarOwner() == null
					|| carDetails.getCarOwner().trim().isEmpty() || carDetails.getCarRegisteredAt() == null
					|| carDetails.getCarRegisteredAt().trim().isEmpty() || carDetails.getContactNumber() == null
					|| carDetails.getContactNumber().trim().isEmpty() || carDetails.getMinimunDistancePerDay() == null
					|| carDetails.getMinimunDistancePerDay().trim().isEmpty()
					|| carDetails.getOwnerLicenseNumber() == null
					|| carDetails.getOwnerLicenseNumber().trim().isEmpty() || carDetails.getPricePerKilometer() == null
					|| carDetails.getPricePerKilometer() == 0 )
			{
				throw new Exception( "Mandatory fields are missing to register the car" );
			}

			// call dao to save booking
			result = carBookingServiceDAO.addCarDetails( carDetails );
		}
		catch( Exception e )
		{
			logger.error( "Error while inserting the user data into the cassandra db while registering user--->"
					+ e.getMessage() );
		}
		return result;

	}

	public List<CarDetails> getAvailableCars( String pickUpPoint, String capacity )
	{
		List<CarDetails> result = null;
		try
		{
			// checking for all the mandatory fields required in the service
			if( pickUpPoint == null || pickUpPoint.trim().isEmpty() || capacity == null || capacity.trim().isEmpty() )
			{
				throw new Exception( "Mandatory fields are missing to book the car" );
			}

			// call dao to get all available cars
			result = carBookingServiceDAO.fetchAvailableCarsOfCity( pickUpPoint, capacity );
		}
		catch( Exception e )
		{
			logger.error( "Error while inserting the user data into the cassandra db while registering user--->"
					+ e.getMessage() );
		}
		return result;

	}

	public boolean checkCarAavailability( CheckAvailabilityInput input )
	{
		boolean result = false;
		try
		{
			if( input == null || input.getCarCapacity() == 0 || input.getCarNumber() == null
					|| input.getCarNumber().trim().isEmpty() || input.getCarRegisteredAt() == null
					|| input.getCarRegisteredAt().trim().isEmpty() )
			{
				throw new Exception( "Mandatory fields are missing to book the car" );
			}
			// call dao to get info of availability of car
			result = carBookingServiceDAO.checkCarAavailability( input );
		}
		catch( Exception e )
		{
			logger.error( "Error while checking CarAavailability from the cassandra db -->" + e.getMessage() );
		}
		return result;
	}
}
