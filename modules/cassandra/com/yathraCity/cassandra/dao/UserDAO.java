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
import com.yathraCity.cassandra.tables.TableNames;
import com.yathraCity.cassandra.tables.UserColumns;
import com.yathraCity.core.LoginInput;
import com.yathraCity.core.RegisterUser;
import com.yathraCity.services.config.ConfigKey;
import com.yathraCity.services.config.Configurator;

/**
 * Adding the new users
 * @author ashwing
 * param user details
 */
public class UserDAO
{

	private static Logger logger = LoggerFactory.getLogger( UserDAO.class );
	private static CassandraQuery cassQuery = null;
	private String keyspace;

	//initlizing the keyspace and session
	public UserDAO ()
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

	//adding the new users
	public boolean addUser( RegisterUser user )
	{
		boolean result = false;
		try
		{
			Statement insert = QueryBuilder.insertInto( keyspace, TableNames.USERS )
					.value( UserColumns.USER_NAME, user.getUserName() )
					.value( UserColumns.USER_ACCOUNT_ID, user.getUserAccountId() )
					.value( UserColumns.PHONE_NUMBER, user.getPhoneNumber() )
					.value( UserColumns.PASSWORD, user.getPassword() ).setConsistencyLevel( ConsistencyLevel.QUORUM )
					.enableTracing();
			System.out.println( insert.toString() );
			cassQuery.executeFuture( insert );
			result = true;
			return result;
		}
		catch( Exception e )
		{
			logger.error( "Error while inserting the user data into the cassandra db while registering user--->"
					+ e.getMessage() );
		}
		return result;

	}

	//fetching the user from the db
	public User fetchUser( LoginInput user )
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
		if( u.getPassword() ==null || u.getPassword().trim().isEmpty() 
				|| u.getUserAccountId() == null || u.getUserAccountId().trim().isEmpty()
				|| u.getUserName()== null || u.getUserName().trim().isEmpty())
			return null;
		else
			return u;
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
	}
}
