package net.pi.pimodule.service.model;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;

public class JerseyConfiguration extends ResourceConfig {
	
	   public JerseyConfiguration() {
	        packages("net.pi.pimodule.service");
//	        register(new LoggingFeature(Logger.getLogger(LoggingFeature.DEFAULT_LOGGER_NAME),
//	        	    Level.INFO, LoggingFeature.Verbosity.PAYLOAD_ANY, 10000));
	        
	    }
}
