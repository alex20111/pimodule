package net.pi.pimodule.serial;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pi4j.io.serial.SerialDataEvent;
import com.pi4j.io.serial.SerialDataEventListener;

public class HandleHC12SerialData implements SerialDataEventListener{
	private static final Logger logger = LogManager.getLogger(HandleHC12SerialData.class);

	@Override
	public void dataReceived(SerialDataEvent event) {
		try {
			logger.debug("HC-12 data recieved: " + event.getAsciiString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
