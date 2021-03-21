package net.pi.pimodule.serial;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.pi.pimodule.db.SensorEntity;
import net.pi.pimodule.db.SensorSql;
import net.pi.pimodule.db.TempEntity;
import net.pi.pimodule.db.TempSql;



public class PoolSensor extends SensorBase {

	private static final Logger logger = LogManager.getLogger(PoolSensor.class);


	private int status = -1;

	public PoolSensor sensorId(int id) {
//		this.sensorId = id;
		return this;
	}
	public PoolSensor init(SensorEntity sensor) {
		//calculate date in seconds, compensate for eastern time (-4)
		formatInitString(sensor);
		return this;
	}

	/**
	 * Command to send to attiny through Teensy.
	 */
	@Override
	public String sendCommand() {
		StringBuilder sb = new StringBuilder(START_MARKER);
		//		sb.append(sensor.getIdentifier());
		sb.append(status);
		sb.append(END_MARKER);

		return sb.toString();
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
				//save data to database.. also save last transmit time.. last update time is when you update it manually. 
				SensorSql sql = new SensorSql();
				SensorEntity ent = sql.findSensor(sensorData.getType(), sensorData.getSensorId());
				
				if (ent != null) {
					//update
					sql.updateLastTransmit(ent.getId(), new Date());
					//update temp
					TempSql tempSql= new TempSql();				
					
					tempSql.addTemp(getTemperatureData(sensorData.getData()));					
				}
				
				
			}
		}catch(Exception ex) {
			logger.error("Error in handleDataReceived" , ex);
		}


		return null;
	}

	private TempEntity getTemperatureData(String data) {
		return null;
	}
	
	public static void main(String args[]) {
		System.out.println("Started");
		PoolSensor s  = new PoolSensor();
		
		String data = "spAA3";
		
//		s.handleDataReceived(data);
//		
//		data = "sp001ok";
//		s.handleDataReceived(data);
		
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
