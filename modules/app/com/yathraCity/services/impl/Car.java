package com.yathraCity.services.impl;

import java.util.ArrayList;
import java.util.List;
import com.razorthink.runtime.ExecException;
import com.razorthink.runtime.ServiceExecutionContext;
import com.yathraCity.cassandra.dao.CarServiceDAO;
import com.yathraCity.cassandra.pojo.CarDetails;
import com.yathraCity.core.CheckAvailabilityInput;
import com.yathraCity.core.FetchCarDetails;
import com.yathraCity.core.ListOfAvailableCars;
import com.yathraCity.core.RegisterCarInput;
import com.yathraCity.core.ResponseMessage;
import com.yathraCity.services.CarInterface;

public class Car implements CarInterface {

	private CarServiceDAO carsDao = new CarServiceDAO();

	@Override
	public ResponseMessage registerCar( ServiceExecutionContext ctx, RegisterCarInput input ) throws ExecException
	{
		ResponseMessage response = new ResponseMessage();
		response.setStatus("500");
		response.setMessage("Failed");
		try
		{
			if( input == null || input.getCarName() == null || input.getCarName().trim().isEmpty()
					|| input.getCarNumber() == null || input.getCarNumber().trim().isEmpty()
					|| input.getCarRegisteredAt() == null || input.getCarRegisteredAt().trim().isEmpty()
					|| input.getMinimunDistancePerDay() == null || input.getMinimunDistancePerDay() == 0
					|| input.getPricePerKilometer() == null || input.getPricePerKilometer() == 0
					|| input.getPricePerKilometer() == null || input.getCarType() == null
					|| input.getCarType().trim().isEmpty() || input.getCarModel() == null
					|| input.getCarModel().trim().isEmpty() )
			{
				throw new Exception("Mandatory fields are missing to register the car");
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
			e.printStackTrace();
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
			if( input == null || input.getCarCapacity() == 0 || input.getCarNumber() == null
					|| input.getCarNumber().trim().isEmpty() || input.getCarRegisteredAt() == null
					|| input.getCarRegisteredAt().trim().isEmpty() )
			{
				throw new Exception("Mandatory fields are missing to book the car");
			}
			if( carsDao.checkCarAavailability(input) )
			{
				response.setStatus("200");
				response.setMessage("Car is still Available");
			}
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public ListOfAvailableCars getCarDetails( ServiceExecutionContext ctx, FetchCarDetails input ) throws ExecException
	{
		ListOfAvailableCars listOfAvaliableCars = new ListOfAvailableCars();
		List<CarDetails> listOfCars = new ArrayList<CarDetails>();
		List<RegisterCarInput> avaliableCars = new ArrayList<RegisterCarInput>();
		try
		{
			if( input == null || input.getCarType() == null || input.getCarType().trim().isEmpty()
					|| input.getRegisteredAt() == null || input.getRegisteredAt().trim().isEmpty() )
			{
				throw new Exception("Mandatory fields are missing to get OTP");
			}
			if( input.getCarNumber() == null || input.getCarNumber().trim().isEmpty() )
			{
				// fetch all the cars of a location
				listOfCars = carsDao.fetchAvailableCarsOfCity(input);

			}
			else
			{
				// fetch a single car details from the db
				listOfCars = carsDao.fetchCarDetails(input);

				if( listOfCars != null && listOfCars.get(0) != null )
				{
					// make the car availability false for 10min
				}
			}

			if( listOfCars != null )
			{
				for( CarDetails c : listOfCars )
				{
					RegisterCarInput car = new RegisterCarInput();
					car.setCarCapacity(c.getCarCapacity());
					car.setCarName(c.getCarName());
					car.setCarType(c.getCarType());
					car.setCarNumber(c.getCarNumber());
					car.setCarRegisteredAt(c.getCarRegisteredAt());
					car.setMinimunDistancePerDay(c.getMinimunDistancePerDay());
					car.setPricePerKilometer(c.getPricePerKilometer());
					avaliableCars.add(car);
				}
			}
			listOfAvaliableCars.setcar(avaliableCars);

		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		return listOfAvaliableCars;
	}

}
