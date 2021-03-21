package net.pi.pimodule.serial;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.pi.pimodule.db.SensorEntity;
import net.pi.pimodule.db.SensorSql;

//Sensor with ID example:
//Sensor: <ip091>
//Master: <ip091,1616162330,65,11,4,3600> 
//
//
//Sensor without ID example:
//Sensor: <spAA5>
//Master: <spAA5,091>
//Sensor: <sp091ok>
//Master: <ip091,1616162330,65,11,4,3600> 
public abstract class SensorBase implements Command{
	
	private static final Logger logger = LogManager.getLogger(SensorBase.class);
	
	
	protected void handleStartCommand(SensorData sensorData) throws ClassNotFoundException, SQLException, IllegalStateException, IOException {
		
		logger.debug("handeling start command: " + sensorData);
		
		String tempId = sensorData.getSensorId();	
		
		if (tempId.startsWith("AA")) {
			logger.debug("handleStartCommand AA");
			//generate a new ID and send the information
			StringBuilder cmd = new StringBuilder(START_MARKER);
			String nextId = new SensorSql().getNextSensorId(sensorData.getType());
			cmd.append(START_CMD + sensorData.getType() + tempId + "," + nextId  );
			cmd.append(END_MARKER);						
			
			SerialHandler.getInstance().sendTeensyStringCommand(cmd.toString());
			
		}else if(sensorData.getData().contains("ok")) {
			//add sensor to DB with new Sensor ID	
			logger.debug("handleStartCommand: Contains ok ");
			//verify if it exist before
			SensorSql sql = new SensorSql();
			SensorEntity sensor = sql.findSensor(sensorData.getType(), tempId);
			if(sensor == null) {
				SensorEntity entity = new SensorEntity();
				entity.setLastTransmit(new Date());
				entity.setLastUpdated(new Date());
				entity.setSensorId(tempId);
				entity.setSensorType(sensorData.getType());
				sql.addSensor(entity);
				
			}else {
				throw new SQLException("Sensor: " + tempId + " Already exist");
			}
		}
		
		
	}
	
	protected void handleInitCommand(SensorData sensorData) throws ClassNotFoundException, SQLException, IllegalStateException, IOException {
		//Sensor with ID example:
		//Sensor: <ip091>
		//Master: <ip091,1616162330,65,11,4,3600> 
		//check if sensor is configured before sending init data..
		
		logger.debug("handleInitCommand");
		
		String sensorId = sensorData.getSensorId();
		
		SensorSql sql = new SensorSql();
		SensorEntity sensor = sql.findSensor(sensorData.getType(), sensorId);
		
		if (sensor != null && sensor.isConfigured()) {
			logger.debug("isConfigured, sending init data: " + sensor);
			String cmd = formatInitString(sensor);
			logger.debug("Sending string command: " + cmd);
			SerialHandler.getInstance().sendTeensyStringCommand(cmd);
			
		}else if (sensor == null){
			//problem, //re-ini using START_CMD //TODO
			logger.info("Recieved an ID that does not exist on the DB, Reset sensor. ID: " + sensorId);
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

}
