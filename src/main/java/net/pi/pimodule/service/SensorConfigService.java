package net.pi.pimodule.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.pi.pimodule.common.Constants;
import net.pi.pimodule.common.SharedData;
import net.pi.pimodule.db.SensorEntity;
import net.pi.pimodule.db.SensorSql;
import net.pi.pimodule.enums.SensorType;
import net.pi.pimodule.serial.PoolSensor;
import net.pi.pimodule.service.model.Message;


@Path("sensorsConfig")
public class SensorConfigService {

	private static final Logger logger = LogManager.getLogger(SensorConfigService.class);

	private SensorSql sql;

	//	private SensorConfigService() {
	//		sql = new SensorSql();
	//	}

	@Path("sensorList")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSensorList() {
		logger.debug("getSensorList ");	

		Message msg = new Message("ERROR","getSensorList error");
		Status status = Status.FORBIDDEN;

		if (sql == null) {
			sql = new SensorSql();
		}


		try {
			List<SensorEntity> sensorList = sql.getAllSensors();

			logger.debug("sensorList:  " + sensorList);	

			return Response.ok(sensorList).build();

		} catch (ClassNotFoundException | SQLException e) {
			logger.error("Error in getSensorList. " , e);
			status = Status.BAD_REQUEST;
		}		

		return Response.status(status).entity(msg).build();
	}

	@Path("fetchSensorById")
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Response fetchSensorByIdStr(String id) {
		logger.debug("getSensorById ");	

		Message msg = new Message("ERROR","getSensorById error");
		Status status = Status.FORBIDDEN;

		if (sql == null) {
			sql = new SensorSql();
		}


		try {
			SensorEntity sensor = sql.findSensorById(Integer.parseInt(id));

			if (sensor != null) {

				logger.debug("sensor:  " + sensor);	

				return Response.ok(sensor).build();
			}else {
				status = Status.BAD_REQUEST;
				msg = new Message("ERROR","Sensor ID " + id + " not found");
			}

		} catch (ClassNotFoundException | SQLException e) {
			logger.error("Error in getSensorList. " , e);
			status = Status.BAD_REQUEST;
		}		

		return Response.status(status).entity(msg).build();
	}

	@Path("updateSensor")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateSensor(SensorEntity sensor) {
		logger.debug("Update sensor: " + sensor);


		Message msg = new Message("ERROR","updateSensor error");
		Status status = Status.FORBIDDEN;

		try {
			SensorSql sql = new SensorSql();
			sensor.setConfigured(false); //since we update, put the configure false.

			if (!sensor.isPowerSave()) {//reset to 0 so the sensor recieve the data correctly
				sensor.setPwSaveEnd(0);
				sensor.setPwSaveStart(0);
				sensor.setPwSaveTransFreq(0);
			}
			
			sql.updateSensor(sensor);

			SensorType type = sensor.getSensorType();

			if (sensor.getLastUpdated() == null && !sensor.isConfigured()) {
				//we need to send the initial commit to the waiting sensor.
				//after that the sensor will update itself when it send the data.			

				if (type == SensorType.POOL) {		

					boolean success = new PoolSensor().sendInitCommand(sensor).go();

					if (!success ) {   
						//save on DB
						msg = new Message("ERROR", "Data Saved but no OK replies when trying to initialize the sensor, try again");
						status = Status.SERVICE_UNAVAILABLE;
						return Response.status(status).entity(msg).build();
					}else {
						msg = new Message("SUCCESS", "Success");					
						return Response.ok().entity(msg).build();
					}
				}
			}else {
				//not a new sensor..DB has already been updated , just return success.
				msg = new Message("SUCCESS", "Sensor " + type.getType() + sensor.getSensorId()+ " Updated");
				return Response.ok(msg).build();
			}

		}catch(Exception ex) {
			logger.error("Error in updateSensor. " , ex);
			msg = new Message("ERROR","Error in logs");
			status = Status.BAD_REQUEST;
		}

		return Response.status(status).entity(msg).build();
	}
	@Path("deleteSensor")
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteSensorById(String id) {
		logger.debug("delete sensor:  " + id);	

		Message msg = new Message("ERROR","Error deleting sensor");
		Status status = Status.FORBIDDEN;

		if (sql == null) {
			sql = new SensorSql();
		}


		try {
			int sensorId = Integer.parseInt(id);
			SensorEntity sensor = sql.findSensorById(sensorId);

			if (sensor != null) {

				logger.debug("Deleting sensor:  " + sensor);
				msg = new Message("SUCCESS", "Sensor " + sensor.getSensorType().getType() + sensor.getSensorId() + " deleted");
				sql.deleteSensor(sensorId);

				return Response.ok(msg).build();
			}else {
				status = Status.BAD_REQUEST;
				msg = new Message("ERROR","Sensor ID " + id + " not found, cannot delete");
			}

		} catch (ClassNotFoundException | SQLException e) {
			logger.error("Error in deleteSensorById. " , e);
			status = Status.BAD_REQUEST;
		}		

		return Response.status(status).entity(msg).build();
	}
	
	@Path("messages")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMessages() {
		
		List<Message> messages = (List<Message>)SharedData.getInstance().getSharedObject(Constants.MESSAGE_ERROR);
		
		if (messages == null) {
			messages = new ArrayList<>();
		}
		
		return Response.ok(messages).build();
	}

}
