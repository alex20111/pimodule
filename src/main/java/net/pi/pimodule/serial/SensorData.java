package net.pi.pimodule.serial;

import net.pi.pimodule.enums.SensorType;
//Sensor with ID example:
//Sensor: <ip091>
//Master: <ip091,1616162330,65,11,4,3600> 
//
//
//Sensor without ID example:
//Sensor: <spAA5>
//Master: <spAA5,091>
//Sensor: <sp091ok>
//Master: <ip091,1616162330,65,11,4,3600> 
public class SensorData {
	
	private String command = "";
	private SensorType type;
	private String sensorId = "";
	private String data = "";
	
	
	public SensorData(String data) {
		command = data.substring(0,1);
		type = SensorType.valueOf(String.valueOf(data.charAt(1)));
		sensorId = data.substring(2, 5);
		data = data.substring(5, data.length());
	}

	public String getCommand() {
		return command;
	}

	public SensorType getType() {
		return type;
	}
	public String getSensorId() {
		return sensorId;
	}
	public String getData() {
		return data;
	}

	@Override
	public String toString() {
		return "SensorData [command=" + command + ", type=" + type + ", sensorId=" + sensorId + ", data=" + data + "]";
	}

	
}
