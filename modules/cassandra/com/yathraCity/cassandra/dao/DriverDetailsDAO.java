package com.yathraCity.cassandra.dao;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.datastax.driver.core.ResultSetFuture;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.yathraCity.cassandra.pojo.DriverDetailsPojo;
import com.yathraCity.cassandra.session.CassandraQuery;
import com.yathraCity.cassandra.tables.DriverDetails;
import com.yathraCity.cassandra.tables.TableNames;
import com.yathraCity.core.BookedCarDetails;
import com.yathraCity.core.ConfirmDriverAvailability;
import com.yathraCity.services.config.ConfigKey;
import com.yathraCity.services.config.Configurator;

public class DriverDetailsDAO {

	private static Logger logger = LoggerFactory.getLogger(DriverDetailsDAO.class);
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

	public boolean addDriverDetails( com.yathraCity.core.DriverDetails details )
	{
		try
		{
			Statement addDetails = QueryBuilder.insertInto(keyspace, TableNames.DRIVER_DETAILS)
					.value(DriverDetails.AGENCY_NAME, details.getAgencyName())
					.value(DriverDetails.AGENCY_PHONE_NUMBER, details.getAgencyPhoneNumber())
					.value(DriverDetails.DRIVER_LICENCE, details.getDriverLicence())
					.value(DriverDetails.DRIVER_NAME, details.getDriverName())
					.value(DriverDetails.DRIVER_PHONE_NUMBER, details.getDriverPhoneNumber())
					.value(DriverDetails.LOCATION, details.getLocation()).value(DriverDetails.AVAILABILITY, false)
					.value(DriverDetails.CAR_TYPE, details.getCarType())
					.value(DriverDetails.CAR_NUMBER, details.getCarNumber());
			cassQuery.executeFuture(addDetails);
	        System.out.println(addDetails.toString());
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		return true;
	}

	public boolean updateDriverAvailability( ConfirmDriverAvailability details )
	{
		try
		{
			Statement update = QueryBuilder.update(keyspace, TableNames.DRIVER_DETAILS)
					.with(QueryBuilder.set(DriverDetails.AVAILABILITY, details.isAvailability()))
					.where(QueryBuilder.eq(DriverDetails.CAR_NUMBER, details.getCarNumber()))
					.and(QueryBuilder.eq(DriverDetails.LOCATION, details.getLocation()))
					.and(QueryBuilder.eq(DriverDetails.DRIVER_LICENCE, details.getDriverLicence()));
			cassQuery.executeFuture(update);
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		return true;
	}
	public void driverDetailsForBooking(BookedCarDetails input)
	{
		try
		{
			Statement get=QueryBuilder.select().all().from(keyspace, TableNames.DRIVER_DETAILS).allowFiltering()
					.where(QueryBuilder.eq(DriverDetails.CAR_NUMBER,input.getCarNumber()))
					.and(QueryBuilder.eq(DriverDetails.LOCATION, input.getCarLocation()))
					.and(QueryBuilder.eq(DriverDetails.CAR_TYPE,input.getCarType()));
			ResultSetFuture results=cassQuery.executeFuture(get);
			System.out.println(get.toString());
			List<DriverDetailsPojo> details= processCarEntity(results);
			input.setCarAgency(details.get(0).getAgencyName());
			input.setCarAgencyPhoneNumber(details.get(0).getAgencyPhoneNumber());
			input.setDrivePhoneNumber(details.get(0).getDriverPhoneNumber());
			input.setDriverName(details.get(0).getDriverName());
			input.setCarlicence(details.get(0).getDriverLicence());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private List<DriverDetailsPojo> processCarEntity( ResultSetFuture results )
	{
		List<DriverDetailsPojo> details = new ArrayList<DriverDetailsPojo>();
		for( Row r : results.getUninterruptibly() )
		{
			DriverDetailsPojo driverDetails=new DriverDetailsPojo();
			driverDetails.setAgencyName(r.getString(DriverDetails.AGENCY_NAME));
			driverDetails.setAgencyPhoneNumber(r.getString(DriverDetails.AGENCY_PHONE_NUMBER));
			driverDetails.setDriverLicence(r.getString(DriverDetails.DRIVER_LICENCE));
			driverDetails.setDriverName(r.getString(DriverDetails.DRIVER_NAME));
			driverDetails.setDriverPhoneNumber(r.getString(DriverDetails.DRIVER_PHONE_NUMBER));
			details.add(driverDetails);
		}
		return details;
	}
}
