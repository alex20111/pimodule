package net.pi.pimodule.serial;

public interface Command {
	public static String START_MARKER = "<";
	public static String END_MARKER = ">";
	public static String DELIMITER = ",";
	
	public static final String INIT_CMD = "i";
	public static final String START_CMD = "s"; //also know as SENSOR id change
	public static final String DATA_CMD = "d";

	public String sendCommand();
	public String handleDataReceived(SensorData sensorData);
	
}
