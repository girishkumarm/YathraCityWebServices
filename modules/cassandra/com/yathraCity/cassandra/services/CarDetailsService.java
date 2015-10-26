package com.yathraCity.cassandra.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.yathraCity.cassandra.dao.CarDetailsDAO;
import com.yathraCity.cassandra.dao.UserDAO;
import com.yathraCity.core.RegisterCarDetails;
import com.yathraCity.core.RegisterUser;

public class CarDetailsService 
{
	private static Logger logger = LoggerFactory.getLogger( UserDAO.class );
	private static CarDetailsDAO carDetailsDAO = new CarDetailsDAO();

	public boolean registerCar( RegisterCarDetails car )
	{
		boolean result = false;
		try
		{
			// checking for all the mandatory fields required in the service
			if( car.getCarModel() != null || !car.getCarModel().trim().isEmpty() || car.getCarName() != null
					|| !car.getCarName().trim().isEmpty() || car.getCarNumber() != null
					|| !car.getCarNumber().trim().isEmpty() || car.getCityName() != null
					|| !car.getCityName().trim().isEmpty() || car.getDriverLicence() != null
					|| !car.getDriverLicence().trim().isEmpty() || car.getDriverName() != null
					|| !car.getDriverName().trim().isEmpty() || car.getMaximumPeople() != null
					|| car.getMaximumPeople() != 0 || car.getMinimumDistance() != null || car.getMinimumDistance() != 0
					|| car.getPhoneNumber() != 0 || car.getPricePerKm() != null || car.getPricePerKm() != 0 )
			{
				result=carDetailsDAO.addCar(car);
			}
			else
			{
				throw new Exception( "Mandatory fields are missing to register the user:" );
			}
			// call dao to save user
		}
		catch( Exception e )
		{
			logger.error( "Error while inserting the user data into the cassandra db while registering user--->"
					+ e.getMessage() );
		}
		return result;

	}

}
