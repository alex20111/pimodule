package net.pi.pimodule.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import home.common.data.Temperature.TempRecName;
import home.websocket.WebSocketException;
import net.pi.pimodule.db.TempEntity;
import net.pi.pimodule.db.TempSql;
import net.pi.pimodule.websocket.Data;
import net.pi.pimodule.websocket.WebSocketClient;

/*
 * Class that handle various sensor services..
 * 
 * 
 * */
@Path("sensors")
public class SensorService {

	private static final Logger logger = LogManager.getLogger(SensorService.class);

	private Gson gson;

//	private static WebSocketClientEndPoint clientEndPoint;
//	private static Thread wsAliveThread;

	public SensorService() {
		gson = new Gson();
	}

	@Path("garage")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response garageSensorValue(String data) {

		logger.debug("Garage Door message status in REST: " + data);
		if (data != null && data.trim().length() > 0) {
			try {
				
				Data newData = gson.fromJson(data, Data.class);
				
				WebSocketClient.getInstance().sendMessage("{'operation': " + Data.GARAGE_FUNCTION + " , 'garageDoorStatus':"+ newData.garageDoorStatus+ " }");

//				connectToEndpoint();
//				clientEndPoint.sendMessage("{'operation': " + Data.GARAGE_FUNCTION + " , 'garageDoorStatus':"+ status+ " }");
				return Response.ok("<respOk>").build();
			}catch(WebSocketException w) {
				logger.error("Web socket exception" , w);
				
			}catch (JsonSyntaxException x) {
				logger.error("Json syntax exception" , x);
			}
			
		}
		return Response.status(Status.BAD_REQUEST).build();

	}
	@Path("sensorUpdate")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response sensorUpdate(String data) {

		logger.debug("Sensor  update" + data);
		if (data != null && data.trim().length() > 0) {
			try {

				Data newData = gson.fromJson(data, Data.class);

				if (newData.operation == Data.GARAGE_TEMP_UPDATE) {
					TempSql sql = new TempSql();

					try {
						TempEntity ent = new TempEntity();
						ent.setRecordedDate(new Date());
						ent.setRecorderName(TempRecName.GARAGE.name());
						ent.setTempC(newData.sensorValue);

						sql.saveTemperature(ent);
						return Response.ok("<respOk>").build();
					}catch (SQLException | ClassNotFoundException e) {
						logger.error("Sensor  update error. " , e);
					}					
				}

			}catch (JsonSyntaxException x) {
				logger.error("Json syntax exception" , x);
			}

		}
		return Response.status(Status.INTERNAL_SERVER_ERROR).build();


	}
	@Path("ping")
	@POST
//	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response pingServer(String message) {

		logger.debug("Sensor pigned with message : " + message);
	
		return Response.ok("<respOk>").build();

	}
	@Path("testPost")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response testPost(String message) {

		logger.debug("Test sensor post message : " + message);
	
		return Response.ok("<respOk>").build();

	}
	@Path("testGet/{status}")
	@GET
	public Response testGet(@PathParam("status") String message) {

		logger.debug("Test sensor get message : " + message);
	
		return Response.ok("<respOk>").build();

	}

	
}
