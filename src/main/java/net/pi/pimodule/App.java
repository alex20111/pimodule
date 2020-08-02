package net.pi.pimodule;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
		logger.info("Starting temperature with sample rate: " + sampleRate);
		tm.startTemperature(sampleRate);
		

        String port = System.getenv("PORT");
        if (port == null || port.isEmpty()) {
            port = "8080";
        }

        String contextPath = "";
        String appBase = ".";

        Tomcat tomcat = new Tomcat();
        tomcat.setPort(Integer.valueOf(port));
        tomcat.getHost().setAppBase(appBase);

        Context context = tomcat.addContext(contextPath, appBase);
        Tomcat.addServlet(context, JERSEY_SERVLET_NAME,
                new ServletContainer(new JerseyConfiguration()));
        context.addServletMappingDecoded("/api/*", JERSEY_SERVLET_NAME);

        tomcat.start();
        tomcat.getServer().await();
    }
}
