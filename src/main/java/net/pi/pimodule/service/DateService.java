package net.pi.pimodule.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.TimeZone;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.luckycatlabs.sunrisesunset.SunriseSunsetCalculator;
import com.luckycatlabs.sunrisesunset.dto.Location;

import net.pi.pimodule.service.model.DayNightCycle;
import net.pi.pimodule.service.model.DayNightCycle.Cycle;
import net.pi.pimodule.service.model.Message;

@Path("date")
public class DateService {
	private static final Logger logger = LogManager.getLogger(DateService.class);
	
	@Path("dayNightCycle/{latitude}/{longitude}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getForecast(@PathParam("latitude") String latitude,@PathParam("longitude") String longitude ) {

		logger.debug("determening if we are the day or night. Lat: " + latitude + "  long: " + longitude);
		
		Message msg ;

		Status status = Status.INTERNAL_SERVER_ERROR;
		
		if (latitude != null && latitude.length() > 0 && longitude != null && longitude.length() > 0 ) {
			 int minutesDiff = 20; // minutes before and after 
			Calendar now = Calendar.getInstance();
			Location loc = new Location("45.41117","-75.69812");
			SunriseSunsetCalculator calc = new SunriseSunsetCalculator(loc, TimeZone.getDefault());
			
			 LocalDateTime 	sunrise = convertToLocalDateTime(calc.getOfficialSunriseCalendarForDate(now)).minusMinutes(minutesDiff);
			LocalDateTime sunset = convertToLocalDateTime(calc.getOfficialSunsetCalendarForDate(now)).plusMinutes(minutesDiff);
			
			LocalDateTime dt = convertToLocalDateTime(now);
			DayNightCycle cycle = null;
			if (dt.isAfter(sunrise) && dt.isBefore(sunset) ) {
				cycle = new DayNightCycle(Cycle.DAY);
			}else if (  dt.isAfter(sunset) || dt.isBefore(sunrise) ){
				cycle = new DayNightCycle(Cycle.NIGHT);
			}else {
				cycle = new DayNightCycle(Cycle.NA);
			}
			
			logger.debug("Sunrise: " + sunrise + "  Sunset: " + sunset+ "  Cycle: " + cycle);						
			
			
			 return Response.ok(cycle).build();
		}else {
			msg = new Message("Missing parms", "Missing latitude or longitude, plese check params");
		}
		
		return Response.status(status).entity(msg).build();
		
	}
	
	private LocalDateTime convertToLocalDateTime(Calendar cal){
		return cal.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
	}
	
}
