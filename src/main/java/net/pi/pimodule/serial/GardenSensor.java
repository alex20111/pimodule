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
				if (sensorData != null && sensorData.getData() != null && sensorData.getData().length() > 0) {
					//get if we recieved a status.
					int ws = 0;
					boolean waterOn = false;
					try {
						ws = Integer.parseInt(sensorData.getData().substring(2,sensorData.getData().length()));

						if (ws == 1){
							waterOn = true;
						}

					}catch(NumberFormatException nfx) {
						logger.debug("No status in the sensor data in handleInitCommand");
					}

					updateWorkerStatus(sensorData, waterOn, true);

				}

			}else if (sensorData.getCommand().startsWith(SEND_CMD)) {// recieve a reply to the send command
				logger.debug("Send command recieved:  " + sensorData);

				if (sensorData.getData().contains(OK_REPLY)) {
					gardenSensorReply.offer(true);

				}else {
					gardenSensorReply.offer(true);

					boolean waterOn = false;
					if ("1".equals(sensorData.getData())){
						waterOn = true;
					}

					updateWorkerStatus(sensorData, waterOn, true);

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

						updateWorkerStatus(we, true, true);
						return true;
					}else {
						logger.debug("No replies from the garden worker - Turn On: " + we.getId());
						updateWorkerStatus(we, false, false);
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

						updateWorkerStatus(we, false, true);
						return true;

					}else {
						logger.debug("No replies from the garden worker - Turn Off: " + we.getId());
						updateWorkerStatus(we, false, false);
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
						updateWorkerStatus(we, false, false);
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

	private void updateWorkerStatus(SensorData sd,  boolean waterOn, boolean alive) throws ClassNotFoundException, SQLException {
		logger.debug("updateWorkerStatus: SensorData: " + sd + " Water on: " + waterOn);
		SensorEntity se = new SensorSql().findSensor(SensorType.GARDEN, sd.getSensorId());

		if (se != null) {
			GardenWorkerEntity gw = new GardenSql().findWorkerBySensorId(se.getId());

			if (gw != null) {
				this.updateWorkerStatus(gw,  waterOn, alive);

			}else {
				logger.info("Garder worker not found for sensor update: " + sd);
				//TODO generate error to UI
			}

		}else {
			logger.info("Sensor not found for garden worker status update: " + sd);
			//TODO generate error to UI
		}
	}

	@SuppressWarnings("unchecked")
	private void updateWorkerStatus(GardenWorkerEntity we, boolean waterOn, boolean alive) {
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
		wsStatus.setAlive(alive);
		wStat.put(we.getId(), wsStatus);

		SharedData.getInstance().putSharedObject(Constants.WORKERS_STATUS, wStat);
	}
}
