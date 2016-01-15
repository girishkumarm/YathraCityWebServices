package com.yathraCity.services.impl;

import com.razorthink.runtime.ExecException;
import com.razorthink.runtime.ServiceExecutionContext;
import com.yathraCity.cassandra.dao.CarServiceDAO;
import com.yathraCity.cassandra.dao.DriverDetailsDAO;
import com.yathraCity.cassandra.pojo.CarDetails;
import com.yathraCity.core.ConfirmDriverAvailability;
import com.yathraCity.core.DriverDetails;
import com.yathraCity.core.FetchCarDetails;
import com.yathraCity.core.ResponseMessage;
import com.yathraCity.services.DriverInterface;
import defaultpkg.ErrorCodes;

public class Driver implements DriverInterface {

	DriverDetailsDAO driverDetailsDao = new DriverDetailsDAO();
	CarServiceDAO carService=new CarServiceDAO();
	private CarServiceDAO carsDao = new CarServiceDAO();

	@Override
	public ResponseMessage registerdriver( ServiceExecutionContext ctx, DriverDetails driverDetails )
			throws ExecException
	{
		
		ResponseMessage message = new ResponseMessage();
		try
		{
			// check for the mandatory fields for the driver
			if( driverDetails == null || driverDetails.getDriverLicence() == null
					|| driverDetails.getDriverLicence().trim().isEmpty() || driverDetails.getLocation() == null
					|| driverDetails.getLocation().trim().isEmpty() || driverDetails.getCarType() == null
					|| driverDetails.getCarType().trim().isEmpty() || driverDetails.getCarNumber() == null
					|| driverDetails.getCarNumber().trim().isEmpty() || driverDetails.getDriverName() == null
					|| driverDetails.getDriverName().trim().isEmpty() || driverDetails.getDriverPhoneNumber() == null
					|| driverDetails.getDriverPhoneNumber().trim().isEmpty() )
			{
				throw new ExecException(ErrorCodes.MISSING_FIELD, null, "Mandatory Fields are missing");
			}
			FetchCarDetails details = new FetchCarDetails();
			details.setRegisteredAt(driverDetails.getLocation());
			details.setCarType(driverDetails.getCarType());
			details.setCarNumber(driverDetails.getCarNumber());
			CarDetails carDetails = carsDao.fetchUnRegisteredCarDetails(details);
			
            if( carDetails == null)
			{
				throw new ExecException(ErrorCodes.BAD_REQUEST, null, "CAR DOES NOT EXIST FOR THE DRIVER");
			}
			else
			{
				// register driver
				boolean result = driverDetailsDao.addDriverDetails(driverDetails);
				carService.updateDriverLicenceAndRegistration(driverDetails.getLocation(), driverDetails.getCarType(), driverDetails.getCarNumber(), driverDetails.getDriverLicence());
				message.setStatus("400");
				message.setMessage("Failed");

				if( result == true )
				{
					message.setStatus("200");
					message.setMessage("successfully saved");
				}
			}
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		return message;
	}

	@Override
	public ResponseMessage updateDriverAvailability( ServiceExecutionContext ctx,
			ConfirmDriverAvailability driverDetails ) throws ExecException
	{
		ResponseMessage message = new ResponseMessage();
		message.setMessage("BAD GATEWAY");
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
			
			boolean driverResults=driverDetailsDao.fetchDrivers(driverDetails);
			if(driverResults==true)
			{
				boolean result = driverDetailsDao.updateDriverAvailability(driverDetails);
	
				// register car
				FetchCarDetails details = new FetchCarDetails();
				details.setRegisteredAt(driverDetails.getLocation());
				details.setCarType(driverDetails.getCarType());
				details.setCarNumber(driverDetails.getCarNumber());
				
				if(driverDetails.isAvailability()==false)
					carsDao.unConfirmRegistration(details);
				else
					carsDao.confirmRegistration(details);
		
				message.setStatus("400");
				message.setMessage("Failed");
				if( result == true )
				{
					message.setStatus("200");
					message.setMessage("Driver details updated successfully");
				}
				
			}
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		return message;
	}
}
