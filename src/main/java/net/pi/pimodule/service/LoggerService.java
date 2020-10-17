package net.pi.pimodule.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.pi.pimodule.service.model.LogMessage;

/**
 * Service to generate debug logs..
 * 
 * format
 * 	Input will be JSON
 * 
 *  {'class':'className', 'errorMessage':'broken please fix', 'fullErrorStack': 'full error stack ex'}
 * 
 * @author ADMIN
 *
 */
@Path("log")
public class LoggerService {
	
	private static final Logger logger = LogManager.getLogger(LoggerService.class);

	@Path("debug")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response debug(LogMessage debugMessage) {
		
		logger.debug("debug log message: " + debugMessage);
		
		return Response.ok().build();
	}
	@Path("error")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response error(LogMessage errorMessage) {
		
		logger.error("debug log message: " + errorMessage);
		
		return Response.ok().build();
	}
}
