package net.pi.pimodule.service;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.pi.pimodule.db.SensorEntity;
import net.pi.pimodule.db.SensorSql;
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
			logger.debug("Sensor Sql null, Initializing");
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
	
	
}
