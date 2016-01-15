package com.yathraCity.cassandra.dao;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.datastax.driver.core.ConsistencyLevel;
import com.datastax.driver.core.ResultSetFuture;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.yathraCity.cassandra.pojo.CarDetails;
import com.yathraCity.cassandra.session.CassandraQuery;
import com.yathraCity.cassandra.tables.CarColumns;
import com.yathraCity.cassandra.tables.TableNames;
import com.yathraCity.core.BookedCarDetails;
import com.yathraCity.core.CheckAvailabilityInput;
import com.yathraCity.core.FetchCarDetails;
import com.yathraCity.core.RegisterCarInput;
import com.yathraCity.services.config.ConfigKey;
import com.yathraCity.services.config.Configurator;

public class CarServiceDAO {

	private static Logger logger = LoggerFactory.getLogger(UserDAO.class);
	private static CassandraQuery cassQuery = null;
	private String keyspace;

	// initlization of the keyspace and session
	public CarServiceDAO()
	{
		try
		{
			cassQuery = new CassandraQuery();
			keyspace = Configurator.getInstance().getProperty(ConfigKey.CASSANDRA_Keyspace);
		}
		catch( IllegalAccessException e )
		{
			e.printStackTrace();
		}
	}

	// Car details storing in DB
	public boolean addCarDetails( RegisterCarInput carDetails )
	{
		boolean result = false;
		try
		{
			Statement insert = QueryBuilder.insertInto(keyspace, TableNames.CARS)
					.value(CarColumns.CAR_AVAILABILITY, carDetails.isCarAvailability())
					.value(CarColumns.CAR_NAME, carDetails.getCarName())
					.value(CarColumns.CAR_NUMBER, carDetails.getCarNumber())
					.value(CarColumns.CAR_REGISTERED_AT, carDetails.getCarRegisteredAt())
					.value(CarColumns.CAR_REGISTERED, false)
					.value(CarColumns.MINIMUM_DISTANCE_PER_DAY, carDetails.getMinimunDistancePerDay())
					.value(CarColumns.PRICE_PER_KILOMETER, carDetails.getPricePerKilometer())
					.value(CarColumns.CAR_TYPE, carDetails.getCarType())
					.value(CarColumns.CAR_MODEL, carDetails.getCarModel())
					.value(CarColumns.CAR_CAPACITY, carDetails.getCarCapacity())
					.setConsistencyLevel(ConsistencyLevel.QUORUM).enableTracing();
			cassQuery.executeFuture(insert);
			result = true;
		}
		catch( Exception e )
		{
			logger.error("Error while inserting the user data into the cassandra db while saving car details--->"
					+ e.getMessage());
		}
		return result;

	}

	// list of cars avaliable to take trip
	public List<CarDetails> fetchAvailableCarsOfCity( FetchCarDetails details )
	{
		List<CarDetails> cars = null;
		try
		{
			Statement get = QueryBuilder.select().all().from(keyspace, TableNames.CARS).allowFiltering()
					.where(QueryBuilder.eq(CarColumns.CAR_REGISTERED_AT, details.getRegisteredAt()))
					.and(QueryBuilder.eq(CarColumns.CAR_TYPE, details.getCarType()))
					.and(QueryBuilder.eq(CarColumns.CAR_AVAILABILITY, true))
					.and(QueryBuilder.eq(CarColumns.CAR_REGISTERED, true)).setConsistencyLevel(ConsistencyLevel.QUORUM)
					.enableTracing();
			ResultSetFuture results = cassQuery.executeFuture(get);
			cars = processCarEntity(results);
		}
		catch( Exception e )
		{
			logger.error("Error while inserting the user data into the cassandra db while registering user--->"
					+ e.getMessage());
		}
		return cars;
	}

	// list of cars avaliable to take trip
	public List<CarDetails> fetchCarDetails( FetchCarDetails details )
	{
		List<CarDetails> cars = null;
		try
		{
			Statement get = QueryBuilder.select().all().from(keyspace, TableNames.CARS).allowFiltering()
					.where(QueryBuilder.eq(CarColumns.CAR_REGISTERED_AT, details.getRegisteredAt()))
					.and(QueryBuilder.eq(CarColumns.CAR_TYPE, details.getCarType()))
					.and(QueryBuilder.eq(CarColumns.CAR_NUMBER, details.getCarNumber()))
					.and(QueryBuilder.eq(CarColumns.CAR_AVAILABILITY, true))
					.and(QueryBuilder.eq(CarColumns.CAR_REGISTERED, true)).setConsistencyLevel(ConsistencyLevel.QUORUM)
					.enableTracing();
			ResultSetFuture results = cassQuery.executeFuture(get);
			cars = processCarEntity(results);
		}
		catch( Exception e )
		{
			logger.error("Error while inserting the user data into the cassandra db while registering user--->"
					+ e.getMessage());
		}
		return cars;
	}

	// fetch unregistered cars
	public CarDetails fetchUnRegisteredCarDetails( FetchCarDetails details )
	{
		List<CarDetails> cars = null;
		try
		{
			Statement get = QueryBuilder.select().all().from(keyspace, TableNames.CARS).allowFiltering()
					.where(QueryBuilder.eq(CarColumns.CAR_REGISTERED_AT, details.getRegisteredAt()))
					.and(QueryBuilder.eq(CarColumns.CAR_NUMBER, details.getCarNumber()))
					.setConsistencyLevel(ConsistencyLevel.QUORUM).enableTracing();
			ResultSetFuture results = cassQuery.executeFuture(get);
			System.out.println(get.toString());
			cars = processCarEntity(results);
			if( cars != null && cars.get(0) != null && cars.size()>0)
			{
				return cars.get(0);
			}
		}
		catch( Exception e )
		{
			logger.error("Error while fetching the unregistered cars from the db--->" + e.getMessage());
		}
		return null;
	}

