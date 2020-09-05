package net.pi.pimodule.listener;


import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.pi.pimodule.thread.ThreadManager;


public class InitManager implements ServletContextListener    {

	private static final Logger logger = LogManager.getLogger(InitManager.class);
	private boolean runningOnPi = true;

	/* Application Startup Event */
	public void contextInitialized(ServletContextEvent ce) 
	{
		logger.debug("Context called at init");

		logger.info("Starting thread manager");

		try {
			ThreadManager tm = ThreadManager.getInstance();

			if (runningOnPi) {
				long sampleRate = 1000 * 60 * 5; // 5 min
				logger.info("Starting temperature with sample rate: " + sampleRate + " in millis. In min: " + ( (sampleRate /1000) / 60) );
				tm.startTemperature(sampleRate);
			}else {
				logger.info("RUNNING IN DEV MODE");
			}
		}catch(Exception ex) {
			ex.printStackTrace();			
		}

		logger.debug("contextInitialized end");
	}


	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub

	}

}
