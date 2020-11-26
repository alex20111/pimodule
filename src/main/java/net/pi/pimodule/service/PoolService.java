package net.pi.pimodule.service;

import java.net.URLDecoder;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//import net.pi.pimodule.common.TemperatureHandler;
import net.pi.pimodule.db.TempEntity;
import net.pi.pimodule.db.TempSql;

@Deprecated
@Path("{data}")
public class PoolService {

	private static final Logger logger = LogManager.getLogger(PoolService.class);
	
	@Context
	private transient HttpServletRequest servletRequest;


//	@Path("{data}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response testPath(@PathParam("data") String path) {

		String queryString = servletRequest.getQueryString();

		String decodedString = "";
		try {

			decodedString = URLDecoder.decode(queryString, "UTF-8");
			
			logger.debug("Pool query string: " + decodedString);
			//cmd=add&type=temp&jsonObject={'tempC':86.34,'recorderName':'pool','batteryLevel':'2.67'}

			if (decodedString != null && decodedString.length() > 0) {
				String temp = decodedString.substring(  decodedString.indexOf("tempC") + 7, decodedString.indexOf("recorderName") - 2  );
//				logger.debug("temp: " + temp);
				if (temp.length() > 0) {			

					TempEntity te = new TempEntity();
					te.setRecordedDate(new Date());
					te.setRecorderName("pool");
					te.setTempC(temp);		
					new TempSql().saveTemperature(te);
					
//					TemperatureHandler.getInstance().setTemperature(te);

					
					
				}
			}
		} catch (Exception e) {
			logger.debug("error in pool temp service", e);
		}

		return Response.ok("{\"result\":\"Success\"}").build();
	}

	///web/service.action?cmd=add&type=temp&jsonObject={'tempC':84.43,'recorderName':'pool','batteryLevel':'2.66'}

	//	/web/service.action?cmd=add&type=temp&jsonObject={'tempC':";
	//    message.concat(temperatureFString);
	//    message.concat(",'recorderName':'pool','batteryLevel':'");
	//    message.concat(voltage);
	//    message.concat("'} HTTP/1.1\r\nHost: 192.168.1.110:8081\r\nConnection: close\r\n\r\n");


	//http://localhost:8080/web/service.action?cmd=add&type=temp&jsonObject={"tempC":"39","recorderName":"pool","batteryLevel":"3.32"}

		public static void main (String args[]) {
			String path = "cmd=add&type=temp&jsonObject={'tempC':86.34,'recorderName':'pool','batteryLevel':'2.67'}";
			
			String json = path.substring(  path.indexOf("tempC") + 7, path.indexOf("recorderName") - 2  );
			
			System.out.println("Json: " + json);
		}
}
//http://localhost:8081/web/service.action?cmd=add&type=temp&jsonObject={'tempC':84.43,'recorderName':'pool','batteryLevel':'2.66'}

//http://localhost:8080/api/pool/service.action%3Fcmd=add%26type%3Dtemp%26jsonObject%3D%7B%22tempC%22%3A%2239%22%2C%22recorderName%22%3A%22pool%22%2C%22batteryLevel%22%3A%223.32%22%7D
