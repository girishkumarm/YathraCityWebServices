package com.yathraCity.services.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Configurator {

	Logger logger = LoggerFactory.getLogger(Configurator.class);
	private HashMap<String, String> properties;
	private Properties storeProperties;

	public Properties getStoreProperties()
	{
		return storeProperties;
	}

	public void setStoreProperties( Properties storeProperties )
	{
		this.storeProperties = storeProperties;
	}

	private static Configurator instance;

	public static Configurator getInstance()
	{
		if( instance == null )
		{
			instance = new Configurator();
		}
		return instance;
	}

	private Configurator()
	{
	}

	public String getProperty( String key ) throws IllegalAccessException
	{
		if( properties == null )
		{
			throw new IllegalAccessException("Config not initialised.");
		}
		return properties.get(key);
	}

	public boolean isConfigured()
	{
		return (properties != null);
	}

	@SuppressWarnings( "rawtypes" )
	public void configure( String filePath ) throws FileNotFoundException, IOException
	{
		if( isConfigured() )
		{
			return;
		}
		logger.info("Using config - " + filePath);
		Properties props = new Properties();
		properties = new HashMap<String, String>();
		File f = new File(filePath);
		if( !f.exists() )
		{
			throw new FileNotFoundException("Config file " + filePath + " not found.");
		}
		props.load(new FileInputStream(new File(filePath)));
		this.setStoreProperties(props);
		for( Entry e : props.entrySet() )
		{
			properties.put((String) e.getKey(), (String) e.getValue());
		}
	}

	public HashMap<String, String> getProperties()
	{
		return properties;
	}
}
