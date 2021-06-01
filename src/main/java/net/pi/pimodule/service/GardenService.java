package net.pi.pimodule.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("garden")
public class GardenService {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllGardenSensors() {
		
		return null;
	}
	@GET
	@Path("{id: \\d+}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getWorkerById(@PathParam("id")  Integer id) {
		
		return null;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void createWorker() {
		
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateWorker() {
		
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