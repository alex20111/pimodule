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
	private SensorType sensorTypeEnum;
	private String sensorId = "";
	private String data = "";
	
	private String fullData = "";
	
	
	public SensorData(String data) {
		
		this.fullData = data;
		this.command = data.substring(0,1);
		this.sensorTypeEnum = SensorType.genEnumType(String.valueOf(data.charAt(1)));
		this.sensorId = data.substring(2, 5);
		this.data = data.substring(5, data.length());
	}

	public String getCommand() {
		return command;
	}

	public SensorType getSensorTypeEnum() {
		return sensorTypeEnum;
	}
	public String getSensorId() {
		return sensorId;
	}
	public String getData() {
		return data;
	}

	public String getFullData() {
		return fullData;
	}

	@Override
	public String toString() {
		return "SensorData [command=" + command + ", sensorTypeEnum=" + sensorTypeEnum + ", sensorId=" + sensorId + ", data=" + data
				+ ", fullData=" + fullData + "]";
	}

	public static void main (String arg[]) {
		String d = "spAA0,34.9,4.76";
		
		SensorData s = new SensorData(d);
		
		System.out.println(s);
	}


	
}
