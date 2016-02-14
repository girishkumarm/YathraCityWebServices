package com.yathraCity.services.impl;

import java.util.List;
import com.razorthink.runtime.ExecException;
import com.razorthink.runtime.ServiceExecutionContext;
import com.yathraCity.cassandra.dao.BookingDetailsDAO;
import com.yathraCity.cassandra.dao.CancelingDAO;
import com.yathraCity.core.BookedCarDetails;
import com.yathraCity.core.BookingList;
import com.yathraCity.core.CancelBooking;
import com.yathraCity.core.ResponseMessage;
import com.yathraCity.services.CarCancelingServiceInterface;
import defaultpkg.ErrorCodes;

public class CarCancelingService implements CarCancelingServiceInterface {

	private CancelingDAO cancelBooking = new CancelingDAO();
	private BookingDetailsDAO bookingDetails = new BookingDetailsDAO();

	@Override
	public ResponseMessage cancelBooking( ServiceExecutionContext ctx, CancelBooking input ) throws ExecException
	{
		ResponseMessage response = new ResponseMessage();
		
		try
		{
			response.setMessage("booking cancellation failed");
			response.setStatus("500");
			if( input == null || input.getCustomerPhoneNumber() == null || input.getCustomerPhoneNumber().trim().isEmpty()
					|| input.getCarNumber() == null || input.getCarNumber().trim().isEmpty() || input.getFromDate() == null
					|| input.getFromDate().trim().isEmpty() )
			{
				throw new ExecException(ErrorCodes.MISSING_FIELD, null, "Mandatory Fields are missing");
			}
			
			cancelBooking.deleteEntryInCarAvaliablity(input.getFromDate(), input.getCarNumber());

			response.setMessage("successfully cancelled");
			response.setStatus("200");
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		return response;

	}

	@Override
	public BookingList fetchAllBookings( ServiceExecutionContext ctx ) throws ExecException
	{
		BookingList lists = new BookingList();
		List<BookedCarDetails> carBooked = null;
		try
		{
			carBooked = bookingDetails.getAllBookingDetails();
			lists.setbookingList(carBooked);
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		return lists;
	}

}
