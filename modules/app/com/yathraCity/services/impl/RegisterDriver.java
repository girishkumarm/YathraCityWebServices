package com.yathraCity.services.impl;

import com.razorthink.runtime.ExecException;
import com.razorthink.runtime.ServiceExecutionContext;
import com.yathraCity.cassandra.services.DriverDetailsService;
import com.yathraCity.core.RegisterDriverDetails;
import com.yathraCity.core.ResponseMessage;
import com.yathraCity.services.RegisterDriverInterface;
import defaultpkg.ErrorCodes;

public class RegisterDriver implements RegisterDriverInterface {

	@Override
	public ResponseMessage registerdriver( ServiceExecutionContext ctx, RegisterDriverDetails registerDriver )
			throws ExecException
	{
		ResponseMessage message = new ResponseMessage();
		try
		{
			if( registerDriver == null || registerDriver.getAgencyName() == null
					|| registerDriver.getAgencyName().trim().isEmpty() || registerDriver.getAgencyPhoneNumber() == null
					|| registerDriver.getAgencyPhoneNumber().trim().isEmpty()
					|| registerDriver.getDriverLicence() == null || registerDriver.getDriverLicence().trim().isEmpty()
					|| registerDriver.getDriverName() == null || registerDriver.getDriverName().trim().isEmpty()
					|| registerDriver.getDriverPhoneNumber() == null
					|| registerDriver.getDriverPhoneNumber().trim().isEmpty() || registerDriver.getLocation() == null
					|| registerDriver.getLocation().trim().isEmpty() )
			{
				throw new ExecException(ErrorCodes.MISSING_FIELD, null, "Mandatory Fields are missing");
			}
			DriverDetailsService driverDetailsService = new DriverDetailsService();
			boolean result = driverDetailsService.driverDetailsService(registerDriver);

			message.setStatus("400");
			message.setMessage("unsuccssfully saved");

			if( result == true )
			{
				message.setStatus("200");
				message.setMessage("succssfully saved");
				return message;
			}
			else
			{
				return message;
			}
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		return null;
	}

}
