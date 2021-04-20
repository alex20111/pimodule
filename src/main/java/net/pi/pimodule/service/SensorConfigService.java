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
import net.pi.pimodule.db.SensorLocSql;
import net.pi.pimodule.db.SensorLocation;
import net.pi.pimodule.db.SensorSql;
import net.pi.pimodule.enums.SensorType;
import net.pi.pimodule.serial.PoolSensor;
import net.pi.pimodule.serial.TemperatureSensor;
import net.pi.pimodule.service.model.Message;


@Path("sensorsConfig")
public class SensorConfigService {

	private static final Logger logger = LogManager.getLogger(SensorConfigService.class);

	private SensorSql sql;
	private SensorLocSql sqlLoc;

	//	private SensorConfigService() {
	//		sql = new SensorSql();
	//	}

	@Path("sensorList")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.TEXT_PLAIN)
	public Response getSensorList(String cmd) {
		//		logger.debug("getSensorList ");	
		
		//ALL_SENSORS --> all sensors with location or not.
		//SENSORS_WITH_LOC --> sensors that only has location attatched to them
		//SENSORS_WITH_NO_LOC --> all sensors that don't have a location attatched to it.

		Message msg = new Message("ERROR","getSensorList error: Command: " + cmd);
		Status status = Status.FORBIDDEN;

		if (sql == null) {
			sql = new SensorSql();
		}
		
		

		try {
			List<SensorEntity> sensorList = new ArrayList<>();
			
			if ("ALL_SENSORS".equalsIgnoreCase(cmd)) {				
				sensorList = sql.getAllSensors(true);
			}else if("SENSORS_WITH_NO_LOC".equalsIgnoreCase(cmd)) {
				sensorList = sql.getAllSensorsWithNoLocation();
			}else if("SENSORS_WITH_LOC".equalsIgnoreCase(cmd)) {
				sensorList = sql.getAllSensorsWithLocation();
			}

			//			logger.debug("sensorList:  " + sensorList);	

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
		logger.debug("getSensorById:  " + id);	

		Message msg = new Message("ERROR","getSensorById error");
		Status status = Status.FORBIDDEN;

		if (sql == null) {
			sql = new SensorSql();
		}


		try {
			SensorEntity sensor = sql.findSensorById(Integer.parseInt(id), true);

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
			
			sensor.setErrorField(""); //reset errors if any

			sql.updateSensor(sensor);

			//also update location
			//1st find if the sensor was attached to an other location.
			sqlLoc = new SensorLocSql();

			if (sensor.getSensorLocation().getId() != -1) {
				SensorLocation loc = sqlLoc.findLocationBySensorId(sensor.getId()); //find current sensor location
				if (loc != null && sensor.getId() != loc.getSensorIdFk()) {
					logger.debug("Loc different from the one provided");
					//we have a location already attached and it's not the same .. remove
					loc.setSensorIdFk(-1);
					sqlLoc.updateLocation(loc);
					//now find the new one and verify that it is free.
					SensorLocation newLoc = sqlLoc.findLocationById(sensor.getSensorLocation().getId(), false);
					//verify that is it not already attached.
					if (newLoc != null && newLoc.getSensorIdFk() < 0) {
						//update with new sensor id
						newLoc.setSensorIdFk(sensor.getId());
						sqlLoc.updateLocation(newLoc);
					}else {
						status = Status.BAD_REQUEST;
						msg = new Message("ERROR","Location already attatched to an other sensor. Sensor ID:  " + newLoc.getSensorIdFk() + " Current sensor id:" + sensor.getId());
						logger.info("Sensor location already attatched to an other one");
						return Response.status(status).entity(msg).build();
					}
				}else if(loc == null) {
					logger.debug("Loc null when adding a new one");
					//no location found for the sensor, then attach
					SensorLocation newLoc = sqlLoc.findLocationById(sensor.getSensorLocation().getId(), false);
					//verify that is it not already attached.
					if (newLoc != null && newLoc.getSensorIdFk() < 0) {
						//update with new sensor id
						newLoc.setSensorIdFk(sensor.getId());
						sqlLoc.updateLocation(newLoc);
					}else {
						status = Status.BAD_REQUEST;
						msg = new Message("ERROR","Location already attatched to an other sensor. Sensor ID attatched to:  " + newLoc.getSensorIdFk() + " Current sensor id:" + sensor.getId());
						logger.info("Sensor location already attatched to an other one. Sensor location id to attatch to: " + sensor.getSensorLocation().getId() + "  new loc sensorId Fk: " + newLoc.getSensorIdFk() + " Current sensor id: " + sensor.getId());
						return Response.status(status).entity(msg).build();
						//TODO error message.
					}
				}
			}else if (sensor.getSensorLocation().getId() == -1 ){
				logger.debug("Sesor is -1, remove it from db");
				//removing location from sensor. find if any location has the sensor id in it and remove it.				
				SensorLocation loc = sqlLoc.findLocationBySensorId(sensor.getId()); //find current sensor location
				if (loc != null) {
					loc.setSensorIdFk(-1);
					sqlLoc.updateLocation(loc);
				}
			}


			SensorType type = sensor.getSensorType();

			if (sensor.getLastUpdated() == null && !sensor.isConfigured()) {
				//we need to send the initial commit to the waiting sensor.
				//after that the sensor will update itself when it send the data.			

				boolean success = false;
				if (type == SensorType.POOL) {		

					success = new PoolSensor().sendInitCommand(sensor).go();

				}else if (type == SensorType.TEMP) {		

					success = new TemperatureSensor().sendInitCommand(sensor).go();			

				}

				if (!success ) {   
					//save on DB
					String errorMsg = "Data Saved. But no OK replies when trying to initialize the sensor, try again";
					msg = new Message("ERROR", errorMsg);
					sensor.setErrorField("ERROR " + errorMsg);
					sql.updateSensor(sensor);
					status = Status.SERVICE_UNAVAILABLE;
					return Response.status(status).entity(msg).build();
				}else {
					msg = new Message("SUCCESS", "Success");					
					return Response.ok().entity(msg).build();
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

		sqlLoc = new SensorLocSql();

		try {
			int sensorId = Integer.parseInt(id);
			SensorEntity sensor = sql.findSensorById(sensorId, true);

			if (sensor != null) {

				logger.debug("Deleting sensor:  " + sensor);
				msg = new Message("SUCCESS", "Sensor " + sensor.getSensorType().getType() + sensor.getSensorId() + " deleted");



				sql.deleteSensor(sensor, true);

				return Response.ok(msg).build();
			}else {
				status = Status.BAD_REQUEST;
				msg = new Message("ERROR","Sensor ID " + id + " not found, cannot delete");
			}

		} catch (Exception e) {
			logger.error("Error in deleteSensorById. " , e);
			status = Status.BAD_REQUEST;
		}		

		return Response.status(status).entity(msg).build();
	}

	@Path("messages")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMessages() {

		@SuppressWarnings("unchecked")
		List<Message> messages = (List<Message>)SharedData.getInstance().getSharedObject(Constants.MESSAGE_ERROR);

		if (messages == null) {
			messages = new ArrayList<>();
		}

		return Response.ok(messages).build();
	}


	/*  LOCATION methods            */

	@Path("locations")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.TEXT_PLAIN)
	public Response getSensorLocationList(String request) {  //because we will need to fetch the location in the manage sensor and manage location page..
		logger.debug("getSensorLocationList: request: " + request);

		//request types: ALL_LOC_NO_SENSOR  // all location but don't retrieve the sensor entity 
		//ALL_LOC_WITH_SENSOR				// all location with sensors if any associated to it
		//ALL_LOC_FREE (No associated sensors) // all location that has no sensor associated to it ( no sensor FK )

		Message msg = new Message("ERROR","getSensorLocationList error. request: " + request);
		Status status = Status.FORBIDDEN;

		if (sqlLoc == null) {
			sqlLoc = new SensorLocSql();
		}

		try {

			List<SensorLocation> locList = new ArrayList<>();
			if("ALL_LOC_NO_SENSOR".equalsIgnoreCase(request)) {
				locList = sqlLoc.getAllSensorLocation(false);
			}else if("ALL_LOC_WITH_SENSOR".equalsIgnoreCase(request)) {
				locList = sqlLoc.getAllSensorLocation(true);
			}else if("ALL_LOC_FREE".equalsIgnoreCase(request)) {
				locList = sqlLoc.getAllLocationWithoutSensors();
			}else {
				status = Status.BAD_REQUEST;
				msg = new Message("ERROR", "BAD request. Request recieved: " + request);
			}

			return Response.ok(locList).build();

		} catch (ClassNotFoundException | SQLException e) {
			logger.error("Error in getSensorLocationList. " , e);
			status = Status.BAD_REQUEST;
		}		

		return Response.status(status).entity(msg).build();
	}

	@Path("addLocation")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addNewLocation(SensorLocation location) {  //because we will need to fetch the location in the manage sensor and manage location page.. 

		//request types: ALL_LOC_NO_SENSOR  // all location but don't retrieve the sensor entity 
		//ALL_LOC_WITH_SENSOR				// all location with sensors if any associated to it
		//ALL_LOC_FREE (No associated sensors) // all location that has no sensor associated to it ( no sensor FK )

		Message msg = new Message("ERROR","addNewLocation error");
		Status status = Status.FORBIDDEN;

		if (sqlLoc == null) {
			sqlLoc = new SensorLocSql();
		}

		try {
			logger.debug("Adding: " + location);

			sqlLoc.addLocation(location);

			msg = new Message("SUCCESS", "Location added successfully");

			return Response.ok().entity(msg).build();

		} catch (ClassNotFoundException | SQLException e) {
			logger.error("Error in addNewLocation. " , e);
			status = Status.BAD_REQUEST;
			msg = new Message("ERROR","addNewLocation error: " + e.getMessage());
		}		

		return Response.status(status).entity(msg).build();
	}

	@Path("updateLocation")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateLocation(SensorLocation location) {  //because we will need to fetch the location in the manage sensor and manage location page..
		logger.debug("updateLocation: request: " + location);


		Message msg = new Message("ERROR","updateLocation error. request: " + location);
		Status status = Status.FORBIDDEN;

		if (sqlLoc == null) {
			sqlLoc = new SensorLocSql();
		}

		try {

			sqlLoc.updateLocation(location);

			msg = new Message("SUCCESS", "Location updated");


			return Response.ok(msg).build();

		} catch (ClassNotFoundException | SQLException e) {
			logger.error("Error in updateLocation. " , e);
			status = Status.BAD_REQUEST;
		}		

		return Response.status(status).entity(msg).build();
	}

	@Path("deleteLocation")
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteLocationById(String id) {
		logger.debug("delete location by ID:  " + id);	

		Message msg = new Message("ERROR","Error deleting location");
		Status status = Status.FORBIDDEN;

		if (sqlLoc == null) {
			sqlLoc = new SensorLocSql();
		}


		try {
			int sensorId = Integer.parseInt(id);
			SensorLocation loc = sqlLoc.findLocationById(sensorId, true);

			if (loc  != null) {
				//just delete..
				sqlLoc.deleteLocation(loc.getId());
				msg = new Message("SUCCESS","Sensor deleted");
				return Response.ok(msg).build();
			}else {
				status = Status.BAD_REQUEST;
				msg = new Message("ERROR","Location ID " + id + " not found, cannot delete");
			}


		} catch (ClassNotFoundException | SQLException e) {
			logger.error("Error in deleteSensorById. " , e);
			status = Status.BAD_REQUEST;
		}		

		return Response.status(status).entity(msg).build();
	}

	@Path("fetchLocationById")
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getLocationById(String id) {
		logger.debug("getLocationById:  " + id);	

		Message msg = new Message("ERROR","getSensorById error");
		Status status = Status.FORBIDDEN;

		if (sqlLoc == null) {
			sqlLoc = new SensorLocSql();
		}

		try {

			SensorLocation loc = sqlLoc.findLocationById(Integer.parseInt(id), true);

			if (loc != null) {
				logger.debug("SensorLocation:  " + loc);	

				return Response.ok(loc).build();

			}else {
				status = Status.BAD_REQUEST;
				msg = new Message("ERROR","SensorLocation ID " + id + " not found");
			}

			//
		} catch (ClassNotFoundException | SQLException e) {
			logger.error("Error in getLocationById. " , e);
			status = Status.BAD_REQUEST;
		}		

		return Response.status(status).entity(msg).build();
	}
}
