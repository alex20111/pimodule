package net.pi.pimodule.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.pi.pimodule.db.TempSql;
import net.pi.pimodule.service.model.Message;
import net.pi.pimodule.temperature.Temperature;

@Path("temperature")
public class TemperatureService {

	private static final Logger logger = LogManager.getLogger(TemperatureService.class);


	@Path("currTemp")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCurrentTemp() {

		Status status = Status.INTERNAL_SERVER_ERROR;

		Temperature temp = new Temperature();
		Message msg;

		try {
			temp = new TempSql().getCurrentStoredTemperature();
			return Response.ok(temp).build();
		}catch(Exception ex) {
			logger.error("error in service: " , ex);
			msg = new Message("Invalid", "Error in temperature: " + ex.getMessage());
		}

		return Response.status(status).entity(msg).build();
	}
	
	

}
