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
import com.yathraCity.core.CheckAvailabilityInput;
import com.yathraCity.core.RegisterCarInput;
import com.yathraCity.services.config.ConfigKey;
import com.yathraCity.services.config.Configurator;

public class CarServiceDAO
{
	private static Logger logger = LoggerFactory.getLogger( UserDAO.class );
	private static CassandraQuery cassQuery = null;
	private String keyspace;

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

	public boolean addCarDetails( RegisterCarInput carDetails )
	{
		boolean result = false;
		try
		{
			Statement insert = QueryBuilder.insertInto( keyspace, TableNames.CARS )
					.value( CarColumns.CONTACT_NUMBER, carDetails.getContactNumber() )
					.value( CarColumns.CAR_AVAILABILITY, carDetails.isCarAvailability() )
					.value( CarColumns.CAR_MODEL, carDetails.getCarModel() )
					.value( CarColumns.CAR_NAME, carDetails.getCarName() )
					.value( CarColumns.CAR_CAPACITY, carDetails.getCarCapacity() )
					.value( CarColumns.CAR_NUMBER, carDetails.getCarNumber() )
					.value( CarColumns.CAR_OWNER, carDetails.getCarOwner() )
					.value( CarColumns.CAR_REGISTERED_AT, carDetails.getCarRegisteredAt() )
					.value( CarColumns.CAR_REGISTERED, carDetails.isRegistered() )
					.value( CarColumns.MINIMUM_DISTANCE_PER_DAY, carDetails.getMinimunDistancePerDay() )
					.value( CarColumns.OWNER_LICENSE_NUMBER, carDetails.getOwnerLicenseNumber() )
					.value( CarColumns.PRICE_PER_KILOMETER, carDetails.getPricePerKilometer() )
					.setConsistencyLevel( ConsistencyLevel.QUORUM )
					.enableTracing();
			//System.out.println( insert.toString() );
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

	public List<CarDetails> fetchAvailableCarsOfCity( String pickUpPoint, int capacity )
	{
		List<CarDetails> cars = null;
		try
		{
			Statement get = QueryBuilder.select().all().from( keyspace, TableNames.CARS ).allowFiltering()
					.where( QueryBuilder.eq( CarColumns.CAR_REGISTERED_AT, pickUpPoint ) )
					.and( QueryBuilder.eq( CarColumns.CAR_CAPACITY, capacity ) )
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

	private List<CarDetails> processCarEntity( ResultSetFuture results )
	{
		List<CarDetails> cars = new ArrayList<CarDetails>();
		for( Row r : results.getUninterruptibly() )
		{
			CarDetails carDetails = new CarDetails();
			carDetails.setCarName( r.getString( CarColumns.CAR_NAME ) );
			carDetails.setCarAvailability( r.getBool( CarColumns.CAR_AVAILABILITY ) );
			carDetails.setCarCapacity( r.getInt( CarColumns.CAR_CAPACITY ) );
			carDetails.setContactNumber( r.getString( CarColumns.CONTACT_NUMBER ) );
			carDetails.setCarModel( r.getString( CarColumns.CAR_MODEL ) );
			carDetails.setCarNumber( r.getString( CarColumns.CAR_NUMBER ) );
			carDetails.setCarOwner( r.getString( CarColumns.CAR_OWNER ) );
			carDetails.setCarRegisteredAt( r.getString( CarColumns.CAR_REGISTERED_AT ) );
			carDetails.setMinimunDistancePerDay( r.getInt( CarColumns.MINIMUM_DISTANCE_PER_DAY ) );
			carDetails.setOwnerLicenseNumber( r.getString( CarColumns.OWNER_LICENSE_NUMBER ) );
			carDetails.setPricePerKilometer( r.getInt( CarColumns.PRICE_PER_KILOMETER ) );
			cars.add( carDetails );
		}
		return cars;
	}

}