	// confirm car registration
	public void confirmRegistration( FetchCarDetails details )
	{
		try
		{
			Statement update = QueryBuilder.update(keyspace, TableNames.CARS)
					.with(QueryBuilder.add(CarColumns.CAR_REGISTERED, true))
					.where(QueryBuilder.eq(CarColumns.CAR_NUMBER, details.getCarNumber()))
					.and(QueryBuilder.eq(CarColumns.CAR_TYPE, details.getCarType()))
					.and(QueryBuilder.eq(CarColumns.CAR_REGISTERED_AT, details.getRegisteredAt()))
					.setConsistencyLevel(ConsistencyLevel.QUORUM).enableTracing();

			cassQuery.executeFuture(update);
		}
		catch( Exception e )
		{
			logger.error("Error while fetching the unregistered cars from the db--->" + e.getMessage());
		}
	}

	// Checking the car avaliablity
	public boolean checkCarAavailability( CheckAvailabilityInput input )
	{
		boolean result = false;
		try
		{
			Statement get = QueryBuilder.select().all().from(keyspace, TableNames.CARS)
					.where(QueryBuilder.eq(CarColumns.CAR_NUMBER, input.getCarNumber()))
					.and(QueryBuilder.eq(CarColumns.CAR_REGISTERED_AT, input.getCarRegisteredAt()))
					.and(QueryBuilder.eq(CarColumns.CAR_CAPACITY, input.getCarCapacity()))
					.and(QueryBuilder.eq(CarColumns.CAR_AVAILABILITY, true))
					.setConsistencyLevel(ConsistencyLevel.QUORUM).enableTracing();
			ResultSetFuture results = cassQuery.executeFuture(get);
			if( processCarEntity(results).get(0) != null )
			{
				updateCarAavailability(input);
				result = true;
			}
		}
		catch( Exception e )
		{
			logger.error("Error while inserting the user data into the cassandra db while registering user--->"
					+ e.getMessage());
		}
		return result;
	}

	// Updating the car avaliablity
	public boolean updateCarAavailability( CheckAvailabilityInput input )
	{
		boolean result = false;
		try
		{
			Statement get = QueryBuilder.update(keyspace, TableNames.CARS)
					.with(QueryBuilder.add(CarColumns.CAR_AVAILABILITY, false))
					.where(QueryBuilder.eq(CarColumns.CAR_NUMBER, input.getCarNumber()))
					.and(QueryBuilder.eq(CarColumns.CAR_REGISTERED_AT, input.getCarRegisteredAt()))
					.and(QueryBuilder.eq(CarColumns.CAR_CAPACITY, input.getCarCapacity()))
					.and(QueryBuilder.eq(CarColumns.CAR_AVAILABILITY, true))
					.setConsistencyLevel(ConsistencyLevel.QUORUM).enableTracing();
			ResultSetFuture results = cassQuery.executeFuture(get);
			if( processCarEntity(results).get(0) != null )
			{
				result = true;
			}
		}
		catch( Exception e )
		{
			logger.error("Error while inserting the user data into the cassandra db while registering user--->"
					+ e.getMessage());
		}
		return result;
	}

	public void getCarDetails(BookedCarDetails input)
	{
		List<CarDetails> cars = null;
		
		try
		{
			Statement getCarDetail=QueryBuilder.select().all().from(keyspace, TableNames.CARS).allowFiltering()
					.where(QueryBuilder.eq(CarColumns.CAR_REGISTERED_AT, input.getCarLocation()))
					.and(QueryBuilder.eq(CarColumns.CAR_TYPE, input.getCarType()))
					.and(QueryBuilder.eq(CarColumns.CAR_NUMBER, input.getCarNumber()))
					.and(QueryBuilder.eq(CarColumns.CAR_DRIVER_LICENCE, input.getCarlicence())).setConsistencyLevel(ConsistencyLevel.QUORUM)
					.enableTracing();
			System.out.println(getCarDetail.toString());
			ResultSetFuture results=cassQuery.executeFuture(getCarDetail);
			cars=processCarEntity(results);
			input.setAddress(cars.get(0).getAddress());
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
	}
	// adding the avaliable car to the list
	private List<CarDetails> processCarEntity( ResultSetFuture results )
	{
		List<CarDetails> cars = new ArrayList<CarDetails>();
		for( Row r : results.getUninterruptibly() )
		{
			CarDetails carDetails = new CarDetails();
			carDetails.setCarType(r.getString(CarColumns.CAR_TYPE));
			carDetails.setCarCapacity(r.getInt(CarColumns.CAR_CAPACITY));
			carDetails.setCarModel(r.getString(CarColumns.CAR_MODEL));
			carDetails.setCarNumber(r.getString(CarColumns.CAR_NUMBER));
			carDetails.setCarRegisteredAt(r.getString(CarColumns.CAR_REGISTERED_AT));
			carDetails.setMinimunDistancePerDay(r.getInt(CarColumns.MINIMUM_DISTANCE_PER_DAY));
			carDetails.setPricePerKilometer(r.getInt(CarColumns.PRICE_PER_KILOMETER));
			cars.add(carDetails);
		}
		return cars;
	}

}
