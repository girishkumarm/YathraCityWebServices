package com.yathraCity.services.impl;


import java.util.ArrayList;
import java.util.List;
import com.razorthink.runtime.ExecException;
import com.razorthink.runtime.ServiceExecutionContext;
import com.yathraCity.cassandra.pojo.CarDetails;
import com.yathraCity.cassandra.services.BookingService;
import com.yathraCity.cassandra.services.CarService;
import com.yathraCity.cassandra.services.UserOTPServices;
import com.yathraCity.core.AvailableCars;
import com.yathraCity.core.CheckAvailabilityInput;
import com.yathraCity.core.ListOfAvailableCars;
import com.yathraCity.core.RegisterBookingInput;
import com.yathraCity.core.RegisterCarInput;
import com.yathraCity.core.ResponseMessage;
import com.yathraCity.services.YathraServiceInterface;

public class YathraService implements YathraServiceInterface
{

	private BookingService bookingService = new BookingService();
	private CarService carBookingService = new CarService();
	private UserOTPServices otpServices = new UserOTPServices();

	@Override
	public ResponseMessage registerCar( ServiceExecutionContext ctx, RegisterCarInput input ) throws ExecException
	{
		ResponseMessage response = new ResponseMessage();
		response.setStatus( "500" );
		response.setMessage( "Booking not confirmed" );
		try
		{
			if( input == null || input.getCarCapacity() == 0 || input.getCarModel() == null
					|| input.getCarModel().trim().isEmpty() || input.getCarName() == null
					|| input.getCarName().trim().isEmpty() || input.getCarNumber() == null
					|| input.getCarNumber().trim().isEmpty() || input.getCarOwner() == null
					|| input.getCarOwner().trim().isEmpty() || input.getCarRegisteredAt() == null
					|| input.getCarRegisteredAt().trim().isEmpty() || input.getContactNumber() == null
					|| input.getContactNumber().trim().isEmpty() || input.getMinimunDistancePerDay() == null
					|| input.getMinimunDistancePerDay()==0 || input.getOwnerLicenseNumber() == null
					|| input.getOwnerLicenseNumber().trim().isEmpty() || input.getPricePerKilometer() == null
					|| input.getPricePerKilometer() == 0 )
			{
				throw new Exception( "Mandatory fields are missing to register the car" );
			}
			if( carBookingService.addCarDetails( input ) )
			{
				response.setStatus( "200" );
				response.setMessage( "Booking confirmed" );
			}
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public ResponseMessage bookCar( ServiceExecutionContext ctx, RegisterBookingInput input ) throws ExecException
	{
		ResponseMessage response = new ResponseMessage();
		response.setStatus( "500" );
		response.setMessage( "Booking not confirmed" );
		try
		{
			if( input == null || input.getPhoneNumber() == 0 || input.getBookedCarName() == null
					|| input.getBookedCarName().trim().isEmpty() || input.getBookedCarNumber() == null
					|| input.getBookedCarNumber().trim().isEmpty() || input.getPickUpPoint() == null
					|| input.getPickUpPoint() == 0 )
			{
				throw new Exception( "Mandatory fields are missing to book the car" );
			}
			if( bookingService.bookCarForAnUser( input ) )
			{
				response.setStatus( "200" );
				response.setMessage( "Booking confirmed" );
			}
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public ResponseMessage checkCarAvailability( ServiceExecutionContext ctx, CheckAvailabilityInput input )
			throws ExecException
	{
		ResponseMessage response = new ResponseMessage();
		response.setStatus( "500" );
		response.setMessage( "Car is already blocked" );
		try
		{
			if( input == null || input.getCarCapacity() == 0 || input.getCarNumber() == null
					|| input.getCarNumber().trim().isEmpty() || input.getCarRegisteredAt() == null
					|| input.getCarRegisteredAt().trim().isEmpty() )
			{
				throw new Exception( "Mandatory fields are missing to book the car" );
			}
			if( carBookingService.checkCarAavailability( input ) )
			{
				response.setStatus( "200" );
				response.setMessage( "Car is still Available" );
			}
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public ListOfAvailableCars getCarDetails( ServiceExecutionContext ctx, String pickUpPoint, String capacity )
			throws ExecException
	{
		ListOfAvailableCars availableCars = new ListOfAvailableCars();
		List<CarDetails> carDetails = new ArrayList<CarDetails>();
		List<AvailableCars> car = new ArrayList<AvailableCars>();
		try
		{
			if( pickUpPoint == null || pickUpPoint.trim().isEmpty() || capacity == null || capacity.trim().isEmpty() )
			{
				throw new Exception( "Mandatory fields are missing to book the car" );
			}
			carDetails = carBookingService.getAvailableCars( pickUpPoint, Integer.parseInt(capacity ));
			if( carDetails != null )
			{
				for( CarDetails c : carDetails )
				{
					if( c != null )
					{
						AvailableCars cc = new AvailableCars();
						cc.setCarCapacity( c.getCarCapacity() );
						cc.setCarName( c.getCarName() );
						cc.setCarNumber( c.getCarNumber() );
						cc.setCarOwner( c.getCarOwner() );
						cc.setCarRegisteredAt( c.getCarRegisteredAt() );
						cc.setContactNumber( c.getContactNumber() );
						cc.setMinimunDistancePerDay( c.getMinimunDistancePerDay() + "" );
						cc.setOwnerLicenseNumber( c.getOwnerLicenseNumber() );
						cc.setPricePerKilometer( c.getPricePerKilometer() );

						car.add( cc );
					}
				}
			}
			if( car != null )
			{
				availableCars.setcar( car );
			}
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		return availableCars;
	}

	@Override
	public ResponseMessage getOTP(ServiceExecutionContext ctx,String phoneNumber) throws ExecException 
	{
		ResponseMessage response = new ResponseMessage();
		response.setStatus( "500" );
		response.setMessage( "Generating otp failed" );
		try
		{
			if( phoneNumber == null || phoneNumber.trim().isEmpty() || phoneNumber.trim().length()>10 || phoneNumber.trim().length()<10 )
			{
				throw new Exception( "Mandatory fields are missing to get OTP" );
			}
			
			String otp=otpServices.generateOTP(phoneNumber);
			
			if( otp != null )
			{
				response.setStatus( "200" );
				response.setMessage( "success" );
			}
			
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public ResponseMessage getOTPMatchResponse(ServiceExecutionContext ctx, String phoneNumber, String otp) throws ExecException 
	{
		ResponseMessage response = new ResponseMessage();
		response.setStatus( "500" );
		response.setMessage( "failed" );
		try
		{
			if( phoneNumber == null || phoneNumber.trim().isEmpty() || phoneNumber.trim().length()>10 || phoneNumber.trim().length()<10 )
			{
				throw new Exception( "Mandatory fields are missing to get OTP" );
			}
			
			String otpPresent=otpServices.fetchOtpForPhoneNumber(phoneNumber);
			
			if( otp.equals(otpPresent) )
			{
				response.setStatus( "200" );
				response.setMessage( "success" );
			}
			
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		return response;
	}
}
