package com.yathraCity.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.razorthink.runtime.ExecException;
import com.razorthink.runtime.ServiceExecutionContext;
import com.yathraCity.cassandra.dao.CarServiceDAO;
import com.yathraCity.cassandra.dao.DriverDetailsDAO;
import com.yathraCity.cassandra.pojo.CarDetails;
import com.yathraCity.cassandra.pojo.DriverDetailsPojo;
import com.yathraCity.core.ConfirmDriverAvailability;
import com.yathraCity.core.DriverDetails;
import com.yathraCity.core.FetchCarDetails;
import com.yathraCity.core.ResponseMessage;
import com.yathraCity.services.DriverInterface;
import defaultpkg.ErrorCodes;

public class Driver implements DriverInterface {

	DriverDetailsDAO driverDetailsDao = new DriverDetailsDAO();
	CarServiceDAO carService = new CarServiceDAO();
	private CarServiceDAO carsDao = new CarServiceDAO();
	private static Logger logger = LoggerFactory.getLogger(Driver.class);
	ResponseMessage message = new ResponseMessage();

	@Override
	public ResponseMessage registerdriver( ServiceExecutionContext ctx, DriverDetails driverDetails )
			throws ExecException
	{

		message.setStatus("500");
		message.setMessage("Register Driver Failed");
		try
		{
			// check for the mandatory fields for the driver
			if( driverDetails == null || driverDetails.getDriverLicence() == null || driverDetails.getCarName() == null
					|| driverDetails.getCarName().trim().isEmpty() || driverDetails.getDriverLicence().trim().isEmpty()
					|| driverDetails.getLocation() == null || driverDetails.getLocation().trim().isEmpty()
					|| driverDetails.getCarType() == null || driverDetails.getCarType().trim().isEmpty()
					|| driverDetails.getCarNumber() == null || driverDetails.getCarNumber().trim().isEmpty()
					|| driverDetails.getDriverName() == null || driverDetails.getDriverName().trim().isEmpty()
					|| driverDetails.getDriverPhoneNumber() == null
					|| driverDetails.getDriverPhoneNumber().trim().isEmpty() )
			{
				throw new ExecException(ErrorCodes.MISSING_FIELD, null, "Mandatory Fields are missing");
			}

			// Check whether a car is present in the db for the driver
			FetchCarDetails details = new FetchCarDetails();
			details.setRegisteredAt(driverDetails.getLocation());
			details.setCarType(driverDetails.getCarType());
			details.setCarNumber(driverDetails.getCarNumber());
			details.setCarName(driverDetails.getCarName());
			CarDetails carDetails = carsDao.fetchCarDetails(details);

			if( carDetails == null )
			{
				message.setMessage("Register Driver Failed: CAR DOES NOT EXIST");
				throw new ExecException(ErrorCodes.BAD_REQUEST, null, "CAR DOES NOT EXIST");
			}
			else
			{
				// check if the car is already registered
				if( !carDetails.isRegistered() )
				{
					// register driver
					driverDetailsDao.addDriverDetails(driverDetails);

					// update the car with driver license number
					carService.updateDriverLicenceAndRegistration(driverDetails.getLocation(),
							driverDetails.getCarType(), driverDetails.getCarNumber(), driverDetails.getDriverLicence(),
							driverDetails.getCarName());

					message.setStatus("200");
					message.setMessage("successfully saved");
				}
				else
				{
					message.setStatus("500");
					message.setMessage("CAR IS ALREADY REGISTERED FOR OTHER DRIVER");
					throw new ExecException(ErrorCodes.MISSING_FIELD, null,
							"CAR IS ALREADY REGISTERED FOR OTHER DRIVER");
				}
			}
		}
		catch( ExecException m )
		{
			logger.error("Error while adding the Driver" + m.getMessage());
			throw m;
		}
		catch( Exception e )
		{
			logger.error("Error while adding the Driver" + e.getMessage());
			throw new ExecException(ErrorCodes.APPLICATION_ERROR, e, e.getMessage());
		}
		return message;
	}

	@Override
	public ResponseMessage updateDriverAvailability( ServiceExecutionContext ctx,
			ConfirmDriverAvailability driverDetails ) throws ExecException
	{
		message.setMessage("Update Failed");
		message.setStatus("500");
		try
		{
			// check for the mandatory fields for the driver
			if( driverDetails == null || driverDetails.getDriverLicence() == null
					|| driverDetails.getDriverLicence().trim().isEmpty() || driverDetails.getLocation() == null
					|| driverDetails.getLocation().trim().isEmpty() || driverDetails.getCarNumber() == null
					|| driverDetails.getCarNumber().trim().isEmpty() )
			{
				throw new ExecException(ErrorCodes.MISSING_FIELD, null, "Mandatory Fields are missing");
			}

			// fetch the driver details to check for the true driver
			DriverDetailsPojo driverResults = driverDetailsDao.fetchDrivers(driverDetails);
			if( driverResults != null )
			{
				FetchCarDetails details = new FetchCarDetails();
				details.setRegisteredAt(driverDetails.getLocation());
				details.setCarType(driverDetails.getCarType());
				details.setCarNumber(driverDetails.getCarNumber());
				details.setCarName(driverDetails.getCarName());

				// check if the car which is to be updated is register for the
				// driver
				CarDetails car = carsDao.fetchCarDetails(details);
				if( car != null && car.getLicenseNumber().equals(driverDetails.getDriverLicence()) )
				{
					// update driver availability
					driverDetailsDao.updateDriverAvailability(driverDetails);

					// update car availability
					carsDao.updateCarAvailability(details, driverDetails.isAvailability());

					message.setMessage("Update Successfull");
					message.setStatus("200");
				}
				else
				{
					message.setMessage("Update Failed Please check car details and try again");
					message.setStatus("500");
				}
			}
			// if driver does not exist throw an exception telling driver
			// missing to update his availability
			else
			{
				message.setMessage("Update Failed : Driver Does not exist to update his availability");
				throw new ExecException(ErrorCodes.BAD_REQUEST, null,
						"Driver Does not exist to update his availability");
			}
		}
		catch( Exception e )
		{
			logger.error("Error while adding the updating the driver avaliablity" + e.getMessage());
		}
		return message;
	}
}
