package com.yathraCity.cassandra.dao;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.datastax.driver.core.ResultSetFuture;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.yathraCity.cassandra.pojo.Coupon;
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
public class CouponsDAO {

	private static Logger logger = LoggerFactory.getLogger(CouponsDAO.class);
	private static CassandraQuery cassQuery = null;
	private String keyspace;

	// initlizing the session and keyspace
	public CouponsDAO()
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
			logger.error("Error while adding the cupon" + e.getMessage());
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

	public boolean updateCoupon( CouponDetails couponDetails )
	{
		boolean msg = false;
		// query to update the coupon details
		try
		{
			Statement update = QueryBuilder.update(keyspace, TableNames.COUPEN_DETAILS)
					.with(QueryBuilder.set(CoupenDetailsColumn.VALIDITY_FROM, couponDetails.getFromDate()))
					.and(QueryBuilder.set(CoupenDetailsColumn.VALID_TO, couponDetails.getToDate()))
					.where(QueryBuilder.eq(CoupenDetailsColumn.COUPEN_NAME, couponDetails.getCoupon()));
			cassQuery.executeFuture(update);
			msg = true;
			return msg;

		}
		catch( Exception e )
		{
			logger.error("Error while updating the cupon" + e.getMessage());
			return msg;
		}

	}

	// deleting the coupons in the DB
	public boolean deleteCoupens( String couponName )
	{
		boolean msg = false;
		try
		{
			Statement deleteCoupens = QueryBuilder.delete().all().from(keyspace, TableNames.COUPEN_DETAILS)
					.where(QueryBuilder.eq(CoupenDetailsColumn.COUPEN_NAME, couponName));
			cassQuery.executeFuture(deleteCoupens);
			msg = true;
			return msg;
		}
		catch( Exception e )
		{
			logger.error("Error while deleting the coupens" + e.getMessage());
			return msg;
		}
	}

	// getting all the coupons from the DB
	public List<Coupon> getListOfAllCoupenDetails() throws Exception
	{
		List<Coupon> allCoupens = new ArrayList<Coupon>();
		try
		{
			// query for getting the coupons
			Statement getCoupens = QueryBuilder.select().all().from(keyspace, TableNames.COUPEN_DETAILS);
			ResultSetFuture result = null;
			// executing the query
			result = cassQuery.executeFuture(getCoupens);

			// initlizing the list of the coupons
			allCoupens = getAllCoupens(result);
		}
		catch( IllegalAccessException e )
		{
			e.printStackTrace();
		}
		return allCoupens;
	}

	public static List<Coupon> getAllCoupens( ResultSetFuture result )
	{
		// adding the coupons to the list
		List<Coupon> myAllCoupen = new ArrayList<Coupon>();
		for( Row r : result.getUninterruptibly() )
		{
			Coupon coupens = new Coupon();
			coupens.setCoupenName(r.getString(CoupenDetailsColumn.COUPEN_NAME));
			coupens.setValidFrom(r.getString(CoupenDetailsColumn.VALIDITY_FROM));
			coupens.setValidTo(r.getString(CoupenDetailsColumn.VALID_TO));
			myAllCoupen.add(coupens);
		}
		return myAllCoupen;
	}

}
