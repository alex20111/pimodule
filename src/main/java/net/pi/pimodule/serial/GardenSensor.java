package net.pi.pimodule.serial;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.pi.pimodule.common.Constants;
import net.pi.pimodule.common.SharedData;
import net.pi.pimodule.db.SensorSql;
import net.pi.pimodule.db.TempSql;
import net.pi.pimodule.db.entity.SensorEntity;
import net.pi.pimodule.db.entity.TempEntity;
import net.pi.pimodule.service.model.Message;

/**
 * Pool sensor.
 * DATA expected to get from sensor
 * "dpxxx,tmp,batt"   the 1st part (command, sensor type and sensorIf is formatted by the SensorData class).
 * 
 * 
 * @author ADMIN
 *
 */

public class GardenSensor extends SensorBase {

	private static final Logger logger = LogManager.getLogger(GardenSensor.class);


	/**
	 * Command to send
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

			}else if(sensorData.getCommand().equals(INIT_CMD)) { //different from base				
				//				//send init data to sensor
				super.handleInitCommand(sensorData);

			}else if (sensorData.getCommand().startsWith(SEND_CMD)) {
				logger.debug("Send command recieved ");
				if (sensorData.getCommand().contains(OK_REPLY)) {
					SensorSql sql = new SensorSql();
					SensorEntity ent = sql.findSensor(sensorData.getSensorTypeEnum(), sensorData.getSensorId());

					if (ent != null) {

						ent.setLastTransmit(new Date());
						sql.updateLastTransmit(ent.getId(), new Date(), true);
					}else {
						logger.error("No sensor found: " + sensorData);
						Message msg = new Message(Constants.WARNING + " - ", "Sensor " + sensorData.getSensorTypeEnum().getType() + sensorData.getSensorId() + " not registered on the DB. Reset ");
						SharedData.getInstance().addToMessage(msg);
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

			String battStr = dataSplit[2];
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
