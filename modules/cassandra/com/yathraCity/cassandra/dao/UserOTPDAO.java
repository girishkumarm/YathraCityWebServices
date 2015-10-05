package com.yathraCity.cassandra.dao;


import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datastax.driver.core.ConsistencyLevel;
import com.datastax.driver.core.ResultSetFuture;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.yathraCity.cassandra.session.CassandraQuery;
import com.yathraCity.cassandra.tables.OTPColumns;
import com.yathraCity.cassandra.tables.TableNames;
import com.yathraCity.services.config.ConfigKey;
import com.yathraCity.services.config.Configurator;

public class UserOTPDAO
{
	private static Logger logger = LoggerFactory.getLogger( UserDAO.class );
	private static CassandraQuery cassQuery = null;
	private String keyspace;

	public UserOTPDAO ()
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

	public String generateOTP( String phoneNumber )
	{
		String result = null ;
		try
		{
			String otp=new Random().nextLong()+"";
			Statement insert = QueryBuilder.insertInto( keyspace, TableNames.OTP )
					.value( OTPColumns.PHONE_NUMBER, phoneNumber )
					.value( OTPColumns.OTP, otp )
					.enableTracing();
			cassQuery.executeFuture( insert );
			result = otp;
		}
		catch( Exception e )
		{
			logger.error( "Error while generating the otp for an user from database--->" + e.getMessage() );
		}
		return result;

	}

	public String fetchOtpForPhoneNumber( String phoneNumber )
	{
		String otp=null;
		try
		{
			Statement get = QueryBuilder.select().all().from( keyspace, TableNames.OTP )
					.where( QueryBuilder.eq( OTPColumns.PHONE_NUMBER, phoneNumber ) )
					.setConsistencyLevel( ConsistencyLevel.QUORUM ).enableTracing();
			ResultSetFuture results = cassQuery.executeFuture( get );
			otp = processOTP( results );
		}
		catch( Exception e )
		{
			logger.error( "Error while fetching otp for an user from database--->" + e.getMessage() );
		}
		return otp;
	}


	private String processOTP( ResultSetFuture results )
	{
		String otp=null;
		for( Row r : results.getUninterruptibly() )
		{
			otp = r.getString( OTPColumns.OTP );
		}
		return otp;
	}

}
