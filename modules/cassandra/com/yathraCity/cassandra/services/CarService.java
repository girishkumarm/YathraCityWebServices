package com.yathraCity.cassandra.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.yathraCity.cassandra.dao.CarServiceDAO;
import com.yathraCity.cassandra.dao.UserDAO;
import com.yathraCity.core.RegisterCarInput;

// this is the api which act as an interface between the services and the dao
// where actually it contacts database
public class CarService {

	private static Logger logger = LoggerFactory.getLogger(UserDAO.class);
	private static CarServiceDAO carBookingServiceDAO = new CarServiceDAO();

	public boolean addCarDetails( RegisterCarInput carDetails )
	{
		boolean result = false;
		try
		{
			// checking for all the mandatory fields required in the service
			if( carDetails == null || carDetails.getCarCapacity() == 0 || carDetails.getCarName() == null
					|| carDetails.getCarName().trim().isEmpty() || carDetails.getCarNumber() == null
					|| carDetails.getCarNumber().trim().isEmpty() || carDetails.getCarRegisteredAt() == null
					|| carDetails.getCarRegisteredAt().trim().isEmpty() || carDetails.getMinimunDistancePerDay() == null
					|| carDetails.getMinimunDistancePerDay() == 0 || carDetails.getPricePerKilometer() == null
					|| carDetails.getPricePerKilometer() == 0 || carDetails.getCarType() == null
					|| carDetails.getCarType().trim().isEmpty() || carDetails.getCarModel() == null
					|| carDetails.getCarModel().trim().isEmpty() )
			{
				throw new Exception("Mandatory fields are missing to register the car");
			}

			// call dao to save booking
			result = carBookingServiceDAO.addCarDetails(carDetails);
		}
		catch( Exception e )
		{
			logger.error("Error while inserting the user data into the cassandra db while registering user--->"
					+ e.getMessage());
		}
		return result;

	}
}
