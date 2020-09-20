package net.pi.pimodule.service.model;

import java.util.List;

import net.pi.pimodule.temperature.Temperature;

public class WeatherInfo {
	
	private String temperature = "";
	private String UV = "";
	private String humidex = "";
	private String wingChill = "";
	private String wind = "";
	private String weather = "";
	private String humidity = "";
	private String observationTime = "";
	
	private Temperature localtemp;
	
	private List<Forecast> forecast;
	
	private WeatherAlert alert;
	
	public String getTemperature() {
		return temperature;
	}
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	public String getUV() {
		return UV;
	}
	public void setUV(String uV) {
		UV = uV;
	}
	public String getHumidex() {
		return humidex;
	}
	public void setHumidex(String humidex) {
		this.humidex = humidex;
	}
	public String getWingChill() {
		return wingChill;
	}
	public void setWingChill(String wingChill) {
		this.wingChill = wingChill;
	}
	public List<Forecast> getForecast() {
		return forecast;
	}
	public void setForecast(List<Forecast> forecast) {
		this.forecast = forecast;
	}
	public String getWind() {
		return wind;
	}
	public void setWind(String wind) {
		this.wind = wind;
	}
	public String getWeather() {
		return weather;
	}
	public void setWeather(String weather) {
		this.weather = weather;
	}
	public String getHumidity() {
		return humidity;
	}
	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}
	public String getObservationTime() {
		return observationTime;
	}
	public void setObservationTime(String observationTime) {
		this.observationTime = observationTime;
	}
	public WeatherAlert getAlert() {
		return alert;
	}
	public void setAlert(WeatherAlert alert) {
		this.alert = alert;
	}
	public Temperature getLocaltemp() {
		return localtemp;
	}
	public void setLocaltemp(Temperature localtemp) {
		this.localtemp = localtemp;
	}
	@Override
	public String toString() {
		return "WeatherInfo [temperature=" + temperature + ", UV=" + UV + ", humidex=" + humidex + ", wingChill="
				+ wingChill + ", forecast=" + forecast + "]";
	}
	
	

}
