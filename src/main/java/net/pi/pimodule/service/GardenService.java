package net.pi.pimodule.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.pi.pimodule.db.GardenSql;
import net.pi.pimodule.db.SensorSql;
import net.pi.pimodule.db.entity.GardenWorkerEntity;
import net.pi.pimodule.db.entity.SensorEntity;

@Path("garden")
public class GardenService {

	private static final Logger logger = LogManager.getLogger(GardenService.class);

	private GardenSql sql;

	public GardenService() {
		sql = new GardenSql();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllGardenWorkers() {

		List<GardenWorkerEntity> workers;
		try {
			workers = sql.getAllWorkers();
		} catch (ClassNotFoundException | SQLException e) {
			logger.error("Error in getAllGardenWorkers", e);

			throw new InternalServerErrorException("Error getting getAllGardenWorkers ");
		}


		return Response.ok(workers).build();
	}
	/**
	 * Get all the sensors that does not have a worker assigned to it. 
	 * @return
	 */
	@GET
	@Path("unassigned")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllUnassignedWorkers() {

		List<SensorEntity> workers = new ArrayList<>();
		try {

			workers = sql.getAllUnassignedWorkers();


		} catch (ClassNotFoundException | SQLException e) {
			logger.error("Error in getAllGardenWorkers", e);

			throw new InternalServerErrorException("Error getting getAllGardenWorkers ");
		}

		return Response.ok(workers).build();
	}
	@GET
	@Path("{id: \\d+}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getWorkerById(@PathParam("id")  Integer id) {

		GardenWorkerEntity worker;
		try {

			if (id != null) {

				worker = sql.findWorkerById(id);
			}else {
				throw new NotFoundException("Cannot found");
			}
		} catch (ClassNotFoundException | SQLException e) {
			logger.error("Error in getWorkerById", e);

			throw new InternalServerErrorException("Error getting getWorkerById ");
		}


		return Response.ok(worker).build();
	}

	@POST  // = create
	@Consumes(MediaType.APPLICATION_JSON)
	public void createWorker() {

	}

	@PUT // = update
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateWorker() {

	}
	@GET
	@Path("{id: \\d+}/deleteSensor")
	@Produces(MediaType.APPLICATION_JSON)
	public void del(@PathParam("id")  Integer id) {

		SensorSql sql = new SensorSql();
		try {
			logger.debug("Delting: " + id);

			SensorEntity sensor = sql.findSensorById(id, false);

			if (sensor != null) {

				sql.deleteSensor(sensor, false);
			}
		}catch(Exception e) {
			logger.error(e);
		}

	}

}


//Exception	Status code	Description
//BadRequestException	400	Malformed message
//NotAuthorizedException	401	Authentication failure
//ForbiddenException	403	Not permitted to access
//NotFoundException	404	Couldn’t find resource
//NotAllowedException	405	HTTP method not supported
//NotAcceptableException	406	Client media type requested not supported
//NotSupportedException	415	Client posted media type not supported
//InternalServerErrorException	500	General server error
//ServiceUnavailableException	503	Server is temporarily unavailable or busy