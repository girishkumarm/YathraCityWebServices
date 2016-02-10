package com.yathraCity.services.impl;

import com.razorthink.runtime.ExecException;
import com.razorthink.runtime.ServiceExecutionContext;
import com.yathraCity.cassandra.dao.BookingDetailsDAO;
import com.yathraCity.cassandra.dao.CancelingDAO;
import com.yathraCity.core.CancelBooking;
import com.yathraCity.core.ResponseMessage;
import com.yathraCity.services.CarCancelingServiceInterface;
import defaultpkg.ErrorCodes;

public class CarCancelingService implements CarCancelingServiceInterface
{
	private CancelingDAO cancelBooking=new CancelingDAO();
	private BookingDetailsDAO cancel=new BookingDetailsDAO();
	private String carNumb;
	private boolean result=false;
    private ResponseMessage response=new ResponseMessage();
	
	@Override
	public ResponseMessage cancelBooking( ServiceExecutionContext ctx, CancelBooking input ) throws ExecException
	{
		response.setMessage("booking canclation failed");
		response.setStatus("500");
		if(input== null || input.getCustomerEmail() == null || input.getCustomerEmail().isEmpty()
				|| input.getCustomerName()==null || input.getCustomerName().isEmpty()
				|| input.getCustomerNumber()==null || input.getCustomerNumber().isEmpty()
				|| input.getFromDate()==null || input.getFromDate().isEmpty()
				|| input.getPickUpCity()==null || input.getPickUpCity().isEmpty()
				|| input.getToDate()==null || input.getToDate().isEmpty()
				|| input.getTravelCity()== null || input.getTravelCity().isEmpty())
		{
			throw new ExecException(ErrorCodes.MISSING_FIELD, null, "Mandatory Fields are missing");
		}
		try
		{
			carNumb=cancel.getAllBookingDetailsOfTheParticularCar(input);
			result=cancelBooking.deleteEntryInCarAvaliablity(input, carNumb);
			if(result==true)
			{
				response.setMessage("details not found");
				response.setStatus("404");
			}
			response.setMessage("succellfully canceled");
			response.setStatus("200");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return response;
		
	}

}
