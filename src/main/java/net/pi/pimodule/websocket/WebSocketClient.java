package net.pi.pimodule.websocket;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import home.websocket.WebSocketClientEndPoint;
import home.websocket.WebSocketException;

public class WebSocketClient {

	private static final Logger logger = LogManager.getLogger(WebSocketClient.class);

	private WebSocketClientEndPoint clientEndPoint;
	private Thread wsAliveThread;

	private static WebSocketClient instance;

	private WebSocketClient() throws WebSocketException {
		try {
			logger.debug("Starting WebSocketClient service: " + (clientEndPoint == null? " Null" : "already initialized"));
			if (clientEndPoint == null) { 
				clientEndPoint = new WebSocketClientEndPoint(new URI("ws://192.168.1.110:8081/events/"), 64800000);
				connectToEndpoint();
			}
		} catch (URISyntaxException e) {
			logger.error("error connecting to endpoint", e);
		}  //18 hours

	}

	public static WebSocketClient getInstance() throws WebSocketException {
		if (instance == null) {
			instance = new WebSocketClient();
		}
		return instance;
	}

	public void sendMessage(String message) {
		clientEndPoint.sendMessage(message);
	}

	public void disconnect() {
		if (wsAliveThread != null && wsAliveThread.isAlive()) {
			wsAliveThread.interrupt();

		}

		if (clientEndPoint != null) {
			try {
				clientEndPoint.closeConnection();
			} catch (IOException e) {
				logger.error("error closing connection" , e);
			}
		}
	}


	private void connectToEndpoint() throws WebSocketException {
		logger.debug("connectToEndpoint , Active? : " + (clientEndPoint != null && clientEndPoint.isConnectionAlive()));

		if (clientEndPoint == null || !clientEndPoint.isConnectionAlive()) {
			logger.debug("Starting connection for sensorService");
			clientEndPoint.connect();


			sendMessage("{ 'operation':1, 'userName':'piModuleV2'}");

			if (wsAliveThread != null && wsAliveThread.isAlive()) {
				wsAliveThread.interrupt();
			}

			wsAliveThread = new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						boolean running = true;
						while(running) {
							if (clientEndPoint.isConnectionAlive()) {
								clientEndPoint.sendMessage("{'operation': " + Data.HEART_BEAT + " , 'userName':'sensorService' }");
							}
							try {
								Thread.sleep(3600000);
							}catch(InterruptedException ie) {
								running = false;
								Thread.currentThread().interrupt();
							}
						}
					}catch(Throwable tr) {
						logger.debug("Severe error: " , tr);

					}

				}

			});

			wsAliveThread.start();
		}
	}

}
