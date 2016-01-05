package com.yathraCity.cassandra.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.yathraCity.cassandra.session.CassandraQuery;
import com.yathraCity.cassandra.tables.CoupenDetailsColumn;
import com.yathraCity.cassandra.tables.TableNames;
import com.yathraCity.services.config.ConfigKey;
import com.yathraCity.services.config.Configurator;

/**
 * Deleting the coupons
 * @author ashwing
 *	param couponName
 */
public class DeleteCoupensDAO 
{
	private static Logger logger = LoggerFactory.getLogger(UserDAO.class);
	private static CassandraQuery cassQuery = null;
	private String keyspace;

	//initlizing the keyspace and session 
	public DeleteCoupensDAO()
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

	//deleting the coupons in the DB
	public boolean deleteCoupens(String coupenName)
	{
		boolean msg=false;
		try
		{
			Statement deleteCoupens=QueryBuilder.delete().all().from(keyspace, TableNames.COUPEN_DETAILS)
					.where(QueryBuilder.eq(CoupenDetailsColumn.COUPEN_NAME, coupenName));
			cassQuery.executeFuture(deleteCoupens);
			msg=true;
			return msg;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return msg;
		}
	}
}
