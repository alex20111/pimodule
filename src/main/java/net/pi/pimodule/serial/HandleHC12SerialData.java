package net.pi.pimodule.serial;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pi4j.io.serial.SerialDataEvent;
import com.pi4j.io.serial.SerialDataEventListener;

import net.pi.pimodule.enums.SensorType;


public class HandleHC12SerialData implements SerialDataEventListener{
	private static final Logger logger = LogManager.getLogger(HandleHC12SerialData.class);

	//temp storage
	private String  tempString = "";

	//values

	private String output = "";

	@Override
	public void dataReceived(SerialDataEvent event) {
		try {
			logger.debug("HC-12 data recieved: " + event.getAsciiString());

			processEvent(event.getAsciiString());
			
			if (output.length() > 0) {
				//get the sensor type:
				SensorData sensorData = new SensorData(output);
//				String sensor = String.valueOf(output.charAt(1));
//				SensorType type = SensorType.valueOf(sensor);
				if (sensorData.getType() == SensorType.POOL) {
					new PoolSensor().handleDataReceived(sensorData);
				}
			}


		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	public void processEvent(String event) {
		output = "";

		boolean recvInProgress = false;
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < event.length() ; i++) {
			if (recvInProgress == true) {
				if (event.charAt(i) == Command.START_MARKER.toCharArray()[0]) {
					//if start found again.. discardthe partial one and continue
					sb = new StringBuilder();
				}else if( event.charAt(i) == Command.END_MARKER.toCharArray()[0]	  ){			  	 
					recvInProgress = false;
					output = sb.toString();
					sb = new StringBuilder();
					tempString = "";
				} else if (event.charAt(i) != Command.END_MARKER.toCharArray()[0]) {
					sb.append(event.charAt(i));
				} 

			} else if (event.charAt(i) == Command.START_MARKER.toCharArray()[0]) {
				recvInProgress = true;

			}else if (tempString.trim().length() > 0) {
				recvInProgress = true;
				sb.append(tempString);
				sb.append(event.charAt(i));
			}
		}

		if (recvInProgress) {
			tempString = sb.toString();
		}

	}

}



//private List<CleanedEvent> outputs = new ArrayList<>();
//
//public ArduinoSerialEvent() {
//	
//}


//
//
//
//
//public boolean isCommand(String command) {
//	return this.command.equals(command);
//}
//
//public boolean isCommandComplete() {
//	return commandComplete;
//}
//public String getOutput() {
//	return output;
//}
//
//public List<CleanedEvent> getOutputs(){
//	return this.outputs;
//}


