package com.yathraCity.services.webapp.init;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.yathraCity.services.config.Configurator;

public class WebAppInit implements ServletContextListener
{

	@Override
	public void contextDestroyed( ServletContextEvent arg0 )
	{
	}

	@Override
	public void contextInitialized( ServletContextEvent arg0 )
	{
		System.out.println( "Initializing Inblox webapp..." );
		try
		{

			System.out.println( ">>>>>>>>" );
			String url = arg0.getServletContext().getRealPath( "/WEB-INF/classes/store.properties" );


			Configurator.getInstance().configure( url );

			System.out.println( "WEBAPP INIT SET UP COMPLETED>>>>>>" );
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}

	}
}