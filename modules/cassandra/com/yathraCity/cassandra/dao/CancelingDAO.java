package com.yathraCity.cassandra.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.yathraCity.cassandra.session.CassandraQuery;
import com.yathraCity.cassandra.tables.CarAvailabilityColumns;
import com.yathraCity.cassandra.tables.TableNames;
import com.yathraCity.core.CancelBooking;
import com.yathraCity.services.config.ConfigKey;
import com.yathraCity.services.config.Configurator;

public class CancelingDAO 
{
	private static CassandraQuery cassQuery = null;
	private String keyspace;
	private static Logger logger = LoggerFactory.getLogger(CancelingDAO.class);
	// initlizing the session and keyspace
	public CancelingDAO()
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
	
	public boolean deleteEntryInCarAvaliablity(CancelBooking cancel,String carNumb)
	{
		boolean result=false;
		try
		{
			Statement delete=QueryBuilder.delete().from(keyspace, TableNames.CAR_AVAILABILITY)
					.where(QueryBuilder.eq(CarAvailabilityColumns.BOOKED_FROM_DATE, cancel.getFromDate()))
					.and(QueryBuilder.eq(CarAvailabilityColumns.CAR_NUMBER, carNumb));
			cassQuery.executeFuture(delete);
			result=true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
}
