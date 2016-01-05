package com.yathraCity.cassandra.dao;

import com.datastax.driver.core.Statement;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.yathraCity.cassandra.session.CassandraQuery;
import com.yathraCity.cassandra.tables.CoupenDetailsColumn;
import com.yathraCity.cassandra.tables.TableNames;
import com.yathraCity.core.CoupenDeails;
import com.yathraCity.services.config.ConfigKey;
import com.yathraCity.services.config.Configurator;

/**
 * Update coupon details
 * @author ashwing
 * param coupon details
 */
public class UpdateCoupenDAO 
{
	private static CassandraQuery cassQuery = null;
	private String keyspace;
	//initlization of the keyspace and session
	public UpdateCoupenDAO()
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
	
	public boolean updateCoupensMeth(CoupenDeails coupenDetails)
	{
		boolean msg=false;
		//query to update the coupon details
		try
		{
		Statement update=QueryBuilder.update(keyspace, TableNames.COUPEN_DETAILS)
				.with(QueryBuilder.set(CoupenDetailsColumn.VALIDITY_FROM, coupenDetails.getFromDate()))
				.and(QueryBuilder.set(CoupenDetailsColumn.VALID_TO, coupenDetails.getToDate()))
				.where(QueryBuilder.eq(CoupenDetailsColumn.COUPEN_NAME, coupenDetails.getCoupen()));
		cassQuery.executeFuture(update);
		msg=true;
		return msg;
		
		}catch(Exception e)
		{
			e.printStackTrace();
			return msg;
		}
		
	}
}
