package com.yathraCity.cassandra.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.yathraCity.cassandra.session.CassandraQuery;
import com.yathraCity.cassandra.tables.CoupenDetailsColumn;
import com.yathraCity.cassandra.tables.TableNames;
import com.yathraCity.core.CouponDetails;
import com.yathraCity.services.config.ConfigKey;
import com.yathraCity.services.config.Configurator;

/**
 * Adding coupons and its details in DB
 * 
 * @author ashwing
 *         param coupon details
 */
public class AddCouponsDetailsDAO {

	private static Logger logger = LoggerFactory.getLogger(UserDAO.class);
	private static CassandraQuery cassQuery = null;
	private String keyspace;

	// initlizing the session and keyspace
	public AddCouponsDetailsDAO()
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

	// adding the coupons in DB
	public boolean addCoupons( CouponDetails myCoupen )
	{
		boolean coupen = false;

		try
		{
			// query for adding the coupon in the DB
			Statement addingCoupen = QueryBuilder.insertInto(keyspace, TableNames.COUPEN_DETAILS)
					.value(CoupenDetailsColumn.COUPEN_NAME, myCoupen.getCoupon())
					.value(CoupenDetailsColumn.VALIDITY_FROM, myCoupen.getFromDate())
					.value(CoupenDetailsColumn.VALID_TO, myCoupen.getToDate());
			cassQuery.executeFuture(addingCoupen);
			coupen = true;
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		if( coupen == true )
		{
			return coupen;
		}
		else
		{
			return false;
		}
	}

}
