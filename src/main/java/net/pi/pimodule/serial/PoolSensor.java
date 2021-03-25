package net.pi.pimodule.serial;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.pi.pimodule.db.SensorEntity;
import net.pi.pimodule.db.SensorSql;
import net.pi.pimodule.db.TempEntity;
import net.pi.pimodule.db.TempSql;
import net.pi.pimodule.enums.SensorType;

/**
 * Pool sensor.
 * DATA expected to get from sensor
 * "dpxxx,tmp,batt"   the 1st part (command, sensor type and sensorIf is formatted by the SensorData class).
 * 
 * 
 * @author ADMIN
 *
 */

public class PoolSensor extends SensorBase {

	private static final Logger logger = LogManager.getLogger(PoolSensor.class);

	private final BlockingQueue<Boolean> sensorReplied =  new ArrayBlockingQueue<>(1);
	private String command = "";

	private SensorType type;
	private String sensorId = "";

	public PoolSensor sendInitCommand(SensorEntity sensor) {
		//calculate date in seconds, compensate for eastern time (-4)
		type = sensor.getSensorType();
		sensorId = sensor.getSensorId();
		command = formatInitString(sensor);
		return this;
	}
	public PoolSensor sendOk(SensorEntity sensor) {
		type = sensor.getSensorType();
		sensorId = sensor.getSensorId();
		command = START_MARKER + OK_CMD + type.getType() + sensor.getSensorId() + END_MARKER;
		return this;
	}

	public boolean go() {
		Boolean success = false;
		try {

			SerialHandler.getInstance().sendTeensyStringCommand(command);
			success = sensorReplied.poll(4000, TimeUnit.MILLISECONDS);

			if (success == null) {
				logger.debug("Timeout, null returned");
				return false;
			}


		} catch (IllegalStateException | IOException  | InterruptedException e) {
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

	/**
	 * Command to send to attiny through Teensy.
	 */
	@Override
	public String sendCommand() {


		return "";
	}

	@Override
	public String handleDataReceived(SensorData sensorData) {

		try {	

			if (sensorData.getCommand().equals(START_CMD)) { //Sensor has no ID and we need to provide one.
				super.handleStartCommand(sensorData);

			}else if(sensorData.getCommand().equals(INIT_CMD)) {
				//send init data to sensor
				super.handleInitCommand(sensorData);
			}else if(sensorData.getCommand().startsWith(DATA_CMD)) {
				logger.debug("Data recieved from sensor: " + sensorData);
				//save data to database.. also save last transmit time.. last update time is when you update it manually. 
				SensorSql sql = new SensorSql();
				SensorEntity ent = sql.findSensor(sensorData.getSensorTypeEnum(), sensorData.getSensorId());

				if (ent != null) {

					//update temp
					TempSql tempSql= new TempSql();				

					TempEntity temp = getTemperatureData(sensorData);
					if (temp != null) {
						logger.debug("formatting and adding pool temperature data: " + temp);
						tempSql.addTemp(temp);	
					}

					ent.setErrorField("");

					if (!ent.isConfigured()) {
						//send new update
						boolean reInitSuccess = sendInitCommand(ent).go();
						//if we have a success, the DB will already be updated..
						if (!reInitSuccess) {
							ent.setErrorField("ERROR: no reply when trying to re-init the sensor. Re-try");
							sql.updateSensor(ent);
						}

					}else {
						ent.setLastTransmit(new Date());
						sendOk(ent).go();
						sql.updateSensor(ent);
					}				
				}
			}
		}catch(Exception ex) {
			logger.error("Error in handleDataReceived" , ex);
		}


		return null;
	}

	private TempEntity getTemperatureData(SensorData sensorData) {


		TempEntity temp = null;

		//split info
		String dataSplit[] = sensorData.getData().split(DELIMITER);

		if (dataSplit.length > 1) {
			temp = new TempEntity();
			temp.setBatteryLevel(dataSplit[2]);
			temp.setHumidity("");
			temp.setRecordedDate(new Date());
			temp.setRecorderName(sensorData.getSensorTypeEnum().name() + sensorData.getSensorId());
			temp.setTempC(dataSplit[1]);
		}

		return temp;
	}


	//  <dp091232-3.43> =  1 = cmd, 2= sensor type, 3-6 = id, 7 to end = data
	//	
	//	Start sequence:
	//
	//		Start (turn on power , init no ID or if button pressed down at start ( to gen a new ID) : 
	//			Sensor: no Id, generate ID with AA in fromt: AA9. Wait 2 min max for config parameters. if not sleep   SENT: <spAA5>
	//			Master: Get a AA9 ID,  it's a new Sensor without id. Generate a new one and store it on the DB. then send the ID : AA9 and the new ID after 012.   SENT: <spAA5,001>
	//					if it's a new ID, then send request to wait for init parameters.
	//			Sensor: Get Init reply with ID AA9. The sensor know that it's the one it sent. look after for the real ID and store it in EEPROM.
	//					If master request init parametes, wait for 5 min then sleep
	//			Master: Send init parameters. SENT: <ip001,1616162330,65,59,4,3600> 
	//			
	//			
	//			
	//		Start ID EXIST: 
	//			Sensor: Send it's ID to the master.. Wait 2 min max for config parameters, if not , sleep
	//			Master: Get the ID, send the init parameters.
	//			Sensor: Get parameters, start.
	//			
	//		Normal flow (when all init done)
	//			Sensor: send data with sensor id.
	//			Master: send ok with sensor id. If new init parameters , send it with the reply
	//			
	//			
	//			
	//			
	//		Sensor with ID example:
	//			Sensor: <ip091>
	//			Master: <ip091,1616162330,65,11,4,3600> 
	//		 
	//		 
	//		 Sensor without ID example:
	//			Sensor: <spAA5>
	//			Master: <spAA5,091>
	//			Sensor: <sp091ok>
	//			Master: <ip091,1616162330,65,11,4,3600> 

	// Sensor data:
	//	Sensor: <dp091232-3.43> 
	// master <op091>

}
