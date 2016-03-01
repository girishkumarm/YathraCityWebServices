package com.yathraCity.cassandra.dao;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.datastax.driver.core.ResultSetFuture;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.yathraCity.cassandra.pojo.CouponsPojo;
import com.yathraCity.cassandra.session.CassandraQuery;
import com.yathraCity.cassandra.tables.CoupenDetailsColumn;
import com.yathraCity.cassandra.tables.TableNames;
import com.yathraCity.services.config.ConfigKey;
import com.yathraCity.services.config.Configurator;

/**
 * getting all the coupons from the DB
 * @author ashwing
 * 
 */
public class AllCoupensDetails 
{
	private static Logger logger = LoggerFactory.getLogger(AllCoupensDetails.class);
	private static CassandraQuery cassQuery = null;
	private String keyspace;
	//initilizing the session and keyspace
	public AllCoupensDetails()
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
	//getting all the coupons from the DB
	public List<CouponsPojo> listOfAllCoupenDetails()
	{
		//query for getting the coupons
		List<CouponsPojo> allCoupens=new ArrayList<CouponsPojo>();
		Statement getCoupens=QueryBuilder.select().all().from(keyspace, TableNames.COUPEN_DETAILS);
		ResultSetFuture result=null;
		try
		{
			//executing the query
			result = cassQuery.executeFuture(getCoupens);
		}
		catch( Exception e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//initlizing the list of the coupons
		allCoupens=getAllCoupens(result);
		return allCoupens;
	}
	
	public static List<CouponsPojo> getAllCoupens(ResultSetFuture result)
	{
		//adding the coupons to the list
		List<CouponsPojo> myAllCoupen=new ArrayList<CouponsPojo>();
		for(Row r:result.getUninterruptibly())
		{
			CouponsPojo coupens=new CouponsPojo();
			coupens.setCoupenName(r.getString(CoupenDetailsColumn.COUPEN_NAME));
			coupens.setValidFrom(r.getString(CoupenDetailsColumn.VALIDITY_FROM));
			coupens.setValidTo(r.getString(CoupenDetailsColumn.VALID_TO));
			myAllCoupen.add(coupens);
		}
		return myAllCoupen;
	}
}
