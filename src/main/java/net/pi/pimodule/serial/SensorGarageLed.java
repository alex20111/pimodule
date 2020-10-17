package net.pi.pimodule.serial;

public class SensorGarageLed implements Command{

	private final Sensor sensor;
	private final int ON = 1;
	private final int OFF = 0;
	
	private int status = -1;
	
	public SensorGarageLed(boolean on) {
		if (on) {
			status = ON;
		}else {
			status = OFF;
		}
		this.sensor = Sensor.GARAGE_LED;
	}

	/**
	 * Command to send to attiny through Teensy.
	 */
	@Override
	public String sendCommand() {
		StringBuilder sb = new StringBuilder(START_MARKER);
		sb.append(sensor.getIdentifier());
		sb.append(status);
		sb.append(END_MARKER);
		
		return sb.toString();
	}

	@Override
	public String handleDataReceived(String data) {
		//we wont never recieve any data..
		return null;
	}
}
