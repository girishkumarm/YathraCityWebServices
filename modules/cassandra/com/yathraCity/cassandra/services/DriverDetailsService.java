package com.yathraCity.cassandra.services;

import com.razorthink.runtime.ExecException;
import com.yathraCity.cassandra.dao.DriverDetailsDAO;
import com.yathraCity.core.DriverDetails;
import defaultpkg.ErrorCodes;

public class DriverDetailsService 
{
	public boolean driverDetailsService(DriverDetails registerDriver)
	{
		try
		{
			if(registerDriver == null || registerDriver.getAgencyName() ==null || registerDriver.getAgencyName().trim().isEmpty()
					|| registerDriver.getAgencyPhoneNumber()== null || registerDriver.getAgencyPhoneNumber().trim().isEmpty()
					|| registerDriver.getDriverLicence()==null || registerDriver.getDriverLicence().trim().isEmpty()
					|| registerDriver.getDriverName()==null || registerDriver.getDriverName().trim().isEmpty()
					|| registerDriver.getDriverPhoneNumber()==null || registerDriver.getDriverPhoneNumber().trim().isEmpty()
					|| registerDriver.getLocation()==null || registerDriver.getLocation().trim().isEmpty())
			{
				throw new ExecException(ErrorCodes.MISSING_FIELD, null, "Mandatory Fields are missing");
			}	
		
		
			DriverDetailsDAO driverdetailsDAO=new DriverDetailsDAO();
			boolean result=driverdetailsDAO.addDriverDetails(registerDriver);
			
			if(result==true)
				return true;
			else if(result==false)
				return false;
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
}
