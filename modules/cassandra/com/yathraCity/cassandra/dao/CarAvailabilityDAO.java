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
import com.yathraCity.cassandra.pojo.CarAvailabilityDetails;
import com.yathraCity.cassandra.session.CassandraQuery;
import com.yathraCity.cassandra.tables.CarAvailabilityColumns;
import com.yathraCity.cassandra.tables.TableNames;
import com.yathraCity.services.config.ConfigKey;
import com.yathraCity.services.config.Configurator;

public class CarAvailabilityDAO {

	private static CassandraQuery cassQuery = null;
	private String keyspace;
	private static Logger logger = LoggerFactory.getLogger(CarAvailabilityDAO.class);


	// initlizing the session and keyspace
	public CarAvailabilityDAO()
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

	public boolean addAvailability( CarAvailabilityDetails input )
	{

		try
		{
			Statement insert = QueryBuilder.insertInto(keyspace, TableNames.CAR_AVAILABILITY)
					.value(CarAvailabilityColumns.CAR_NUMBER, input.getCarNumber())
					.value(CarAvailabilityColumns.BOOKED_FROM_DATE, input.getFromDate())
					.value(CarAvailabilityColumns.BOOKED_TILL_DATE, input.getToDate());
			cassQuery.executeFuture(insert);
			return true;
		}
		catch( Exception e )
		{
			logger.error( "Error while adding the avaliablity"
					+ e.getMessage() );
			return false;
		}
	}

	public List<CarAvailabilityDetails> fetchCarAvailabilty( String carNumber )
	{
		List<CarAvailabilityDetails> carAvailability = null;
		try
		{
			Statement get = QueryBuilder.select().all().from(keyspace, TableNames.CAR_AVAILABILITY).allowFiltering()
					.where(QueryBuilder.eq(CarAvailabilityColumns.CAR_NUMBER, carNumber))
					.setConsistencyLevel(ConsistencyLevel.QUORUM).enableTracing();

			ResultSetFuture results = cassQuery.executeFuture(get);

			carAvailability = processCarAvailabilityEntity(results);
		}
		catch( Exception e )
		{
			logger.error( "Error while fetching the car avaliablity"
					+ e.getMessage() );
		}
		return carAvailability;
	}

	public void deleteCarAvailabilty( String carNumber )
	{
		try
		{
			Statement get = QueryBuilder.delete().all().from(keyspace, TableNames.CAR_AVAILABILITY)
					.where(QueryBuilder.eq(CarAvailabilityColumns.CAR_NUMBER, carNumber))
					.setConsistencyLevel(ConsistencyLevel.QUORUM).enableTracing();

			cassQuery.executeFuture(get);
		}
		catch( Exception e )
		{
			logger.error( "Error while deleting the caar avaliablity"
					+ e.getMessage() );
		}
	}

	public void deleteOldCarAvailabilty( CarAvailabilityDetails input )
	{
		try
		{
			Statement get = QueryBuilder.delete().all().from(keyspace, TableNames.CAR_AVAILABILITY)
					.where(QueryBuilder.eq(CarAvailabilityColumns.CAR_NUMBER, input.getCarNumber()))
					.setConsistencyLevel(ConsistencyLevel.QUORUM).enableTracing();

			cassQuery.executeFuture(get);
		}
		catch( Exception e )
		{
			logger.error( "Error while deleting avaliablity"
					+ e.getMessage() );
		}
	}

	// adding the avaliable car to the list
	private List<CarAvailabilityDetails> processCarAvailabilityEntity( ResultSetFuture results )
	{
		List<CarAvailabilityDetails> cars = new ArrayList<CarAvailabilityDetails>();
		for( Row r : results.getUninterruptibly() )
		{
			CarAvailabilityDetails carDetails = new CarAvailabilityDetails();
			carDetails.setCarNumber(r.getString(CarAvailabilityColumns.CAR_NUMBER));
			carDetails.setFromDate(r.getString(CarAvailabilityColumns.BOOKED_FROM_DATE));
			carDetails.setToDate(r.getString(CarAvailabilityColumns.BOOKED_TILL_DATE));
			cars.add(carDetails);
		}
		return cars;
	}
}
