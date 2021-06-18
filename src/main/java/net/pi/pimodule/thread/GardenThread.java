package net.pi.pimodule.thread;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.pi.pimodule.db.SensorLocSql;
import net.pi.pimodule.db.SensorSql;


public class GardenThread implements Runnable{
	private static final Logger logger = LogManager.getLogger(GardenThread.class);
	
	private SensorSql sensorSql;
	private SensorLocSql locSql;
	
	public GardenThread() {
		sensorSql = new SensorSql();
		locSql = new SensorLocSql();
		logger.debug("Garden thread started");
	}
	@Override
	public void run() {
		
//		locSql..
		
	}

}
