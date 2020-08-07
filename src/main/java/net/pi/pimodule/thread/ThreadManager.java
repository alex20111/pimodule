package net.pi.pimodule.thread;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.pi.pimodule.db.TempSql;


public class ThreadManager {

	private static final Logger logger = LogManager.getLogger(ThreadManager.class);
	
	private ScheduledExecutorService scheduler;

	private static ThreadManager threadManager;

	public int wthErrorCount = 0;

	private ThreadManager(){		
		scheduler = Executors.newScheduledThreadPool(3);

	}
	public static ThreadManager getInstance(){
		if (threadManager == null){
			synchronized (ThreadManager.class) {
				if (threadManager == null){
					threadManager = new ThreadManager();
				}
			}
		}
		return threadManager;
	}


	public void startTemperature(long sampleRateMillis) throws ClassNotFoundException, IOException, SQLException {
		
		new TempSql().createTable();
		
		TemperatureThread t = new TemperatureThread();
		
		scheduler.scheduleAtFixedRate(t, 0, sampleRateMillis, TimeUnit.MILLISECONDS);
	}
	
	public void startWebsiteMonitoring(long sampleRateMillis) throws ClassNotFoundException, IOException, SQLException {
		logger.info("startWebsiteMonitoring - Sampling rate: " + sampleRateMillis);
		
		scheduler.scheduleAtFixedRate(new CheckWebsite(), 0, sampleRateMillis, TimeUnit.MILLISECONDS);
	}
	
}
