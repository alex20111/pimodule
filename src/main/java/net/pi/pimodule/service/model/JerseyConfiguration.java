package net.pi.pimodule.service.model;

import org.glassfish.jersey.server.ResourceConfig;

public class JerseyConfiguration extends ResourceConfig {
	
	   public JerseyConfiguration() {
	        packages("net.pi.pimodule.service");
	    }
}
