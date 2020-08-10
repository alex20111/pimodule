package net.pi.pimodule;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

import net.pi.pimodule.service.model.JerseyConfiguration;
import net.pi.pimodule.thread.ThreadManager;


/**
 * api connect: http://ipaddress:port/api/*
 * so for the temp service: http://ipaddress:port/api/temperature/currTemp
 *
 */
public class App {

	private static final Logger logger = LogManager.getLogger(App.class);

    private static final String JERSEY_SERVLET_NAME = "jersey-container-servlet";

	public static void main( String[] args ) throws Exception
	{
		logger.info("Starting application PiMonitor");		
		
		new App().start();	
		
	}
	
	void start() throws Exception {
		
		logger.info("Starting thread manager");
		
		ThreadManager tm = ThreadManager.getInstance();
		
		
		long sampleRate = 1000 * 60 * 5; // 5 min
		logger.info("Starting temperature with sample rate: " + sampleRate + " in millis. In min: " + ( (sampleRate /1000) / 60) );
		tm.startTemperature(sampleRate);
		
		
		  Server server = new Server(8081);

	        ServletContextHandler ctx = 
	                new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
	                
	        ctx.setContextPath("/");
	        server.setHandler(ctx);

	        ServletHolder serHol = ctx.addServlet(ServletContainer.class, "/api/*");
	        serHol.setInitOrder(1);
	        serHol.setInitParameter("jersey.config.server.provider.packages", 
	                "net.pi.pimodule.service");

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
