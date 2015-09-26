package com.yathraCity.cassandra.session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.HostDistance;
import com.datastax.driver.core.PoolingOptions;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.SocketOptions;
import com.datastax.driver.core.exceptions.AuthenticationException;
import com.datastax.driver.core.exceptions.NoHostAvailableException;
import com.datastax.driver.core.policies.ConstantReconnectionPolicy;
import com.datastax.driver.core.policies.DowngradingConsistencyRetryPolicy;
import com.yathraCity.services.config.ConfigKey;
import com.yathraCity.services.config.Configurator;

public class CassandraSession {

	static Logger logger = LoggerFactory.getLogger(CassandraSession.class);
	private static CassandraSession instance;
	private Cluster cluster = null;
	private Session keyspaceSession = null, clusterSession = null;

	private CassandraSession()
	{
	}

	public synchronized static CassandraSession getInstance()
	{
		if( instance == null )
		{
			instance = new CassandraSession();
		}
		return instance;
	}

	public synchronized Session getClusterSession() throws Exception
	{
		if( cluster == null )
			connectKeySpaceSession();
		if( clusterSession == null )
			clusterSession = cluster.connect();
		return clusterSession;
	}

	public synchronized Session getKeyspaceSession() throws Exception
	{
		if( cluster == null )
		{
			connectKeySpaceSession();
		}
		if( keyspaceSession == null )
		{
			System.out.println(Configurator.getInstance().getProperty(ConfigKey.CASSANDRA_Keyspace));
			if( isClusterClosed() )
				keyspaceSession = cluster.connect(Configurator.getInstance().getProperty(ConfigKey.CASSANDRA_Keyspace));
		}
		if( isClusterClosed() && this.keyspaceSession.isClosed() )
			isKeySpaceSessionClosed();

		return keyspaceSession;
	}

	public void closeKeyspaceSession() throws IllegalAccessException
	{
		if( keyspaceSession != null )
		{
			keyspaceSession.close();
		}
	}

	private boolean isClusterClosed() throws Exception
	{

		if( cluster != null )
		{
			if( cluster.isClosed() )
			{
				connectKeySpaceSession();
			}
		}
		return true;
	}

	private boolean isKeySpaceSessionClosed() throws IllegalAccessException
	{
		if( keyspaceSession != null )
		{
			if( keyspaceSession.isClosed() )
			{
				keyspaceSession = cluster.connect(Configurator.getInstance().getProperty(ConfigKey.CASSANDRA_Keyspace));
			}
		}
		return true;
	}

	public void closeClusterSession() throws IllegalAccessException
	{
		if( clusterSession != null )
		{
			clusterSession.close();
		}
	}

	private void connectKeySpaceSession() throws Exception
	{
		try
		{
			String coreConnections = "2";
			String maxCoreConnections = "400";
			String maxRqstsPerCntn = "128";
			String minRqstsPerCntn = "2";
			String sockeTimeOutString = Configurator.getInstance().getProperty(ConfigKey.CASSANDRA_SOCKET_TIME_OUT);
			int sockeTimeOut = Integer.parseInt(sockeTimeOutString);
			String sockeAlive = Configurator.getInstance().getProperty(ConfigKey.CASSANDRA_KEEP_ALIVE);
			boolean keepAlive = Boolean.parseBoolean(sockeAlive);
			PoolingOptions poolingOpts = new PoolingOptions();
			poolingOpts.setCoreConnectionsPerHost(HostDistance.REMOTE, Integer.parseInt(coreConnections));
			poolingOpts.setMaxConnectionsPerHost(HostDistance.REMOTE, Integer.parseInt(maxCoreConnections));
			poolingOpts.setMaxSimultaneousRequestsPerConnectionThreshold(HostDistance.REMOTE,
					Integer.parseInt(maxRqstsPerCntn));
			poolingOpts.setMinSimultaneousRequestsPerConnectionThreshold(HostDistance.REMOTE,
					Integer.parseInt(minRqstsPerCntn));
			SocketOptions socketOptions = new SocketOptions();
			socketOptions.setConnectTimeoutMillis(sockeTimeOut);
			socketOptions.setKeepAlive(keepAlive);

			String userName = Configurator.getInstance().getProperty(ConfigKey.CASSANDRA_USERNAME);
			String password = Configurator.getInstance().getProperty(ConfigKey.CASSANDRA_PASSWORD);

			String listOfNodes = Configurator.getInstance().getProperty(ConfigKey.CASSANDRA_Node);
			System.out.println("nodesssssssssssssss " + listOfNodes);
			String[] nodes = listOfNodes.split(",");
			cluster = Cluster.builder().withPoolingOptions(poolingOpts).addContactPoints(nodes)
					.withRetryPolicy(DowngradingConsistencyRetryPolicy.INSTANCE)
					.withReconnectionPolicy(new ConstantReconnectionPolicy(100L)).withCredentials(userName, password)
					.build();

		}
		catch( AuthenticationException e )
		{
			logger.error("Provided credentials are invalid!");
		}
		catch( NoHostAvailableException e )
		{
			logger.error("No host in the {} cluster can be contacted to execute the query.");
			throw e;
		}
		catch( Exception e )
		{
			logger.error("exception while getting the keyspace session " + e.getMessage());
			throw e;
		}
	}
}
