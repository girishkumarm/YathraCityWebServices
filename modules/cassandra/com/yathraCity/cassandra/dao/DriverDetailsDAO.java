package com.yathraCity.cassandra.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.yathraCity.cassandra.session.CassandraQuery;
import com.yathraCity.cassandra.tables.DriverDetails;
import com.yathraCity.cassandra.tables.TableNames;
import com.yathraCity.core.RegisterDriverDetails;
import com.yathraCity.services.config.ConfigKey;
import com.yathraCity.services.config.Configurator;

public class DriverDetailsDAO 
{
	private static Logger logger = LoggerFactory.getLogger(UserDAO.class);
	private static CassandraQuery cassQuery = null;
	private String keyspace;

	// initlizing the session and keyspace
	public DriverDetailsDAO()
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
	
	public boolean addDriverDetails(RegisterDriverDetails details)
	{
		try
		{
			Statement addDetails=QueryBuilder.insertInto(keyspace,TableNames.DRIVER_DETAILS)
					.value(DriverDetails.AGENCY_NAME, details.getAgencyName())
					.value(DriverDetails.AGENCY_PHONE_NUMBER, details.getAgencyPhoneNumber())
					.value(DriverDetails.DRIVER_LICENCE, details.getDriverLicence())
					.value(DriverDetails.DRIVER_NAME, details.getDriverName())
					.value(DriverDetails.DRIVER_PHONE_NUMBER, details.getDriverPhoneNumber())
					.value(DriverDetails.LOCATION, details.getLocation())
					.value(DriverDetails.UUID, CarServiceDAO.getUniqueID());
			cassQuery.executeFuture(addDetails);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
}
