package net.pi.pimodule.serial;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.pi.pimodule.common.Constants;
import net.pi.pimodule.common.SharedData;
import net.pi.pimodule.db.SensorSql;
import net.pi.pimodule.db.entity.SensorEntity;
import net.pi.pimodule.enums.SensorType;
import net.pi.pimodule.service.model.Message;

//Sensor with ID example:
//Sensor: <ip091>
//Master: <ip091,1616162330,65,11,4,3600> 
//
//
//Sensor without ID example:
//Sensor: <spAA5>
//Master: <spAA5,091>
//Sensor: <sp091ok>
//Master: <ip091,1616162330,65,11,4,3600> //only sent when the sensor config is done.
public abstract class SensorBase implements Command{

	private static final Logger logger = LogManager.getLogger(SensorBase.class);
	protected final static BlockingQueue<Boolean> sensorReplied =  new ArrayBlockingQueue<>(1);

	private String command = "";
	private boolean waitForReply = true;

	private SensorType type;
	private String sensorId = "";
	
	private Object monitor = new Object();



	protected void handleStartCommand(SensorData sensorData) throws ClassNotFoundException, SQLException, IllegalStateException, IOException {

		logger.debug("handeling start command: " + sensorData);

		String tempId = sensorData.getSensorId();	

		if (tempId.startsWith("AA")) {
			logger.debug("handleStartCommand AA");
			//generate a new ID and send the information
			StringBuilder cmd = new StringBuilder(START_MARKER);

			String nextId = new SensorSql().getNextSensorId(sensorData.getSensorTypeEnum());
			cmd.append(START_CMD + sensorData.getSensorTypeEnum().getType() + tempId + "," + nextId  );
			cmd.append(END_MARKER);						

			SerialHandler.getInstance().sendTeensyStringCommand(cmd.toString());

		}
		else if(sensorData.getData().contains(OK_REPLY)) {
			//add sensor to DB with new Sensor ID	
			logger.debug("handleStartCommand: Contains ok ");
			//verify if it exist before
			SensorSql sql = new SensorSql();
			SensorEntity sensor = sql.findSensor(sensorData.getSensorTypeEnum(), tempId);
			if(sensor == null) { //new sensor add initial default to the DB
				SensorEntity entity = new SensorEntity();
				entity.setLastTransmit(new Date());
				//				entity.setLastUpdated(new Date()); // don't need it now since it's not being updated but added.
				entity.setSensorId(tempId);
				entity.setSensorType(sensorData.getSensorTypeEnum());
				sql.addSensor(entity);

			}else {
				sensor.setErrorField("ERROR: sensor already exist and recieved an OK, the sensor should not exist at this point. DATA: " + sensorData.getFullData());
				sql.updateSensor(sensor);
				throw new SQLException("Sensor: " + tempId + " Already exist and recieved ok");
			}
		}


	}

	protected void handleInitCommand(SensorData sensorData) throws ClassNotFoundException, SQLException, IllegalStateException, IOException {
		//Sensor with ID example:
		//Sensor: <ip091>
		//Master: <ip091,1616162330,65,11,4,3600> 
		//Sensor: <ip091ok> 
		//check if sensor is configured before sending init data..

		logger.debug("handleInitCommand");

		String sensorId = sensorData.getSensorId();

		SensorSql sql = new SensorSql();
		SensorEntity sensor = sql.findSensor(sensorData.getSensorTypeEnum(), sensorId);

		if (sensor != null && sensor.isConfigured()  ) {
			logger.debug("isConfigured, sensor: " + sensor);
			if(sensorData.getData().contains(OK_REPLY)) {
				//sensor received the configuration and replied that it was ok
				sensor.setLastTransmit(new Date());
				sensor.setErrorField("");
				sql.updateSensor(sensor);
			}else {
				//sensor sending init Id and waiting for the master to send the configuration
				String cmd = formatInitString(sensor);
				logger.debug("Sending string command: " + cmd);
				SerialHandler.getInstance().sendTeensyStringCommand(cmd);
			}
		}else if(sensor != null && !sensor.isConfigured() && sensorData.getData().contains(OK_REPLY)){ //sent when new sensor is added and then user manually configure it.
			logger.debug("Not configured and received confirmation, update: " + sensor);
			//if sensor is not configured and received OK
			Date now = new Date();
			sensor.setConfigured(true);
			sensor.setLastUpdated(now);  //here because we updated the configuration, we need to also update the lastUpdate date.
			sensor.setLastTransmit(now);
			sensor.setErrorField("");
			sql.updateSensor(sensor);

		
			sensorReplied.offer(true);

		}else if(sensor != null && !sensor.isConfigured() ){

			if (sensor.getLastUpdated() == null) {
				//we received a init for the sensor but not configured yet.. update DB and wait 10 min;
				//this can be the case if a sensor shutdown and the user turn it back on.  
				logger.debug("Init recieved but sensor not configured: " +sensor);
				sensor.setLastTransmit(new Date());
				sensor.setErrorField("WARN: Waiting for initialization");
				sql.updateSensor(sensor);
			}else if (sensor.getLastUpdated() != null){
				//sensor sending init Id and waiting for the master to send the configuration
				String cmd = formatInitString(sensor);
				logger.debug("sensor not configured but has been registered. sending string command: " + cmd);
				SerialHandler.getInstance().sendTeensyStringCommand(cmd);
			}

		}		
		else if (sensor == null){
			//problem, //re-ini using START_CMD //TODO
			logger.info("Recieved an ID that does not exist on the DB, Reset sensor. ID: " + sensorId); //TODO notify sensor screen..(web)
			Message msg = new Message("ERROR - Unidentified sensor", "Sensor " + sensorData.getSensorTypeEnum().getType() + sensorData.getSensorId() + " has asked to registered but do not exist on the database.");
			SharedData.getInstance().addToMessage(msg);
		}

	}

