package com.yathraCity.cassandra.dao;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datastax.driver.core.ConsistencyLevel;
import com.datastax.driver.core.ResultSetFuture;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.UUIDGenerator;
import com.yathraCity.cassandra.pojo.CarDetails;
import com.yathraCity.cassandra.session.CassandraQuery;
import com.yathraCity.cassandra.tables.CarColumns;
import com.yathraCity.cassandra.tables.TableNames;
import com.yathraCity.core.CheckAvailabilityInput;
import com.yathraCity.core.FetchCarDetails;
import com.yathraCity.core.RegisterCarInput;
import com.yathraCity.services.config.ConfigKey;
import com.yathraCity.services.config.Configurator;

public class CarServiceDAO
{
	private static Logger logger = LoggerFactory.getLogger( UserDAO.class );
	private static CassandraQuery cassQuery = null;
	private String keyspace;

	//initlization of the keyspace and session
	public CarServiceDAO ()
	{
		try
		{
			cassQuery = new CassandraQuery();
			keyspace = Configurator.getInstance().getProperty( ConfigKey.CASSANDRA_Keyspace );
		}
		catch( IllegalAccessException e )
		{
			e.printStackTrace();
		}
	}

	//Car details storing in DB
	public boolean addCarDetails( RegisterCarInput carDetails )
	{
		boolean result = false;
		try
		{
			Statement insert = QueryBuilder.insertInto( keyspace, TableNames.CARS )
					.value( CarColumns.CAR_AVAILABILITY, carDetails.isCarAvailability() )
					.value( CarColumns.CAR_NAME, carDetails.getCarName() )
					.value( CarColumns.CAR_NUMBER, carDetails.getCarNumber() )
					.value( CarColumns.CAR_REGISTERED_AT, carDetails.getCarRegisteredAt() )
					.value( CarColumns.CAR_REGISTERED, carDetails.isRegistered() )
					.value( CarColumns.MINIMUM_DISTANCE_PER_DAY, carDetails.getMinimunDistancePerDay() )
					.value( CarColumns.PRICE_PER_KILOMETER, carDetails.getPricePerKilometer() )
					.value( CarColumns.CAR_TYPE, carDetails.getCarType())
					.value( CarColumns.CAR_MODEL, carDetails.getCarModel())
					.value( CarColumns.CAR_CAPACITY, carDetails.getCarCapacity())
					.setConsistencyLevel( ConsistencyLevel.QUORUM )
					.enableTracing();
			System.out.println( insert.toString() );
			cassQuery.executeFuture( insert );
			result = true;
		}
		catch( Exception e )
		{
			logger.error( "Error while inserting the user data into the cassandra db while saving car details--->"
					+ e.getMessage() );
		}
		return result;

	}

	//list of cars avaliable to take trip
	public List<CarDetails> fetchAvailableCarsOfCity( FetchCarDetails details )
	{
		List<CarDetails> cars = null;
		try
		{
			Statement get = QueryBuilder.select().all().from( keyspace, TableNames.CARS ).allowFiltering()
					.where( QueryBuilder.eq( CarColumns.CAR_REGISTERED_AT, details.getRegisteredAt() ) )
					.and( QueryBuilder.eq( CarColumns.CAR_TYPE, details.getCarType() ) )
					.and( QueryBuilder.eq( CarColumns.CAR_MODEL, details.getCarModel() ) )
					.and( QueryBuilder.eq( CarColumns.CAR_AVAILABILITY, true ) )
					.and( QueryBuilder.eq( CarColumns.CAR_REGISTERED, true ) )
					.setConsistencyLevel( ConsistencyLevel.QUORUM ).enableTracing();
			System.out.println( get.toString() );
			ResultSetFuture results = cassQuery.executeFuture( get );
			cars = processCarEntity( results );
		}
		catch( Exception e )
		{
			logger.error( "Error while inserting the user data into the cassandra db while registering user--->"
					+ e.getMessage() );
		}
		return cars;
	}

	//Checking the car avaliablity
	public boolean checkCarAavailability( CheckAvailabilityInput input )
	{
		boolean result = false;
		try
		{
			Statement get = QueryBuilder.select().all().from( keyspace, TableNames.CARS )
					.where( QueryBuilder.eq( CarColumns.CAR_NUMBER, input.getCarNumber() ) )
					.and( QueryBuilder.eq( CarColumns.CAR_REGISTERED_AT, input.getCarRegisteredAt() ) )
					.and( QueryBuilder.eq( CarColumns.CAR_CAPACITY, input.getCarCapacity() ) )
					.and( QueryBuilder.eq( CarColumns.CAR_AVAILABILITY, true ) )
					.setConsistencyLevel( ConsistencyLevel.QUORUM ).enableTracing();
			ResultSetFuture results = cassQuery.executeFuture( get );
			if( processCarEntity( results ).get( 0 ) != null )
			{
				updateCarAavailability( input );
				result = true;
			}
		}
		catch( Exception e )
		{
			logger.error( "Error while inserting the user data into the cassandra db while registering user--->"
					+ e.getMessage() );
		}
		return result;
	}

	//Updating the car avaliablity
	public boolean updateCarAavailability( CheckAvailabilityInput input )
	{
		boolean result = false;
		try
		{
			Statement get = QueryBuilder.update( keyspace, TableNames.CARS )
					.with( QueryBuilder.add( CarColumns.CAR_AVAILABILITY, false ) )
					.where( QueryBuilder.eq( CarColumns.CAR_NUMBER, input.getCarNumber() ) )
					.and( QueryBuilder.eq( CarColumns.CAR_REGISTERED_AT, input.getCarRegisteredAt() ) )
					.and( QueryBuilder.eq( CarColumns.CAR_CAPACITY, input.getCarCapacity() ) )
					.and( QueryBuilder.eq( CarColumns.CAR_AVAILABILITY, true ) )
					.setConsistencyLevel( ConsistencyLevel.QUORUM ).enableTracing();
			ResultSetFuture results = cassQuery.executeFuture( get );
			if( processCarEntity( results ).get( 0 ) != null )
			{
				result = true;
			}
		}
		catch( Exception e )
		{
			logger.error( "Error while inserting the user data into the cassandra db while registering user--->"
					+ e.getMessage() );
		}
		return result;
	}

	//adding the avaliable car to the list
	private List<CarDetails> processCarEntity( ResultSetFuture results )
	{
		List<CarDetails> cars = new ArrayList<CarDetails>();
		for( Row r : results.getUninterruptibly() )
		{
			CarDetails carDetails = new CarDetails();
			carDetails.setCarName( r.getString( CarColumns.CAR_NAME ) );
			carDetails.setCarCapacity( r.getInt( CarColumns.CAR_CAPACITY ) );
			carDetails.setCarModel( r.getString( CarColumns.CAR_MODEL ) );
			carDetails.setCarNumber( r.getString( CarColumns.CAR_NUMBER ) );
			carDetails.setCarRegisteredAt( r.getString( CarColumns.CAR_REGISTERED_AT ) );
			carDetails.setMinimunDistancePerDay( r.getInt( CarColumns.MINIMUM_DISTANCE_PER_DAY ) );
			carDetails.setPricePerKilometer( r.getInt( CarColumns.PRICE_PER_KILOMETER ) );
			cars.add( carDetails );
		}
		return cars;
	}
	
}
