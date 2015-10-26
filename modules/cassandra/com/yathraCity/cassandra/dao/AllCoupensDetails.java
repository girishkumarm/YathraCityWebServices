package com.yathraCity.cassandra.dao;

import java.util.ArrayList;
import java.util.List;
import com.datastax.driver.core.ResultSetFuture;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.yathraCity.cassandra.pojo.CoupensPojo;
import com.yathraCity.cassandra.session.CassandraQuery;
import com.yathraCity.cassandra.tables.CoupenDetailsColumn;
import com.yathraCity.cassandra.tables.TableNames;
import com.yathraCity.services.config.ConfigKey;
import com.yathraCity.services.config.Configurator;

public class AllCoupensDetails 
{
	private static CassandraQuery cassQuery = null;
	private String keyspace;
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
	
	public List<CoupensPojo> listOfAllCoupenDetails()
	{
		List<CoupensPojo> allCoupens=new ArrayList<CoupensPojo>();
		Statement getCoupens=QueryBuilder.select().all().from(keyspace, TableNames.COUPEN_DETAILS);
		ResultSetFuture result=null;
		try
		{
			result = cassQuery.executeFuture(getCoupens);
		}
		catch( Exception e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		allCoupens=getAllCoupens(result);
		return allCoupens;
	}
	
	public static List<CoupensPojo> getAllCoupens(ResultSetFuture result)
	{
		List<CoupensPojo> myAllCoupen=new ArrayList<CoupensPojo>();
		for(Row r:result.getUninterruptibly())
		{
			CoupensPojo coupens=new CoupensPojo();
			System.out.println("inserting");
			coupens.setCoupenName(r.getString(CoupenDetailsColumn.COUPEN_NAME));
			System.out.println(r.getString(CoupenDetailsColumn.COUPEN_NAME));
			coupens.setValidFrom(r.getString(CoupenDetailsColumn.VALIDITY_FROM));
			System.out.println(r.getString(CoupenDetailsColumn.VALIDITY_FROM));
			coupens.setValidTo(r.getString(CoupenDetailsColumn.VALID_TO));
			System.out.println(r.getString(CoupenDetailsColumn.VALID_TO));
			System.out.println("outing");
			myAllCoupen.add(coupens);
		}
		return myAllCoupen;
	}
}
