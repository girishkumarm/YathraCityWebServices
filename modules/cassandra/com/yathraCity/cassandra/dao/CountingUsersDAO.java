package com.yathraCity.cassandra.dao;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.datastax.driver.core.ResultSetFuture;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.yathraCity.cassandra.session.CassandraQuery;
import com.yathraCity.cassandra.tables.NumberOfUsers;
import com.yathraCity.cassandra.tables.TableNames;
import com.yathraCity.core.TotalNumberOfUsers;
import com.yathraCity.services.config.ConfigKey;
import com.yathraCity.services.config.Configurator;

public class CountingUsersDAO 
{

	private static Logger logger = LoggerFactory.getLogger( UserDAO.class );
	private static CassandraQuery cassQuery = null;
	private String keyspace;

	public CountingUsersDAO()
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
		Statement update=QueryBuilder.update(keyspace,TableNames.COUNT_VIEWS )
				.with(QueryBuilder.incr(NumberOfUsers.COUNTER_VALUE))
				.where(QueryBuilder.eq(NumberOfUsers.URL_NAME,"tabcars.in"));
		cassQuery.executeFuture( update );
	}

    public int getNumberOfUsers() throws Exception
    {
    	Statement get=QueryBuilder.select().from(null, TableNames.COUNT_VIEWS)
    			.where(QueryBuilder.eq(NumberOfUsers.URL_NAME, "tabcars.in"));
    	ResultSetFuture result=cassQuery.executeFuture(get);
    	List<TotalNumberOfUsers> users=process(result);
    	return users.get(1).getCount();
    }
    
    private List<TotalNumberOfUsers> process(ResultSetFuture result)
    {
    	List<TotalNumberOfUsers> users=new ArrayList<TotalNumberOfUsers>();
    	for( Row r: result.getUninterruptibly())
    	{
    		TotalNumberOfUsers totalCount=new TotalNumberOfUsers();
    		totalCount.setCount(Integer.valueOf(r.getString(NumberOfUsers.COUNTER_VALUE)));
    		users.add(totalCount);
    	}
    	return users;
    }
}
