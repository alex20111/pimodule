package net.pi.pimodule.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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

import javassist.tools.rmi.ObjectNotFoundException;
import net.pi.pimodule.common.Constants;
import net.pi.pimodule.common.SharedData;
import net.pi.pimodule.common.WorkerStatus;
import net.pi.pimodule.db.GardenSql;
import net.pi.pimodule.db.entity.GardenWorkerEntity;
import net.pi.pimodule.db.entity.SensorEntity;
import net.pi.pimodule.serial.GardenSensor;

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

		logger.debug("getAllGardenWorkers()");

		List<GardenWorkerEntity> workers = new ArrayList<>();
		try {
			workers = sql.getAllWorkers();

			@SuppressWarnings("unchecked")
			Map<Integer, WorkerStatus> status = (Map<Integer, WorkerStatus>)SharedData.getInstance().getSharedObject(Constants.WORKERS_STATUS);

			workers.stream().forEach(w -> w.setStatus(status.get(w.getId())));

			logger.debug("workers: " + workers);
		} catch (Exception e) {
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

		logger.debug("Get worker by id: " + id);

		GardenWorkerEntity worker;
		try {

			if (id != null) {

				worker = sql.findWorkerById(id);

				if (worker == null) {
					throw new NotFoundException("Resource not found");
				}else {
					@SuppressWarnings("unchecked")
					Map<Integer, WorkerStatus> status = (Map<Integer, WorkerStatus>)SharedData.getInstance().getSharedObject(Constants.WORKERS_STATUS);
					worker.setStatus(status.get(worker.getId()));
				}

				logger.debug("worker" + worker);

			}else {
				throw new NotFoundException("ID not provided");
			}
		} catch (ClassNotFoundException | SQLException e) {
			logger.error("Error in getWorkerById", e);

			throw new InternalServerErrorException("Error getting getWorkerById ");
		}


		return Response.ok(worker).build();
	}

	@GET
	@Path("{id: \\d+}/turnOn")
	@Produces(MediaType.TEXT_PLAIN)
	public String turnOn(@PathParam("id")  Integer id) {
		logger.debug("Turning on water: " + id);

		GardenWorkerEntity worker = null;
		try {

			if (id != null) {

				worker = sql.findWorkerById(id);

				if (worker != null) {
					@SuppressWarnings("unchecked")
					Map<Integer, WorkerStatus> status = (Map<Integer, WorkerStatus>)SharedData.getInstance().getSharedObject(Constants.WORKERS_STATUS);

					try {
						logger.debug("Worker status befoore: " + status.get(worker.getId()));

						boolean success = new GardenSensor(worker).turnOnWater();

						if (success) {
							logger.debug("Worker status after: " + status.get(worker.getId()));
							return "TURN_ON_SUCCESS";
						}

					}catch(NoSuchElementException | ObjectNotFoundException e) {
						logger.info("problem turning on the water: " + e.getMessage());
						throw new NotFoundException("error turning on the water");
					}

				}else {
					throw new NotFoundException("Resource not found");
				}

			}else {
				throw new NotFoundException("ID not provided");
			}
		} catch (ClassNotFoundException | SQLException e) {
			logger.error("Error in getWorkerById", e);

			throw new InternalServerErrorException("Error getting getWorkerById ");
		}
		return "TURN_ON_ERROR";

	}
	@GET
	@Path("{id: \\d+}/turnOff")
	@Produces(MediaType.TEXT_PLAIN)
	public String turnOff(@PathParam("id")  Integer id) { ////////////////change to string!!!!!!!!!!!!!!!!!! return
		logger.debug("Turning off water: " + id);

		GardenWorkerEntity worker = null;
		try {

			if (id != null) {

				worker = sql.findWorkerById(id);

				if (worker != null) {
					@SuppressWarnings("unchecked")
					Map<Integer, WorkerStatus> status = (Map<Integer, WorkerStatus>)SharedData.getInstance().getSharedObject(Constants.WORKERS_STATUS);

					try {
						logger.debug("Worker status befoore: " + status.get(worker.getId()));

						boolean success = new GardenSensor(worker).turnOffWater();

						if (success) {
							logger.debug("Worker status after: " + status.get(worker.getId()));
							return "TURN_OFF_SUCCESS";
						}					

					}catch(NoSuchElementException | ObjectNotFoundException e) {
						logger.info("problem turning on the water: " + e.getMessage());
						throw new NotFoundException("error turning on the water");
					}

				}else {
					throw new NotFoundException("Resource not found");
				}

			}else {
				throw new NotFoundException("ID not provided");
			}
		} catch (ClassNotFoundException | SQLException e) {
			logger.error("Error in getWorkerById", e);

			throw new InternalServerErrorException("Error getting getWorkerById ");
		}
		return "TURN_OFF_ERROR";
	}

	@POST  // = create
	@Consumes(MediaType.APPLICATION_JSON)
	public void createWorker(GardenWorkerEntity worker) {
		logger.debug("Create worker : " + worker);

		if (worker != null) {
			try {
				//TODO verify if exist 1st with the sensorid FK
				
				
				int pk = sql.addWorker(worker);
				
				worker.setId(pk);
				
				//get worker status after
				boolean success = new GardenSensor(worker).turnOffWater();

				if (!success) {
					logger.error("Could not contact the worker: " + worker);
				}			
				
			} catch (ClassNotFoundException | SQLException | NoSuchElementException | ObjectNotFoundException e) {
				logger.error("Error while adding worker", e);
				throw new InternalServerErrorException("Error while adding worker");
			}
		}else {
			throw new NotFoundException("No worker provided");
		}
	}

	@PUT // = update
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateWorker(GardenWorkerEntity worker) {
		logger.debug("update worker : " + worker);

		if (worker != null) {
			try {
				 sql.updateWorker(worker);
			
//				//get worker status after
//				boolean success = new GardenSensor(worker).turnOffWater();
//
//				if (!success) {
//					logger.error("Could not contact the worker: " + worker);
//				}			
				
			} catch (ClassNotFoundException | SQLException e) {
				logger.error("Error while updating worker", e);
				throw new InternalServerErrorException("Error while updating worker");
			}
		}else {
			throw new NotFoundException("No worker provided");
		}

	}
	@DELETE
	@Path("{id: \\d+}")
	public void del(@PathParam("id")  Integer id) {
		logger.debug("Deleting worker: " + id);


		if (id != null) {

			try {
				sql.deleteWorker(id);
			} catch (ClassNotFoundException | SQLException e) {
				logger.error("Error while deleting worker", e);
				throw new InternalServerErrorException("Error while deleting worker");
			}

		}else {
			throw new NotFoundException("No worker id provided to delete");
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