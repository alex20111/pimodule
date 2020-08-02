package net.pi.pimodule.thread;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import net.pi.pimodule.db.TempSql;


public class ThreadManager {

	private ScheduledExecutorService scheduler;

	private static ThreadManager threadManager;

	public int wthErrorCount = 0;

	private ThreadManager(){		
		scheduler = Executors.newScheduledThreadPool(11);

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
	
}
