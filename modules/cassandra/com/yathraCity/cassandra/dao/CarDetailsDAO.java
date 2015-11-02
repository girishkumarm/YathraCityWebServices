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
import com.yathraCity.cassandra.pojo.User;
import com.yathraCity.cassandra.session.CassandraQuery;
import com.yathraCity.cassandra.tables.CarDetailsColoumns;
import com.yathraCity.cassandra.tables.TableNames;
import com.yathraCity.cassandra.tables.UserColumns;
import com.yathraCity.core.LoginInput;
import com.yathraCity.core.RegisterCarDetails;
import com.yathraCity.core.RegisterUser;
import com.yathraCity.services.config.ConfigKey;
import com.yathraCity.services.config.Configurator;

/**
 * Registration of the car
 * @author ashwing
 * Car details
 */
public class CarDetailsDAO 
{

		private static Logger logger = LoggerFactory.getLogger( UserDAO.class );
		private static CassandraQuery cassQuery = null;
		private String keyspace;
		
		//initilization of the keyspace and the session
		public CarDetailsDAO ()
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

		//storing the car details in DB
		public boolean addCar( RegisterCarDetails car )
		{
			boolean result = false;
			try
			{
				Statement insert = QueryBuilder.insertInto( keyspace, TableNames.CAR_DETAILS )
						.value( CarDetailsColoumns.CAR_MODEL, car.getCarModel() )
						.value( CarDetailsColoumns.CAR_NAME, car.getCarName() )
						.value( CarDetailsColoumns.CAR_NUMBER, car.getCarNumber() )
						.value( CarDetailsColoumns.CITY_NAME, car.getCityName() )
						.value( CarDetailsColoumns.PHONE_NUMBER, car.getPhoneNumber() )
						.value( CarDetailsColoumns.DRIVER_LICENCE, car.getDriverLicence() )
						.value( CarDetailsColoumns.MAXIMUM_PEOPLE, car.getMaximumPeople() )
						.value( CarDetailsColoumns.MINIMUM_DISTANCE, car.getMinimumDistance() )
						.value( CarDetailsColoumns.PRICE_PERKM, car.getPricePerKm())
						.value( CarDetailsColoumns.DRIVE_NAME, car.getDriverName())
						.setConsistencyLevel( ConsistencyLevel.QUORUM )
						.enableTracing();
				System.out.println("hereeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
				System.out.println( insert.toString() );
				cassQuery.executeFuture( insert );
				result = true;
			}
			catch( Exception e )
			{
				logger.error( "Error while inserting the user data into the cassandra db while registering user--->"
						+ e.getMessage() );
			}
			return result;

		}

		/*public User fetchUser( LoginInput user )
		{
			User u = new User();
			try
			{
				Statement get = QueryBuilder.select().all().from( keyspace, TableNames.USERS )
						.where( QueryBuilder.eq( UserColumns.USER_ACCOUNT_ID, user.getUserAccountId() ) )
						.and( QueryBuilder.eq( UserColumns.PASSWORD, user.getPassword() ) )
						.setConsistencyLevel( ConsistencyLevel.QUORUM ).enableTracing();
				ResultSetFuture results = cassQuery.executeFuture( get );
				u = processEntity( results ).get( 0 );
			}
			catch( Exception e )
			{
				logger.error( "Error while inserting the user data into the cassandra db while registering user--->"
						+ e.getMessage() );
			}
			if( u != null )
				return u;
			else
				return null;
		}

		private List<User> processEntity( ResultSetFuture results )
		{
			List<User> monitorEntityList = new ArrayList<User>();
			for( Row r : results.getUninterruptibly() )
			{
				User User = new User();
				User.setUserName( r.getString( UserColumns.USER_NAME ) );
				User.setUserAccountId( r.getString( UserColumns.USER_ACCOUNT_ID ) );
				User.setPassword( r.getString( UserColumns.PASSWORD ) );
				monitorEntityList.add( User );
			}
			return monitorEntityList;
		}*/
}
