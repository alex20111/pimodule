package net.pi.pimodule.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import home.common.data.Temperature;
import net.pi.pimodule.db.TempSql;
import net.pi.pimodule.service.model.Forecast;
import net.pi.pimodule.service.model.Message;
import net.pi.pimodule.service.model.WeatherAlert;
import net.pi.pimodule.service.model.WeatherInfo;
import net.weather.action.WeatherAction;
import net.weather.bean.WeatherCurrentModel;
import net.weather.bean.WeatherForecastModel;
import net.weather.bean.WeatherGenericModel;
import net.weather.enums.WeatherLang;

@Path("temperature")
public class TemperatureService {

	private static final Logger logger = LogManager.getLogger(TemperatureService.class);


	@Path("currTemp")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCurrentTemp() {

		Status status = Status.INTERNAL_SERVER_ERROR;
		//
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

	/**
	 * Get the environment canada weather or the local sensor information
	 * 
	 * @param key = Environment canada key ( ex: ottawa kanata = on-118 ). If no key is provided, don't load it.
	 * @param localSensor = Load local sensor information.. Yes = load it, no = do not load it.
	 * @return
	 */
	@Path("weather/{key}/{loadLocalSensor}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getForecast(@PathParam("key") String key,@PathParam("loadLocalSensor") String localSensor ) {

		logger.debug("Loading forecast: " + key + "  Local sensor: " + localSensor);

		Status status = Status.INTERNAL_SERVER_ERROR;

		boolean weatherQueryOk = false; //check to see if we called the local sensor or the env can weather.If none of them is called, return BAD request.

		WeatherInfo weather = new WeatherInfo();
		Message msg;

		//		if (key == null || key.length() == 0) {
		//			key = "qc-59";  //chelsea
		//		}


		//		qc-59 - chelsea
		try {
			if (key != null && key.length() > 0) {
				weatherQueryOk = true;
				WeatherGenericModel wgm = WeatherAction.getEnvironmentCanadaRSSWeather(key, WeatherLang.english, false, true);

				WeatherCurrentModel wcm = wgm.getWeatherCurrentModel();

				weather.setTemperature(String.valueOf(wcm.getCurrTemp()));
				weather.setHumidex(wcm.getFeelsLike());
				weather.setHumidity(wcm.getHumidity());
				weather.setWingChill(wcm.getWindChill());
				weather.setWind(wcm.getWindDirectionText());
				weather.setObservationTime(wcm.getObservationTime());
				weather.setWeather(wcm.getSummary());

				List<Forecast> forecasts = new ArrayList<Forecast>();

				wgm.getWForecastModel().stream().forEach(f -> {
					Forecast forecast = new Forecast(); 
					forecast.setForecast(f.getForecast());
					forecast.setDayOfWeek(f.getDayOfWeek());
					forecast.setUvIndex(f.getUvIndex());
					forecast.setWeather(f.getWeatherOutlook().trim());
					forecasts.add(forecast);
				});

				weather.setForecast(forecasts);

				if (wgm.getWeatherAlert() != null) {
					weather.setAlert(new WeatherAlert(wgm.getWeatherAlert().getDescription(), wgm.getWeatherAlert().getMessage(), wgm.getWeatherAlert().getLevel()));
				}

				//set UV index
				if (wgm.getWForecastModel().size() > 0) {
					weather.setUV(wgm.getWForecastModel().get(0).getUvIndex());
				}

			}

			if ("yes".equalsIgnoreCase(localSensor)) {
				weatherQueryOk = true;
				//fetch local sensor info
				Temperature temp = new Temperature();

				try {

					temp = new TempSql().getCurrentStoredTemperature();
					weather.setLocaltemp(temp);
				}catch(SQLException | ClassNotFoundException e ) {
					weather.setLocaltemp(new Temperature());
					logger.error("Cannot get Local Sensor temperature", e);
				}
			}

			if (weatherQueryOk) {
				return Response.ok(weather).build();
			}else {
				status = Status.BAD_REQUEST;
				msg = new Message("Input Parm Incorrect", "No valid input parm provided. Missing env can or localsensor switch. Env can key: " + key + "   --  Local Sensor: " + localSensor);
			}

		}catch(Exception ex) {
			logger.error("error in service: " , ex);
			status = Status.INTERNAL_SERVER_ERROR;
			msg = new Message("Invalid", "Error in forecast: " + ex.getMessage());
		}

		return Response.status(status).entity(msg).build();
	}



}
