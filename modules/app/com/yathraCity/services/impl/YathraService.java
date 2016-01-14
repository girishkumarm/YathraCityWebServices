package com.yathraCity.services.impl;

import java.util.ArrayList;
import java.util.List;
import com.razorthink.runtime.ExecException;
import com.razorthink.runtime.ServiceExecutionContext;
import com.yathraCity.cassandra.dao.BookingDetailsDAO;
import com.yathraCity.cassandra.pojo.CarDetails;
import com.yathraCity.cassandra.services.CarService;
import com.yathraCity.cassandra.services.UserOTPServices;
import com.yathraCity.core.BookedCarDetails;
import com.yathraCity.core.CheckAvailabilityInput;
import com.yathraCity.core.FetchCarDetails;
import com.yathraCity.core.ListOfAvailableCars;
import com.yathraCity.core.RegisterCarInput;
import com.yathraCity.core.RegisterUserResponse;
import com.yathraCity.core.ResponseMessage;
import com.yathraCity.services.YathraServiceInterface;

/**
 * Car registration with the details
 * 
 * @author ashwing
 *         param registration of the car,booking the car
 */
public class YathraService implements YathraServiceInterface {

//	private BookingService bookingService = new BookingService();
	private CarService carBookingService = new CarService();
	private UserOTPServices otpServices = new UserOTPServices();
	// service to register the car

/*	@Override
	public ResponseMessage bookCar( ServiceExecutionContext ctx, RegisterBookingInput input ) throws ExecException
	{
		ResponseMessage response = new ResponseMessage();
		response.setStatus("500");
		response.setMessage("Booking not confirmed");
		try
		{
			if( input == null || input.getPhoneNumber() == 0 || input.getBookedCarName() == null
					|| input.getBookedCarName().trim().isEmpty() || input.getBookedCarNumber() == null
					|| input.getBookedCarNumber().trim().isEmpty() || input.getPickUpPoint() == null
					|| input.getPickUpPoint() == 0 )
			{
				throw new Exception("Mandatory fields are missing to book the car");
			}
			if( bookingService.bookCarForAnUser(input) )
			{
				response.setStatus("200");
				response.setMessage("Booking confirmed");
			}
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		return response;
	}
*/
	/*
	 * public ListOfAvailableCars getCarDetails( ServiceExecutionContext ctx,
	 * String pickUpPoint, String capacity )
	 * throws ExecException
	 * {
	 * ListOfAvailableCars availableCars = new ListOfAvailableCars();
	 * List<CarDetails> carDetails = new ArrayList<CarDetails>();
	 * List<AvailableCars> car = new ArrayList<AvailableCars>();
	 * try
	 * {
	 * if( pickUpPoint == null || pickUpPoint.trim().isEmpty() || capacity ==
	 * null || capacity.trim().isEmpty() )
	 * {
	 * throw new Exception( "Mandatory fields are missing to book the car" );
	 * }
	 * carDetails = carBookingService.getAvailableCars( pickUpPoint,
	 * Integer.parseInt(capacity ));
	 * if( carDetails != null )
	 * {
	 * for( CarDetails c : carDetails )
	 * {
	 * if( c != null )
	 * {
	 * AvailableCars cc = new AvailableCars();
	 * cc.setCarCapacity( c.getCarCapacity() );
	 * cc.setCarName( c.getCarName() );
	 * cc.setCarNumber( c.getCarNumber() );
	 * cc.setCarOwner( c.getCarOwner() );
	 * cc.setCarRegisteredAt( c.getCarRegisteredAt() );
	 * cc.setContactNumber( c.getContactNumber() );
	 * cc.setMinimunDistancePerDay( c.getMinimunDistancePerDay() + "" );
	 * cc.setOwnerLicenseNumber( c.getOwnerLicenseNumber() );
	 * cc.setPricePerKilometer( c.getPricePerKilometer() );
	 * car.add( cc );
	 * }
	 * }
	 * }
	 * if( car != null )
	 * {
	 * availableCars.setcar( car );
	 * }
	 * }
	 * catch( Exception e )
	 * {
	 * e.printStackTrace();
	 * }
	 * return availableCars;
	 * }
	 */
	@Override
	public ResponseMessage getOTP( ServiceExecutionContext ctx, String phoneNumber ) throws ExecException
	{
		ResponseMessage response = new ResponseMessage();
		response.setStatus("500");
		response.setMessage("Generating otp failed");
		try
		{
			if( phoneNumber == null || phoneNumber.trim().isEmpty() || phoneNumber.trim().length() > 10
					|| phoneNumber.trim().length() < 10 )
			{
				throw new Exception("Mandatory fields are missing to get OTP");
			}
			// before generating fetch for the otp
			// if exist send the same otp

			// else generate as given below
			String otp = otpServices.generateOTP(phoneNumber);

			if( otp != null )
			{
				response.setStatus("200");
				response.setMessage("success");
			}

		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public ResponseMessage getOTPMatchResponse( ServiceExecutionContext ctx, String phoneNumber, String otp )
			throws ExecException
	{
		ResponseMessage response = new ResponseMessage();
		response.setStatus("500");
		response.setMessage("failed");
		try
		{
			if( phoneNumber == null || phoneNumber.trim().isEmpty() || phoneNumber.trim().length() > 10
					|| phoneNumber.trim().length() < 10 )
			{
				throw new Exception("Mandatory fields are missing to get OTP");
			}

			String otpPresent = otpServices.fetchOtpForPhoneNumber(phoneNumber);

			if( otp.equals(otpPresent) )
			{
				response.setStatus("200");
				response.setMessage("success");
			}

		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public ResponseMessage bookingCar( ServiceExecutionContext ctx, BookedCarDetails input ) throws ExecException
	{
		ResponseMessage response=new ResponseMessage();
		response.setMessage("booking unsuccessful");
		response.setStatus("500");
		boolean result=false;
		try
		{
			if(input == null || input.getFromDate()== null || input.getFromDate().trim().isEmpty()
					|| input.getToDate()== null || input.getToDate().trim().isEmpty()
					|| input.getTravelCity() == null || input.getTravelCity().trim().isEmpty()
					|| input.getCarLocation()==null || input.getCarLocation().trim().isEmpty()
					|| input.getCarNumber()==null ||input.getCarNumber().trim().isEmpty()
					|| input.getCarType() == null || input.getCarType().trim().isEmpty())
			{
				throw new Exception("Mandatory fields are missing to get for booking");
			}
			BookingDetailsDAO bookTheCar=new BookingDetailsDAO();
			result=bookTheCar.bookCar(input);
			if(result== true)
			{
				response.setMessage("successfully booked");
				response.setStatus("200");
				
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return response;
		}
		return response;
	}

}
