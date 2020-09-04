package net.pi.pimodule;

import javax.websocket.server.ServerContainer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.websocket.jsr356.server.deploy.WebSocketServerContainerInitializer;
import org.glassfish.jersey.servlet.ServletContainer;

import net.pi.pimodule.thread.ThreadManager;
import net.pi.pimodule.websocket.MyWebSocket;


/**
 * api connect: http://ipaddress:port/api/*
 * so for the temp service: http://ipaddress:port/web/temperature/currTemp
 *
 */
public class App {

	private static final Logger logger = LogManager.getLogger(App.class);

	private static boolean runningOnPi = false; //swith with not testing on raspberry pi.. to remove the pi4j depencency

	public static void main( String[] args ) throws Exception
	{
		logger.info("Starting application PiMonitor");	

		if (args.length > 0 && args[0].equalsIgnoreCase("local")) {
			runningOnPi = false;
			logger.info("Running without PI libraries");	
		}

		new App().start();	

	}

	void start() throws Exception {

		logger.info("Starting thread manager");

		ThreadManager tm = ThreadManager.getInstance();

		if (runningOnPi) {
			long sampleRate = 1000 * 60 * 5; // 5 min
			logger.info("Starting temperature with sample rate: " + sampleRate + " in millis. In min: " + ( (sampleRate /1000) / 60) );
			tm.startTemperature(sampleRate);
		}

		Server server = new Server(8081);

		ServletContextHandler ctx = 
				new ServletContextHandler(ServletContextHandler.SESSIONS);

		ctx.setContextPath("/");
		server.setHandler(ctx);

		ServletHolder serHol = ctx.addServlet(ServletContainer.class, "/web/*");
		serHol.setInitOrder(1);
		serHol.setInitParameter("jersey.config.server.provider.packages", 
				"net.pi.pimodule.service");
		
		 // Initialize javax.websocket layer
        ServerContainer wscontainer = WebSocketServerContainerInitializer.configureContext(ctx);
        
     // Add WebSocket endpoint to javax.websocket layer
        wscontainer.addEndpoint(MyWebSocket.class);

        
        
		try {
			
			server.start();
			server.join();
		} catch (Exception ex) {
			ex.printStackTrace();
			//	            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		} finally {

			server.destroy();
		}
	} //relaxedQueryChars="[]|{}^&#x5c;&#x60;&quot;&lt;&gt;"
}
