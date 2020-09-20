package net.pi.pimodule.service.model;

public class WeatherAlert {
	private String title = "";
	private String message = "";
	private String level = "";
	
	public WeatherAlert(String title, String message, String level) {
		this.title  = title;
		this.message = message;
		this.level = level;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
	
	
}
