package com.yathraCity.cassandra.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.yathraCity.cassandra.session.CassandraQuery;
import com.yathraCity.cassandra.tables.NumberOfUserse;
import com.yathraCity.cassandra.tables.TableNames;
import com.yathraCity.services.config.ConfigKey;
import com.yathraCity.services.config.Configurator;

public class CountingDAO 
{

	private static Logger logger = LoggerFactory.getLogger( UserDAO.class );
	private static CassandraQuery cassQuery = null;
	private String keyspace;

	public CountingDAO()
	{
		
		try
		{
			cassQuery=new CassandraQuery();
			keyspace=Configurator.getInstance().getProperty(ConfigKey.CASSANDRA_Keyspace);
		}
		catch( Exception  e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void countUsers() throws Exception
	{
		Statement update=QueryBuilder.update(keyspace,TableNames.COUNTER_TABLE )
				.with(QueryBuilder.incr(NumberOfUserse.COUNTER_VALUE))
				.where(QueryBuilder.eq(NumberOfUserse.URL,"xyz"));
		cassQuery.executeFuture( update );
	}

	/*private Clause eq( String uRL, String string )
	{
		// TODO Auto-generated method stub
		return null;
	}*/

/*	private Assignment set( String cOUNT, Assignment incr )
	{
		// TODO Auto-generated method stub
		return null;
	}*/

	
}
