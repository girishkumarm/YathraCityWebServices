package com.yathraCity.services.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.gson.Gson;
import com.razorthink.runtime.ExecException;
import com.razorthink.runtime.ServiceExecutionContext;
import com.yathraCity.cassandra.dao.CarAvailabilityDAO;
import com.yathraCity.cassandra.dao.CarServiceDAO;
import com.yathraCity.cassandra.dao.UserDAO;
import com.yathraCity.cassandra.pojo.CarAvailabilityDetails;
import com.yathraCity.cassandra.pojo.CarDetails;
import com.yathraCity.core.CheckAvailabilityInput;
import com.yathraCity.core.FetchCarDetails;
import com.yathraCity.core.ListOfAvailableCars;
import com.yathraCity.core.RegisterCarInput;
import com.yathraCity.core.ResponseMessage;
import com.yathraCity.services.CarInterface;

public class Car implements CarInterface {

	private CarServiceDAO carsDao = new CarServiceDAO();
	private CarAvailabilityDAO carAvailabilityDAO = new CarAvailabilityDAO();
	private static Logger logger = LoggerFactory.getLogger( Car.class );
	

	@Override
	public ResponseMessage registerCar( ServiceExecutionContext ctx, RegisterCarInput input ) throws ExecException
	{
		ResponseMessage response = new ResponseMessage();
		response.setStatus("500");
		response.setMessage("Failed");
		try
		{
			if( input == null || input.getCarName() == null || input.getCarName().trim().isEmpty()
					|| input.getCarName() == null || input.getCarName().trim().isEmpty() || input.getCarNumber() == null
					|| input.getCarNumber().trim().isEmpty() || input.getCarRegisteredAt() == null
					|| input.getCarRegisteredAt().trim().isEmpty() || input.getMinimunDistancePerDay() == null
					|| input.getMinimunDistancePerDay() == 0 || input.getPricePerKilometer() == null
					|| input.getPricePerKilometer() == 0 || input.getPricePerKilometer() == null
					|| input.getCarType() == null || input.getCarType().trim().isEmpty() || input.getCarModel() == null
					|| input.getCarModel().trim().isEmpty() )
			{
				throw new Exception("Mandatory fields are missing to register the car");
			}

			if( input.getCarAgency() == null || input.getCarAgency().trim().isEmpty() )
			{
				input.setCarAgency("TAB");
			}
			// calling service to store in DB
			if( carsDao.addCarDetails(input) )
			{
				response.setStatus("200");
				response.setMessage("car registred");
			}
		}
		catch( Exception e )
		{
			logger.error( "Error while adding cars"
					+ e.getMessage() );
		}
		return response;
	}

	@Override
	public ResponseMessage checkCarAvailability( ServiceExecutionContext ctx, CheckAvailabilityInput input )
			throws ExecException
	{
		ResponseMessage response = new ResponseMessage();
		response.setStatus("500");
		response.setMessage("Car is already blocked");
		try
		{
			if( input == null || input.getCarNumber() == null || input.getCarNumber().trim().isEmpty()
					|| input.getCarRegisteredAt() == null || input.getCarRegisteredAt().trim().isEmpty() )
			{
				throw new Exception("Mandatory fields are missing to book the car");
			}
			FetchCarDetails details = new FetchCarDetails();
			details.setCarType(input.getCarType());
			details.setCarNumber(input.getCarNumber());
			details.setRegisteredAt(input.getCarRegisteredAt());
			details.setCarName(input.getCarName());

			// fetch the car and check if it is available or not
			CarDetails car = carsDao.fetchCarDetails(details);
			if( car.isAvailable() )
			{
				response.setStatus("200");
				response.setMessage("Car is still Available");
			}
		}
		catch( Exception e )
		{
			logger.error( "Error while checking the avaliable cars"
					+ e.getMessage() );
		}
		return response;
	}

