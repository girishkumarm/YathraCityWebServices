package com.yathraCity.cassandra.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.yathraCity.cassandra.session.CassandraQuery;
import com.yathraCity.cassandra.tables.CoupenDetailsColumn;
import com.yathraCity.cassandra.tables.TableNames;
import com.yathraCity.core.CoupenDeails;
import com.yathraCity.services.config.ConfigKey;
import com.yathraCity.services.config.Configurator;

public class AddCoupensDetailsDAO {

	private static Logger logger = LoggerFactory.getLogger(UserDAO.class);
	private static CassandraQuery cassQuery = null;
	private String keyspace;

	public AddCoupensDetailsDAO()
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

	public boolean addCoupens(CoupenDeails myCoupen)
	{
		boolean coupen=false;
		
		try
		{
		Statement addingCoupen=QueryBuilder.insertInto(keyspace,TableNames.COUPEN_DETAILS)
				.value(CoupenDetailsColumn.COUPEN_NAME,myCoupen.getCoupen())
				.value(CoupenDetailsColumn.VALIDITY_FROM, myCoupen.getFromDate())
				.value(CoupenDetailsColumn.VALID_TO, myCoupen.getToDate());
		cassQuery.executeFuture(addingCoupen);
		coupen=true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		if(coupen==true)
		{
			return coupen;
		}
		else
		{
			return false;
		}
	}

}
