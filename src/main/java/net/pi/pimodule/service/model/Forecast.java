package net.pi.pimodule.service.model;

public class Forecast {

	private String dayOfWeek = "";	
	private String forecast = "";
	private String uvIndex = "";
	private String weather = "";
	
	public String getForecast() {
		return forecast;
	}
	public void setForecast(String forecast) {
		this.forecast = forecast;
	}
	public String getDayOfWeek() {
		return dayOfWeek;
	}
	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
	public String getUvIndex() {
		return uvIndex;
	}
	public void setUvIndex(String uvIndex) {
		this.uvIndex = uvIndex;
	}
	public String getWeather() {
		return weather;
	}
	public void setWeather(String weather) {
		this.weather = weather;
	}
	
	
}
