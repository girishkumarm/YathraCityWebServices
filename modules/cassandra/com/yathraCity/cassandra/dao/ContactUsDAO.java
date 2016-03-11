package com.yathraCity.cassandra.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.yathraCity.cassandra.session.CassandraQuery;
import com.yathraCity.cassandra.tables.ContactUsColumns;
import com.yathraCity.cassandra.tables.TableNames;
import com.yathraCity.core.ContactUs;
import com.yathraCity.services.config.ConfigKey;
import com.yathraCity.services.config.Configurator;

public class ContactUsDAO {

	private static Logger logger = LoggerFactory.getLogger(ContactUsDAO.class);
	private static CassandraQuery cassQuery = null;
	private String keyspace;

	// initlization of the keyspace and session
	public ContactUsDAO()
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

	public boolean storeMessage( ContactUs contact )
	{
		boolean result = false;
		try
		{
			Statement store = QueryBuilder.insertInto(keyspace, TableNames.CONTACT)
					.value(ContactUsColumns.EMAIL_ID, contact.getEmailId())
					.value(ContactUsColumns.MESSAGE, contact.getMessage())
					.value(ContactUsColumns.PHONE_NUMBER, contact.getPhoneNumber())
					.value(ContactUsColumns.NAME, contact.getName());
			cassQuery.executeFuture(store);
			result = true;
		}
		catch( Exception e )
		{
			logger.error("error is caused in the storing in the db" + e.getMessage());
		}
		return result;
	}
}