	@Override
	public ListOfAvailableCars getAvailableCarDetails( ServiceExecutionContext ctx, FetchCarDetails input )
			throws ExecException
	{
		ListOfAvailableCars listOfAvaliableCars = new ListOfAvailableCars();
		List<CarDetails> listOfCars = new ArrayList<CarDetails>();
		List<RegisterCarInput> avaliableCars = new ArrayList<RegisterCarInput>();
		try
		{
			if( input == null || input.getCarType() == null || input.getCarType().trim().isEmpty()
					|| input.getRegisteredAt() == null || input.getRegisteredAt().trim().isEmpty()
					|| input.getCarName() == null || input.getCarName().trim().isEmpty() || input.getFromDate() == null
					|| input.getFromDate().trim().isEmpty() || input.getToDate() == null
					|| input.getToDate().trim().isEmpty() )
			{
				throw new Exception("Mandatory fields are missing to get OTP");
			}
			if( input.getCarNumber() == null || input.getCarNumber().trim().isEmpty() )
			{
				// fetch all the cars of a location
				listOfCars = carsDao.fetchAvailableCarsOfCity(input);
				System.out.println("size : " + listOfCars.size());
			}
			else
			{
				// fetch a single car details from the db
				CarDetails car = carsDao.fetchCarDetails(input);

				if( car != null )
				{
					listOfCars.add(car);
				}
			}

			if( listOfCars != null )
			{
				System.out.println(new Gson().toJson(listOfCars));
				for( CarDetails c : listOfCars )
				{
					// check if the car is available
					if( c.isAvailable() && c.isRegistered() )
					{
						System.out.println("Available");
						// check if the car is already booked are not
						List<CarAvailabilityDetails> carAvailability = carAvailabilityDAO
								.fetchCarAvailabilty(c.getCarNumber());
						if( carAvailability != null && carAvailability.size() > 0 )
						{
							// if the car is already booked match for the
							// booking clashes
							for( CarAvailabilityDetails details : carAvailability )
							{
								System.out.println("Car availability is not empty");
								if( !details.getFromDate().equals(input.getFromDate())
										&& !details.getToDate().equals(input.getFromDate()) )
								{
									SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
									Date from = sdf.parse(details.getFromDate());
									Date to = sdf.parse(details.getToDate());
									Date bookingFromDate = sdf.parse(input.getFromDate());
									Date bookingToDate = sdf.parse(input.getToDate());

									if( from.getTime() > bookingToDate.getTime()
											|| to.getTime() < bookingFromDate.getTime() )
									{
										avaliableCars.add(getCarDetails(c));
									}
									else if( (from.compareTo(bookingFromDate) * to.compareTo(bookingFromDate)) <= 0
											|| (from.compareTo(bookingToDate) * to.compareTo(bookingToDate)) <= 0
											|| (bookingFromDate.compareTo(from) * bookingToDate.compareTo(from)) <= 0
											|| (bookingFromDate.compareTo(to) * bookingToDate.compareTo(to)) <= 0 )
									{
										System.out.println("Inside if loop");
										// this car is already booked on the
										// given
										// dates
										if( input.getCarNumber() != null && !input.getCarNumber().trim().isEmpty() )
										{
											throw new Exception("Sorry The car is already booked by some one else");
										}
									}
								}
							}
						}
						else
						{
							System.out.println("Adding car");
							avaliableCars.add(getCarDetails(c));
						}
					}
				}
			}
			listOfAvaliableCars.setcar(avaliableCars);

		}
		catch( Exception e )
		{
			logger.error( "Error while getting cars"
					+ e.getMessage() );
		}
		return listOfAvaliableCars;
	}

	private RegisterCarInput getCarDetails( CarDetails details )
	{
		RegisterCarInput car = new RegisterCarInput();
		car.setCarCapacity(details.getCarCapacity());
		car.setCarName(details.getCarName());
		car.setCarType(details.getCarType());
		car.setCarNumber(details.getCarNumber());
		car.setCarRegisteredAt(details.getCarRegisteredAt());
		car.setMinimunDistancePerDay(details.getMinimunDistancePerDay());
		car.setPricePerKilometer(details.getPricePerKilometer());
		car.setCarAvailability(details.isAvailable());
		car.setRegistered(details.isRegistered());
		car.setCarModel(details.getCarModel());
		car.setCarCapacity(details.getCarCapacity());
		car.setCarAgency(details.getCarAgency());
		return car;
	}

}
