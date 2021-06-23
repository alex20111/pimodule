package net.pi.pimodule.common;

import java.time.format.DateTimeFormatter;

public class Constants {

	public static final String DB_URL = "/opt/jettyServer/piModule";
	public static final String DB_USER = "piModuleUser";
	public static final String DB_PASS = "109256";
	
	public static final String MESSAGE_ERROR = "ERROR_MESSAGE";
	
	//Message level
	public static final String ERROR = "ERROR";
	public static final String WARNING = "WARN";
	public static final String INFO = "INFO";
	
	//Garden
	public static final String WORKERS_STATUS = "workersStatus";
	public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
}
