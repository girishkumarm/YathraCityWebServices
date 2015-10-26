package com.yathraCity.cassandra.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.yathraCity.cassandra.session.CassandraQuery;
import com.yathraCity.cassandra.tables.CoupenDetailsColumn;
import com.yathraCity.cassandra.tables.TableNames;
import com.yathraCity.services.config.ConfigKey;
import com.yathraCity.services.config.Configurator;

public class DeleteCoupensExpiredDAO 
{
	private static Logger logger = LoggerFactory.getLogger(UserDAO.class);
	private static CassandraQuery cassQuery = null;
	private String keyspace;

	public DeleteCoupensExpiredDAO()
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

	public boolean deleteCoupens()
	{
		boolean msg=false;
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		System.out.println(dateFormat.format(date));
		
		String currentDate=dateFormat.format(date);
		
		try
		{
			Statement deleteCoupens=QueryBuilder.delete().all().from(keyspace, TableNames.COUPEN_DETAILS)
					.where(QueryBuilder.eq(CoupenDetailsColumn.VALID_TO, currentDate));
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