	protected String getDateInSeconds() {
		int compensate = 1000*60*60*4;		
		long dateInSec = ( new Date().getTime() - (compensate) ) / 1000 ;
		return  String.valueOf(dateInSec);
	}

	protected String formatInitString(SensorEntity sensor) {

		//<ip091,1616162330,65,11,4,3600> 

		StringBuilder sb = new StringBuilder(START_MARKER);
		sb.append(INIT_CMD);
		sb.append(sensor.getSensorType().getType());
		sb.append(sensor.getSensorId());
		sb.append(DELIMITER);
		sb.append(getDateInSeconds());
		sb.append(DELIMITER);
		sb.append(sensor.getTransFreq());
		sb.append(DELIMITER);
		sb.append(sensor.getPwSaveStart());
		sb.append(DELIMITER);
		sb.append(sensor.getPwSaveEnd());
		sb.append(DELIMITER);
		sb.append(sensor.getPwSaveTransFreq());
		sb.append(END_MARKER);

		return sb.toString();
	}

	protected void awaitSensorReply(SensorEntity sensor, SensorSql sql) {

		new Thread(new Runnable() {

			@Override
			public void run() {
				//send new update
				boolean reInitSuccess = sendInitCommand(sensor).go();
				//if we have a success, the DB will already be updated..
				if (!reInitSuccess) {
					sensor.setErrorField("ERROR: no reply when trying to re-init the sensor. Re-try");
					try {
						sql.updateSensor(sensor);
					} catch (ClassNotFoundException | SQLException e) {
						Message msg = new Message("ERROR - Update", "Sensor " + sensor.getSensorType().getType() + sensor.getSensorId() + " update error: " + e.getMessage() ); 
						SharedData.getInstance().addToMessage(msg);
						logger.error("awaitSensorReply: " , e);
					}
				}				
			}			
		}).start();

	}

	public SensorBase sendInitCommand(SensorEntity sensor) {
		//calculate date in seconds, compensate for eastern time (-4)
		type = sensor.getSensorType(); // used in command GO
		sensorId = sensor.getSensorId();
		command = formatInitString(sensor);
		waitForReply = true;
		return this;
	}

	public SensorBase sendOk(SensorEntity sensor) {
		type = sensor.getSensorType();
		sensorId = sensor.getSensorId();
		command = START_MARKER + OK_CMD + type.getType() + sensor.getSensorId() + END_MARKER;
		waitForReply = false;
		return this;
	}

	public boolean go() {
		Boolean success = true;
		try {

			SerialHandler.getInstance().sendTeensyStringCommand(command);

			//emtpy the queue if anything is added
			
			
			if (waitForReply) {
				if (!sensorReplied.isEmpty()) {
					sensorReplied.clear();
				}
				success = sensorReplied.poll(4000, TimeUnit.MILLISECONDS);

				if (success == null) {
					logger.debug("Timeout, null returned");
					return false;
				}
			}

		} catch (IllegalStateException | IOException | InterruptedException  e) {
			logger.error("Error in GO() ",e);
			SensorSql sql = new SensorSql();
			try {
				SensorEntity sensorEntity = sql.findSensor(type, sensorId);
				sensorEntity.setErrorField("ERROR: Error while tring to talk to the sensor, Logs. Error: " + e.getMessage());
				sql.updateSensor(sensorEntity);
			} catch (ClassNotFoundException | SQLException e1) {
				logger.error("Error updating entity ",e);
			}

			success = false;
		}
		return success;
	}


}
