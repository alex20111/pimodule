package net.pi.pimodule.thread;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.pi.pimodule.common.Constants;
import net.pi.pimodule.common.SharedData;
import net.pi.pimodule.common.WorkerStatus;
import net.pi.pimodule.db.GardenSql;
import net.pi.pimodule.db.entity.GardenWorkerEntity;
import net.pi.pimodule.enums.ScheduleType;
import net.pi.pimodule.serial.GardenSensor;


public class GardenThread implements Runnable{
	private static final Logger logger = LogManager.getLogger(GardenThread.class);

	private GardenSql gardenSql;

	public GardenThread() {
		gardenSql = new GardenSql();

		logger.debug("Garden thread started");
	}
	@Override
	public void run() {

		logger.debug("Garden thread Executing loop");
		//load all workers
		try {
			List<GardenWorkerEntity> workers = gardenSql.getAllWorkers();


			if (!workers.isEmpty()) {
				//check if they need to be turned on.
				LocalDateTime now = LocalDateTime.now();

				@SuppressWarnings("unchecked")
				Map<Integer, WorkerStatus> wStat = (Map<Integer, WorkerStatus>) SharedData.getInstance().getSharedObject(Constants.WORKERS_STATUS);

				for(GardenWorkerEntity we : workers) {
					//convert watering date to LocalDateTime
					LocalDateTime currWateringDate = LocalDateTime.parse(we.getWateringDate(), Constants.DATE_FORMATTER);
					LocalDateTime finishedDateTime = currWateringDate.plusMinutes(we.getWateringDuration());
					WorkerStatus status = wStat.get(we.getId());
					
					logger.debug("currWateringDate: " +  currWateringDate + "    finishedDateTime:" + finishedDateTime);

					try {
						if(now.isAfter(currWateringDate) && now.isBefore(finishedDateTime)  
//								&& !status.isDoNotWater()
								&& !status.isWatering() && !status.isScheduleWatering()) {
							
							
							logger.debug("Watering for worker: " + we.getName());
							new GardenSensor(we).turnOnWater();
							status.setScheduleWatering(true);;
							wStat.put(we.getId(), status);
							
						}else if(now.isAfter(finishedDateTime) && status.isWatering()){
							logger.debug("Turn off watering for worker: " + we.getName());
							//if time has finished .. shut down
							new GardenSensor(we).turnOffWater();
							//reset new start date
							resetStartWateringDate(we, currWateringDate);
							
							status.setScheduleWatering(false);;
							wStat.put(we.getId(), status);
							
						}else if(now.isAfter(finishedDateTime)) {
							
							LocalDateTime newDate = now.withHour(currWateringDate.getHour()).withMinute(currWateringDate.getMinute());
							
							logger.debug("Now is after finising date " + we.getName() + " new date: " + newDate);
							
							resetStartWateringDate(we, newDate);
							status.setScheduleWatering(false);;
							wStat.put(we.getId(), status);
						}
					}catch(Exception ex) {
						logger.error("Error processing sensor in Garden Thread: ", ex);
					}
				}

			}


		} catch (Throwable e) {
			logger.error("Error in garden  thread", e);
		}

	}
	
	private void resetStartWateringDate(GardenWorkerEntity we, LocalDateTime currWateringDate ) throws ClassNotFoundException, SQLException {
		
		ScheduleType schedule = we.getScheduleType();		
		
		if (schedule == ScheduleType.DAILY) {
			LocalDateTime newWateringDate = currWateringDate.plusDays(1);
			we.setWateringDate(newWateringDate.format(Constants.DATE_FORMATTER));
			
		}else if (schedule == ScheduleType.TWO_DAYS) {
			LocalDateTime newWateringDate = currWateringDate.plusDays(2);
			we.setWateringDate(newWateringDate.format(Constants.DATE_FORMATTER));
			
		}else if (schedule == ScheduleType.THREE_DAYS) {
			LocalDateTime newWateringDate = currWateringDate.plusDays(3);
			we.setWateringDate(newWateringDate.format(Constants.DATE_FORMATTER));
			
		}else if (schedule == ScheduleType.FOUR_DAYS) {
			LocalDateTime newWateringDate = currWateringDate.plusDays(4);
			we.setWateringDate(newWateringDate.format(Constants.DATE_FORMATTER));
			
		}else if (schedule == ScheduleType.WEEK) {
			LocalDateTime newWateringDate = currWateringDate.plusDays(7);
			we.setWateringDate(newWateringDate.format(Constants.DATE_FORMATTER));
			
		}
		
		gardenSql.updateWorker(we);
		
	}
	
	



}
