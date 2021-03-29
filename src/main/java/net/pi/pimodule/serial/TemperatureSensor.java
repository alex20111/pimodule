package net.pi.pimodule.serial;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.pi.pimodule.common.Constants;
import net.pi.pimodule.common.SharedData;
import net.pi.pimodule.db.SensorEntity;
import net.pi.pimodule.db.SensorSql;
import net.pi.pimodule.db.TempEntity;
import net.pi.pimodule.db.TempSql;
import net.pi.pimodule.enums.SensorType;
import net.pi.pimodule.service.model.Message;

/**
 * Temperature sensor.
 * DATA expected to get from sensor
 * "dpxxx,tmp,batt"   the 1st part (command, sensor type and sensorIf is formatted by the SensorData class).
 * 
 * 
 * @author ADMIN
 *
 */

public class TemperatureSensor extends SensorBase {

	private static final Logger logger = LogManager.getLogger(TemperatureSensor.class);

	private String command = "";
	private boolean waitForReply = true;

	private SensorType type;
	private String sensorId = "";

	public TemperatureSensor sendInitCommand(SensorEntity sensor) {
		//calculate date in seconds, compensate for eastern time (-4)
		type = sensor.getSensorType();
		sensorId = sensor.getSensorId();
		command = formatInitString(sensor);
		waitForReply = true;
		return this;
	}
	public TemperatureSensor sendOk(SensorEntity sensor) {
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

			if (waitForReply) {
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
						logger.debug("formatting and adding  temperature data: " + temp);
						tempSql.addTemp(temp);	
					}

					ent.setErrorField("");

					if (!ent.isConfigured()) {
						logger.debug("Updating sensor information" );
						awaitSensorReply(ent, sql);

					}else {
						ent.setLastTransmit(new Date());
						ent.setBattLvl(temp.getBatteryLevel());
						sendOk(ent).go();
						sql.updateSensor(ent);
					}				
				}else {
					Message msg = new Message(Constants.WARNING + " - ", "Sensor " + sensorData.getSensorTypeEnum().getType() + sensorData.getSensorId() + " not registered on the DB. Reset ");
					SharedData.getInstance().addToMessage(msg);
				}
			}
		}catch(Exception ex) {
			logger.error("Error in handleDataReceived" , ex);
		}


		return null;
	}

	private void awaitSensorReply(SensorEntity sensor, SensorSql sql) {

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

	private TempEntity getTemperatureData(SensorData sensorData) {
		//dt000,94.3,2332,4344    --> d = data, t = temp , 000 = sensor id, 94.3 = temperature, 2332 = humidity, 4344 = batt voltage. This is for SI7021.

		TempEntity temp = null;

		//split info
		String dataSplit[] = sensorData.getData().split(DELIMITER);

		if (dataSplit.length > 1) {
			temp = new TempEntity();

			String humidity = "0";
			if (dataSplit[2] != null) {
				try {
					int h = Integer.parseInt(dataSplit[2]);
					h = h/100;
					humidity = String.valueOf(h);
				}catch(NumberFormatException nfx) {

				}
			}

			String battStr = dataSplit[3];
			float fmtBatt = 0f;
			if (battStr.length() > 0) {

				int battLen = battStr.length();
				if (battLen == 4) {
					fmtBatt = Float.valueOf(battStr) / 1000;
				}else if (battLen == 3) {
					fmtBatt = Float.valueOf(battStr) / 100;
				}else if (battLen == 2) {
					fmtBatt = Float.valueOf(battStr) / 10;
				}else if (battLen == 1){
					fmtBatt = Float.valueOf(battStr);
				}
			}

			temp.setBatteryLevel(String.valueOf(fmtBatt));
			temp.setHumidity(humidity);
			temp.setRecordedDate(new Date());
			temp.setRecorderName("TEMP" + sensorData.getSensorId());
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
