package net.pi.pimodule.serial;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javassist.tools.rmi.ObjectNotFoundException;
import net.pi.pimodule.common.Constants;
import net.pi.pimodule.common.SharedData;
import net.pi.pimodule.common.WorkerStatus;
import net.pi.pimodule.db.GardenSql;
import net.pi.pimodule.db.SensorSql;
import net.pi.pimodule.db.entity.GardenWorkerEntity;
import net.pi.pimodule.db.entity.SensorEntity;
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

public class GardenSensor extends SensorBase {

	private static final Logger logger = LogManager.getLogger(GardenSensor.class);
	private static final int TURN_ON = 1;
	private static final int TURN_OFF = 0;

	//	protected final static Object gardenSensorReply = new Object();
	protected  static BlockingQueue<Boolean> gardenSensorReply =  new ArrayBlockingQueue<>(1);

	private GardenWorkerEntity we;

	public GardenSensor() {}
	public GardenSensor(GardenWorkerEntity we) {

		this.we = we;

	}
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

			}else if (sensorData.getCommand().startsWith(SEND_CMD)) {// recieve a reply to the send command
				logger.debug("Send command recieved:  " + sensorData);

				if (sensorData.getData().contains(OK_REPLY)) {
					gardenSensorReply.offer(true);

				}else {
					gardenSensorReply.offer(true);
					SensorEntity se = new SensorSql().findSensor(SensorType.GARDEN, sensorData.getSensorId());

					if (se != null) {
						GardenWorkerEntity gw = new GardenSql().findWorkerBySensorId(se.getId());

						if (gw != null) {

							boolean waterOn = false;
							if ("1".equals(sensorData.getData())){
								waterOn = true;
							}

							updateWorkerStatus(gw, waterOn);
						}else {
							logger.info("Garder worker not found for sensor update: " + sensorData);
							//TODO generate error to UI
						}

					}else {
						logger.info("Sensor not found for garden worker status update: " + sensorData);
						//TODO generate error to UI
					}
				}
			}

		}catch(Exception ex) {
			logger.error("Error in handleDataReceived" , ex);
		}


		return null;
	}
	/**
	 * Turn on the water
	 * @throws NoSuchElementException
	 * @throws ObjectNotFoundException 
	 */
	public boolean turnOnWater() throws NoSuchElementException, ObjectNotFoundException, IllegalAccessError{
		//<cg0911>
		logger.debug("TurnOnWater:  " + we);

		if (we == null) {
			throw new ObjectNotFoundException("Please use the constructor and provide the worker to turn on the water for");
		}

		try {
			SensorEntity sensor = new SensorSql().findSensorById(we.getSensorIdFk(), false);

			if (sensor != null) {

				StringBuilder cmd = new StringBuilder(START_MARKER);
				cmd.append(SEND_CMD);
				cmd.append(sensor.getSensorType().getType());
				cmd.append(sensor.getSensorId());
				cmd.append(TURN_ON);
				cmd.append(END_MARKER);


				try {
					Boolean answer = waitForWorkerAnswer(cmd.toString());


					if (answer != null && answer.booleanValue() == true) {

						updateWorkerStatus(we, true);
						return true;

					}else {
						logger.debug("No replies from the garden worker - Turn On: " + we.getId());
						return false;
					}
				} catch (InterruptedException e) {
					logger.debug("gardenSensorReply interrupted: " , e);
				}

			}else {
				throw new NoSuchElementException("No sensor exist. Sensor ID requested: " + we.getSensorIdFk());
			}
		} catch (ClassNotFoundException | SQLException | IllegalStateException | IOException e) {

			throw new NoSuchElementException("Error in getting sensor");
		}

		return false;

	}
	/**
	 * Turn off the water
	 * @throws NoSuchElementException
	 * @throws ObjectNotFoundException 
	 */
	public boolean turnOffWater() throws NoSuchElementException, ObjectNotFoundException {
		//<cgXXX0>
		logger.debug("turnOffWater:  " + we);

		if (we == null) {
			throw new ObjectNotFoundException("Please use the constructor and provide the worker to turn OFF the water for");
		}

		try {
			SensorEntity sensor = new SensorSql().findSensorById(we.getSensorIdFk(), false);

			if (sensor != null) {

				StringBuilder cmd = new StringBuilder(START_MARKER);
				cmd.append(SEND_CMD);
				cmd.append(sensor.getSensorType().getType());
				cmd.append(sensor.getSensorId());
				cmd.append(TURN_OFF);
				cmd.append(END_MARKER);

					try {
						Boolean answer = waitForWorkerAnswer(cmd.toString());

						if (answer != null && answer.booleanValue() == true) {

							updateWorkerStatus(we, false);
							return true;

						}else {
							logger.debug("No replies from the garden worker - Turn Off: " + we.getId());
						}
					} catch (InterruptedException e) {
						logger.debug("gardenSensorReply interrupted: " , e);
					}
				
			}else {
				throw new NoSuchElementException("No sensor exist. Sensor ID requested: " + we.getSensorIdFk());
			}
		} catch (ClassNotFoundException | SQLException | IllegalStateException | IOException e) {

			throw new NoSuchElementException("Error in getting sensor");
		}
		return false;

	}
	public boolean getStatus() throws NoSuchElementException {
		//<cgXXX0>

		logger.debug("getStatus:  " + we);

		try {
			SensorEntity sensor = new SensorSql().findSensorById(we.getSensorIdFk(), false);

			if (sensor != null) {

				StringBuilder cmd = new StringBuilder(START_MARKER);
				cmd.append(SEND_CMD);
				cmd.append(sensor.getSensorType().getType());
				cmd.append(sensor.getSensorId());
				cmd.append(STATUS);
				cmd.append(END_MARKER);

					try {
						Boolean answer = waitForWorkerAnswer(cmd.toString());

						if (answer != null && answer.booleanValue() == true) { 
							return true;
						}else {
							logger.debug("No replies from the garden worker - Status: " + we.getId());
						}
					} catch (InterruptedException e) {
						logger.debug("gardenSensorReply interrupted: " , e);
					}
				
			}else {
				throw new NoSuchElementException("No sensor exist. Sensor ID requested: " + we.getSensorIdFk());
			}
		} catch (ClassNotFoundException | SQLException | IllegalStateException | IOException e) {

			throw new NoSuchElementException("Error in getting sensor");
		}
		return false;
	}

	/**
	 * Central wating area to wait for a worker to answer in case more than 1 send info.
	 * @return
	 * @throws InterruptedException
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	private synchronized Boolean waitForWorkerAnswer(String cmd) throws InterruptedException, IllegalStateException, IOException {
		SerialHandler.getInstance().sendTeensyStringCommand(cmd);
		return gardenSensorReply.poll(4000, TimeUnit.MILLISECONDS);
	}

	@SuppressWarnings("unchecked")
	private void updateWorkerStatus(GardenWorkerEntity we, boolean waterOn) {
		Map<Integer, WorkerStatus> wStat = (Map<Integer, WorkerStatus>) SharedData.getInstance().getSharedObject(Constants.WORKERS_STATUS);
		
		if (wStat == null) {
			wStat = new HashMap<>();
		}
		
		WorkerStatus wsStatus = wStat.get(we.getId());
		
		if (wsStatus == null) {
			wsStatus = new WorkerStatus();
		}
		
		wsStatus.setWatering(waterOn);
		wsStatus.setLastUpdate(LocalDateTime.now().format(Constants.DATE_FORMATTER));
		wsStatus.setWorkerId(we.getId());
		wStat.put(we.getId(), wsStatus);

		SharedData.getInstance().putSharedObject(Constants.WORKERS_STATUS, wStat);
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
