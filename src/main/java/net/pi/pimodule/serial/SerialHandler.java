package net.pi.pimodule.serial;

import java.io.IOException;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pi4j.io.serial.Baud;
import com.pi4j.io.serial.DataBits;
import com.pi4j.io.serial.FlowControl;
import com.pi4j.io.serial.Parity;
import com.pi4j.io.serial.Serial;
import com.pi4j.io.serial.SerialConfig;
import com.pi4j.io.serial.SerialDataEvent;
import com.pi4j.io.serial.SerialDataEventListener;
import com.pi4j.io.serial.SerialFactory;
import com.pi4j.io.serial.StopBits;

import net.pi.pimodule.temperature.DataListener;


public class SerialHandler {
	private static final Logger logger = LogManager.getLogger(SerialHandler.class);
	private  Serial teensySerial;
	private  Serial ceiscoSerial;

	private static SerialHandler handler;

	public SerialHandler() {


	}
	public static SerialHandler getInstance(){
		if (handler == null){
			synchronized (SerialHandler.class) {
				if (handler == null){
					handler = new SerialHandler();
				}
			}
		}
		return handler;
	}
	public void startCeiscoSerial() throws IOException{ 

		logger.info("Starting Serial listener");
		// Please see this blog article for instructions on how to disable
		// the OS console for this port:
		// https://www.cube-controls.com/2015/11/02/disable-serial-port-terminal-output-on-raspbian/
		// create an instance of the serial communications class
		ceiscoSerial = SerialFactory.createInstance();


		// create and register the serial data listener
		ceiscoSerial.addListener(new DataListener());

		// create serial config object
		SerialConfig config = new SerialConfig();

		// set default serial settings (device, baud rate, flow control, etc)
		//
		// by default, use the DEFAULT com port on the Raspberry Pi (exposed on GPIO header)
		// NOTE: this utility method will determine the default serial port for the
		//       detected platform and board/model.  For all Raspberry Pi models
		//       except the 3B, it will return "/dev/ttyAMA0".  For Raspberry Pi
		//       model 3B may return "/dev/ttyS0" or "/dev/ttyAMA0" depending on
		//       environment configuration.
		config.device("/dev/ttyAMA0")
		.baud(Baud._9600)
		.dataBits(DataBits._8)
		.parity(Parity.NONE)
		.stopBits(StopBits._1)
		.flowControl(FlowControl.NONE);

		// open the default serial device/port with the configuration settings
		ceiscoSerial.open(config);         

	}


	public void startTeensySerial() throws IOException {
		logger.debug("openTeensySerial()");
		//open an other port 
		teensySerial = SerialFactory.createInstance();

		teensySerial.addListener(new SerialDataEventListener() {

			@Override
			public void dataReceived(SerialDataEvent event) {
				try {
					logger.debug( "New event !!!!!!!!!!!!!   ->  " + event.getAsciiString());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		});
		// create serial config object
		SerialConfig config = new SerialConfig();

		config.device("/dev/ttyACM0")
		.baud(Baud._9600)
		.dataBits(DataBits._8)
		.parity(Parity.NONE)
		.stopBits(StopBits._1)
		.flowControl(FlowControl.NONE);

		// open the default serial device/port with the configuration settings
		teensySerial.open(config);         

	}
	/**
	 * Send string to teensy for sensor.
	 * @param command
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public void sendTeensyCommand(Command command) throws IllegalStateException, IOException {
		String toSend = command.sendCommand();
		logger.debug("Sending command to teensy: " + toSend);
		teensySerial.write(toSend);
	}
	
	public void closeTeensySerial() throws IllegalStateException, IOException {
		logger.debug("closeTeensySerial()");
		if (teensySerial.isOpen()) {
			teensySerial.close();
		}
	}
	
	public void closeCeiscoLogger() throws IllegalStateException, IOException{
		logger.debug("closeCeiscoLogger()");
		if (ceiscoSerial != null && ceiscoSerial.isOpen() ){
			ceiscoSerial.close();
		}

	}


}
