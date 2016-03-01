package com.yathraCity.cassandra.session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datastax.driver.core.ResultSetFuture;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.exceptions.InvalidQueryException;
import com.datastax.driver.core.exceptions.NoHostAvailableException;
import com.datastax.driver.core.exceptions.QueryExecutionException;
import com.datastax.driver.core.exceptions.QueryValidationException;

public class CassandraQuery {

	private static Logger logger = LoggerFactory.getLogger(CassandraQuery.class);

	public ResultSetFuture executeFuture( Statement query ) throws Exception
	{
		ResultSetFuture results = null;
		Session session = null;
		try
		{
			session = CassandraSession.getInstance().getKeyspaceSession();
			results = session.executeAsync(query.toString());
		}
		catch( InvalidQueryException e )
		{
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		catch( NoHostAvailableException e )
		{
			logger.error("No host in the cluster can be contacted to execute the query.");
			throw e;
		}
		catch( QueryExecutionException e )
		{
			logger.error("An exception was thrown by Cassandra because it cannot "
					+ "successfully execute the query with the specified consistency level.");
			throw e;
		}
		catch( QueryValidationException e )
		{
			logger.error("The query is not valid, for example, incorrect syntax." + e.getMessage());
			e.printStackTrace();
			throw e;
		}
		catch( IllegalStateException e )
		{
			logger.error("Illegal Sate exception");
			throw e;
		}
		catch( Exception e )
		{
			logger.error("Exception occured while executing the query in emailDAO " + e.getMessage());
			throw e;
		}

		return results;
	}
}
