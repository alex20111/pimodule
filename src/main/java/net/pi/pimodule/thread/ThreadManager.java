package net.pi.pimodule.thread;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.pi.pimodule.common.Constants;
import net.pi.pimodule.common.SharedData;
import net.pi.pimodule.common.WorkerStatus;
import net.pi.pimodule.db.GardenSql;
import net.pi.pimodule.db.TempSql;
import net.pi.pimodule.db.entity.GardenWorkerEntity;
import net.pi.pimodule.serial.GardenSensor;


public class ThreadManager {

	private static final Logger logger = LogManager.getLogger(ThreadManager.class);

	private ScheduledExecutorService scheduler;

	private static ThreadManager threadManager;

	public int wthErrorCount = 0;

	private ThreadManager(){		
		scheduler = Executors.newScheduledThreadPool(5);

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

	public void startCleanUpThread() {


		LocalDateTime now = LocalDateTime.now();

		LocalDateTime future = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), 23, 59, 59);

		ZonedDateTime zdtNow = ZonedDateTime.of(now, ZoneId.systemDefault());

		ZonedDateTime zdtFuture = ZonedDateTime.of(future, ZoneId.systemDefault());
		long delay = zdtFuture.toInstant().toEpochMilli() - zdtNow.toInstant().toEpochMilli();
		scheduler.scheduleAtFixedRate(new CleanUpThread(), delay, 86400000, TimeUnit.MILLISECONDS);
	}
	
	public void startGardenIrrigation() throws ClassNotFoundException, IOException, SQLException {
		logger.info("startGardenIrrigation");
	
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime plus1Min = now.plusMinutes(1).withSecond(0);
		
		long diff = ChronoUnit.SECONDS.between(now, plus1Min);
		logger.debug("startGardenIrrigation diff: " + diff);

		scheduler.scheduleAtFixedRate(new GardenThread(), diff, 60, TimeUnit.SECONDS);
	}
	
	public void pingGardenWorkersForStatus() throws ClassNotFoundException, IOException, SQLException {
		logger.info("pingGardenWorkersForStatus");
			

		scheduler.scheduleAtFixedRate(new Runnable() {
			
			@Override
			public void run() {
				//1st add an empty map into the shared data.
				Map<Integer, WorkerStatus> wStat = new HashMap<Integer, WorkerStatus>();
				SharedData.getInstance().putSharedObject(Constants.WORKERS_STATUS,wStat );

				try {

					List<GardenWorkerEntity> workers = new GardenSql().getAllWorkers();

					if (!workers.isEmpty()) {

						for(GardenWorkerEntity we : workers) {				
							new GardenSensor(we).getStatus();				
						}
					}
				}catch (Exception ex) {
					logger.error("error in getWorkersStatus" , ex);
				}
				
			}
		}, 0, 5, TimeUnit.HOURS);
	}

}
